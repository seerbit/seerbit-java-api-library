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
package com.seerbit.service;

import com.google.gson.JsonObject;
import com.seerbit.Client;
import com.seerbit.exception.SeerbitException;
import com.seerbit.util.Utility;
import java.util.Map;

/**
 *
 * @author Seerbit
 */
public class RefundService extends ServiceMerchantImpl {

    /**
     *
     * @param client
     * @param token
     */
    public RefundService(final Client client, final String token) {
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
    public JsonObject doValidate(String transactionId) {
        requiresToken = true;
        String endpointURL = String.format("card/v1/get/transaction/status/%s", transactionId);
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * GET /merchants/api/v1/user/{userId}/refunds/?page={start_page}&size={size} API call
     *
     * @param userId
     * @param from
     * @param to
     * @return response
     */
    public JsonObject getAllRefund(String userId, int from, int to) {
        this.requiresToken = true;
        if (from > to) {
            throw new SeerbitException("first page should not be greater than last page");
        }
        String endpointURL = String.format(
                "merchants/api/v1/user/%s/refunds/?page=%d&size=%d",
                userId,
                from,
                to
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * GET /merchants/api/v1/user/{userId}/refunds/ref/{refundId} API call
     *
     * @param userId
     * @param refundId
     * @return response
     */
    public JsonObject getRefund(String userId, String refundId) {
        this.requiresToken = true;
        String endpointURL = String.format(
                "merchants/api/v1/user/%s/refunds/%s",
                userId,
                refundId
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * GET /merchants/api/v1/user/{userId}/refunds API call
     * 
     * @param userId
     * @param payload
     * @return response
     */
    public JsonObject doRefund(String userId, Map<String, Object> payload) {
        this.requiresToken = true;
        String endpointURL = String.format(
                "merchants/api/v1/user/%s/refunds",
                userId
        );
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

}
