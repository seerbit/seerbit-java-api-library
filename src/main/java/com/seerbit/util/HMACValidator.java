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

//import com.google.gson.JsonParser;
//import java.io.UnsupportedEncodingException;
//import javax.crypto.Mac;
//import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Seerbit
 */
public class HMACValidator implements HMACValidatorConstants {

//    public static boolean isTokenValid512(String rawJsonRequest, String authToken, String secretKey)
//            throws Exception {
//        Mac mac = getMac512(secretKey);
//        final byte[] mac_data = mac.doFinal(toBytes(rawJsonRequest));
//
//        String result = DatatypeConverter.printHexBinary(mac_data);
//        return result.toLowerCase().equals(authToken);
//    }
//
//    private static Mac getMac512(String secretKey) throws Exception {
//        Mac mac = Mac.getInstance(HMAC_SHA512_ALGORITHM);
//        mac.init(getSecretKeySpec(HMAC_SHA512_ALGORITHM, secretKey));
//        return mac;
//    }
//
//    private static SecretKeySpec getSecretKeySpec(String algorithm, String secretKey)
//            throws Exception {
//        byte[] byteKey = secretKey.getBytes("UTF-8");
//        return new SecretKeySpec(byteKey, algorithm);
//    }
//
//    private static byte[] toBytes(String jsonString)
//            throws UnsupportedEncodingException {
//        return JsonParser.parseString(jsonString).getAsString().getBytes();
//    }
}
