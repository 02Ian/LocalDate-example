package com.seveneleven.esl.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

@Data
public class Resource implements Serializable {

    @SerializedName("urn:ietf:params:scim:schemas:sailpoint:1.0:User")
    @Expose
    public User user;
    @SerializedName("displayName")
    @Expose
    public String displayName;
    @SerializedName("active")
    @Expose
    public Boolean active;
    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("userName")
    @Expose
    public String userName;
}