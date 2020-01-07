/*
 * Copyright (C) 2019 Seerbit
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.seerbit.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class SHA256 {

    /**
     * Get the hashed string
     * 
     * @param hash
     * @return hashString value
     */
    private static String toHexString(byte[] hash) {
        StringBuilder hashString = new StringBuilder();
        for (byte hashByte : hash) {
            hashString.append(String.format("%02x", hashByte));
        }
        return hashString.toString();
    }

    /**
     * hash function
     * 
     * @param publicKey
     * @param privateKey
     * @return clientSecret
     */
    public static String doHashSHA256(final String publicKey, final String privateKey) {
        String clientSecret = null;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            StringBuilder hashStringBuilder = new StringBuilder();
            hashStringBuilder.append(publicKey);
            hashStringBuilder.append(".");
            hashStringBuilder.append(privateKey);
            String hashString = hashStringBuilder.toString();
            byte[] hash = digest.digest(hashString.getBytes(StandardCharsets.UTF_8));
            clientSecret = SHA256.toHexString(hash);
        } catch (NoSuchAlgorithmException exception) {
            log.error(exception.getMessage());
        }
        return clientSecret;
    }
}
