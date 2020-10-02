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
package com.seerbit.v2.service.impl;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.exception.SeerbitException;
import com.seerbit.v2.httpclient.impl.HttpClientImpl;
import com.seerbit.v2.service.Request;
import com.seerbit.v2.service.Service;
import com.seerbit.v2.util.Utility;

import java.util.Map;
import java.util.Objects;

import static com.seerbit.v2.enums.EnvironmentEnum.LIVE;
import static com.seerbit.v2.enums.EnvironmentEnum.TEST;

/** @author Seerbit */
public class ServiceImpl implements Service, Request {

  private final HttpClientImpl httpClient;

  boolean requiresToken;

  protected Client client;
  protected JsonObject response;
  protected String token;

  /** @param client A non-optional class, the client */
  ServiceImpl(Client client) {
    String message;
    Utility.nonNull(client);

    if (Objects.isNull(client.getConfig().get("environment"))) {
      message =
          String.format(
              "Client does not have correct environment. Use %s or %s",
              LIVE.getEnvironment(), TEST.getEnvironment());
      throw new SeerbitException(message);
    }

    if (Objects.isNull(client.getConfig().get("publicKey"))) {
      message = "Client doesn\'t have a merchant public key. Set a public key using the client";
      throw new SeerbitException(message);
    }

    if (Objects.isNull(client.getConfig().get("privateKey"))) {
      message = "Client doesn\'t have a merchant private key. Set a private key using the client";
      throw new SeerbitException(message);
    }

    this.httpClient = new HttpClientImpl();
    this.client = client;
    this.response = new JsonObject();
  }

  /** @return client */
  @Override
  public Client getClient() {
    return client;
  }

  /** @return requiresToken */
  @Override
  public boolean isTokenRequired() {
    return requiresToken;
  }

  /**
   * @param endpoint A non-optional String, the endpoint url
   * @param payload A non-optional Map, the payload
   * @param token A non-optional String, the auth token
   * @return json
   */
  @Override
  public JsonObject postRequest(String endpoint, Map<String, Object> payload, String token) {
    String message = "Set a field named \"apiBase\" in the client configuration";
    Objects.requireNonNull(client.getConfig().get("apiBase"), message);
    String endpointURL = client.getConfig().get("apiBase").toString().concat(endpoint);
    return httpClient.post(this, endpointURL, payload, token);
  }

  /**
   * @param endpoint A non-optional String, the endpoint url
   * @param payload A non-optional Map, the payload
   * @param token A non-optional String, the auth token
   * @return json
   */
  @Override
  public JsonObject putRequest(String endpoint, Map<String, Object> payload, String token) {
    String message = "Set a field named \"apiBase\" in the client configuration";
    Objects.requireNonNull(client.getConfig().get("apiBase"), message);
    String endpointURL = client.getConfig().get("apiBase").toString().concat(endpoint);
    return httpClient.put(this, endpointURL, payload, token);
  }

  /**
   * @param endpoint A non-optional String, the endpoint url
   * @param token A non-optional String, the auth token
   * @return json
   */
  @Override
  public JsonObject getRequest(String endpoint, String token) {
    String message = "Set a field named \"apiBase\" in the client configuration";
    Objects.requireNonNull(client.getConfig().get("apiBase"), message);
    String endpointURL = client.getConfig().get("apiBase").toString().concat(endpoint);
    return httpClient.get(this, endpointURL, token);
  }
}
