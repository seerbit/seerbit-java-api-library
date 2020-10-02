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
package com.seerbit.v2.demo.status;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.StatusService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.StatusServiceImpl;

/** @author Seerbit */
public class GetSubscriptionStatusDemo {

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
   * @param billingReference (java.lang.String)
   * @return response
   */
  private static JsonObject doQueryStatus(String token, String billingReference) {
    System.out.println("================== start get subscription status ==================");
    StatusService statusService = new StatusServiceImpl(client, token);
    JsonObject response = statusService.getSubscriptionStatus(billingReference);
    System.out.println("================== stop get subscription status ==================");
    return response;
  }

  /** @param args String arguments array for main function */
  public static void main(String... args) {
    String billingReference = "227171662717";
    String token = GetSubscriptionStatusDemo.doAuthenticate();
    JsonObject response = GetSubscriptionStatusDemo.doQueryStatus(token, billingReference);
    System.out.println("get subscription status response: " + response.toString());
  }
}
