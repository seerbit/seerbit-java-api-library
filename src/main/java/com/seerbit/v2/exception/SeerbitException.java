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
package com.seerbit.v2.exception;

import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/** @author Seerbit */
@Getter
@Log4j2
public class SeerbitException extends RuntimeException {

  private SeerbitException() {
    super();
  }

  /** @param message A non-optional String, the error message */
  public SeerbitException(final String message) {
    super(message);
  }

  /** @param response A non-optional CloseableHttpResponse, the http response object */
  public static void handleException(final JsonObject response) {
    String errorMessage;
    String status;

    if (response.has("status") && response.has("data")) {
      status = response.get("status").getAsString();
      if (status.equalsIgnoreCase("error")) {
        throw new SeerbitException(
            response.get("data").getAsJsonObject().get("message").getAsString());
      }
    }

    if (response.has("message") && response.has("error")) {
      errorMessage =
          String.format(
              "%s: %s", response.get("message").toString(), response.get("error").toString());
      log.error(errorMessage);
      throw new SeerbitException(response.get("message").getAsString());
    } else if (response.has("message")) {
      throw new SeerbitException(response.get("message").getAsString());
    }
  }
}
