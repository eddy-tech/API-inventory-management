package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface ProviderOrderEndPoint {

    String PROVIDER_ORDER_ENDPOINT = Constants.API_ROOT + "/providerOrder";
    String UPDATE_STATE_ORDER = PROVIDER_ORDER_ENDPOINT + "/stateOrder/{idOrder}/{stateOrder}";
    String UPDATE_PROVIDER = PROVIDER_ORDER_ENDPOINT + "/update/provider/{idOrder}/{idProvider}";
    String UPDATE_ARTICLE = PROVIDER_ORDER_ENDPOINT + "/update/article/{idOrder}/{idOrderLine}/{idArticle}";
    String UPDATE_QUANTITY_ORDER = PROVIDER_ORDER_ENDPOINT + "/update/quantityOrder/{idOrder}/{idOrderLine}/{quantity}";
    String FIND_PROVIDER_ORDER_BY_ID = PROVIDER_ORDER_ENDPOINT + "/id/{idProviderOrder}";
    String FIND_PROVIDER_ORDER_BY_CODE_PROVIDER_ORDER = PROVIDER_ORDER_ENDPOINT  + "/filter/{codeOrder}";
    String FIND_PROVIDER_ORDER_LINE_BY_PROVIDER_ORDER_ID = PROVIDER_ORDER_ENDPOINT  + "/filter/providerOrderLine/{idOrder}";
    String DELETE_PROVIDER_ORDER = FIND_PROVIDER_ORDER_BY_ID;
    String DELETE_ARTICLE = PROVIDER_ORDER_ENDPOINT + "/{idOrder}/{idOrderLine}";
}
