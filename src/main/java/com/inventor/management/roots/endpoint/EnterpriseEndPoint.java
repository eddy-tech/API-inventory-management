package com.inventor.management.routes.endpoint;

import com.inventor.management.routes.Constants;

public interface EnterpriseEndPoint {

    String ENTERPRISE_ENDPOINT = Constants.API_ROOT + "/enterprises";
    String FIND_ENTERPRISE_BY_ID = ENTERPRISE_ENDPOINT + "/{idEnterprise}";
    String DELETE_ENTERPRISE = FIND_ENTERPRISE_BY_ID;

}
