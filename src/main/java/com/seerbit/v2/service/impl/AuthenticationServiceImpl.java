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
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.seerbit.v2.service.impl;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.ClientConstants;
import com.seerbit.v2.NumericConstants;
import com.seerbit.v2.config.Config;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.util.Utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Seerbit
 */
public class AuthenticationServiceImpl extends ServiceImpl implements AuthenticationService, ClientConstants, NumericConstants {

	/**
	 * @param client A non-optional class, the client
	 */
	public AuthenticationServiceImpl(Client client) {
		super(client);
		Utility.nonNull(client);
	}

	/**
	 * POST /api/v2/encrypt/keys
	 *
	 * @return response
	 */
	@Override
	public JsonObject doAuth() {
		String key;
		Config config;
		Map<String, Object> payload;

		client = this.getClient();
		config = client.getConfig();
		key = config.getPrivateKey() + "." + config.getPublicKey();
		payload = new HashMap<>(MIN_SIZE);
		payload.put("key", key);
		response = this.postRequest(AUTHENTICATION_ENDPOINT, payload, null);
		return response;
	}

	/**
	 * @return token
	 */
	@Override
	public String getToken() {
		JsonObject encryptedSecKey;
		String encryptedKey;
		encryptedKey = "";

		if (Objects.nonNull(response)) {

			if (response.has("data")) {
				encryptedSecKey = response.get("data").getAsJsonObject();

				if (encryptedSecKey.has("EncryptedSecKey")) {
					encryptedSecKey = encryptedSecKey.get("EncryptedSecKey").getAsJsonObject();
					encryptedKey = encryptedSecKey.get("encryptedKey").getAsString();
				}

			}

		}

		return encryptedKey;
	}
}
