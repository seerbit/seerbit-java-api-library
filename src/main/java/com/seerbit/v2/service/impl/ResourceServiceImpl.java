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
import com.seerbit.v2.service.ResourceService;
import com.seerbit.v2.util.Utility;

import static com.seerbit.v2.ClientConstants.BANK_LIST_ENDPOINT;

/** @author Seerbit */
public class ResourceServiceImpl extends ServiceImpl implements ResourceService {

  /**
   * @param client A non-optional class, the client
   * @param token A non-optional String, the auth token
   */
  public ResourceServiceImpl(Client client, String token) {
    super(client);
    Utility.nonNull(client);
    this.token = token;
  }

  /**
   * GET /api/v2/banks/merchant/{publicKey} API call
   *
   * @return response
   */
  @Override
  public JsonObject getBankList(String publicKey) {
    this.requiresToken = true;
    String endpointURL = String.format(BANK_LIST_ENDPOINT, publicKey);
    response = this.getRequest(endpointURL, token);
    return response;
  }
}
