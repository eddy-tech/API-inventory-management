package com.inventor.management.inventor_management.providerOrder.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface ProviderOrderEndPoint {
    String PROVIDER_ORDER_ENDPOINT = API_ROOT + "/providerOrder";
    String UPDATE_PROVIDER_ORDER_ENDPOINT ="/{idProviderOrder}";
    String UPDATE_STATE_ORDER ="/stateOrder/{idOrder}/{stateOrder}";
    String UPDATE_PROVIDER ="/update/provider/{idOrder}/{idProvider}";
    String UPDATE_ARTICLE ="/update/article/{idOrder}/{idOrderLine}/{idArticle}";
    String UPDATE_QUANTITY_ORDER ="/update/quantityOrder/{idOrder}/{idOrderLine}";
    String FIND_PROVIDER_ORDER_BY_ID ="/id/{idProviderOrder}";
    String FIND_PROVIDER_ORDER_BY_CODE_PROVIDER_ORDER ="/filter/{codeOrder}";
    String FIND_PROVIDER_ORDER_LINE_BY_PROVIDER_ORDER_ID ="/filter/providerOrderLine/{idOrder}";
    String DELETE_PROVIDER_ORDER = FIND_PROVIDER_ORDER_BY_ID;
    String DELETE_ARTICLE ="/{idOrder}/{idOrderLine}";
}
