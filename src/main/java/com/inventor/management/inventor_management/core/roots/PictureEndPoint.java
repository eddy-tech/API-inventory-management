package com.inventor.management.inventor_management.core.roots;

import static com.inventor.management.inventor_management.core.utils.Constants.API_ROOT;

public interface PictureEndPoint {
    String PICTURE_ENDPOINT = API_ROOT + "/picture";
    String SAVE_PICTURE = "/save/{id}";
    String SAVE_PICTURE_USER = "/user/save/{id}";
}
