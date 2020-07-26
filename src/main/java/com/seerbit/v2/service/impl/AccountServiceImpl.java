/*
 * Copyright (C) 2020 Seerbit
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
package com.seerbit.v2.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.ClientConstants;
import com.seerbit.v2.RequestValidator;
import com.seerbit.v2.model.Account;
import com.seerbit.v2.model.Otp;
import com.seerbit.v2.service.AccountService;
import com.seerbit.v2.util.Utility;

import java.util.Map;

/**
 * @author Seerbit
 */
@SuppressWarnings("unchecked")
public class AccountServiceImpl extends ServiceImpl implements AccountService, ClientConstants {

	private Map<String, Object> payload;
	private ObjectMapper mapper;

	/**
	 * @param client A non-optional class, the client
	 * @param token  A nonA non-optional String, the auth token
	 */
	public AccountServiceImpl(Client client, String token) {
		super(client);
		this.token = token;
		Utility.nonNull(client);
	}

	/**
	 * POST /api/v2/payments/initiates
	 *
	 * @param accountPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doAuthorize(Account accountPayload) {
		this.requiresToken = true;
		RequestValidator.doValidate(accountPayload);
		mapper = new ObjectMapper();
		payload = mapper.convertValue(accountPayload, Map.class);
		payload.put("publicKey", this.getClient().getPublicKey());
		response = this.postRequest(INITIATE_PAYMENT_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/validate
	 *
	 * @param otpPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doValidate(Otp otpPayload) {
		this.requiresToken = true;
		RequestValidator.doValidate(otpPayload);
		mapper = new ObjectMapper();
		payload = mapper.convertValue(otpPayload, Map.class);
		response = this.postRequest(VALIDATE_PAYMENT_ENDPOINT, payload, token);
		return response;
	}
}
