package com.inventor.management.inventor_management.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SourceStockMovement {
    CUSTOMER_ORDER("CUSTOMER_ORDER"),
    PROVIDER_ORDER("PROVIDER_ORDER"),
    SALE("SALE");

    private final String value;
}
