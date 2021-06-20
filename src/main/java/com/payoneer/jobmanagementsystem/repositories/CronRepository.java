package com.payoneer.jobmanagementsystem.repositories;

import com.payoneer.jobmanagementsystem.models.Cron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CronRepository extends JpaRepository<Cron, Integer> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE cron_details SET cron_expression = :cronExpression", nativeQuery = true)
    void updateCron(@Param("cronExpression") String cronExpression);
}
