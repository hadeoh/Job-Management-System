package com.payoneer.jobmanagementsystem.pojos;

import com.payoneer.jobmanagementsystem.enums.State;
import lombok.Data;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class JobDto {
    @NotEmpty
    private String requestBody;
    @NotEmpty
    private String url;
    @NotNull
    private Integer priorityLevel;
    private State status;
    @NotNull
    private String httpMethod;
}
