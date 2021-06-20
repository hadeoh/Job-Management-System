package com.payoneer.jobmanagementsystem.controllers;

import com.payoneer.jobmanagementsystem.models.Cron;
import com.payoneer.jobmanagementsystem.pojos.CronRequest;
import com.payoneer.jobmanagementsystem.services.CronService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/cron-expression")
public class CronController {

    private final CronService cronService;

    @GetMapping
    public Cron getCronExpression() {
        return cronService.getCronExpression();
    }

    @PutMapping
    public String updateCronExpression(@RequestBody CronRequest cronRequest) {
        cronService.updateCronExpression(cronRequest);
        return "success";
    }
}
