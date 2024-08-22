package com.inventor.management.inventor_management.customerOrder.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface CustomerOrderEndPoint {
    String CUSTOMER_ORDER_ENDPOINT = API_ROOT + "/customerOrder";
    String UPDATE_CUSTOMER_ORDER_ENDPOINT = "/{idCustomerOrder}";
    String FIND_CUSTOMER_ORDER_BY_ID = "/id/{idCustomerOrder}";
    String UPDATE_STATE_ORDER = "/stateOrder/{idOrder}";
    String UPDATE_CUSTOMER = "/update/customer/{idOrder}/{idCustomer}";
    String UPDATE_ARTICLE = "/update/article/{idOrder}/{idOrderLine}/{idArticle}";
    String UPDATE_QUANTITY_ORDER = "/update/quantityOrder/{idOrder}/{idOrderLine}";
    String FIND_CUSTOMER_ORDER_BY_CODE_CUSTOMER_ORDER = "/filter/{codeOrder}";
    String FIND_CUSTOMER_ORDER_LINE_BY_CUSTOMER_ORDER_ID = "/filter/customerOrderLine/{idOrder}";
    String DELETE_CUSTOMER_ORDER = FIND_CUSTOMER_ORDER_BY_ID;
    String DELETE_ARTICLE = "/article/{idOrder}/{idOrderLine}";
}
