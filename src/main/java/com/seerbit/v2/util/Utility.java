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
package com.seerbit.v2.util;

import com.seerbit.v2.Client;
import com.seerbit.v2.exception.SeerbitException;

import java.util.Objects;

/**
 * @author Seerbit
 */
public class Utility {

	/**
	 * @param client A non-optional class, the client
	 */
	public static void nonNull(Client client) {

		if (Objects.isNull(client)) {
			throw new SeerbitException("Client class cannot be null");
		}

	}
}
