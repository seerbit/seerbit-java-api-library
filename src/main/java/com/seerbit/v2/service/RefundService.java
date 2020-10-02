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
import com.seerbit.v2.model.Refund;

/** @author Seerbit */
public interface RefundService {

  /**
   * @param businessId A non-optional String, the business Id
   * @param page A non-optional int, the initial page
   * @param size A non-optional int, the page size
   * @return JsonObject
   */
  JsonObject getAllRefund(String businessId, int page, int size);

  /**
   * @param businessId A non-optional String, the business Id
   * @param refundId A non-optional String, the refund Id
   * @return JsonObject
   */
  JsonObject getRefund(String businessId, String refundId);

  /**
   * @param businessId A non-optional String, the business Id
   * @param payload A non-optional class, the payload
   * @return JsonObject
   */
  JsonObject doRefund(String businessId, Refund payload);
}
