package com.sjtu.trade.utils;

import com.sjtu.trade.fix.FixClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;

/**
 * The type Md5 util.
 * @author yanghuadong
 * @date 2019-03-18
 */
public final class Md5Util {

    /**
     * Instantiates a new Md 5 util.
     */
    private static final Logger LOG = LoggerFactory.getLogger(Md5Util.class);
    private Md5Util() {
        throw new UnsupportedOperationException("U can't instance me");
    }

    /**
     * Gets md 5.
     *
     * @param source the md 5 str
     * @return the md 5
     */
    public static String getMD5(String source) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(source.getBytes("utf-8"));
            StringBuffer sb = new StringBuffer();
            String temp;
            for (byte b : bytes) {
                temp = Integer.toHexString(b & 0XFF);
                sb.append(temp.length() == 1 ? "0" + temp : temp);
            }

            return sb.toString();
        } catch (Exception e) {
            LOG.error("getMD5 with unexpected exception, str:{}", source, e);
            return null;
        }
    }
}
