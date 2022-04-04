package com.fashion_shop.validation.commons;

import com.fashion_shop.model.commons.Stock;

public final class StockValidator {
    public static  boolean validateStock(Stock stock){
        if (stock.getCount() < 0) return false;
        return true;
    }
}
