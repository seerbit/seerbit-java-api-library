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
import com.seerbit.v2.RequestValidator;
import com.seerbit.v2.model.MobileMoney;
import com.seerbit.v2.service.MobileMoneyService;
import com.seerbit.v2.util.Utility;

import java.util.Map;

import static com.seerbit.v2.ClientConstants.AVAILABLE_NETWORKS_ENDPOINT;
import static com.seerbit.v2.ClientConstants.INITIATE_PAYMENT_ENDPOINT;

@SuppressWarnings("unchecked")
public class MobileMoneyServiceImpl extends ServiceImpl implements MobileMoneyService {

  /**
   * @param client A non-optional class, the client
   * @param token A non-optional String, the auth token
   */
  public MobileMoneyServiceImpl(Client client, String token) {
    super(client);
    this.token = token;
    Utility.nonNull(client);
  }

  /**
   * POST /api/v2/payments/initiates
   *
   * @param mobileMoneyPayload A non-optional class, the payload
   * @return response
   */
  @Override
  public JsonObject doAuthorize(MobileMoney mobileMoneyPayload) {
    this.requiresToken = true;
    RequestValidator.doValidate(mobileMoneyPayload);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> payload = mapper.convertValue(mobileMoneyPayload, Map.class);
    response = this.postRequest(INITIATE_PAYMENT_ENDPOINT, payload, token);
    return response;
  }

  /**
   * POST /api/v2/networks
   *
   * @return response
   */
  @Override
  public JsonObject getAvailableNetworks() {
    this.requiresToken = true;
    response = this.getRequest(AVAILABLE_NETWORKS_ENDPOINT, token);
    return response;
  }
}
