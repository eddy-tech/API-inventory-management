package com.inventor.management.inventor_management.sale.roots;

import com.inventor.management.core.utils.Constants;

public interface SaleEndPoint {
    String SALE_ENDPOINT = Constants.API_ROOT + "/sales";
    String UPDATE_SALE_ENDPOINT = Constants.API_ROOT + "/sales/{idSale}";
    String FIND_SALE_BY_ID = SALE_ENDPOINT + "/id/{idSale}";
    String FIND_SALE_BY_CODE_SALE = SALE_ENDPOINT + "/filter/{idCodeSale}";
    String DELETE_SALE = FIND_SALE_BY_ID;
}
