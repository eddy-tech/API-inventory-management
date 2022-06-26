package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface CustomerEndPoint {

    String CUSTOMER_ENDPOINT = Constants.API_ROOT + "/customers";
    String FIND_CUSTOMER_BY_ID = CUSTOMER_ENDPOINT + "/{idCustomer}";
    String DELETE_CUSTOMER = FIND_CUSTOMER_BY_ID;
}
