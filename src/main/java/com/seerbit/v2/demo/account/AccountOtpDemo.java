package com.seerbit.v2.demo.account;

import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.Seerbit;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.impl.SeerbitImpl;
import com.seerbit.v2.model.Otp;
import com.seerbit.v2.service.AccountService;
import com.seerbit.v2.service.AuthenticationService;
import com.seerbit.v2.service.impl.AccountServiceImpl;
import com.seerbit.v2.service.impl.AuthenticationServiceImpl;

/**
 * @author centricgateway
 */
public class AccountOtpDemo {

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
	private static JsonObject doOtpValidate(String token) {
		Otp otp;
		AccountService accountService;
		JsonObject response;

		otp = new Otp();
		accountService = new AccountServiceImpl(client, token);
		otp.setLinkingReference("2399293JSNBJBSFSDFSDS");
		otp.setOtp("123456");
		response = accountService.doValidate(otp);

		return response;
	}

	/**
	 * @param args String arguments array for main function
	 */
	public static void main(String... args) {
		String token;
		JsonObject response;

		token = AccountOtpDemo.doAuthenticate();
		response = AccountOtpDemo.doOtpValidate(token);
		System.out.println("account otp response: " + response.toString());
	}
}
