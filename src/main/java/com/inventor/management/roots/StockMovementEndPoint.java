package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface StockMovementEndPoint {

    String STOCK_MOVEMENT_ENDPOINT = Constants.API_ROOT + "/stockMovement";
    String ARTICLE_REAL_STOCK = STOCK_MOVEMENT_ENDPOINT + "/realStock/{idArticle}";
    String LIST_STOCK_MOVEMENT_ARTICLE = STOCK_MOVEMENT_ENDPOINT + "/filter/article/{idArticle}";
    String ENTRANCE_STOCK = STOCK_MOVEMENT_ENDPOINT + "/entrance";
    String EXIT_STOCK = STOCK_MOVEMENT_ENDPOINT + "/exit";
    String STOCK_CORRECTION_NEGATIVE = STOCK_MOVEMENT_ENDPOINT + "/correction/negative";
    String STOCK_CORRECTION_POSITIVE = STOCK_MOVEMENT_ENDPOINT + "/correction/positive";
}
