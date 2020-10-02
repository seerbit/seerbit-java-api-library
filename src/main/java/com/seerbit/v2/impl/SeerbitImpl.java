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
package com.seerbit.v2.impl;

import com.seerbit.v2.Seerbit;

import java.util.Objects;

/** @author Seerbit */
public class SeerbitImpl implements Seerbit {

  private static String apiVersion = null;

  /** @return apiVersion */
  @Override
  public String getApiVersion() {
    String message = "API version is null. Please set the API Version";
    Objects.requireNonNull(SeerbitImpl.apiVersion, message);
    return SeerbitImpl.apiVersion;
  }

  /** @param apiVersion A non-optional String, the api version */
  @Override
  public void setApiVersion(final String apiVersion) {
    SeerbitImpl.apiVersion = apiVersion;
  }
}
