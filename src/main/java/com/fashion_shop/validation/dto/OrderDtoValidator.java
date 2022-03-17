package com.fashion_shop.validation.dto;

import com.fashion_shop.model.dto.OrderUpdateReqDto;
import com.fashion_shop.validation.ValidationConstants;

public class OrderDtoValidator {

    public static boolean chekOrderUpdateDto(OrderUpdateReqDto dto){
        if (dto.getCount() < ValidationConstants.ORDER_PRODUCT_COUNT_MIN_VALUE ||
        dto.getCount() > ValidationConstants.ORDER_PRODUCT_COUNT_MAX_VALUE){
            return  false;
        }
        return true;
    }
}
