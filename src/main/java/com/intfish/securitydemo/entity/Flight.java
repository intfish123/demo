package com.intfish.securitydemo.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Flight {
    private Integer id;
    private String name;
    private LocalDateTime startTime;
}
