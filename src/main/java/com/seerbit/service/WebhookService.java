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

import static com.seerbit.enums.ClientConstantsEnum.WEBHOOK_ENDPOINT;

/**
 *
 * @author Seerbit
 */
public class WebhookService extends ServiceMerchantImpl {
    
    public WebhookService(final Client client, final String token) {
        super(client);
        Utility.doClientNonNull(client);
        this.token = token;
    }
    
    /**
     * PUT /merchants/api/v1/business/{business_id}/webhook API call
     *
     * @param businessId
     * @param payload
     * @return response
     */
    public JsonObject doUpdateWebhookURL(String businessId, Map<String, Object> payload) {
        this.requiresToken = true;       
        String endpointURL = String.format(
                WEBHOOK_ENDPOINT.getValue(),
                businessId
        );
        response = this.putRequest(endpointURL, payload, token);
        return response;
    }
    
}
