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
import com.seerbit.service.TransactionService;
import com.seerbit.util.Utility;

/**
 *
 * @author Seerbit
 */
public class TransactionServiceImpl extends ServiceTransactionImpl 
        implements TransactionService, ClientConstants {
    
    /**
     * 
     * @param client
     * @param token 
     */
    public TransactionServiceImpl(final Client client, final String token) {
        super(client);
        Utility.doClientNonNull(client);
        this.token = token;
    }
    
    /**
     * 
     * @param transId
     * @return response
     */
    @Override
    public JsonObject doValidateTransaction(final String transId) {
        requiresToken = true;
        String endpointURL = String.format("%s/%s", VALIDATE_TRANS_CARD_ENDPOINT, transId);
        response = this.getRequest(endpointURL, token);
        return response;
    }
}
