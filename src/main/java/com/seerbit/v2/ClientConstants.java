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

/** @author Seerbit */
public abstract class ClientConstants {

  // base url
  public static final String TEST_API_BASE = "https://pilot-backend.seerbitapi.com/";
  public static final String LIVE_API_BASE = "https://seerbitapi.com/";

  // authentication
  public static final String AUTHENTICATION_ENDPOINT = "api/v2/encrypt/keys";

  // bank, checkout, & card payment options
  public static final String HASH_REQUEST = "api/v2/encrypt/hashs";
  public static final String INITIALIZE_TRANSACTIONS = "api/v2/payments";
  public static final String INITIATE_PAYMENT_ENDPOINT = "api/v2/payments/initiates";
  public static final String VALIDATE_PAYMENT_ENDPOINT = "api/v2/payments/validate";
  public static final String VALIDATE_CARD_PAYMENT_ENDPOINT = "api/v2/payments/otp";
  public static final String PREAUTH_AUTHORIZE_ENDPOINT = "api/v2/payments/authorise";
  public static final String PAYMENT_CAPTURE_ENDPOINT = "api/v2/payments/capture";
  public static final String PAYMENT_REFUND_ENDPOINT = "api/v2/payments/refund";
  public static final String PAYMENT_CANCEL_ENDPOINT = "api/v2/payments/cancel";
  public static final String PAYMENT_CHARGE_ENDPOINT = "api/v2/payments/charge";
  public static final String TOKENIZATION_ENDPOINT = "api/v2/payments/tokenize";

  // refunds
  public static final String REFUND_ENDPOINT = "merchants/api/v1/user/%s/refunds";
  public static final String REFUND_DETAIL_ENDPOINT = "merchants/api/v1/user/%s/refunds/%s";
  public static final String REFUND_LIST_ENDPOINT =
      "merchants/api/v1/user/%s/refunds?page=%s&size=%s";

  // orders
  public static final String ORDERS_ENDPOINT = "api/v2/payments/order";

  // mobile money
  public static final String AVAILABLE_NETWORKS_ENDPOINT = "api/v2/networks";

  // recurring
  public static final String SUBSCRIPTION_ENDPOINT = "api/v2/recurring/subscribes";
  public static final String CUSTOMER_SUBSCRIPTION_ENDPOINT = "api/v2/recurring/%s/customerId/%s";
  public static final String UPDATE_SUBSCRIPTION_ENDPOINT = "api/v2/recurring/updates";
  public static final String MERCHANT_SUBSCRIPTIONS_ENDPOINT = "api/v2/recurring/publicKey/%s";
  public static final String CHARGE_ENDPOINT = "api/v2/recurring/charge";

  // status queries
  public static final String TRX_STATUS_ENDPOINT = "api/v2/payments/query/%s";
  public static final String SUBSCRIPTION_STATUS_ENDPOINT = "api/v2/recurring/billingId/%S";

  // bank list
  public static final String BANK_LIST_ENDPOINT = "api/v2/banks/merchant/%s";

  // version types
  public static final String VERSION_ONE = "1.0.0";
  public static final String VERSION_TWO = "1.0.1";
}
