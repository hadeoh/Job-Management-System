package com.payoneer.jobmanagementsystem.services;

import com.payoneer.jobmanagementsystem.enums.State;
import com.payoneer.jobmanagementsystem.models.Job;

import java.util.List;

public interface JobService {
    Job addJob(Job job);
    void processBatch();
}
