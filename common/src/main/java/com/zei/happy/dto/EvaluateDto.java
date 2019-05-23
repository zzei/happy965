package com.zei.happy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class EvaluateDto implements Serializable{

    private String dateStr;

    private String userStr;

    private String companyStr;

}
