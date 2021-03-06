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
package com.seerbit;

/**
 *
 * @author Seerbit
 */
public interface ClientConstants {

    static final String PILOT_API_BASE = "https://pilot-backend.seerbitapi.com/";
    static final String LIVE_API_BASE = "https://seerbitapi.com/";

    // version one endpoints 
    static final String TRANSACTION_AUTH_ENDPOINT = "sbt/api/v1/auth";
    static final String MERCHANT_AUTH_ENDPOINT = "merchants/api/v1/auth/login";
    static final String VALIDATE_TRANS_CARD_ENDPOINT = "sbt/api/card/v1/get/transaction/status";
    static final String INITIATE_TRANSACTION_ENDPOINT = "sbt/api/account/v1/initiate/transaction";
    static final String VALIDATE_TRANS_ACCOUNT_ENDPOINT = "sbt/api/account/v1/validate/transaction";
    static final String INITIATE_CARD_ENDPOINT = "sbt/api/card/v1/init/transaction";
    static final String VALIDATE_OTP_ENDPOINT = "sbt/api/card/v1/validate/otp";
    static final String GET_ALL_DISPUTE_ENDPOINT = "merchants/api/v1/user/%s/disputes/?page=%d&size=%d";
    static final String GET_DISPUTE_ENDPOINT = "merchants/api/v1/user/%s/disputes/%s";
    static final String ADD_DISPUTE_ENDPOINT = "merchants/api/v1/user/%s/disputes";
    static final String CLOSE_DISPUTE_ENDPOINT = "merchants/api/v1/user/%s/disputes/%s/close";
    static final String UPDATE_DISPUTE_ENDPOINT = "merchants/api/v1/user/%s/disputes/%s";
    static final String GET_ALL_REFUND_ENDPOINT = "merchants/api/v1/user/%s/refunds/?page=%d&size=%d";
    static final String VALIDATE_TRANSACTION_ENDPOINT = "card/v1/get/transaction/status/%s";
    static final String DO_REFUND_ENDPOINT = "merchants/api/v1/user/%s/refunds";
    static final String GET_REFUND_ENDPOINT = "merchants/api/v1/user/%s/refunds/%s";

    // version types
    static final String VERSION_ONE = "1.0.0";
}
