package com.sjtu.trade.utils;

import org.springframework.lang.Nullable;

public enum StatusType {
    PENDING(1),
    DONE(2),
    CANCELPENDING(3);
    StatusType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @Nullable
    public static StatusType resolve(int s) {
        for (StatusType type : values()) {
            if (type.type == s) {
                return type;
            }
        }
        return null;
    }
    private final int type;
}
