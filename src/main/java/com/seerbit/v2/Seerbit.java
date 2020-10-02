/*
 * Copyright (C) 2020 Seerbit
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.seerbit.v2;

/** @author Seerbit */
public interface Seerbit {

  /** @return apiBase */
  default String getApiBase() {
    return ClientConstants.LIVE_API_BASE;
  }

  /** @return apiBasePilot */
  default String getApiBaseTest() {
    return ClientConstants.TEST_API_BASE;
  }

  /** @return apiVersion */
  String getApiVersion();

  /** @param apiVersion A non-optional String, the api version */
  void setApiVersion(final String apiVersion);
}
