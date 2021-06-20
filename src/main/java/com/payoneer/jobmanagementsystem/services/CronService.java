package com.payoneer.jobmanagementsystem.services;

import com.payoneer.jobmanagementsystem.models.Cron;
import com.payoneer.jobmanagementsystem.pojos.CronRequest;

import java.util.List;

public interface CronService {
    Cron getCronExpression();
    String updateCronExpression(CronRequest cronRequest);
}
