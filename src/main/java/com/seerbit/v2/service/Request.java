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

import java.util.Map;

/** @author Seerbit */
public interface Request {

  /**
   * @param endpoint A non-optional String, the endpoint url
   * @param token A non-optional String, the auth token
   * @param payload A non-optional Map, the payload
   * @return JsonObject
   */
  JsonObject postRequest(String endpoint, Map<String, Object> payload, String token);

  /**
   * @param endpoint A non-optional String, the endpoint url
   * @param token A non-optional String, the auth token
   * @param payload A non-optional Map, the payload
   * @return JsonObject
   */
  JsonObject putRequest(String endpoint, Map<String, Object> payload, String token);

  /**
   * @param endpoint A non-optional String, the endpoint url
   * @param token A non-optional String, the auth token
   * @return JsonObject
   */
  JsonObject getRequest(String endpoint, String token);
}
