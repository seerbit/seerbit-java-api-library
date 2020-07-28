package com.seerbit.v2.demo.mobile_money;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.MobileMoney;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;

public class MobileMoneyDemo {

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
	 * @return
	 */
	public static JsonObject doMobileMoneyPayment(String token) {
		String paymentReference;
		MobileMoney mobileMoney;
		return new JsonObject();
	}

	/**
	 * @param args
	 */
	public static void main(String... args) {
		String token;

		token = MobileMoneyDemo.doAuthenticate();
	}

}
