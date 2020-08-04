package com.seerbit.v2.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.RequestValidator;
import com.seerbit.v2.exception.SeerbitException;
import com.seerbit.v2.model.StandardCheckout;
import com.seerbit.v2.service.StandardCheckoutService;
import com.seerbit.v2.util.Utility;

import java.util.Map;

import static com.seerbit.v2.ClientConstants.HASH_REQUEST;
import static com.seerbit.v2.ClientConstants.INITIALIZE_TRANSACTIONS;

@SuppressWarnings("unchecked")
public class StandardCheckoutServiceImpl extends ServiceImpl implements StandardCheckoutService {

	/**
	 * @param client A non-optional class, the client
	 * @param token  A non-optional String, the auth token
	 */
	public StandardCheckoutServiceImpl(Client client, String token) {
		super(client);
		Utility.nonNull(client);
		this.token = token;
	}

	/**
	 * POST /api/v2/payments
	 *
	 * @param standardCheckoutPayload A non-optional class, the payload
	 *
	 * @return
	 */
	@Override
	public JsonObject doInitializeTransaction(StandardCheckout standardCheckoutPayload) {
		ObjectMapper mapper;
		Map<String, Object> payload;

		RequestValidator.doValidate(standardCheckoutPayload);
		this.requiresToken = true;
		mapper = new ObjectMapper();
		payload = mapper.convertValue(standardCheckoutPayload, Map.class);
		response = this.postRequest(INITIALIZE_TRANSACTIONS, payload, token);
		return response;
	}

	/**
	 * @param standardCheckoutPayload A non-optional class, the payload
	 *
	 * @return
	 */
	@Override
	public String getHash(StandardCheckout standardCheckoutPayload) {
		ObjectMapper mapper;
		Map<String, Object> payload;
		String hash;

		RequestValidator.doValidate(standardCheckoutPayload);
		this.requiresToken = true;
		mapper = new ObjectMapper();
		payload = mapper.convertValue(standardCheckoutPayload, Map.class);
		System.out.println("request: " + new Gson().toJson(standardCheckoutPayload));
		response = this.postRequest(HASH_REQUEST, payload, token);

		if (response.has("data")) {
			response = response.get("data").getAsJsonObject();

			if (response.has("hash")) {
				hash = response.get("hash").getAsJsonObject().get("hash").getAsString();
			} else {
				throw new SeerbitException("Unable to obtain hash");
			}

		} else {
			throw new SeerbitException("Unable to obtain hash");
		}

		return hash;
	}
}
