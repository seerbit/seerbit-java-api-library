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
package com.seerbit.httpclient;

import com.google.gson.JsonObject;
import com.seerbit.service.Service;
import java.util.Map;

/**
 *
 * @author Seerbit
 */
public interface HttpClient {

    /**
     * 
     * @param service
     * @param requestUrl
     * @param params
     * @param token
     * @return json
     */
    JsonObject post(
            Service service,
            String requestUrl, 
            Map<String, Object> params, 
            String token
    );

    /**
     * 
     * @param service
     * @param requestUrl
     * @param params
     * @param token
     * @return json
     */
    JsonObject put(
            Service service, 
            String requestUrl, 
            Map<String, Object> params, 
            String token
    );

    /**
     * 
     * @param service
     * @param requestUrl
     * @param token
     * @return json
     */
    JsonObject get(
            Service service, 
            String requestUrl, 
            String token
    );
}
