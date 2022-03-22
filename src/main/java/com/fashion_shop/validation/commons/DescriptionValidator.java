package com.fashion_shop.validation.commons;

import com.fashion_shop.model.commons.Descriptions;
import com.fashion_shop.validation.ValidationConstants;

public final class DescriptionValidator{
    public static boolean validateDescription(Descriptions descriptions){
        return descriptions.getComment().length() < ValidationConstants.PRODUCT_DESCRIPTION_MIN_LENGTH ||
                descriptions.getComment().length() > ValidationConstants.PRODUCT_DESCRIPTION_MAX_LENGTH;
    }
}
