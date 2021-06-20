package com.payoneer.jobmanagementsystem.services;

import com.payoneer.jobmanagementsystem.models.Cron;
import com.payoneer.jobmanagementsystem.pojos.CronRequest;
import com.payoneer.jobmanagementsystem.repositories.CronRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronServiceImpl implements CronService {

    private final CronRepository cronRepository;

    @Override
    public Cron getCronExpression() {
        List<Cron> crons = new ArrayList<>();
        try {
            crons = cronRepository.findAll();
        } catch (Exception e) {
            log.info("An error occurred {}", e.getMessage());
        }
        return crons.get(0);
    }

    @Override
    public String updateCronExpression(CronRequest cronRequest) {
        try {
            cronRepository.updateCron(cronRequest.getCronExpression());
        } catch (Exception e) {
            log.info("An error occurred {}", e.getMessage());
        }
        return "success";
    }
}
