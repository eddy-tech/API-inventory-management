package com.inventor.management.routes.endpoint;

import com.inventor.management.routes.Constants;

public interface CustomerOrderEndPoint {

    String CUSTOMER_ORDER_ENDPOINT = Constants.API_ROOT + "/customerOrder";
    String FIND_CUSTOMER_ORDER_BY_ID = CUSTOMER_ORDER_ENDPOINT + "/{idCustomerOrder}";
    String FIND_CUSTOMER_ORDER_BY_CODE_CUSTOMER_ORDER = CUSTOMER_ORDER_ENDPOINT  + "/{codeOrder}";
    String DELETE_CUSTOMER_ORDER = FIND_CUSTOMER_ORDER_BY_ID;
}
