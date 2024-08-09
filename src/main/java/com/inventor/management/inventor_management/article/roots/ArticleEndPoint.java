package com.inventor.management.inventor_management.article.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface ArticleEndPoint {
    String ARTICLE_ENDPOINT = API_ROOT + "/articles";
    String UPDATE_ARTICLE_ENDPOINT = "/{idArticle}";
    String FIND_HISTORY_SALES = "/history/sales/{idArticle}";
    String FIND_HISTORY_CUSTOMER_ORDER = "/history/customerOrder/{idArticle}";
    String FIND_HISTORY_PROVIDER_ORDER = "/history/providerOrder/{idArticle}";
    String FIND_ALL_ARTICLE_BY_CATEGORY = "/filter/category/{idCategory}";
    String FIND_ARTICLE_BY_ID = "/id/{idArticle}";
    String FIND_ARTICLE_BY_CODE_ARTICLE = "/filter/{idCodeArticle}";
    String DELETE_ARTICLE = FIND_ARTICLE_BY_ID;
}
