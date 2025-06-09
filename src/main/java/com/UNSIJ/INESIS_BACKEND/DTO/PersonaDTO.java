package com.UNSIJ.INESIS_BACKEND.dto;

import lombok.Data;


@Data
public class PersonaDTO {
    private String name;
    private String relationship;
    private String company;
    private String job;
    private Double gross;
    private Double net;
    private Long idParentesco;
}

