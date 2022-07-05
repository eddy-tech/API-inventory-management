package com.inventor.management.roots;

import com.inventor.management.utils.Constants;

public interface PictureEndPoint {

    String PICTURE_ENDPOINT = Constants.API_ROOT + "/pictures";
    String SAVE_PICTURE = PICTURE_ENDPOINT + "/save/{id}/{title}/{context}";
}
