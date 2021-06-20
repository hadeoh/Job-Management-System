package com.payoneer.jobmanagementsystem.models;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Table(name = "cron_details")
@Entity
@Data
public class Cron {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cron_expression")
    private String cronExpression;

//    @Column(name = "created_at", nullable = false, updatable = false)
//    @CreationTimestamp
//    private Timestamp createdAt;
//
//    @Column(name = "updated_at", nullable = false)
//    @UpdateTimestamp
//    private Timestamp updatedAt;
}
