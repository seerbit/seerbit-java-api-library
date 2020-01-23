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
import com.seerbit.model.Dispute;
import com.seerbit.service.DisputeService;
import com.seerbit.util.Utility;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class DisputeServiceImpl extends ServiceMerchantImpl 
        implements DisputeService, ClientConstants {

    /**
     *
     * @param client
     * @param token
     */
    public DisputeServiceImpl(Client client, String token) {
        super(client);
        this.token = token;
        Utility.doClientNonNull(client);
    }

    /**
     * GET /merchants/api/v1/user/{businessId}/disputes/?page={start_page}&size={size}
     *
     * @param businessId
     * @param page
     * @param size
     * @return response
     */
    @Override
    public JsonObject getAllDispute(String businessId, int page, int size) {
        this.requiresToken = true;
        String endpointURL = String.format(
                GET_ALL_DISPUTE_ENDPOINT,
                businessId,
                page,
                size
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * GET /merchants/api/v1/user/{businessId}/disputes/{disputeId}
     *
     * @param businessId
     * @param disputeId
     * @return response
     */
    @Override
    public JsonObject getDispute(String businessId, String disputeId) {
        this.requiresToken = true;
        String endpointURL = String.format(
                GET_DISPUTE_ENDPOINT,
                businessId,
                disputeId
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * PUT /merchants/api/v1/user/{businessId}/disputes/{disputeReference}
     *
     * @param businessId
     * @param disputeReference
     * @param disputePayload
     * @return response
     */
    @Override
    public JsonObject doUpdateDispute(
            String businessId, 
            String disputeReference, 
            Dispute disputePayload
    ) {
        this.requiresToken = true;
        String endpointURL = String.format(
                UPDATE_DISPUTE_ENDPOINT,
                businessId,
                disputeReference
        );
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.convertValue(disputePayload, Map.class);
        response = this.putRequest(endpointURL, payload, token);
        return response;
    }

}
