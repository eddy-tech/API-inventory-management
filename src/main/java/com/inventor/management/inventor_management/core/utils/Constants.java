package com.inventor.management.inventor_management.core.utils;

public interface Constants {
    String API_ROOT = "inventoryManagement/api/v1";
    String OBJECT_EXCEPTION = "Object not valid exception has occurred";
    String USER_EXITS_EXCEPTION = "A user already exists with the provided email";


    // Delete messages
    String DELETE_ARTICLE_CUSTOMER_ORDER = "Unable to delete an article that already use in customer order";
    String DELETE_ARTICLE_PROVIDER_ORDER = "Unable to delete an article that already use in provider order";
    String DELETE_ARTICLE_SALE = "Unable to delete an article that already use in sale";
    String DELETE_CATEGORY = "Unable to delete a category that already using by article";
    String DELETE_CUSTOMER_CUSTOMER_ORDER = "Unable to delete a customer that has already customer orders";

    //Picture saving
    String PICTURE_ARTICLE = "Error saving picture of article";
    String PICTURE_CUSTOMER = "Error saving picture of customer";

    // State Order
    String EDIT_STATE_ORDER = "Unable to edit state order with a new article ID null";

    // Database Connection not found error
}
