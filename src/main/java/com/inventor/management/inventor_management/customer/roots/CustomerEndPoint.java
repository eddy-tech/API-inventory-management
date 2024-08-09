package com.inventor.management.inventor_management.customer.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface CustomerEndPoint {
    String CUSTOMER_ENDPOINT = API_ROOT + "/customers";
    String UPDATE_CUSTOMER_ENDPOINT = "/{idCustomer}";
    String FIND_CUSTOMER_BY_ID = "/id/{idCustomer}";
    String DELETE_CUSTOMER = FIND_CUSTOMER_BY_ID;
}
