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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
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
import com.seerbit.model.Account;
import com.seerbit.model.OTP;
import com.seerbit.service.AccountService;
import com.seerbit.util.Utility;
import java.util.Map;
import lombok.extern.log4j.Log4j2;


/**
 *
 * @author Seerbit
 */
@Log4j2
public class AccountServiceImpl extends ServiceTransactionImpl 
        implements AccountService, ClientConstants {

    /**
     * 
     * @param client
     * @param token 
     */
    public AccountServiceImpl(final Client client, final String token) {
        super(client);
        this.token = token;
        Utility.doClientNonNull(client);
    }

    /**
     * POST /sbt/api/account/v1/initiate/transaction
     * 
     * @param accountPayload
     * @return response
     */
    @Override
    public JsonObject doAuthorize(Account accountPayload) {
        super.setRequiresToken(true);
        String endpointURL = INITIATE_TRANSACTION_ENDPOINT;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.convertValue(accountPayload, Map.class);
        payload.put("publicKey", this.getClient().getPublicKey());
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

    /**
     * POST /sbt/api/account/v1/validate/transaction
     * 
     * @param otpPayload
     * @return response
     */
    @Override
    public JsonObject doValidateTransaction(OTP otpPayload) {
        this.requiresToken = true;
        String endpointURL = VALIDATE_TRANS_ACCOUNT_ENDPOINT;
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> payload = mapper.convertValue(otpPayload, Map.class);
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

}
