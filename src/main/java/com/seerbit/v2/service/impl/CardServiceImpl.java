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
package com.seerbit.v2.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.seerbit.v2.Client;
import com.seerbit.v2.ClientConstants;
import com.seerbit.v2.RequestValidator;
import com.seerbit.v2.model.CardPayment;
import com.seerbit.v2.model.CardPreAuth;
import com.seerbit.v2.model.OtpCard;
import com.seerbit.v2.model.PaymentCancel;
import com.seerbit.v2.model.PaymentCapture;
import com.seerbit.v2.model.PaymentRefund;
import com.seerbit.v2.service.CardService;
import com.seerbit.v2.util.Utility;

import java.util.Map;

/**
 * @author Seerbit
 */
@SuppressWarnings("unchecked")
public class CardServiceImpl extends ServiceImpl implements CardService, ClientConstants {

	private ObjectMapper mapper;
	private Map<String, Object> payload;

	/**
	 * @param client A non-optional class, the client
	 * @param token  A non-optional String, the auth token
	 */
	public CardServiceImpl(Client client, String token) {
		super(client);
		this.token = token;
		Utility.nonNull(client);
	}

	/**
	 * POST /api/v2/payments/initiates
	 *
	 * @param cardPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doAuthorize(CardPayment cardPayload) {
		this.requiresToken = true;
		mapper = new ObjectMapper();
		payload = mapper.convertValue(cardPayload, Map.class);
		response = this.postRequest(INITIATE_PAYMENT_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/otp
	 *
	 * @param otpCard A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doValidate(OtpCard otpCard) {
		this.requiresToken = true;
		RequestValidator.doValidate(otpCard);
		mapper = new ObjectMapper();
		payload = mapper.convertValue(otpCard, Map.class);
		response = this.postRequest(VALIDATE_CARD_PAYMENT_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/authorise
	 *
	 * @param cardPreAuthPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doPreauthAuthorization(CardPreAuth cardPreAuthPayload) {
		this.requiresToken = true;
		RequestValidator.doValidate(cardPreAuthPayload);
		mapper = new ObjectMapper();
		payload = mapper.convertValue(cardPreAuthPayload, Map.class);
		response = this.postRequest(PREAUTH_AUTHORIZE_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/capture
	 *
	 * @param paymentCapturePayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doPaymentCapture(PaymentCapture paymentCapturePayload) {
		this.requiresToken = true;
		RequestValidator.doValidate(paymentCapturePayload);
		mapper = new ObjectMapper();
		payload = mapper.convertValue(paymentCapturePayload, Map.class);
		response = this.postRequest(PAYMENT_CAPTURE_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/refund
	 *
	 * @param paymentRefundPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doPaymentRefund(PaymentRefund paymentRefundPayload) {
		this.requiresToken = true;
		RequestValidator.doValidate(paymentRefundPayload);
		mapper = new ObjectMapper();
		payload = mapper.convertValue(paymentRefundPayload, Map.class);
		response = this.postRequest(PAYMENT_REFUND_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/cancel
	 *
	 * @param paymentCancelPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doPaymentCancel(PaymentCancel paymentCancelPayload) {
		this.requiresToken = true;
		RequestValidator.doValidate(paymentCancelPayload);
		mapper = new ObjectMapper();
		payload = mapper.convertValue(paymentCancelPayload, Map.class);
		response = this.postRequest(PAYMENT_CANCEL_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/charge
	 *
	 * @param cardPaymentPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doPaymentChargeNon3D(CardPayment cardPaymentPayload) {
		this.requiresToken = true;
		mapper = new ObjectMapper();
		payload = mapper.convertValue(cardPaymentPayload, Map.class);
		response = this.postRequest(PAYMENT_CHARGE_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/tokenize
	 *
	 * @param cardPaymentPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doTokenize(CardPayment cardPaymentPayload) {
		this.requiresToken = true;
		mapper = new ObjectMapper();
		payload = mapper.convertValue(cardPaymentPayload, Map.class);
		response = this.postRequest(TOKENIZATION_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/initiates
	 *
	 * @param cardPaymentPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doPaymentCharge3DS(CardPayment cardPaymentPayload) {
		this.requiresToken = true;
		mapper = new ObjectMapper();
		payload = mapper.convertValue(cardPaymentPayload, Map.class);
		response = this.postRequest(INITIATE_PAYMENT_ENDPOINT, payload, token);
		return response;
	}

	/**
	 * POST /api/v2/payments/initiates
	 *
	 * @param cardPaymentPayload A non-optional class, the payload
	 *
	 * @return response
	 */
	@Override
	public JsonObject doPaymentCharge3D(CardPayment cardPaymentPayload) {
		this.requiresToken = true;
		mapper = new ObjectMapper();
		payload = mapper.convertValue(cardPaymentPayload, Map.class);
		response = this.postRequest(INITIATE_PAYMENT_ENDPOINT, payload, token);
		return response;
	}
}
