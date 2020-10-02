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
import com.seerbit.v2.ClientConstants;
import com.seerbit.v2.service.StatusService;
import com.seerbit.v2.util.Utility;

/** @author Seerbit */
@SuppressWarnings("unchecked")
public class StatusServiceImpl extends ServiceImpl implements StatusService, ClientConstants {

  /**
   * @param client A non-optional class, the client
   * @param token A non-optional String, the auth token
   */
  public StatusServiceImpl(Client client, String token) {
    super(client);
    Utility.nonNull(client);
    this.token = token;
  }

  /**
   * GET /api/v2/payments/query/{paymentReference}
   *
   * @param paymentReference A non-optional String, the payment reference
   * @return response
   */
  @Override
  public JsonObject getTransactionStatus(String paymentReference) {
    this.requiresToken = true;
    String endpointURL = String.format(TRX_STATUS_ENDPOINT, paymentReference);
    response = this.getRequest(endpointURL, token);
    return response;
  }

  /**
   * GET /api/v2/recurring/billingId/{billingId}
   *
   * @param billingId A non-optional String, the billing id
   * @return response
   */
  @Override
  public JsonObject getSubscriptionStatus(String billingId) {
    this.requiresToken = true;
    String endpointURL = String.format(SUBSCRIPTION_STATUS_ENDPOINT, billingId);
    response = this.getRequest(endpointURL, token);
    return response;
  }
}
