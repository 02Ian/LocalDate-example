package com.seveneleven.esl.entity;


import lombok.Data;
import org.hibernate.annotations.Columns;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STORE_CONFIGURATION_V22")
public class StoreConfigurationV22 {

    @Id
    @Column(name="DGE_CONFIG_ID")
    private String dgeConfigId;
    @Column(name="STORE_ID")
    private int storeId;
    @Column(name="DGE_PARAMETER_NAME")
    private String dgeParameterName;
    @Column(name="DGE_PARAMETER_VALUE")
    private String dgeParameterValue;

}
