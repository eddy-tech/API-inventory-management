package com.inventor.management.inventor_management.sale.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface SaleEndPoint {
    String SALE_ENDPOINT = API_ROOT + "/sales";
    String UPDATE_SALE_ENDPOINT ="/{idSale}";
    String FIND_SALE_BY_ID ="/id/{idSale}";
    String FIND_SALE_BY_CODE_SALE ="/filter/{idCodeSale}";
    String DELETE_SALE = FIND_SALE_BY_ID;
}
