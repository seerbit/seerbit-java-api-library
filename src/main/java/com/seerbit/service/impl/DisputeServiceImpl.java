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

import com.google.gson.JsonObject;
import com.seerbit.Client;
import com.seerbit.exception.SeerbitException;
import com.seerbit.service.DisputeService;
import com.seerbit.util.Utility;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

import static com.seerbit.enums.ClientConstantsEnum.ADD_DISPUTE_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.CLOSE_DISPUTE_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.GET_ALL_DISPUTE_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.GET_DISPUTE_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.UPDATE_DISPUTE_ENDPOINT;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class DisputeServiceImpl extends ServiceMerchantImpl 
        implements DisputeService {

    /**
     *
     * @param client
     * @param token
     */
    public DisputeServiceImpl(final Client client, final String token) {
        super(client);
        this.token = token;
        Utility.doClientNonNull(client);
    }

    /**
     * GET /merchants/api/v1/user/{userId}/disputes/?page={start_page}&size={size}
     *
     * @param userId
     * @param from
     * @param to
     * @return response
     */
    @Override
    public JsonObject getAllDispute(final String userId, int from, int to) {
        this.requiresToken = true;
        if (from > to) {
            throw new SeerbitException("first page should not be greater than last page");
        }
        String endpointURL = String.format(
                GET_ALL_DISPUTE_ENDPOINT.getValue(),
                userId,
                from,
                to
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * GET /merchants/api/v1/user/{userId}/disputes/{disputeId}
     *
     * @param userId
     * @param disputeId
     * @return response
     */
    @Override
    public JsonObject getDispute(String userId, String disputeId) {
        this.requiresToken = true;
        String endpointURL = String.format(
                GET_DISPUTE_ENDPOINT.getValue(),
                userId,
                disputeId
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * POST /merchants/api/v1/user/{userId}/disputes
     *
     * @param userId
     * @param payload
     * @return response
     */
    @Override
    public JsonObject add(String userId, Map<String, Object> payload) {
        this.requiresToken = true;
        String endpointURL = String.format(
                ADD_DISPUTE_ENDPOINT.getValue(),
                userId
        );
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

    /**
     * POST /merchants/api/v1/user/{userId}/disputes/{disputeId}/close
     *
     * @param userId
     * @param disputeId
     * @param payload
     * @return response
     */
    @Override
    public JsonObject doCloseDispute(
            String userId,
            String disputeId,
            Map<String, Object> payload
    ) {
        this.requiresToken = true;
        String endpointURL = String.format(
                CLOSE_DISPUTE_ENDPOINT.getValue(),
                userId,
                disputeId
        );
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

    /**
     * PUT /merchants/api/v1/user/{userId}/disputes
     *
     * @param userId
     * @param payload
     * @return response
     */
    @Override
    public JsonObject doUpdateDispute(String userId, Map<String, Object> payload) {
        this.requiresToken = true;
        String endpointURL = String.format(
                UPDATE_DISPUTE_ENDPOINT.getValue(),
                userId
        );
        response = this.putRequest(endpointURL, payload, token);
        return response;
    }

}
