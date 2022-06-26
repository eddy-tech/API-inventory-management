package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface SaleEndPoint {

    String SALE_ENDPOINT = Constants.API_ROOT + "/sales";
    String FIND_SALE_BY_ID = SALE_ENDPOINT + "/{idSale}";
    String FIND_SALE_BY_CODE_SALE = SALE_ENDPOINT + "/{idCodeSale}";
    String DELETE_SALE = FIND_SALE_BY_ID;
}
