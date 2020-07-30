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
package com.seerbit.v2;

import com.seerbit.v2.config.Config;
import com.seerbit.v2.config.impl.ConfigImpl;
import com.seerbit.v2.enums.EnvironmentEnum;
import com.seerbit.v2.exception.SeerbitException;

import java.util.Objects;

import static com.seerbit.v2.enums.EnvironmentEnum.LIVE;
import static com.seerbit.v2.enums.EnvironmentEnum.TEST;

/**
 * @author Seerbit
 */
public class Client {

	private Config config;

	/**
	 * default constructor
	 */
	public Client() {
		config = new ConfigImpl();
		config.put("version", ClientConstants.VERSION_TWO);
		config.put("authenticationScheme", "Bearer ");
	}

	/**
	 * @param version A non-optional String, the api version
	 */
	public Client(final String version) {
		config = new ConfigImpl();
		config.put("authenticationScheme", "Bearer ");

		if ("1.0.1".equals(version)) {
			config.put("version", ClientConstants.VERSION_TWO);
		} else {
			throw new SeerbitException("Version must be \"1.0.1\"");
		}

	}

	/**
	 * @return config
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * @param config A non-optional class, the SDK config
	 */
	public void setConfig(Config config) {
		this.config = config;
	}

	/**
	 * @param publicKey A non-optional String, the merchant public key
	 */
	public void setPublicKey(final String publicKey) {
		config.put("publicKey", publicKey);
	}

	/**
	 * @param privateKey A non-optional String, the merchant private key
	 */
	public void setPrivateKey(final String privateKey) {
		config.put("privateKey", privateKey);
	}

	/**
	 * @return publicKey
	 */
	public String getPublicKey() {
		return String.valueOf(config.get("publicKey"));
	}

	/**
	 * @return privateKey
	 */
	public String getPrivateKey() {
		return String.valueOf(config.get("privateKey"));
	}

	/**
	 * @return version
	 */
	public String getVersion() {
		return String.valueOf(config.get("version"));
	}

	/**
	 * @param environment A non-optional String, the client environment
	 */
	public void setEnvironment(String environment) {
		String errorMessage;

		if (environment.equalsIgnoreCase(EnvironmentEnum.LIVE.getEnvironment())) {
			config.put("environment", EnvironmentEnum.LIVE.getEnvironment());
			config.put("apiBase", ClientConstants.LIVE_API_BASE);
		} else if (environment.equalsIgnoreCase(TEST.getEnvironment())) {
			config.put("environment", TEST.getEnvironment());
			config.put("apiBase", ClientConstants.TEST_API_BASE);
		} else {
			errorMessage = String.format(
				"This environment does not exist, use \"%s\" or \"%s\"", LIVE.getEnvironment(), TEST.getEnvironment()
			);
			throw new SeerbitException(errorMessage);
		}
	}

	/**
	 * @return environment
	 */
	public String getEnvironment() {
		String environment;

		environment = null;

		if (Objects.nonNull(config.get("environment"))) {
			environment = String.valueOf(config.get("environment"));
		}

		return environment;
	}

	/**
	 * @param apiBase A non-optional String, the api base
	 */
	public void setApiBase(final String apiBase) {
		config.put("apiBase", apiBase);
	}

	/**
	 * @return apiBase
	 */
	public String getApiBase() {
		return String.valueOf(config.get("apiBase"));
	}

	/**
	 * @param timeout A non-optional int, the http request timeout
	 */
	public void setTimeout(final int timeout) {
		config.put("timeout", timeout);
	}

	/**
	 *
	 * @return authenticationScheme A non-optional String, the authentication scheme
	 */
	public String getAuthenticationScheme() {
		return String.valueOf(config.get("authenticationScheme"));
	}

	/**
	 * @param authenticationScheme A non-optional String, the authentication scheme
	 */
	public void setAuthenticationScheme(final String authenticationScheme) {

		switch(authenticationScheme.toLowerCase()) {
			case "bearer ":
			case "basic ":
				config.put("authenticationScheme", authenticationScheme);
			default:
				throw new SeerbitException("Invalid authentication scheme");
		}

	}
}
