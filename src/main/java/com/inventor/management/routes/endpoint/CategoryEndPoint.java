package com.inventor.management.routes.endpoint;

import com.inventor.management.routes.Constants;

public interface CategoryEndPoint {
    String CATEGORY_ENDPOINT = Constants.API_ROOT + "/categories";
    String FIND_CATEGORY_BY_ID = CATEGORY_ENDPOINT + "/{idCategory}";
    String FIND_CATEGORY_BY_CODE_CATEGORY = CATEGORY_ENDPOINT  + "/{idCodeCategory}";
    String DELETE_CATEGORY = FIND_CATEGORY_BY_ID;
}