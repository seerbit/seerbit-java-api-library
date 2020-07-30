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
public class Card3dsDemo {

	private static Client client;

	/**
	 * @return token (java.lang.String)
	 */
	private static String doAuthenticate() {
		Seerbit seerbit;
		String token;
		AuthenticationService authService;
		JsonObject json;
		String jsonString;

		System.out.println("================== start authentication ==================");
		seerbit = new SeerbitImpl();
		client = new Client();
		client.setApiBase(seerbit.getApiBase());
		client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
		client.setPublicKey("public_key");
		client.setPrivateKey("private_key");
		client.setTimeout(20);
		authService = new AuthenticationServiceImpl(client);
		json = authService.doAuth();
		jsonString = String.format("auth response: \n%s", json.toString());
		System.out.println(jsonString);
		System.out.println("================== end authentication ==================");
		System.out.println("\n");
		System.out.println("\n");
		token = authService.getToken();
		return token;
	}

	/**
	 * @param token token (java.lang.String)
	 *
	 * @return response
	 */
	private static JsonObject doCard3dsCharge(String token) {
		JsonObject response;
		CardService cardService;
		CardPayment cardPayment;

		cardPayment = CardPayment
			.builder()
			.publicKey(client.getPublicKey())
			.amount("1000.00")
			.fee("10.00")
			.fullName("Victor Ighalo")
			.mobileNumber("08032000033")
			.currency("NGN")
			.country("NG")
			.paymentReference("SBT1237473728")
			.email("johndoe@gmail.com")
			.productId("Foods")
			.productDescription("Test Description")
			.clientAppCode("kpp64")
			.redirectUrl("www.ser1.com")
			.channelType("Mastercard")
			.deviceType("Apple Laptop")
			.sourceIP("127.0.0.1:3456")
			.cardNumber("5123450000000008")
			.cvv("100")
			.expiryMonth("05")
			.expiryYear("21")
			.pin("####")
			.retry("false")
			.paymentType("CARD")
			.invoiceNumber("1234567890abc123ac")
			.build();
		cardService = new CardServiceImpl(client, token);
		response = cardService.doPaymentCharge3DS(cardPayment);

		return response;
	}

	/**
	 * @param args String arguments array for main function
	 */
	public static void main(String... args) {
		String token;
		JsonObject response;

		token = Card3dsDemo.doAuthenticate();
		response = Card3dsDemo.doCard3dsCharge(token);
		System.out.println("card 3ds response: " + response.toString());
	}
}
