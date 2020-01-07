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
import com.seerbit.util.Utility;
import java.util.Map;

import static com.seerbit.enums.ClientConstantsEnum.CREATE_SUBSCRIPTION_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.GET_SUBSCRIPTION_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.RECURRING_DEBIT_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.RETRIEVE_SUBSCRIPTION_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.UPDATE_SUBSCRIPTION_ENDPOINT;

/**
 *
 * @author Seerbit
 */
public class RecurringPaymentService extends ServiceTransactionImpl {

    /**
     *
     * @param client
     * @param token
     */
    public RecurringPaymentService(final Client client, final String token) {
        super(client);
        Utility.doValidateVersionTwo(client.getVersion());
        Utility.doClientNonNull(client);
        this.token = token;
    }

    /**
     * POST /api/v2/recurring/subscribes
     *
     * @param payload
     * @return response
     */
    public JsonObject doCreateSubscription(Map<String, Object> payload) {
        this.requiresToken = true;
        String endpointURL = CREATE_SUBSCRIPTION_ENDPOINT.getValue();
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

    /**
     * GET /api/v2/recurring/{publicKey}/{customerId}/{subscriptionId}
     *
     * @param publicKey
     * @param customerId
     * @param subscriptionId
     * @return response
     */
    public JsonObject getCustomerSubscription(
            String publicKey,
            String customerId,
            String subscriptionId
    ) {
        this.requiresToken = true;
        // ask Kola about this reference
        String endpointURL = String.format(
                GET_SUBSCRIPTION_ENDPOINT.getValue(),
                publicKey,
                customerId,
                subscriptionId
        );
        response = this.getRequest(endpointURL, token);
        return response;
    }

    /**
     * POST /api/v2/recurring/updates
     *
     * @param payload
     * @return response
     */
    public JsonObject doUpdateSubscription(Map<String, Object> payload) {
        // ask kola if this is a GET or POST response
        this.requiresToken = true;
        String endpointURL = UPDATE_SUBSCRIPTION_ENDPOINT.getValue();
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

    /**
     * GET /api/v2/recurring/{publicKey}
     *
     * @return response
     */
    public JsonObject doRetrieveSubscription() {
        this.requiresToken = true;
        String endpointURL = RETRIEVE_SUBSCRIPTION_ENDPOINT.getValue();
        response = this.getRequest(endpointURL, token);
        return response;
    }
    
    
    /**
     * POST /api/v2/recurring/charge 
     *
     * @param payload
     * @return response
     */
    public JsonObject doRecurringDebits(Map<String, Object> payload) {
        this.requiresToken = true;
        String endpointURL = RECURRING_DEBIT_ENDPOINT.getValue();
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }
}
