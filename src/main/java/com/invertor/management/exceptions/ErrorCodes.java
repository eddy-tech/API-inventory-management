package com.invertor.management.exceptions;


public enum ErrorCodes {
    ARTICLES_NOT_FOUND(1000),
    ARTICLE_NOT_VALID(1001),
    CATEGORY_NOT_FOUND(2000),
    CLIENT_NOT_FOUND (3000),
    CUSTOMER_ORDER_NOT_FOUND(4000),
    PROVIDER_ORDER_NOT_FOUND(5000),
    ENTERPRISE_NOT_FOUND(6000),
    PROVIDER_NOT_FOUND(7000),
    CUSTOMER_ORDER_LINE_NOT_FOUND(8000),
    PROVIDER_ORDER_LINE_NOT_FOUND(9000),
    SALE_LINE_NOT_FOUND(10000),
    STOCK_MOVEMENT_NOT_FOUND(11000),
    USER_NOT_FOUND(12000),
    SALE_NOT_FOUND(13000);

    private int code;

    ErrorCodes(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

}
