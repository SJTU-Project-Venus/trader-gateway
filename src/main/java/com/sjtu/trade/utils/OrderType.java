package com.sjtu.trade.utils;

import org.springframework.lang.Nullable;

public enum OrderType {
    LIMIT(1),
    STOP(2),
    CANCEL(3),
    MARKET(4);
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
