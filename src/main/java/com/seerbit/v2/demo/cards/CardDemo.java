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
import com.google.gson.JsonSyntaxException;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.CardPayment;
import com.seerbit.v2.model.Transaction;
import com.seerbit.v2.model.TransactionDetail;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.CardService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.CardServiceImpl;
import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.Objects;

/**
 * @author Seerbit
 */
@Log4j2
@SuppressWarnings("unchecked")
public class CardDemo {


	private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	public static void doCardDemo() {
		String token;
		AuthenticationService authService;
		Seerbit seerbit;
		Client client;
		String jsonString;
		JsonObject json;
		ObjectMapper mapper;
		Map<String, Object> payload;
		TransactionDetail transactionDetail;
		Transaction transaction;
		CardService cardService;

		try {
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
			token = authService.getToken();

			if (Objects.nonNull(token)) {
				log.info("================== start initiate card (non-3DS) ==================");
				cardService = new CardServiceImpl(client, token);
				CardPayment card = new CardPayment();
				card.setCvv("100");
				card.setCardNumber("5123450000000008");
				card.setExpiryMonth("05");
				card.setExpiryYear("21");
				card.setPin("1234");
				card.setFullName("Aminu Grod");
				card.setPublicKey(client.getConfig().getPublicKey());
				card.setPaymentReference("trx0001");
				card.setEmail("demo@example.com");
				card.setMobileNumber("08012345678");
				card.setChannelType("Mastercard");
				card.setDeviceType("Nokia 3310");
				card.setSourceIP("127.0.0.20");
				card.setCurrency("NGN");
				card.setRetry("false");
				card.setInvoiceNumber("1234567890abc123ac");
				card.setProductDescription("demo");
				card.setCountry("NG");
				card.setFee("1.00");
				card.setAmount("150.00");
				card.setClientAppCode("appl");
				card.setRedirectUrl("http://www.example.com");
				card.setProductId("Foods");
				mapper = new ObjectMapper();
				payload = mapper.convertValue(card, Map.class);
				log.info("Request Body: \n" + GSON.toJson(payload));
				json = cardService.doAuthorize(card);
				jsonString = String.format("Initiate CardPayment response: \n%s", GSON.toJson(GSON.fromJson(json.toString(), Map.class)));
				log.info(jsonString);
				log.info("================== end initiate card (non-3DS) ==================");
				log.info("\n");
				log.info("\n");
				log.info("================== start validate otp ==================");

				if (Objects.nonNull(json.getAsJsonObject("transaction"))) {
					transactionDetail = new TransactionDetail();
					transaction = new Transaction();
					transactionDetail.setOtp("12345");
					transactionDetail.setLinkingReference(
						String.valueOf(json.getAsJsonObject("transaction").get("linkingreference").getAsString())
					);
					transaction.setTransaction(transactionDetail);
					json = cardService.doValidate(transaction);
					payload = mapper.convertValue(transaction, Map.class);
					log.info("Validate Otp Request: \n" + GSON.toJson(payload));
					jsonString = String.format("Validate Otp response: \n%s", GSON.toJson(GSON.fromJson(json.toString(), Map.class)));
					log.info(jsonString);
				} else {
					log.info("Null Pointer Exception on JSON Object");
				}

				log.info("================== end validate otp ==================");
				log.info("\n");
				log.info("\n");

				log.info("================== start initiate card (3DS) ==================");
				card = new CardPayment();
				card.setCvv("100");
				card.setCardNumber("5123450000000008");
				card.setExpiryMonth("05");
				card.setExpiryYear("21");
				card.setPin("1234");
				card.setFullName("Aminu Grod");
				card.setPublicKey(client.getConfig().getPublicKey());
				card.setPaymentReference("trx0001");
				card.setEmail("demo@example.com");
				card.setMobileNumber("08012345678");
				card.setChannelType("Mastercard");
				card.setDeviceType("Nokia 3310");
				card.setSourceIP("127.0.0.20");
				card.setCurrency("NGN");
				card.setRetry("false");
				card.setInvoiceNumber("1234567890abc123ac");
				card.setProductDescription("demo");
				card.setCountry("NG");
				card.setFee("1.00");
				card.setAmount("150.00");
				card.setClientAppCode("appl");
				card.setRedirectUrl("http://www.example.com");
				card.setProductId("Foods");
				mapper = new ObjectMapper();
				payload = mapper.convertValue(card, Map.class);
				log.info("Request Body: \n" + GSON.toJson(payload));
				json = cardService.doAuthorize(card);
				jsonString = String.format("3DS CardPayment response: \n%s", GSON.toJson(GSON.fromJson(json.toString(), Map.class)));
				log.info(jsonString);
				log.info("================== end initiate card (3DS) ==================");
			} else {
				log.info("AuthenticationService Failed");
			}

		} catch (JsonSyntaxException exception) {
			log.error(exception.getMessage());
		}
	}

	public static void main(String... args) {
		doCardDemo();
	}
}
