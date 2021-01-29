package com.seveneleven.esl.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class OauthToken {
    @SerializedName(value = "access_token")
    private String accessToken;
}
