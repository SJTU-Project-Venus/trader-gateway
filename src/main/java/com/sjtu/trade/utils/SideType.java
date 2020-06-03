package com.sjtu.trade.utils;

import org.springframework.lang.Nullable;

public enum  SideType {
    BUY(1),
    SELL(2);

    SideType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Nullable
    public static SideType resolve(int s) {
        for (SideType type : values()) {
            if (type.type == s) {
                return type;
            }
        }
        return null;
    }
    private final int type;
}
