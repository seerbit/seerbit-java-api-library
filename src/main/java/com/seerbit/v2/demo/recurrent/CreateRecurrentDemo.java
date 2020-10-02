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
package com.seerbit.v2.demo.recurrent;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.Subscription;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.RecurringService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.RecurringServiceImpl;

/** @author Seerbit */
public class CreateRecurrentDemo {

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
  private static JsonObject doCreateSubscription(String token) {
    System.out.println("================== start create subscription ==================");
    Subscription subscription =
        Subscription.builder()
            .amount("20")
            .billingCycle("DAILY")
            .billingPeriod("4")
            .callbackUrl("https://checkout.seerbitapi.com")
            .cardName("Bola Olat")
            .cardNumber("2223000000000007")
            .country("KE")
            .currency("KES")
            .cvv("100")
            .email("okechukwu.diei2@gmail.com")
            .expiryMonth("05")
            .expiryYear("21")
            .mobileNumber("08030540611")
            .paymentReference("SBT2272727182827788172")
            .planId("")
            .publicKey(client.getPublicKey())
            .startDate("2019-01-11")
            .productDescription("Test Token")
            .productId("Terrain")
            .subscriptionAmount(false)
            .build();
    RecurringService recurringService = new RecurringServiceImpl(client, token);
    JsonObject response = recurringService.doCreateSubscription(subscription);
    System.out.println("================== end create subscription ==================");
    return response;
  }

  /** @param args String arguments array for main function */
  public static void main(String... args) {
    String token = CreateRecurrentDemo.doAuthenticate();
    JsonObject response = CreateRecurrentDemo.doCreateSubscription(token);
    System.out.println("get create subscription response: " + response.toString());
  }
}
