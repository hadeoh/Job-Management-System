package com.payoneer.jobmanagementsystem.utils;

import com.payoneer.jobmanagementsystem.models.Job;

import java.util.Comparator;

public class JobComparator implements Comparator<Job> {
    @Override
    public int compare(Job o1, Job o2) {
        if (o1.getPriorityLevel() < o1.getPriorityLevel())
            return 1;
        else if (o1.getPriorityLevel() > o1.getPriorityLevel())
            return -1;
        return 0;

    }
}
