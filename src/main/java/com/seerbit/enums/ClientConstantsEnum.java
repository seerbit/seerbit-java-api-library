/*
 * Copyright (C) 2019 Seerbit
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
package com.seerbit.enums;

/**
 *
 * @author Seerbit
 */
public enum ClientConstantsEnum {

    PILOT_API_BASE("https://pilot-backend.seerbitapi.com/sbt/api/"),
    LIVE_API_BASE("https://stg-backend.seerbitapi.com/"),
    TRANSACTION_AUTH_ENDPOINT("sbt/api/v1/auth"),
    MERCHANT_AUTH_ENDPOINT("merchants/api/v1/auth/login"),
    VALIDATE_TRANS_CARD_ENDPOINT("sbt/api/card/v1/get/transaction/status"),
    INITIATE_TRANSACTION_ENDPOINT("sbt/api/account/v1/initiate/transaction"),
    INITIATE_TRANSACTION_ENDPOINT_V2("sbt/api/account/v2/initiate/transaction"),
    VALIDATE_TRANS_ACCOUNT_ENDPOINT("sbt/api/account/v1/validate/transaction"),
    INITIATE_CARD_ENDPOINT("sbt/api/card/v1/init/transaction"),
    VALIDATE_OTP_ENDPOINT("sbt/api/card/v1/validate/otp"),
    CHECKOUT_STANDARD_ENDPOINT("sbt/api/v2/payments"),
    ORDER_ENDPOINT("sbt/api/order/v2/payments/order"),
    CREATE_SUBSCRIPTION_ENDPOINT("sbt/api/v2/recurring/subscribes"),
    GET_SUBSCRIPTION_ENDPOINT("sbt/api/v2/recurring/%s/%s/%s"),
    UPDATE_SUBSCRIPTION_ENDPOINT("sbt/api/v2/recurring/updates"),
    RETRIEVE_SUBSCRIPTION_ENDPOINT("sbt/api/v2/recurring/{publicKey}"),
    RECURRING_DEBIT_ENDPOINT("sbt/api/v2/recurring/charge"),
    GET_ALL_DISPUTE_ENDPOINT("merchants/api/v1/user/%s/disputes/?page=%d&size=%d"),
    GET_DISPUTE_ENDPOINT("merchants/api/v1/user/%s/disputes/%s"),
    ADD_DISPUTE_ENDPOINT("merchants/api/v1/user/%s/disputes"),
    CLOSE_DISPUTE_ENDPOINT("merchants/api/v1/user/%s/disputes/%s/close"),
    UPDATE_DISPUTE_ENDPOINT("merchants/api/v1/user/%s/disputes"),
    WEBHOOK_ENDPOINT("merchants/api/v1/business/%s/webhook"),
    VERSION_ONE("1.0.0"),
    VERSION_TWO("1.0.1");

    private final String value;

    /**
     *
     * @param value
     */
    ClientConstantsEnum(final String value) {
        this.value = value;
    }

    /**
     *
     * @return value
     */
    public String getValue() {
        return value;
    }
}
