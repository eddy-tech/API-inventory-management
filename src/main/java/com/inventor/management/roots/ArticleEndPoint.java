package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface ArticleEndPoint {

    String ARTICLE_ENDPOINT = Constants.API_ROOT + "/articles";
    String FIND_HISTORY_SALES = ARTICLE_ENDPOINT + "/history/sales/{idArticle}";
    String FIND_HISTORY_CUSTOMER_ORDER = ARTICLE_ENDPOINT + "/history/customerOrder/{idArticle}";
    String FIND_HISTORY_PROVIDER_ORDER = ARTICLE_ENDPOINT + "/history/providerOrder/{idArticle}";
    String FIND_ALL_ARTICLE_BY_CATEGORY = ARTICLE_ENDPOINT + "/filter/category/{idCategory}";
    String FIND_ARTICLE_BY_ID = ARTICLE_ENDPOINT + "/{idArticle}";
    String FIND_ARTICLE_BY_CODE_ARTICLE = ARTICLE_ENDPOINT + "/filter/{idCodeArticle}";
    String DELETE_ARTICLE = FIND_ARTICLE_BY_ID;
}
