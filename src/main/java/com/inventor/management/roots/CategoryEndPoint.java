package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface CategoryEndPoint {

    String CATEGORY_ENDPOINT = Constants.API_ROOT + "/categories";
    String UPDATE_CATEGORY_ENDPOINT = Constants.API_ROOT + "/categories/{idCategory}";
    String FIND_CATEGORY_BY_ID = CATEGORY_ENDPOINT + "/id/{idCategory}";
    String FIND_CATEGORY_BY_CODE_CATEGORY = CATEGORY_ENDPOINT  + "/filter/{idCodeCategory}";
    String DELETE_CATEGORY = FIND_CATEGORY_BY_ID;
}
