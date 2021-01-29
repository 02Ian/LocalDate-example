package com.seveneleven.esl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ResponseDetails implements Serializable {

    @SerializedName("totalResults")
    private String totalResults;
    @SerializedName("Resources")
    private List<Resource> resourceList;

}
