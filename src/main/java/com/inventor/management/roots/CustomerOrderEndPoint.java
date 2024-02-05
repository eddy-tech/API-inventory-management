package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface CustomerOrderEndPoint {

    String CUSTOMER_ORDER_ENDPOINT = Constants.API_ROOT + "/customerOrder";
    String UPDATE_CUSTOMER_ORDER_ENDPOINT = Constants.API_ROOT + "/customerOrder/{idCustomerOrder}";
    String FIND_CUSTOMER_ORDER_BY_ID = CUSTOMER_ORDER_ENDPOINT + "/id/{idCustomerOrder}";
    String UPDATE_STATE_ORDER = CUSTOMER_ORDER_ENDPOINT + "/stateOrder/{idOrder}/{stateOrder}";
    String UPDATE_CUSTOMER = CUSTOMER_ORDER_ENDPOINT + "/update/customer/{idOrder}/{idCustomer}";
    String UPDATE_ARTICLE = CUSTOMER_ORDER_ENDPOINT + "/update/article/{idOrder}/{idOrderLine}/{idArticle}";
    String UPDATE_QUANTITY_ORDER = CUSTOMER_ORDER_ENDPOINT + "/update/quantityOrder/{idOrder}/{idOrderLine}/{quantity}";
    String FIND_CUSTOMER_ORDER_BY_CODE_CUSTOMER_ORDER = CUSTOMER_ORDER_ENDPOINT  + "/filter/{codeOrder}";
    String FIND_CUSTOMER_ORDER_LINE_BY_CUSTOMER_ORDER_ID = CUSTOMER_ORDER_ENDPOINT  + "/filter/customerOrderLine/{idOrder}";
    String DELETE_CUSTOMER_ORDER = FIND_CUSTOMER_ORDER_BY_ID;
    String DELETE_ARTICLE = CUSTOMER_ORDER_ENDPOINT + "/article/{idOrder}/{idOrderLine}";
}
