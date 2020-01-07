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

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.seerbit.Client;
import com.seerbit.config.Config;
import com.seerbit.exception.ConnectionException;
import com.seerbit.exception.SeerbitException;
import com.seerbit.service.Service;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import static com.seerbit.enums.HttpHeaderEnum.CONTENT_TYPE;
import static com.seerbit.enums.NumericConstantsEnum.MIN_SIZE;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class HttpClientImpl implements HttpClient {

    private static final CloseableHttpClient HTTP_CLIENT = HttpClients.createDefault();

    private final Gson GSON;

    private HttpPost postRequest;
    private HttpPut putRequest;
    private HttpGet getRequest;
    private CloseableHttpResponse response;
    private JsonObject json;
    private int statusCode;

    /**
     *
     * default constructor
     */
    public HttpClientImpl() {
        statusCode = -20;
        GSON = new Gson();
        json = new JsonObject();
    }

    /**
     *
     * @param service
     * @param url
     * @param params
     * @param token
     * @return json
     */
    @Override
    public JsonObject post(
            Service service,
            String url,
            Map<String, Object> params,
            String token
    ) {
        try {
            String contentTypeParam = CONTENT_TYPE.getParam();
            String contentTypeValue = CONTENT_TYPE.getValue();
            Client client = service.getClient();
            Config config = client.getConfig();
            String requestBody = GSON.toJson(params);
            postRequest = new HttpPost(url);
            postRequest.setHeader(contentTypeParam, contentTypeValue);
            postRequest.setHeader("Request-Timeout", String.valueOf(config.getTimeout()));
            StringEntity entity = new StringEntity(requestBody);
            postRequest.setEntity(entity);
            if (service.isTokenRequired()) {
                if (Objects.isNull(token) || token.length() < MIN_SIZE.getValue()) {
                    throw new ConnectionException("Please provide an authentication token.");
                } else {
                    String authenticationString = String.format("Bearer %s", token);
                    postRequest.setHeader("Authorization", authenticationString);
                }
            }
            log.info("Request headers to Seerbit: " + Arrays.toString(postRequest.getAllHeaders()));
            Object[] result = this.request(postRequest);
            response = (CloseableHttpResponse) result[0];
            statusCode = Integer.parseInt(String.valueOf(result[1]));
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info(String.format("JsonResponse: %s", responseBody));
            if (statusCode < 200 && statusCode > 299 && Objects.nonNull(response)) {
                SeerbitException.handleException(response);
            }
            json = JsonParser.parseString(responseBody).getAsJsonObject();
        } catch (IOException | ParseException exception) {
            log.error(exception.getMessage());
        }
        return json;
    }

    /**
     *
     * @param service
     * @param url
     * @param params
     * @param token
     * @return json
     */
    @Override
    public JsonObject put(
            Service service,
            String url,
            Map<String, Object> params,
            String token
    ) {
        try {
            String contentTypeParam = CONTENT_TYPE.getParam();
            String contentTypeValue = CONTENT_TYPE.getValue();
            Client client = service.getClient();
            Config config = client.getConfig();
            String requestBody = GSON.toJson(params);
            putRequest = new HttpPut(url);
            putRequest.setHeader(contentTypeParam, contentTypeValue);
            putRequest.setHeader("Request-Timeout", String.valueOf(config.getTimeout()));
            StringEntity entity = new StringEntity(requestBody);
            putRequest.setEntity(entity);
            if (service.isTokenRequired()) {
                if (Objects.isNull(token) || token.length() < MIN_SIZE.getValue()) {
                    throw new ConnectionException("Please provide an authentication token.");
                } else {
                    String authenticationString = String.format("Bearer %s", token);
                    putRequest.setHeader("Authorization", authenticationString);
                }
            }
            log.info("Request headers to Seerbit: " + Arrays.toString(putRequest.getAllHeaders()));
            Object[] result = this.request(putRequest);
            response = (CloseableHttpResponse) result[0];
            statusCode = Integer.parseInt(String.valueOf(result[1]));
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info(String.format("JsonResponse: %s", responseBody));
            if (statusCode < 200 && statusCode > 299 && Objects.nonNull(response)) {
                SeerbitException.handleException(response);
            }
            json = JsonParser.parseString(responseBody).getAsJsonObject();
        } catch (IOException | ParseException exception) {
            log.error(exception.getMessage());
        }
        return json;
    }

    /**
     *
     * @param service
     * @param url
     * @param token
     * @return json
     */
    @Override
    public JsonObject get(
            Service service,
            String url,
            String token
    ) {
        try {
            Client client = service.getClient();
            Config config = client.getConfig();
            getRequest = new HttpGet(url);
            getRequest.setHeader("Request-Timeout", String.valueOf(config.getTimeout()));
            if (service.isTokenRequired()) {
                if (Objects.isNull(token) || token.length() < MIN_SIZE.getValue()) {
                    throw new ConnectionException("Please provide an authentication token.");
                } else {
                    String authenticationString = String.format("Bearer %s", token);
                    getRequest.setHeader("Authorization", authenticationString);
                }
            }
            log.info("Request headers to Seerbit: " + Arrays.toString(getRequest.getAllHeaders()));
            Object[] result = this.request(getRequest);
            response = (CloseableHttpResponse) result[0];
            statusCode = Integer.parseInt(String.valueOf(result[1]));
            String responseBody = EntityUtils.toString(response.getEntity());
            log.info(String.format("JsonResponse: %s", responseBody));
            if (statusCode < 200 && statusCode > 299 && Objects.nonNull(response)) {
                SeerbitException.handleException(response);
            }
            json = JsonParser.parseString(responseBody).getAsJsonObject();
        } catch (IOException | ParseException exception) {
            log.error(exception.getMessage());
        }
        return json;
    }

    /**
     *
     * @param postRequest
     * @return json
     */
    protected Object[] request(final HttpPost postRequest) {
        try {
            response = HTTP_CLIENT.execute(postRequest);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
        return new Object[]{response, statusCode};
    }

    /**
     *
     * @param putRequest
     * @return json
     */
    protected Object[] request(final HttpPut putRequest) {
        try {
            response = HTTP_CLIENT.execute(putRequest);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
        return new Object[]{response, statusCode};
    }

    /**
     *
     * @param getRequest
     * @return json
     */
    protected Object[] request(final HttpGet getRequest) {
        try {
            response = HTTP_CLIENT.execute(getRequest);
            statusCode = response.getStatusLine().getStatusCode();
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
        return new Object[]{response, statusCode};
    }

}
