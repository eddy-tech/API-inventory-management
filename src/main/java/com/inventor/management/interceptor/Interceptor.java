package com.inventor.management.interceptor;

import com.inventor.management.utils.JwtUnit;
import org.hibernate.EmptyInterceptor;
import org.slf4j.MDC;
import org.springframework.util.StringUtils;

public class Interceptor extends EmptyInterceptor {

    @Override
    public String onPrepareStatement(String sql){
        if(StringUtils.hasLength(sql) && sql.toLowerCase().startsWith("select")){
            //select user0_.
           final String entityName = sql.substring(JwtUnit.SUB_STRING, sql.indexOf(".")); // BEGIN TO TAKE A FIRST OCCURRENCE
            final String idEnterprise = MDC.get("id_enterprise");
            if(StringUtils.hasLength(entityName)
                    && !entityName.toLowerCase().contains("enterprise")
                    && !entityName.toLowerCase().contains("roles")
                    && StringUtils.hasLength(idEnterprise)) {

                if(sql.contains("where")){
                    sql = sql + "and" + entityName + ".idEnterprise = " + idEnterprise;
                } else {
                    sql = sql + "where" + entityName + ".idEnterprise = " + idEnterprise;
                }
            }
        }
        return super.onPrepareStatement(sql);
    }
}
