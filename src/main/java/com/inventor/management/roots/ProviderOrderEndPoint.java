package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface ProviderOrderEndPoint {

    String PROVIDER_ORDER_ENDPOINT = Constants.API_ROOT + "/providerOrder";
    String FIND_PROVIDER_ORDER_BY_ID = PROVIDER_ORDER_ENDPOINT + "/{idProviderOrder}";
    String FIND_PROVIDER_ORDER_BY_CODE_PROVIDER_ORDER = PROVIDER_ORDER_ENDPOINT  + "/{codeOrder}";
    String DELETE_PROVIDER_ORDER = FIND_PROVIDER_ORDER_BY_ID;
}
