package com.inventor.management.routes.endpoint;

import com.inventor.management.routes.Constants;

import java.lang.module.FindException;

public interface ArticleEndPoint {
    String ARTICLE_ENDPOINT = Constants.API_ROOT + "/articles";
    String FIND_ARTICLE_BY_ID = ARTICLE_ENDPOINT + "/{idArticle}";
    String FIND_ARTICLE_BY_CODE_ARTICLE = ARTICLE_ENDPOINT + "/{idCodeArticle}";
    String DELETE_ARTICLE = FIND_ARTICLE_BY_ID;
}
