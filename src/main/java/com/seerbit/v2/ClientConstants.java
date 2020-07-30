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
package com.seerbit.v2;

/**
 * @author Seerbit
 */
public interface ClientConstants {

	// base url
	String TEST_API_BASE = "https://stg-backend.seerbitapi.com/";
	String LIVE_API_BASE = "https://seerbitapi.com/";

	// authentication
	String AUTHENTICATION_ENDPOINT = "api/v2/encrypt/keys";

	// bank & card payment options
	String INITIATE_PAYMENT_ENDPOINT = "api/v2/payments/initiates";
	String VALIDATE_PAYMENT_ENDPOINT = "api/v2/payments/validate";
	String VALIDATE_CARD_PAYMENT_ENDPOINT = "api/v2/payments/otp";
	String PREAUTH_AUTHORIZE_ENDPOINT = "api/v2/payments/authorise";
	String PAYMENT_CAPTURE_ENDPOINT = "api/v2/payments/capture";
	String PAYMENT_REFUND_ENDPOINT = "api/v2/payments/refund";
	String PAYMENT_CANCEL_ENDPOINT = "api/v2/payments/cancel";
	String PAYMENT_CHARGE_ENDPOINT = "api/v2/payments/charge";
	String TOKENIZATION_ENDPOINT = "api/v2/payments/tokenize";

	//refunds
	String REFUND_ENDPOINT = "merchants/api/v1/user/%s/refunds";
	String REFUND_DETAIL_ENDPOINT = "merchants/api/v1/user/%s/refunds/%s";
	String REFUND_LIST_ENDPOINT = "merchants/api/v1/user/%s/refunds?page=%s&size=%s";

	// orders
	String ORDERS_ENDPOINT = "api/v2/payments/order";

	// mobile money
	String AVAILABLE_NETWORKS_ENDPOINT = "api/v2/networks";

	//recurring
	String SUBSCRIPTION_ENDPOINT = "api/v2/recurring/subscribes";
	String CUSTOMER_SUBSCRIPTION_ENDPOINT = "api/v2/recurring/%s/customerId/%s";
	String UPDATE_SUBSCRIPTION_ENDPOINT = "api/v2/recurring/updates";
	String MERCHANT_SUBSCRIPTIONS_ENDPOINT = "api/v2/recurring/publicKey/%s";
	String CHARGE_ENDPOINT = "api/v2/recurring/charge";

	// status queries
	String TRX_STATUS_ENDPOINT = "api/v2/payments/query/%s";
	String SUBSCRIPTION_STATUS_ENDPOINT = "api/v2/recurring/billingId/%S";

	// bank list
	String BANK_LIST_ENDPOINT = "api/v2/banks/merchant/%s";

	// version types
	String VERSION_ONE = "1.0.0";
	String VERSION_TWO = "1.0.1";
}
