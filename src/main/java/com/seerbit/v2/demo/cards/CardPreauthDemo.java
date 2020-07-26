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
package com.seerbit.v2.demo.cards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.Transaction;
import com.seerbit.v2.model.TransactionDetail;
import com.seerbit.v2.service.CardService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

/**
 * @author Seerbit
 */
@Log4j2
@SuppressWarnings("unchecked")
public class CardPreauthDemo {

	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	static String doAuthenticate() {
		String token;
		AuthenticationServiceImpl authService;
		Seerbit seerbit;
		Client client;
		String jsonString;
		JsonObject json;
		ObjectMapper mapper;
		Map<String, Object> payload;
		TransactionDetail transactionDetail;
		Transaction transaction;
		CardService cardService;

		log.info("================== start authentication ==================");
		seerbit = new SeerbitImpl();
		client = new Client();
		client.setApiBase(seerbit.getApiBase());
		client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
		client.setPublicKey("public_key");
		client.setPrivateKey("private_key");
		client.setTimeout(20);
		authService = new AuthenticationServiceImpl(client);
		json = authService.doAuth();
		jsonString = String.format("auth response: \n%s", GSON.toJson(GSON.fromJson(json.toString(), Map.class)));
		log.info(jsonString);
		log.info("================== end authentication ==================");
		log.info("\n");
		log.info("\n");
		return authService.getToken();
	}

	public static void main(String... args) {
		String token;

		token = CardPreauthDemo.doAuthenticate();
	}
}
