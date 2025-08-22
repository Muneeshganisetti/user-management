package com.muneesh.DTO;

import lombok.Data;

import javax.swing.text.StringContent;


@Data
public class PrescriptionRequest {
    private String medication;
    private String dosage;
}
