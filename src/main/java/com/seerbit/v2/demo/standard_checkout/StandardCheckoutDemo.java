/*
 * Copyright (C) 2020 Seerbit
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
package com.seerbit.v2.demo.standard_checkout;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.exception.SeerbitException;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.StandardCheckout;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.StandardCheckoutService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.StandardCheckoutServiceImpl;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** @author Seerbit */
public class StandardCheckoutDemo {

  private static Client client;

  /** @return token (java.lang.String) */
  private static String doAuthenticate() {
    System.out.println("================== start authentication ==================");
    Seerbit seerbit = new SeerbitImpl();
    client = new Client();
    client.setApiBase(seerbit.getApiBase());
    client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
    client.setPublicKey("public_key");
    client.setPrivateKey("private_key");
    client.setTimeout(20);
    AuthenticationService authService = new AuthenticationServiceImpl(client);
    JsonObject json = authService.doAuth();
    String jsonString = String.format("auth response: \n%s", json.toString());
    System.out.println(jsonString);
    System.out.println("================== end authentication ==================");
    System.out.println("\n");
    System.out.println("\n");
    return authService.getToken();
  }

  /**
   * @param token (java.lang.String)
   * @return response
   */
  private static JsonObject doInitializeTransaction(String token) {
    System.out.println("================== start initialize transaction ==================");
    StandardCheckoutService standardCheckoutService =
        new StandardCheckoutServiceImpl(client, token);
    // build hash payload
    StandardCheckout standardCheckoutHash =
        StandardCheckout.builder()
            .amount("100.00")
            .callbackUrl("http://yourwebsite.com")
            .country("NG")
            .currency("NGN")
            .email("okechukwu.diei2@gmail.com")
            .paymentReference("643108207792124616573324Q145A")
            .productId("227271")
            .productDescription("Fish Products")
            .publicKey(client.getPublicKey())
            .build();
    // get hash
    String hash = standardCheckoutService.getHash(standardCheckoutHash);
    // build checkout payload
    StandardCheckout standardCheckout =
        StandardCheckout.builder()
            .amount("100.00")
            .callbackUrl("http://yourwebsite.com")
            .country("NG")
            .currency("NGN")
            .email("okechukwu.diei2@gmail.com")
            .hash(hash)
            .hashType("sha256")
            .paymentReference("643108207792124616573324Q145A")
            .productDescription("Fish Products")
            .productId("227271")
            .publicKey(client.getPublicKey())
            .build();
    JsonObject response = standardCheckoutService.doInitializeTransaction(standardCheckout);
    System.out.println("================== stop initialize transaction ==================");
    return response;
  }

  private static String doSha256(String rawKey) throws Exception {
    try {
      MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
      byte[] encrypted = messageDigest.digest(rawKey.getBytes());
      return new String(Hex.encodeHex(encrypted));
    } catch (NoSuchAlgorithmException ex) {
      String errorMessage = "Unable to hash this string";
      throw new Exception(errorMessage);
    }
  }

  private static JsonObject doInitializeTransactionWithManualHash(String token) {
    System.out.println(
        "================== start initialize transaction with manual hash ==================");
    StandardCheckoutService standardCheckoutService =
        new StandardCheckoutServiceImpl(client, token);
    // build hash payload
    String standardCheckoutHash =
        "amount=100.00"
            + "&"
            + "callbackUrl=http://yourwebsite.com"
            + "&"
            + "country=NG"
            + "&"
            + "currency=NGN"
            + "&"
            + "email=okechukwu.diei2@gmail.com"
            + "&"
            + "paymentReference=643108207792124616573324Q145B"
            + "&"
            + "productDescription=Fish Products"
            + "&"
            + "productId=227271"
            + "&"
            + "publicKey="
            + client.getPublicKey()
            + client.getPrivateKey();

    String hash;
    try {
      // get hash
      hash = StandardCheckoutDemo.doSha256(standardCheckoutHash);
    } catch (Exception exception) {
      throw new SeerbitException(exception.getMessage());
    }

    // build checkout payload
    StandardCheckout standardCheckout =
        StandardCheckout.builder()
            .amount("100.00")
            .callbackUrl("http://yourwebsite.com")
            .country("NG")
            .currency("NGN")
            .email("okechukwu.diei2@gmail.com")
            .hash(hash)
            .hashType("sha256")
            .paymentReference("643108207792124616573324Q145B")
            .productDescription("Fish Products")
            .productId("227271")
            .publicKey(client.getPublicKey())
            .build();
    JsonObject response = standardCheckoutService.doInitializeTransaction(standardCheckout);
    System.out.println(
        "================== stop initialize transaction with manual hash ==================");

    return response;
  }

  /** @param args String arguments array for main function */
  public static void main(String... args) {
    String token = StandardCheckoutDemo.doAuthenticate();
    JsonObject response = StandardCheckoutDemo.doInitializeTransaction(token);
    System.out.println("standard checkout response: " + response.toString());
    response = StandardCheckoutDemo.doInitializeTransactionWithManualHash(token);
    System.out.println("standard checkout response (manual hash): " + response.toString());
  }
}
