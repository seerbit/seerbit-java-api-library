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
package com.seerbit.service;

import com.google.gson.JsonObject;
import com.seerbit.Client;
import com.seerbit.util.Utility;
import java.util.Map;
import lombok.extern.log4j.Log4j2;

import static com.seerbit.enums.ClientConstantsEnum.INITIATE_TRANSACTION_ENDPOINT;
import static com.seerbit.enums.ClientConstantsEnum.VALIDATE_TRANS_ACCOUNT_ENDPOINT;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class AccountService extends ServiceTransactionImpl {

    /**
     * 
     * @param client
     * @param token 
     */
    public AccountService(final Client client, final String token) {
        super(client);
        this.token = token;
        Utility.doClientNonNull(client);
    }

    /**
     * POST /sbt/api/account/v1/initiate/transaction
     * 
     * @param payload
     * @return response
     */
    public JsonObject doAuthorize(Map<String, Object> payload) {
        super.setRequiresToken(true);
        // ask victor about this public key property if it should be camel case or snake case
        String endpointURL = INITIATE_TRANSACTION_ENDPOINT.getValue();
        payload.put("publicKey", this.getClient().getPublicKey());
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

    /**
     * POST /sbt/api/account/v1/validate/transaction
     * 
     * @param payload
     * @return response
     */
    public JsonObject doValidateTransaction(Map<String, Object> payload) {
        this.requiresToken = true;
        // ask victor about this public key property if it should be camel case or snake case
        String endpointURL = VALIDATE_TRANS_ACCOUNT_ENDPOINT.getValue();
        response = this.postRequest(endpointURL, payload, token);
        return response;
    }

}
