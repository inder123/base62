/*
 * Copyright (C) 2018 Inderjeet Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.singhinderjeet.base62;

import java.math.BigInteger;

/**
 * A utility class to convert MD5 values to shorter Base 62 values. The base 62 values are limited to characters [0-9, a-z, A-Z]
 *
 * @author Inderjeet Singh
 */
public class Md5ToBase62 {

    private static final char[] BASE_62_DIGITS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final BigInteger SIXTY_TWO = BigInteger.valueOf(62L);

    /**
     * Returns a base 62 string for an md5 string
     * @param md5 A valid md5 string of size 32
     * @return a string that represent the value of the string as a base 62 number
     */
    public static String toBase62(String md5) {
        BigInteger value = new BigInteger(md5, 16);
        return toBase62(value);
    }

    /**
     * Converts a base62 string into an MD5 string
     *
     * @param base62 The string representing a number in base 62
     * @return the string (padded with enough leading zeros, if needed)
     * @throws NumberFormatException if base62 is not in base 62 format
     */
    public static String fromBase62(String base62) {
        BigInteger value = fromBase62ToInteger(base62);
        String str = value.toString(16);
        int len = str.length();
        if (len < 32) {
            StringBuilder sb = new StringBuilder(32);
            int leadingZeroesCount = 32-len;
            for (int i = 0; i < leadingZeroesCount; ++i) {
                sb.append('0');
            }
            sb.append(str);
            str = sb.toString();
        }
        return str;
    }

    private static String toBase62(BigInteger value) {
        StringBuilder base62 = new StringBuilder();
        if ((value.signum() < 0)) base62.append('-');
        BigInteger bal = value.abs();
        while (bal.compareTo(SIXTY_TWO) > 0) {
            int digit = bal.mod(SIXTY_TWO).intValue();
            base62.append(BASE_62_DIGITS[digit]);
            bal = bal.divide(SIXTY_TWO);
        }
        int digit = bal.mod(SIXTY_TWO).intValue();
        base62.append(BASE_62_DIGITS[digit]);
        return base62.toString();
    }

    private static BigInteger fromBase62ToInteger(String base62) {
        BigInteger value = BigInteger.ZERO;
        BigInteger multiplier = BigInteger.ONE;
        for (int i = 0; i < base62.length(); ++i) {
            char c = base62.charAt(i);
            BigInteger digit = digitValue(c);
            value = value.add(multiplier.multiply(digit));
            multiplier = multiplier.multiply(SIXTY_TWO);
        }
        return value;
    }

    private static BigInteger digitValue(char c) {
        for (int i = 0; i < BASE_62_DIGITS.length; ++i) {
            if (BASE_62_DIGITS[i] == c) {
                return BigInteger.valueOf(i);
            }
        }
        throw new NumberFormatException("Invalid base 62 character: " + c);
    }
}
