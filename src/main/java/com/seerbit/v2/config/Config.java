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
package com.seerbit.v2.config;

import java.util.HashMap;
import java.util.Map;

import static com.seerbit.v2.NumericConstants.MIN_SIZE;

/** @author Seerbit */
public interface Config {

  Map<String, Object> data = new HashMap<>(MIN_SIZE);

  /**
   * @param key A non-optional String, the key
   * @param value A non-optional String, the key value
   */
  void put(final String key, final Object value);

  int getTimeout();

  /**
   * @param param A non-optional String, the param
   * @return java.lang.Object
   */
  Object get(final String param);

  /** @return publicKey */
  String getPublicKey();

  /** @return privateKey */
  String getPrivateKey();
}
