/*
 * Copyright (C) 2019 Seerbit
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
package com.seerbit.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.seerbit.Client;
import com.seerbit.ClientConstants;
import com.seerbit.model.Refund;
import com.seerbit.service.RefundService;
import com.seerbit.util.Utility;
import java.util.Map;

/**
 *
 * @author Seerbit
 */
public class RefundServiceImpl extends ServiceMerchantImpl 
        implements RefundService, ClientConstants {

    /**
     *
     * @param client
     * @param token
     */
    public RefundServiceImpl(final Client client, final String token) {
        super(client);
        Utility.doClientNonNull(client);
        this.token = token;
    }

    /**
     * GET /card/v1/get/transaction/status/{transactionId} API call
     *
     * @param transactionId
     * @return response
     */
    @Override
    public JsonObject doValidate(String transactionId) {
        requiresToken = true;
        String endpointURL = String.format(VALIDATE_TRANSACTION_ENDPOINT, transactionId);
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * GET /merchants/api/v1/user/{businessId}/refunds/?page={start_page}&size={size} API call
     *
     * @param businessId
     * @param page
     * @param size
     * @return response
     */
    @Override
    public JsonObject getAllRefund(String businessId, int page, int size) {
        this.requiresToken = true;
        String endpointURL = String.format(
                GET_ALL_REFUND_ENDPOINT,
                businessId,
                page,
                size
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * GET /merchants/api/v1/user/{businessId}/refunds/{refundId} API call
     *
     * @param businessId
     * @param refundId
     * @return response
     */
    @Override
    public JsonObject getRefund(String businessId, String refundId) {
        this.requiresToken = true;
        String endpointURL = String.format(
                GET_REFUND_ENDPOINT,
                businessId,
                refundId
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * GET /merchants/api/v1/user/{businessId}/refunds API call
     * 
     * @param businessId
     * @param refundPayload
     * @return response
     */
    @Override
    public JsonObject doRefund(String businessId, Refund refundPayload) {
        this.requiresToken = true;
        String endpointURL = String.format(
                DO_REFUND_ENDPOINT,
                businessId
        );
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.convertValue(refundPayload, Map.class);
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

}
