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

import com.seerbit.util.SHA256;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Seerbit
 */
public class ConfigImpl implements Config {
    
    public ConfigImpl() {}

    /**
     *
     * @param params
     */
    public ConfigImpl(Map<String, Object> params) {
        if (Objects.nonNull(params)) {
            params.forEach((key, value) -> {
                ConfigImpl.data.put(key, value);
            });
        }
    }

    /**
     *
     * @return publicKey
     */
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

    /**
     *
     * @return privateKey
     */
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
     *
     * @param key
     * @return java.lang.Object
     */
    @Override
    public Object get(String key) {
        return (Objects.nonNull(ConfigImpl.data.get(key))) ? ConfigImpl.data.get(key) : null;
    }

    /**
     *
     * @return clientSecret
     */
    @Override
    public String getClientSecret() {
        String clientSecret = SHA256.doHashSHA256(this.getPublicKey(), this.getPrivateKey());
        return clientSecret;
    }

    /**
     *
     * @param key
     * @param value
     */
    @Override
    public void put(String key, Object value) {
        ConfigImpl.data.put(key, value);
    }

    /**
     *
     * @return hashedString
     */
    @Override
    public String getHash() {
        return SHA256.doHashSHA256(this.getPublicKey(), this.getPrivateKey());
    }

    /**
     *
     * @return password
     */
    @Override
    public String getPassword() {
        if (Objects.nonNull(ConfigImpl.data.get("password"))) {
            return String.valueOf(ConfigImpl.data.get("password"));
        }
        return null;
    }

    /**
     *
     * @return userName
     */
    @Override
    public String getUsername() {
        if (Objects.nonNull(ConfigImpl.data.get("userName"))) {
            return String.valueOf(ConfigImpl.data.get("userName"));
        }
        return null;
    }

    /**
     *
     * @return timeout
     */
    @Override
    public int getTimeout() {
        if (Objects.nonNull(ConfigImpl.data.get("timeout"))) {
            String timeout = String.valueOf(ConfigImpl.data.get("timeout"));
            return Integer.parseInt(timeout);
        }
        return NIL;
    }

}
