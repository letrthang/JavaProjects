package com.em.util;

import org.apache.commons.lang3.StringUtils;

/**
 * utility for string processing. try to use Apache string lib as possible
 * before you create a new util here
 * 
 * @author Thang Le
 *
 */
public class StringUtil {

    private StringUtil() {
    }

    /**
     * 
     * @param value
     * @return
     */
    public static boolean isBlank(String value) {

	return StringUtils.isBlank(value);
    }

}
