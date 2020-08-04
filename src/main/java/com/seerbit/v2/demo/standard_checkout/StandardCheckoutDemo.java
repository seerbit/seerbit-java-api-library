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
package com.seerbit.v2.demo.standard_checkout;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.exception.SeerbitException;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.StandardCheckout;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.StandardCheckoutService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.StandardCheckoutServiceImpl;
import org.apache.commons.codec.binary.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Seerbit
 */
public class StandardCheckoutDemo {

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
	 * @param token (java.lang.String)
	 *
	 * @return response
	 */
	private static JsonObject doInitializeTransaction(String token) {
		StandardCheckoutService standardCheckoutService;
		StandardCheckout standardCheckout;
		StandardCheckout standardCheckoutHash;
		String hash;
		JsonObject response;

		System.out.println("================== start initialize transaction ==================");
		standardCheckoutService = new StandardCheckoutServiceImpl(client, token);
		// build hash payload
		standardCheckoutHash = StandardCheckout
			.builder()
			.amount("100.00")
			.callbackUrl("http://yourwebsite.com")
			.country("NG")
			.currency("NGN")
			.email("okechukwu.diei2@gmail.com")
			.paymentReference("643108207792124616573324Q145A")
			.productId("227271")
			.productDescription("Fish Products")
			.publicKey(client.getPublicKey())
			.build();
		// get hash
		hash = standardCheckoutService.getHash(standardCheckoutHash);
		// build checkout payload
		standardCheckout = StandardCheckout
			.builder()
			.amount("100.00")
			.callbackUrl("http://yourwebsite.com")
			.country("NG")
			.currency("NGN")
			.email("okechukwu.diei2@gmail.com")
			.hash(hash)
			.hashType("sha256")
			.paymentReference("643108207792124616573324Q145A")
			.productDescription("Fish Products")
			.productId("227271")
			.publicKey(client.getPublicKey())
			.build();
		response = standardCheckoutService.doInitializeTransaction(standardCheckout);
		System.out.println("================== stop initialize transaction ==================");

		return response;
	}

	private static String doSha256(String rawKey) throws Exception {
		MessageDigest messageDigest;
		String errorMessage;
		byte[] encrypted;

		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			encrypted = messageDigest.digest(rawKey.getBytes());
			return new String(Hex.encodeHex(encrypted));
		} catch (NoSuchAlgorithmException ex) {
			errorMessage = "Unable to hash this string";
			throw new Exception(errorMessage);
		}

	}

	private static JsonObject doInitializeTransactionWithManualHash(String token) {
		StandardCheckoutService standardCheckoutService;
		StandardCheckout standardCheckout;
		String hash;
		JsonObject response;
		String standardCheckoutHash;

		System.out.println("================== start initialize transaction with manual hash ==================");
		standardCheckoutService = new StandardCheckoutServiceImpl(client, token);
		// build hash payload
		standardCheckoutHash = new StringBuilder()
			.append("amount=100.00")
			.append("&")
			.append("callbackUrl=http://yourwebsite.com")
			.append("&")
			.append("country=NG")
			.append("&")
			.append("currency=NGN")
			.append("&")
			.append("email=okechukwu.diei2@gmail.com")
			.append("&")
			.append("paymentReference=643108207792124616573324Q145B")
			.append("&")
			.append("productDescription=Fish Products")
			.append("&")
			.append("productId=227271")
			.append("&")
			.append("publicKey=")
			.append(client.getPublicKey())
			.append(client.getPrivateKey())
			.toString();

		try {
			// get hash
			hash = StandardCheckoutDemo.doSha256(standardCheckoutHash);
		} catch (Exception exception) {
			throw new SeerbitException(exception.getMessage());
		}

		// build checkout payload
		standardCheckout = StandardCheckout
			.builder()
			.amount("100.00")
			.callbackUrl("http://yourwebsite.com")
			.country("NG")
			.currency("NGN")
			.email("okechukwu.diei2@gmail.com")
			.hash(hash)
			.hashType("sha256")
			.paymentReference("643108207792124616573324Q145B")
			.productDescription("Fish Products")
			.productId("227271")
			.publicKey(client.getPublicKey())
			.build();
		response = standardCheckoutService.doInitializeTransaction(standardCheckout);
		System.out.println("================== stop initialize transaction with manual hash ==================");

		return response;
	}

	/**
	 * @param args String arguments array for main function
	 */
	public static void main(String... args) {
		String token;
		JsonObject response;

		token = StandardCheckoutDemo.doAuthenticate();
		response = StandardCheckoutDemo.doInitializeTransaction(token);
		System.out.println("standard checkout response: " + response.toString());
		response = StandardCheckoutDemo.doInitializeTransactionWithManualHash(token);
		System.out.println("standard checkout response (manual hash): " + response.toString());
	}
}
