package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface ProviderOrderEndPoint {

    String PROVIDER_ORDER_ENDPOINT = Constants.API_ROOT + "/providerOrder";
    String UPDATE_STATE_ORDER = PROVIDER_ORDER_ENDPOINT + "/{idOrder}/{stateOrder}";
    String UPDATE_CUSTOMER = PROVIDER_ORDER_ENDPOINT + "/{idOrder}/{idCustomer}";
    String UPDATE_ARTICLE = PROVIDER_ORDER_ENDPOINT + "/{idOrder}/{idOrderLine}/{idArticle}";
    String UPDATE_QUANTITY_ORDER = PROVIDER_ORDER_ENDPOINT + "/{idOrder}/{idOrderLine}/{quantity}";
    String FIND_PROVIDER_ORDER_BY_ID = PROVIDER_ORDER_ENDPOINT + "/{idProviderOrder}";
    String FIND_PROVIDER_ORDER_BY_CODE_PROVIDER_ORDER = PROVIDER_ORDER_ENDPOINT  + "/{codeOrder}";
    String FIND_PROVIDER_ORDER_LINE_BY_PROVIDER_ORDER_ID = PROVIDER_ORDER_ENDPOINT  + "/{idOrder}";
    String DELETE_PROVIDER_ORDER = FIND_PROVIDER_ORDER_BY_ID;
    String DELETE_ARTICLE = PROVIDER_ORDER_ENDPOINT + "/{idOrder}/{idOrderLine}";
}
