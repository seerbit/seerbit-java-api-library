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
package com.seerbit.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.seerbit.Client;
import com.seerbit.Seerbit;
import com.seerbit.enums.EnvironmentEnum;
import com.seerbit.impl.SeerbitImpl;
import com.seerbit.service.TransactionService;
import com.seerbit.service.impl.TransactionAuthenticationImpl;
import com.seerbit.service.impl.TransactionServiceImpl;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class TransactionTest {

    private String token;
    private TransactionAuthenticationImpl authService;
    private Seerbit seerbitApp;
    private Client client ;
    private String transactionId;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void doTestTransactionStatusOperations() {
        try {
            System.out.println("================== start authentication ==================");
            token = null;
            seerbitApp = new SeerbitImpl();
            client = new Client();
            client.setTimeout(20);
            client.setAPIBase(seerbitApp.getApiBase());
            client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
            client.setPublicKey("SBTESTPUBK_PjQ5dFOi522L383MlsQYUMAe6cZYviTF");
            client.setPrivateKey("SBTESTSECK_9CDyHxbubCHnqJba5iiIytD5TLyySiHNvBY1UhPX");
            client.setTimeout(20);
            authService = new TransactionAuthenticationImpl(client);
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
                    System.out.println("================== start check transaction status ==================");
                    transactionId = "123456789";
                    TransactionService transactionService = new TransactionServiceImpl(client, token);
                    json = transactionService.doValidateTransaction(transactionId);
                    System.out.print("Check Transaction Status Response: ");
                    System.out.println(json.toString());
                    System.out.println("================== end check transaction status ==================");
                }
            }
        } catch (JsonSyntaxException exception) {
            log.error(exception.getMessage());
        }
    }
    
}
