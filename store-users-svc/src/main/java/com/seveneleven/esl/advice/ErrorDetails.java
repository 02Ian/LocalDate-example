package com.seveneleven.esl.advice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetails {
    private Date timestamp;
    private Object message;
    private String details;
    private String status;
    private String statusDescription;
}