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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.ClientConstants;
import com.seerbit.v2.RequestValidator;
import com.seerbit.v2.model.RecurringDebit;
import com.seerbit.v2.model.Subscription;
import com.seerbit.v2.service.RecurringService;
import com.seerbit.v2.util.Utility;

import java.util.Map;

/** @author Seerbit */
@SuppressWarnings("unchecked")
public class RecurringServiceImpl extends ServiceImpl implements RecurringService, ClientConstants {

  /**
   * @param client A non-optional class, the client
   * @param token A non-optional String, the auth token
   */
  public RecurringServiceImpl(Client client, String token) {
    super(client);
    Utility.nonNull(client);
    this.token = token;
  }

  /**
   * POST /api/v2/recurring/subscribes
   *
   * @param subscriptionPayload A non-optional class, the payload
   * @return response
   */
  @Override
  public JsonObject doCreateSubscription(Subscription subscriptionPayload) {
    this.requiresToken = true;
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> payload = mapper.convertValue(subscriptionPayload, Map.class);
    response = this.postRequest(SUBSCRIPTION_ENDPOINT, payload, token);
    return response;
  }

  /**
   * GET /api/v2/recurring/{publicKey}/customerId/{customerId}
   *
   * @param publicKey A non-optional String, the merchant public key
   * @param customerId A non-optional String, the customer Id
   * @return response
   */
  @Override
  public JsonObject getCustomerSubscriptions(String publicKey, String customerId) {
    this.requiresToken = true;
    String endpointURL = String.format(CUSTOMER_SUBSCRIPTION_ENDPOINT, publicKey, customerId);
    response = this.getRequest(endpointURL, token);
    return response;
  }

  /**
   * PUT /api/v2/recurring/updates
   *
   * @param subscriptionPayload A non-optional class, the payload
   * @return response
   */
  @Override
  public JsonObject doUpdateSubscription(Subscription subscriptionPayload) {
    this.requiresToken = true;
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> payload = mapper.convertValue(subscriptionPayload, Map.class);
    response = this.putRequest(UPDATE_SUBSCRIPTION_ENDPOINT, payload, token);
    return response;
  }

  /**
   * GET /api/v2/recurring/publicKey/{publicKey}
   *
   * @param publicKey A non-optional String, the merchant public key
   * @return response
   */
  @Override
  public JsonObject getMerchantSubscriptions(String publicKey) {
    this.requiresToken = true;
    String endpointURL = String.format(MERCHANT_SUBSCRIPTIONS_ENDPOINT, publicKey);
    response = this.getRequest(endpointURL, token);
    return response;
  }

  /**
   * POST /api/v2/recurring/charge
   *
   * @param recurringDebitPayload A non-optional class, the payload
   * @return response
   */
  @Override
  public JsonObject doRecurringDebit(RecurringDebit recurringDebitPayload) {
    this.requiresToken = true;
    RequestValidator.doValidate(recurringDebitPayload);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> payload = mapper.convertValue(recurringDebitPayload, Map.class);
    response = this.postRequest(CHARGE_ENDPOINT, payload, token);
    return response;
  }
}
