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

import static com.seerbit.enums.ClientConstantsEnum.VALIDATE_TRANS_CARD_ENDPOINT;

/**
 *
 * @author Seerbit
 */
public class TransactionService extends ServiceTransactionImpl {
    
    /**
     * 
     * @param client
     * @param token 
     */
    public TransactionService(final Client client, final String token) {
        super(client);
        Utility.doClientNonNull(client);
        this.token = token;
    }
    
    /**
     * 
     * @param transId
     * @return response
     */
    public JsonObject doValidateTransaction(final String transId) {
        String transStatusEndpoint = VALIDATE_TRANS_CARD_ENDPOINT.getValue();
        requiresToken = true;
        response = this.getRequest(String.format("%s/%s", transStatusEndpoint, transId), token);
        return response;
    }
}
