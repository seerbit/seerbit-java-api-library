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
package com.seerbit.v2.demo.cards;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.CardPayment;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.CardService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.CardServiceImpl;

/** @author Seerbit */
public class CardAuthorizeDemo {

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
   * @param token token (java.lang.String)
   * @return response
   */
  private static JsonObject doCardAuthorize(final String token) {
    System.out.println("================== start card authorize ==================");
    CardService cardService = new CardServiceImpl(client, token);
    CardPayment cardPayment =
        CardPayment.builder()
            .publicKey(client.getPublicKey())
            .amount("100.00")
            .fee("10")
            .fullName("John Doe")
            .mobileNumber("08032000001")
            .currency("KES")
            .country("KE")
            .paymentReference("LKJHGFDR123UI23992JN23R3")
            .email("jd@gmail.com")
            .productId("Food")
            .productDescription("Rasberry")
            .clientAppCode("kpp2")
            .redirectUrl("http://checkout.com")
            .channelType("Mastercard")
            .deviceType("Laptop")
            .sourceIP("127.0.0.1")
            .cardNumber("5123450000000008")
            .cvv("100")
            .expiryMonth("05")
            .expiryYear("21")
            .pin("1234")
            .retry("false")
            .invoiceNumber("1234567890abc123ac")
            .type("3DSECURE")
            .paymentType("CARD")
            .build();
    System.out.println("request: " + new Gson().toJson(cardPayment));
    JsonObject response = cardService.doAuthorize(cardPayment);
    System.out.println("================== end card authorize ==================");
    return response;
  }

  /** @param args String arguments array for main function */
  public static void main(String... args) {
    String token = CardAuthorizeDemo.doAuthenticate();
    JsonObject response = CardAuthorizeDemo.doCardAuthorize(token);
    System.out.println("card authorize response: " + response.toString());
  }
}
