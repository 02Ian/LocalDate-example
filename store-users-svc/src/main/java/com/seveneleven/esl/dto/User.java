package com.seveneleven.esl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
    @SerializedName("upid")
    @Expose
    public String upid;

    @SerializedName("address1")
    @Expose
    public String address1;

    @SerializedName("address2")
    @Expose
    public String address2;

    @SerializedName("city")
    @Expose
    public String city;

    @SerializedName("state")
    @Expose
    public String state;

    @SerializedName("division")                  // ***NOTE.!!!***// Using Division for zip
    @Expose
    public String zip;

    @SerializedName("PHONE1_HOME")
    @Expose
    public String pHONE1HOME;

    @SerializedName("dob")
    @Expose
    public String dob;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @SerializedName("hireDate")
    @Expose
    public String hireDate;
}
