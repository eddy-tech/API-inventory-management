package com.invertor.management.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
public class SaleDto {

    private Long id;

    private Long idEnterprise;

    private String codeSale;

    private Instant dateSale;

    private String comment;
}
