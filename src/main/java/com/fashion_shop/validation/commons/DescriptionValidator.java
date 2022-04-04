package com.fashion_shop.validation.commons;

import com.fashion_shop.model.commons.Description;
import com.fashion_shop.validation.ValidationConstants;

public final class DescriptionValidator{
    public static boolean validateDescription(Description description){
        return description.getComment().length() > ValidationConstants.PRODUCT_DESCRIPTION_MIN_LENGTH &&
                description.getComment().length() < ValidationConstants.PRODUCT_DESCRIPTION_MAX_LENGTH;
    }
}
