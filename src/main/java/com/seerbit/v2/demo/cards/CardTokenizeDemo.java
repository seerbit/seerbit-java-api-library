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

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.AuthType;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.CardPayment;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.CardService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.CardServiceImpl;

/**
 * @author Seerbit
 */
public class CardTokenizeDemo {

	private static Client client;

	/**
	 * @param token token (java.lang.String)
	 *
	 * @return response
	 */
	private static JsonObject doCardTokenize(String token) {
		JsonObject response;
		CardService cardService;
		CardPayment cardPayment;

		System.out.println("================== start card tokenize ==================");
		cardPayment = CardPayment
			.builder()
			.publicKey(client.getPublicKey())
			.amount("1000.00")
			.fullName("Victor Ighalo")
			.mobileNumber("08032000033")
			.currency("KES")
			.country("KE")
			.paymentReference("SBT1237473728")
			.email("johndoe@gmail.com")
			.pin("1234")
			.cardNumber("5123450000000008")
			.cvv("100")
			.expiryMonth("05")
			.expiryYear("21")
			.productId("Foods")
			.productDescription("Test Description")
			.build();
		cardService = new CardServiceImpl(client, token);
		response = cardService.doTokenize(cardPayment);
		System.out.println("================== end card tokenize ==================");

		return response;
	}

	/**
	 * @param args String arguments array for main function
	 */
	public static void main(String... args) {
		String token;
		JsonObject response;
		Seerbit seerbit;
		AuthenticationService authService;

		seerbit = new SeerbitImpl();
		client = new Client();
		client.setApiBase(seerbit.getApiBase());
		client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
		client.setPublicKey("public_key");
		client.setPrivateKey("private_key");
		client.setTimeout(20);
		client.setAuthenticationScheme(AuthType.BASIC.getType());
		authService = new AuthenticationServiceImpl(client);
		token = authService.getBasicAuthorizationEncodedString();
		response = CardTokenizeDemo.doCardTokenize(token);
		System.out.println("card non-3d response: " + response.toString());
	}
}
