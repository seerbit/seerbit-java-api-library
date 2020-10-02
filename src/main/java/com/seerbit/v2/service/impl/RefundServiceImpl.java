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
import com.seerbit.v2.model.Refund;
import com.seerbit.v2.service.RefundService;
import com.seerbit.v2.util.Utility;

import java.util.Map;

/** @author Seerbit */
@SuppressWarnings("unchecked")
public class RefundServiceImpl extends ServiceImpl implements RefundService, ClientConstants {

  /**
   * @param client A non-optional class, the client
   * @param token A non-optional String, the auth token
   */
  public RefundServiceImpl(Client client, String token) {
    super(client);
    Utility.nonNull(client);
    this.token = token;
  }

  /**
   * GET /merchants/api/v1/user/{businessId}/refunds/?page={start_page}&size={size} API call
   *
   * @param businessId A non-optional String, the business id
   * @param page A non-optional int, the initial page
   * @param size A non-optional int, the page size
   * @return response
   */
  @Override
  public JsonObject getAllRefund(String businessId, int page, int size) {
    this.requiresToken = true;
    String endpointURL = String.format(REFUND_LIST_ENDPOINT, businessId, page, size);
    response = this.getRequest(endpointURL, token);
    return response;
  }

  /**
   * GET /merchants/api/v1/user/{businessId}/refunds/{refundId} API call
   *
   * @param businessId A non-optional String, the business id
   * @param refundId A non-optional String, the refund id
   * @return response
   */
  @Override
  public JsonObject getRefund(String businessId, String refundId) {
    this.requiresToken = true;
    String endpointURL = String.format(REFUND_DETAIL_ENDPOINT, businessId, refundId);
    response = this.getRequest(endpointURL, token);
    return response;
  }

  /**
   * POST /merchants/api/v1/user/{businessId}/refunds API call
   *
   * @param businessId A non-optional String, the business id
   * @param refundPayload A non-optional class, the payload
   * @return response
   */
  @Override
  public JsonObject doRefund(String businessId, Refund refundPayload) {
    this.requiresToken = true;
    String endpointURL = String.format(REFUND_ENDPOINT, businessId);
    ObjectMapper mapper = new ObjectMapper();
    Map<String, Object> payload = mapper.convertValue(refundPayload, Map.class);
    response = this.postRequest(endpointURL, payload, token);
    return response;
  }
}
