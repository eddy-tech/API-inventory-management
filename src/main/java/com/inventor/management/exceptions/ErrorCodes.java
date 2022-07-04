package com.inventor.management.exceptions;


public enum ErrorCodes {
    ARTICLES_NOT_FOUND(1000),
    ARTICLE_NOT_VALID(1001),
    CATEGORY_NOT_FOUND(2000),
    CATEGORY_NOT_VALID(2001),
    CLIENT_NOT_FOUND (3000),
    CLIENT_NOT_VALID (3001),
    CUSTOMER_NOT_FOUND(14000),
    CUSTOMER_NOT_VALID (14001),
    CUSTOMER_ORDER_NOT_FOUND(4000),
    CUSTOMER_ORDER_NOT_VALID(4001),
    CUSTOMER_ORDER_NOT_MODIFIABLE(4002),
    PROVIDER_ORDER_NOT_FOUND(5000),
    PROVIDER_ORDER_NOT_VALID(5001),
    ENTERPRISE_NOT_FOUND(6000),
    ENTERPRISE_NOT_VALID(6001),
    PROVIDER_NOT_FOUND(7000),
    PROVIDER_NOT_VALID(7001),
    PROVIDER_ORDER_NOT_MODIFIABLE(7002),
    CUSTOMER_ORDER_LINE_NOT_FOUND(8000),
    CUSTOMER_ORDER_LINE_NOT_VALID(8001),
    PROVIDER_ORDER_LINE_NOT_FOUND(9000),
    PROVIDER_ORDER_LINE_NOT_VALID(9001),
    SALE_LINE_NOT_FOUND(10000),
    SALE_LINE_NOT_VALID(10001),
    STOCK_MOVEMENT_NOT_FOUND(11000),
    STOCK_MOVEMENT_NOT_VALID(11001),
    USER_NOT_FOUND(12000),
    USER_NOT_VALID(12001),
    BAD_CREDENTIALS(12003),
    SALE_NOT_FOUND(13000),
    SALE_NOT_VALID(13001),
    // TECHNICAL EXCEPTION LIST
    UPDATE_PICTURE_EXCEPTION(14000),
    UNKNOWN_CONTEXT (14001);

    private int code;
    ErrorCodes(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

}
