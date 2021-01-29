package com.seveneleven.esl.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class APIResponse implements Serializable {

    private Date requestDateTime;
    private String status;
    private String statusDescription;
}
