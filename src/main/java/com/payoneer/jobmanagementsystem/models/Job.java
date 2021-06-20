package com.payoneer.jobmanagementsystem.models;

import com.payoneer.jobmanagementsystem.enums.State;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "jobs")
@Entity
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "request_body", nullable = false)
    private String requestBody;

    @Column(nullable = false)
    private String url;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private State status;

    @Column(name = "priority_level", nullable = false)
    private Integer priorityLevel;

    @Column(name = "http_method", nullable = false)
    private String httpMethod;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at", nullable = false)
    @UpdateTimestamp
    private Timestamp updatedAt;
}
