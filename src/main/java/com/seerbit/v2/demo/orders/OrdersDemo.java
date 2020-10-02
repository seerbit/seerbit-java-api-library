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
package com.seerbit.v2.demo.orders;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.Order;
import com.seerbit.v2.model.OrderDetails;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.OrderService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

/** @author Seerbit */
public class OrdersDemo {

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
  private static JsonObject doOrders(String token) {
    Order order = new Order();
    List<OrderDetails> orders = new ArrayList<>(1);
    OrderDetails orderDetails = new OrderDetails();
    OrderService orderService = new OrderServiceImpl(client, token);
    order.setEmail("okechukwu.diei2@gmail.com");
    order.setPublicKey(client.getPublicKey());
    order.setPaymentReference("SBT327273672728");
    order.setFullName("Diei Okechukwu Peter");
    order.setOrderType("BULK_BULK");
    order.setMobileNumber("08000000001");
    order.setCallbackUrl("https://yourdomain.com");
    order.setCountry("NG");
    order.setCurrency("NGN");
    order.setAmount("100.00");
    orderDetails.setOrderId("22S789420214623");
    orderDetails.setCurrency("NGN");
    orderDetails.setAmount("1.00");
    orderDetails.setProductDescription("mango");
    orderDetails.setProductId("fruits");
    orders.add(orderDetails);
    order.setOrders(orders);
    return orderService.doAuthorize(order);
  }

  /** @param args String arguments array for main function */
  public static void main(String... args) {
    String token = OrdersDemo.doAuthenticate();
    JsonObject response = OrdersDemo.doOrders(token);
    System.out.println("orders demo response: " + response.toString());
  }
}
