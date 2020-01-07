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

import static com.seerbit.enums.ClientConstantsEnum.CHECKOUT_STANDARD_ENDPOINT;

/**
 *
 * @author Seerbit
 */
public class CheckoutService extends ServiceTransactionImpl {

    /**
     *
     * @param client
     * @param token
     */
    public CheckoutService(final Client client, final String token) {
        super(client);
        this.token = token;
        Utility.doClientNonNull(client);
    }

    /**
     * POST /api/v2/payments
     *
     * @param payload
     * @return response
     */
    public JsonObject doAuthorize(Map<String, Object> payload) {
        super.setRequiresToken(true);
        String endpointURL = CHECKOUT_STANDARD_ENDPOINT.getValue();
        payload.put("publicKey", this.getClient().getPublicKey());
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }
}
