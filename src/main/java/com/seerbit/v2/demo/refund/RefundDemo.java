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
package com.seerbit.v2.demo.refund;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.seerbit.v2.Client;
import com.seerbit.v2.NumericConstants;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.enums.RefundTypeEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.Refund;
import com.seerbit.v2.service.RefundService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;
import com.seerbit.v2.service.impl.RefundServiceImpl;
import lombok.extern.log4j.Log4j2;

import java.util.Map;
import java.util.Objects;

/**
 * @author Seerbit
 */
@Log4j2
public class RefundDemo implements NumericConstants {

	private final static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

	private static void doRefundDemo() {
		String token;
		Seerbit seerbit;
		Client client;
		AuthenticationServiceImpl authService;
		String jsonString;
		RefundService refundService;
		Refund refundPayload;

		try {
			System.out.println("================== start authentication ==================");
			seerbit = new SeerbitImpl();
			client = new Client();
			client.setApiBase(seerbit.getApiBase());
			client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
			client.setPublicKey("public_key");
			client.setPrivateKey("private_key");
			client.setTimeout(20);
			authService = new AuthenticationServiceImpl(client);
			JsonObject json = authService.doAuth();
			jsonString = String.format("auth response: \n%s", GSON.toJson(GSON.fromJson(json.toString(), Map.class)));
			System.out.println(jsonString);
			System.out.println("================== end authentication ==================");
			System.out.println();
			System.out.println();
			token = authService.getToken();

			if (Objects.nonNull(token)) {
				System.out.println("================== start get all refund ==================");
				refundService = new RefundServiceImpl(client, token);
				json = refundService.getAllRefund("00000001", 0, 10);
				System.out.print("All Refund Response: ");
				System.out.println(json.toString());
				System.out.println("================== end get all refund ==================");
				System.out.println();
				System.out.println();
				System.out.println("================== start get refund ==================");
				json = refundService.getRefund("00000001", "01");
				System.out.print("Get Refund Response: ");
				System.out.println(json.toString());
				System.out.println("================== end get refund ==================");
				System.out.println();
				System.out.println();
				System.out.println("================== start do refund ==================");
				refundPayload = new Refund();
				refundPayload.setAmount("100");
				refundPayload.setResolution("Buyer genuinely didn’t receive value and refund will be sent");
				refundPayload.setResolutionImage("logo.png");
				refundPayload.setType(RefundTypeEnum.PARTIAL_REFUND);
				json = refundService.doRefund("00000001", refundPayload);
				System.out.println(json.toString());
				System.out.println("================== end do refund ==================");
				System.out.println();
				System.out.println();
			} else {
				System.out.println("AuthenticationService Failed");
			}

		} catch (JsonSyntaxException exception) {
			log.error(exception.getMessage());
		}
	}

	public static void main(String... args) {
		doRefundDemo();
	}
}
