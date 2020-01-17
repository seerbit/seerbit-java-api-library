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
import com.seerbit.ClientConstants;
import com.seerbit.NumericConstants;
import com.seerbit.config.Config;
import com.seerbit.service.Authentication;
import com.seerbit.util.Utility;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Seerbit
 */
public class MerchantAuthenticationImpl extends ServiceMerchantImpl
        implements Authentication, NumericConstants, ClientConstants {

    /**
     *
     * @param client
     */
    public MerchantAuthenticationImpl(final Client client) {
        super(client);
        Utility.doClientNonNull(client);
    }

    /**
     *
     * @return response
     */
    @Override
    public JsonObject doAuth() {
        client = this.getClient();
        Config config = client.getConfig();
        Map<String, Object> payload = new HashMap<>(MIN_VALUE);
        payload.put("email", config.getUsername());
        payload.put("password", config.getPassword());
        String merchantAuthEndpoint = MERCHANT_AUTH_ENDPOINT;
        this.response = this.postRequest(merchantAuthEndpoint, payload, null);
        return this.response;
    }

    /**
     *
     * @return token
     */
    public String getToken() {
        if (Objects.nonNull(this.response)) {
            return this.response.getAsJsonObject("payload").get("token").getAsString();
        }
        return null;
    }

}
