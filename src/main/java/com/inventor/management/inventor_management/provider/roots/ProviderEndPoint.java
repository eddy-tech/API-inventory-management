package com.inventor.management.inventor_management.provider.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface ProviderEndPoint {
    String PROVIDER_ENDPOINT = API_ROOT + "/providers";
    String UPDATE_PROVIDER_ENDPOINT = "/{idProvider}";
    String FIND_PROVIDER_BY_ID = "/id/{idProvider}";
    String DELETE_PROVIDER = FIND_PROVIDER_BY_ID;
}
