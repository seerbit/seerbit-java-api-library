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
import com.seerbit.v2.model.Ussd;
import com.seerbit.v2.service.UssdService;
import com.seerbit.v2.util.Utility;

import java.util.Map;

import static com.seerbit.v2.ClientConstants.INITIATE_PAYMENT_ENDPOINT;

@SuppressWarnings("unchecked")
public class UssdServiceImpl extends ServiceImpl implements UssdService {

  /**
   * @param client A non-optional class, the client
   * @param token A non-optional String, the auth token
   */
  public UssdServiceImpl(Client client, String token) {
    super(client);
    this.token = token;
    Utility.nonNull(client);
  }

  /**
   * POST /api/v2/payments/initiates
   *
   * @param ussd A non-optional class, the payload
   * @return response
   */
  @Override
  public JsonObject doAuthorize(Ussd ussd) {
    this.requiresToken = true;
    RequestValidator.doValidate(ussd);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> payload = mapper.convertValue(ussd, Map.class);
    response = this.postRequest(INITIATE_PAYMENT_ENDPOINT, payload, token);
    return response;
  }
}
