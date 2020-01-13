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
package com.seerbit.config;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Seerbit
 */
public interface Config {

    static final int NIL = 0;
    static final int INITIAL_CAPACITY = 1;
    Map<String, Object> data = new HashMap<>(INITIAL_CAPACITY);

    /**
     *
     * @param key
     * @param value
     */
    void put(final String key, final Object value);

    int getTimeout();

    /**
     *
     * @param param
     * @return java.lang.Object
     */
    Object get(final String param);

    /**
     *
     * @return publicKey
     */
    String getPublicKey();

    /**
     *
     * @return privateKey
     */
    String getPrivateKey();

    /**
     *
     * @return hashedString
     */
    String getHash();

    /**
     *
     * @return clientSecret
     */
    String getClientSecret();

    /**
     *
     * @return password
     */
    String getPassword();

    /**
     *
     * @return userName
     */
    String getUsername();
}
