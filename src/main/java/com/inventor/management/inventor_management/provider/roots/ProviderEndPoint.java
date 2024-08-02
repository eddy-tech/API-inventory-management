package com.inventor.management.inventor_management.provider.roots;

import com.inventor.management.core.utils.Constants;

public interface ProviderEndPoint {
    String PROVIDER_ENDPOINT = Constants.API_ROOT + "/providers";
    String UPDATE_PROVIDER_ENDPOINT = Constants.API_ROOT + "/providers/{idProvider}";
    String FIND_PROVIDER_BY_ID = PROVIDER_ENDPOINT + "/id/{idProvider}";
    String DELETE_PROVIDER = FIND_PROVIDER_BY_ID;
}
