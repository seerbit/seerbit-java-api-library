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
public class CardAuthorizeDemo {

	private static Client client;

	/**
	 * @param token token (java.lang.String)
	 *
	 * @return response
	 */
	private static JsonObject doCardAuthorize(final String token) {
		CardService cardService;
		CardPayment cardPayment;
		JsonObject response;

		cardService = new CardServiceImpl(client, token);
		cardPayment = CardPayment
			.builder()
			.cvv("100")
			.cardNumber("5123450000000008")
			.expiryMonth("05")
			.expiryYear("21")
			.pin("1234")
			.fullName("Aminu Grod")
			.publicKey(client.getConfig().getPublicKey())
			.paymentReference("trx0001")
			.email("demo@example.com")
			.mobileNumber("08012345678")
			.channelType("Mastercard")
			.deviceType("Nokia 3310")
			.sourceIP("127.0.0.20")
			.currency("NGN")
			.retry("false")
			.invoiceNumber("1234567890abc123ac")
			.productDescription("demo")
			.country("NG")
			.fee("1.00")
			.amount("150.00")
			.clientAppCode("appl")
			.redirectUrl("http://www.example.com")
			.productId("Foods")
			.invoiceNumber("1234567890abc123ac")
			.retry("false")
			.type("3DSECURE")
			.build();
		response = cardService.doAuthorize(cardPayment);

		return response;
	}

	/**
	 * @param args String arguments array for main function
	 */
	public static void main(String... args) {
		String token;
		Seerbit seerbit;
		AuthenticationService authService;
		JsonObject response;

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
		response = CardAuthorizeDemo.doCardAuthorize(token);
		System.out.println("card authorize response: " + response.toString());
	}
}
