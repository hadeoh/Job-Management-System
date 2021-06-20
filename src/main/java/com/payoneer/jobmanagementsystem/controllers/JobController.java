package com.payoneer.jobmanagementsystem.controllers;

import com.payoneer.jobmanagementsystem.enums.State;
import com.payoneer.jobmanagementsystem.models.Job;
import com.payoneer.jobmanagementsystem.pojos.JobDto;
import com.payoneer.jobmanagementsystem.responses.Response;
import com.payoneer.jobmanagementsystem.services.JobService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/jobs")
public class JobController {

    private final JobService jobService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Response<Job>> addJob(@Valid @RequestBody JobDto jobDto) {
        jobDto.setStatus(State.QUEUED);
        Response<Job> response = new Response<>();
        Job job = jobService.addJob(modelMapper.map(jobDto, Job.class));
        if (job != null && !job.getRequestBody().isEmpty()) {
            response.setData(job);
            response.setStatus(HttpStatus.CREATED);
            response.setMessage("Job successfully added");
        } else {
            response.setMessage("Unable to process request to add job at the moment");
            response.setStatus(HttpStatus.EXPECTATION_FAILED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
