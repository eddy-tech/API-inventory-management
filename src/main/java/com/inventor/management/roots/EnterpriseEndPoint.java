package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface EnterpriseEndPoint {

    String ENTERPRISE_ENDPOINT = Constants.API_ROOT + "/enterprises";
    String FIND_ENTERPRISE_BY_ID = ENTERPRISE_ENDPOINT + "/id/{idEnterprise}";
    String DELETE_ENTERPRISE = FIND_ENTERPRISE_BY_ID;
}
