package com.payoneer.jobmanagementsystem.repositories;

import com.payoneer.jobmanagementsystem.enums.State;
import com.payoneer.jobmanagementsystem.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query(value = "SELECT * FROM jobs WHERE status = :status ORDER BY priority_level DESC", nativeQuery = true)
    List<Job> findByStatus(@Param("status") String status);
}
