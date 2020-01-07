/*
 * Copyright (C) 2019 Seerbit
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
package com.seerbit.exception;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Seerbit
 */
@Getter
@Log4j2
public class SeerbitException extends RuntimeException {

    protected String status;
    protected String errorType;
    protected String errorCode;
    protected int code;

    public SeerbitException() {
        super();
    }

    /**
     * 
     * @param message 
     */
    public SeerbitException(final String message) {
        super(message);
    }

    /**
     * 
     * @param message
     * @param status
     * @param code
     * @param errorType
     * @param errorCode 
     */
    public SeerbitException(
            final String message,
            final String status,
            final int code,
            final String errorType,
            final String errorCode
    ) {
        super(message);
        this.status = status;
        this.errorType = errorType;
        this.code = code;
        this.errorCode = errorCode;
    }

    /**
     * 
     * @param response 
     */
    public static void handleException(final CloseableHttpResponse response) {
        String responseBody = null;
        try {
            responseBody = EntityUtils.toString(response.getEntity());
        } catch (IOException | ParseException exception) {
            log.error(exception.getMessage());
        }
        JsonObject json = JsonParser.parseString(responseBody).getAsJsonObject();
        if (json.has("message") && json.has("errorCode")) {
            String errorMessage = String.format(
                    "%s: %s",
                    json.get("message").toString(),
                    json.get("errorCode").toString()
            );
            log.error(errorMessage);
            throw new SeerbitException(
                    json.get("message").getAsString(),
                    json.get("status").getAsString(),
                    json.get("code").getAsInt(),
                    json.get("errorType").getAsString(),
                    json.get("errorCode").getAsString()
            );
        }
        throw new SeerbitException();
    }
}
