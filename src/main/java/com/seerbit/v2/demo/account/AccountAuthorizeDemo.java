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
package com.seerbit.v2.demo.account;

import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.Account;
import com.seerbit.v2.service.AccountService;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.impl.AccountServiceImpl;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;

/**
 * @author Seerbit
 */
public class AccountAuthorizeDemo {

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
	 * @param token
	 *
	 * @return response (com.google.gson.JsonObject)
	 */
	private static JsonObject doAuthorize(String token) {
		AccountService accountService;
		Account account;
		JsonObject response;

		response = new JsonObject();

		System.out.println("================== start account authorization ==================");

		try {
			accountService = new AccountServiceImpl(client, token);
			account = new Account();
			account.setFullName("Musa Chukwuma Adetutu");
			account.setEmail("musa@example.com");
			account.setMobileNumber("08012345678");
			account.setPublicKey(client.getPublicKey());
			account.setChannelType("BANK_ACCOUNT");
			account.setDeviceType("nokia 33");
			account.setSourceIP("1.0.1.0");
			account.setPaymentReference("trx0001");
			account.setCurrency("NGN");
			account.setProductDescription("put a description here");
			account.setProductId("Foods");
			account.setCountry("NG");
			account.setFee("1.00");
			account.setAmount("100.00");
			account.setClientAppCode("app1");
			account.setRedirectUrl("http://www.example.com");
			account.setAccountName("Diei Okechukwu Peter");
			account.setAccountNumber("2213132677");
			account.setBankCode("057");
			account.setBvn("22912882998");
			account.setRetry("false");
			account.setInvoiceNumber("1234567891abc123ac");
			account.setDateOfBirth("09-08-1909");
			account.setPaymentType("ACCOUNT");
			response = accountService.doAuthorize(account);
		} catch (JsonSyntaxException exception) {
			System.out.println(exception.getMessage());
		}

		System.out.println("================== stop account authorization ==================");
		System.out.println("\n");
		System.out.println("\n");

		return response;
	}

	/**
	 * @param args String arguments array for main function
	 */
	public static void main(String... args) {
		String token;
		JsonObject response;

		token = AccountAuthorizeDemo.doAuthenticate();
		response = AccountAuthorizeDemo.doAuthorize(token);
		System.out.println("account authorization response: " + response.toString());
	}
}
