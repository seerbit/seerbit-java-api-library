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
package com.seerbit.v2.enums;

/** @author Seerbit */
public enum HttpHeaderEnum {
  CONTENT_TYPE("Content-Type", "application/json");

  private final String param;
  private final String value;

  /**
   * @param param A non-optional String, the http header param
   * @param value A non-optional String, the http header param value
   */
  HttpHeaderEnum(final String param, final String value) {
    this.param = param;
    this.value = value;
  }

  /** @return param */
  public String getParam() {
    return param;
  }

  /** @return value */
  public String getValue() {
    return value;
  }
}
