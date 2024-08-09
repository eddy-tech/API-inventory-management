package com.inventor.management.inventor_management.category.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface CategoryEndPoint {
    String CATEGORY_ENDPOINT = API_ROOT + "/categories";
    String UPDATE_CATEGORY_ENDPOINT = "/categories/{idCategory}";
    String FIND_CATEGORY_BY_ID = "/id/{idCategory}";
    String FIND_CATEGORY_BY_CODE_CATEGORY = "/filter/{idCodeCategory}";
    String DELETE_CATEGORY = FIND_CATEGORY_BY_ID;
}
