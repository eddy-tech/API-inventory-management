package com.invertor.management.dto;

import com.invertor.management.entities.SaleLine;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

@Data
public class SaleDto {

    private Long id;

    private String codeSale;

    private Instant dateSale;

    private String comments;

    private List<SaleLine> saleLines;
}
