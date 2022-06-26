package com.inventor.management.utils;

public class JwtUnit {

    public static final String SECRET_KEY = "mySecret256";
    public static final  String AUTH_HEADER = "Authorization";
    public static final  String PREFIX = "Bearer ";
    public static final long EXPIRE_ACCESS_TOKEN = 10*60*1000;
    public static final long EXPIRE_REFRESH_TOKEN = 40*60*1000;
    public static final String ADMIN_ROLE = "ADMIN";
    public static final String USER_ROLE = "USER";
    public static final String ENTERPRISE_ROLE = "ENTERPRISE";
}
