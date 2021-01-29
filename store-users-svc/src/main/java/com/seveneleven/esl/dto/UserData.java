package com.seveneleven.esl.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserData implements Serializable {

    private String upId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;
    private String birthDate;
    private String address_1;
    private String address_2;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String hireDate;
    private String store;

}
