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
package com.seerbit.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.seerbit.Client;
import com.seerbit.Seerbit;
import com.seerbit.SeerbitImpl;
import com.seerbit.enums.EnvironmentEnum;
import com.seerbit.model.Webhook;
import com.seerbit.service.MerchantAuthentication;
import com.seerbit.service.WebhookService;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import static com.seerbit.enums.NumericConstantsEnum.MIN_VALUE;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class WebhookTest {
    private String token;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void doWebhookTestOperations() {
        try {
            System.out.println("================== start authentication ==================");
            token = null;
            Seerbit seerbitApp = new SeerbitImpl();
            Client client = new Client();
            client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
            client.setUsername("victorighalo@gmail.com");
            client.setPassword("WISdom@1");
            client.setAPIBase(seerbitApp.getApiBase());
            client.setTimeout(20);
            MerchantAuthentication authService = new MerchantAuthentication(client);
            JsonObject json = authService.doAuth();
            String jsonString = String.format(
                    "auth response: %s", 
                    GSON.toJson(GSON.fromJson(json.toString(), Map.class))
            );
            System.out.println(jsonString);
            System.out.println("================== end authentication ==================");
            System.out.println();
            System.out.println();
            if (Objects.nonNull(json)) {
                token = authService.getToken();
                if (Objects.nonNull(token)) {
                    System.out.println("================== start update webhook url ==================");
                    WebhookService webhookService = new WebhookService(client, token);
                    Map<String, Object> webhookPayload = new HashMap<>(MIN_VALUE.getValue());
                    Webhook webhook = new Webhook();
                    webhook.setUrl("www.webhook.com");
                    webhook.setActive(true);
                    webhookPayload.put("webhook", webhook);
                    json = webhookService.doUpdateWebhookURL("00000001", webhookPayload);
                    System.out.println("Webhook Request: " + GSON.toJson(webhookPayload));
                    System.out.print("Webhook Response: ");
                    System.out.println(json.toString());
                    System.out.println("================== end update webhook url ==================");
                    System.out.println();
                    System.out.println();
                } else {
                    System.out.println("Authentication Failed");
                }
            }
        } catch (JsonSyntaxException exception) {
            log.error(exception.getMessage());
        }
    }
}
