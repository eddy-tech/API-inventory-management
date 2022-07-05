package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface ProviderEndPoint {

    String PROVIDER_ENDPOINT = Constants.API_ROOT + "/providers";
    String FIND_PROVIDER_BY_ID = PROVIDER_ENDPOINT + "/id/{idProvider}";
    String DELETE_PROVIDER = FIND_PROVIDER_BY_ID;
}
