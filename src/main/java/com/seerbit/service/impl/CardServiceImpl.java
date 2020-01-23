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
import com.seerbit.model.Card;
import com.seerbit.model.Transaction;
import com.seerbit.service.CardService;
import com.seerbit.util.Utility;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class CardServiceImpl extends ServiceTransactionImpl 
        implements CardService, ClientConstants {

    /**
     *
     * @param client
     * @param token
     */
    public CardServiceImpl(Client client, String token) {
        super(client);
        this.token = token;
        Utility.doClientNonNull(client);
    }

    /**
     * POST /sbt/api/card/v1/init/transaction API call
     *
     * @param cardPayload
     * @return response
     */
    @Override
    public JsonObject doAuthorize(Card cardPayload) {
        super.setRequiresToken(true);
        String endpointURL = INITIATE_CARD_ENDPOINT;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.convertValue(cardPayload, Map.class);
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

    /**
     * POST /sbt/api/card/v1/validate/otp API call
     *
     * @param transactionPayload
     * @return response
     */
    @Override
    public JsonObject doValidateOTP(Transaction transactionPayload) {
        this.requiresToken = true;
        String endpointURL = VALIDATE_OTP_ENDPOINT;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.convertValue(transactionPayload, Map.class);
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

}
