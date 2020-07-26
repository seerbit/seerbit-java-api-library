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
package com.seerbit.v2.service;

import com.google.gson.JsonObject;
import com.seerbit.v2.model.RecurringDebit;
import com.seerbit.v2.model.Subscription;

/**
 * @author Seerbit
 */
public interface RecurringService {

	/**
	 * @param subscription A non-optional class, the payload
	 *
	 * @return JsonObject
	 */
	JsonObject doCreateSubscription(Subscription subscription);

	/**
	 * @param publicKey  A non-optional String, the merchant public key
	 * @param customerId A non-optional String, the customer Id
	 *
	 * @return JsonObject
	 */
	JsonObject getCustomerSubscriptions(String publicKey, String customerId);

	/**
	 * @param subscription A non-optional class, the payload
	 *
	 * @return JsonObject
	 */
	JsonObject doUpdateSubscription(Subscription subscription);

	/**
	 * @param publicKey A non-optional String, the merchant public key
	 *
	 * @return JsonObject
	 */
	JsonObject getMerchantSubscriptions(String publicKey);

	/**
	 * @param recurringDebit A non-optional class, the payload
	 *
	 * @return JsonObject
	 */
	JsonObject doRecurringDebit(RecurringDebit recurringDebit);
}
