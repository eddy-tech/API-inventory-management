package com.inventor.management.inventor_management.enterprise.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface EnterpriseEndPoint {
    String ENTERPRISE_ENDPOINT = API_ROOT + "/enterprises";
    String UPDATE_ENTERPRISE_ENDPOINT = "/{idEnterprise}";
    String FIND_ENTERPRISE_BY_ID = "/id/{idEnterprise}";
    String DELETE_ENTERPRISE = FIND_ENTERPRISE_BY_ID;
}
