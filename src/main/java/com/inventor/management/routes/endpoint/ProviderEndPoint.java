package com.inventor.management.routes.endpoint;

import com.inventor.management.routes.Constants;

public interface ProviderEndPoint {
    String PROVIDER_ENDPOINT = Constants.API_ROOT + "/providers";
    String FIND_PROVIDER_BY_ID = PROVIDER_ENDPOINT + "/{idProvider}";
    String DELETE_PROVIDER = FIND_PROVIDER_BY_ID;
}
