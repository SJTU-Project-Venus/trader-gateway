package com.sjtu.trade.utils;

import org.springframework.lang.Nullable;

public enum OrderType {
    BUY(1),
    SELL(2);

    OrderType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Nullable
    public static OrderType resolve(int s) {
        for (OrderType type : values()) {
            if (type.type == s) {
                return type;
            }
        }
        return null;
    }
    private final int type;
}
