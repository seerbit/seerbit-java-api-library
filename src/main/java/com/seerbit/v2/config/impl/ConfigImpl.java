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
package com.seerbit.v2.config.impl;

import com.seerbit.v2.config.Config;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static com.seerbit.v2.NumericConstants.NIL;

/** @author Seerbit */
@NoArgsConstructor
public class ConfigImpl implements Config {

  /** @return publicKey */
  @Override
  public String getPublicKey() {
    String publicKey = null;

    if (!ConfigImpl.data.isEmpty()) {
      if (Objects.nonNull(ConfigImpl.data.get("publicKey"))) {
        publicKey = String.valueOf(ConfigImpl.data.get("publicKey"));
      }
    }

    return publicKey;
  }

  /** @return privateKey */
  @Override
  public String getPrivateKey() {
    String privateKey = null;

    if (!ConfigImpl.data.isEmpty()) {
      if (Objects.nonNull(ConfigImpl.data.get("privateKey"))) {
        privateKey = String.valueOf(ConfigImpl.data.get("privateKey"));
      }
    }

    return privateKey;
  }

  /**
   * @param key
   * @return java.lang.Object
   */
  @Override
  public Object get(String key) {
    return (Objects.nonNull(ConfigImpl.data.get(key))) ? ConfigImpl.data.get(key) : null;
  }

  /**
   * @param key A non-optional String, the key
   * @param value A non-optional String, the key value
   */
  @Override
  public void put(String key, Object value) {
    ConfigImpl.data.put(key, value);
  }

  /** @return timeout */
  @Override
  public int getTimeout() {
    String timeout;

    if (Objects.nonNull(ConfigImpl.data.get("timeout"))) {
      timeout = String.valueOf(ConfigImpl.data.get("timeout"));
      return Integer.parseInt(timeout);
    }

    return NIL;
  }
}
