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
import com.seerbit.enums.EnvironmentEnum;
import com.seerbit.exception.SeerbitException;
import com.seerbit.httpclient.HttpClientImpl;
import com.seerbit.util.Utility;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Seerbit
 */
public class ServiceTransactionImpl implements Service {

    private final HttpClientImpl httpClient;
    
    protected boolean requiresToken;
    protected Client client;
    protected JsonObject response;
    protected String token;

    /**
     *
     * @param client
     */
    public ServiceTransactionImpl(final Client client) {
        String message;
        Utility.doClientNonNull(client);
        this.response = new JsonObject();
        this.httpClient = new HttpClientImpl();
        if (Objects.isNull(client.getConfig().get("environment"))) {
            message = String.format(
                    "Client does not have correct environment. Use %s or %s",
                    EnvironmentEnum.LIVE.getEnvironment(),
                    EnvironmentEnum.PILOT.getEnvironment()
            );
            throw new SeerbitException(message);
        }
        if (Objects.isNull(client.getConfig().get("publicKey"))) {
            message = String.format(
                    "Client doesn\'t have a merchant public key. Set a public key using the client"
            );
            throw new SeerbitException(message);
        }
        if (Objects.isNull(client.getConfig().get("privateKey"))) {
            message = String.format(
                    "Client doesn\'t have a merchant private key. Set a private key using the client"
            );
            throw new SeerbitException(message);
        }
        this.client = client;
    }

    /**
     *
     * @return client
     */
    @Override
    public Client getClient() {
        return client;
    }

    /**
     *
     * @return requiresToken
     */
    @Override
    public boolean isTokenRequired() {
        return requiresToken;
    }

    /**
     *
     * @param requiresToken
     */
    @Override
    public void setRequiresToken(final boolean requiresToken) {
        this.requiresToken = requiresToken;
    }

    /**
     *
     * @param endpoint
     * @param payload
     * @param token
     * @return json
     */
    protected JsonObject postRequest(String endpoint, Map<String, Object> payload, String token) {
        String message = "Set a field named \"apiBase\" in the client configuration";
        Objects.requireNonNull(client.getConfig().get("apiBase"), message);
        String endpointURL = client.getConfig().get("apiBase").toString().concat(endpoint);
        JsonObject json = httpClient.post(this, endpointURL, payload, token);
        return json;
    }   

    /**
     *
     * @param endpoint
     * @param payload
     * @param token
     * @return json
     */
    protected JsonObject putRequest(String endpoint, Map<String, Object> payload, String token) {
        String message = "Set a field named \"apiBase\" in the client configuration";
        Objects.requireNonNull(client.getConfig().get("apiBase"), message);
        String endpointURL = client.getConfig().get("apiBase").toString().concat(endpoint);
        JsonObject json = httpClient.put(this, endpointURL, payload, token);
        return json;
    }

    /**
     *
     * @param endpoint
     * @param token
     * @return json
     */
    protected JsonObject getRequest(String endpoint, String token) {
        String message = "Set a field named \"apiBase\" in the client configuration";
        Objects.requireNonNull(client.getConfig().get("apiBase"), message);
        String endpointURL = client.getConfig().get("apiBase").toString().concat(endpoint);
        JsonObject json = httpClient.get(this, endpointURL, token);
        return json;
    }
}
