package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface CustomerEndPoint {

    String CUSTOMER_ENDPOINT = Constants.API_ROOT + "/customers";
    String UPDATE_CUSTOMER_ENDPOINT = Constants.API_ROOT + "/customers/{idCustomer}";
    String FIND_CUSTOMER_BY_ID = CUSTOMER_ENDPOINT + "/id/{idCustomer}";
    String DELETE_CUSTOMER = FIND_CUSTOMER_BY_ID;
}
