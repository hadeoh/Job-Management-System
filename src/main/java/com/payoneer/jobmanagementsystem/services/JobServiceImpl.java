package com.payoneer.jobmanagementsystem.services;

import com.payoneer.jobmanagementsystem.components.RestCaller;
import com.payoneer.jobmanagementsystem.enums.State;
import com.payoneer.jobmanagementsystem.models.Job;
import com.payoneer.jobmanagementsystem.repositories.JobRepository;
import com.payoneer.jobmanagementsystem.utils.JobComparator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.PriorityQueue;

@Service
@Slf4j
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final RestCaller restCaller;

    @Override
    public Job addJob(Job job) {
        Job newJob = new Job();
        try {
            newJob = jobRepository.saveAndFlush(job);
        } catch (Exception e) {
            log.info("An exception occurred while adding a job due to {}", e.getMessage());
        }
        return newJob;
    }

    @Override
    public void processBatch() {
        List<Job> batchList = getPendingJobs("QUEUED");

        if (!batchList.isEmpty()) {
            JobComparator jobComparator = new JobComparator();
            PriorityQueue<Job> priorityQueue = new PriorityQueue<Job>(batchList.size(), jobComparator);
            for (Job job: batchList) {
                priorityQueue.offer(job);
            }
            while (!priorityQueue.isEmpty()) {
                Job jobToRun = priorityQueue.poll();
                try {
                    ResponseEntity<String> response = restCaller.makeRequest(jobToRun.getUrl(), jobToRun.getRequestBody(), HttpMethod.valueOf(jobToRun.getHttpMethod()));
                    changeJobStatus(jobToRun.getId(), State.RUNNING);
                    if (response.getStatusCode().equals(HttpStatus.OK)) {
                        changeJobStatus(jobToRun.getId(), State.SUCCESS);
                    } else {
                        changeJobStatus(jobToRun.getId(), State.FAILED);
                    }
                } catch (Exception e) {
                    log.info("An error occurred while performing this job due to {}", e.getMessage());
                    changeJobStatus(jobToRun.getId(), State.FAILED);
                }
            }
        } else log.info("No pending jobs at the moment");
    }

    private List<Job> getPendingJobs(String status) {
        List<Job> jobs = new ArrayList<>();
        try {
            jobs = jobRepository.findByStatus(status);
        } catch (Exception e) {
            log.info("An exception occurred while getting the pending jobs due to {}", e.getMessage());
        }
        return jobs;
    }

    private Job changeJobStatus(Integer jobId, State status) {
        Job updatedJob = new Job();
        try {
            Optional<Job> job = jobRepository.findById(jobId);
            if (job.isPresent()) {
                job.get().setStatus(status);
                updatedJob = jobRepository.save(job.get());
            }
        } catch (Exception e) {
            log.info("An exception occurred while changing the status of a job due to {}", e.getMessage());
        }
        return updatedJob;
    }
}
