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

import com.seerbit.config.Config;
import com.seerbit.config.impl.ConfigImpl;
import com.seerbit.exception.SeerbitException;
import java.util.Objects;

import static com.seerbit.enums.ClientConstantsEnum.LIVE_API_BASE;
import static com.seerbit.enums.ClientConstantsEnum.PILOT_API_BASE;
import static com.seerbit.enums.ClientConstantsEnum.VERSION_ONE;
import static com.seerbit.enums.EnvironmentEnum.LIVE;
import static com.seerbit.enums.EnvironmentEnum.PILOT;

/**
 *
 * @author Seerbit
 */
public class Client {

    private Config config;

    /**
     * 
     * default constructor
     */
    public Client() {
        config = new ConfigImpl();
        config.put("version", VERSION_ONE.getValue());
    }
    
    /**
     * 
     * @param version 
     */
    public Client(final String version) {
        config = new ConfigImpl();
        switch (version) {
            case "1.0.0":
                config.put("version", VERSION_ONE.getValue());
                break;
            default:
                config.put("version", VERSION_ONE.getValue());
                break;
        }    
    }

    /**
     *
     * @return config
     */
    public Config getConfig() {
        return this.config;
    }

    /**
     *
     * @param config
     */
    public void setConfig(Config config) {
        this.config = config;
    }

    /**
     *
     * @param publicKey
     */
    public void setPublicKey(final String publicKey) {
        this.config.put("publicKey", publicKey);
    }

    /**
     *
     * @param privateKey
     */
    public void setPrivateKey(final String privateKey) {
        this.config.put("privateKey", privateKey);
    }

    /**
     *
     * @param userName
     */
    public void setUsername(final String userName) {
        this.config.put("userName", userName);
    }

    /**
     *
     * @param password
     */
    public void setPassword(final String password) {
        this.config.put("password", password);
    }

    /**
     *
     * @return userName
     */
    public String getUsername() {
        return String.valueOf(this.config.get("userName"));
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return String.valueOf(this.config.get("password"));
    }

    /**
     *
     * @return publicKey
     */
    public String getPublicKey() {
        return String.valueOf(this.config.get("publicKey"));
    }

    /**
     *
     * @return privateKey
     */
    public String getPrivateKey() {
        return String.valueOf(this.config.get("privateKey"));
    }

    /**
     *
     * @return version
     */
    public String getVersion() {
        return String.valueOf(this.config.get("version"));
    }

    /**
     *
     * @param environment
     */
    public void setEnvironment(String environment) {
        if (environment.equalsIgnoreCase(LIVE.getEnvironment())) {
            this.config.put("environment", LIVE.getEnvironment());
            this.config.put("apiBase", LIVE_API_BASE.getValue());
        } else if (environment.equalsIgnoreCase(PILOT.getEnvironment())) {
            this.config.put("environment", PILOT.getEnvironment());
            this.config.put("apiBase", PILOT_API_BASE.getValue());
        } else {
            String errorMessage = String.format(
                    "This environment does not exist, use \"%s\" or \"%s\"",
                    LIVE.getEnvironment(),
                    PILOT.getEnvironment()
            );
            throw new SeerbitException(errorMessage);
        }
    }

    /**
     *
     * @return environment
     */
    public String getEnvironment() {
        String environment = null;
        if (Objects.nonNull(this.config.get("environment"))) {
            environment = String.valueOf(this.config.get("environment"));
        }
        return environment;
    }

    /**
     *
     * @param apiBase
     */
    public void setAPIBase(final String apiBase) {
        this.config.put("apiBase", apiBase);
    }

    /**
     *
     * @return apiBase
     */
    public String getAPIBase() {
        return String.valueOf(this.config.get("apiBase"));
    }

    /**
     *
     * @param timeout
     */
    public void setTimeout(final int timeout) {
        this.config.put("timeout", timeout);
    }
}
