package com.inventor.management.routes.endpoint;

import com.inventor.management.routes.Constants;

public interface CustomerEndPoint {

    String CUSTOMER_ENDPOINT = Constants.API_ROOT + "/customers";
    String FIND_CUSTOMER_BY_ID = CUSTOMER_ENDPOINT + "/{idCustomer}";
    String DELETE_CUSTOMER = FIND_CUSTOMER_BY_ID;
}
