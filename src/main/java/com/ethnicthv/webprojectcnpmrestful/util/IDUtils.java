package com.ethnicthv.webprojectcnpmrestful.util;

import java.util.UUID;

public class IDUtils {
    public static Long getID() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }
}
