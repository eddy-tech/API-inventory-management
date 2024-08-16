package com.inventor.management.inventor_management.stockMovement.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface StockMovementEndPoint {
    String STOCK_MOVEMENT_ENDPOINT = API_ROOT + "/stockMovement";
    String ARTICLE_REAL_STOCK ="/realStock/{idArticle}";
    String LIST_STOCK_MOVEMENT_ARTICLE ="/filter/article/{idArticle}";
    String ENTRANCE_STOCK ="/entrance";
    String EXIT_STOCK ="/exit";
    String STOCK_CORRECTION_NEGATIVE ="/correction/negative";
    String STOCK_CORRECTION_POSITIVE ="/correction/positive";
}
