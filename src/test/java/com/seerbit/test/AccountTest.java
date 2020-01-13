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
import com.seerbit.impl.SeerbitImpl;
import com.seerbit.enums.EnvironmentEnum;
import com.seerbit.model.Account;
import com.seerbit.service.AccountService;
import com.seerbit.service.impl.AccountServiceImpl;
import com.seerbit.service.impl.TransactionAuthenticationImpl;
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
public class AccountTest {

    private String token;
    private TransactionAuthenticationImpl authService;
    private Seerbit seerbitApp;
    private Client client ;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void doTestAccountOperations() {
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
            com.seerbit.model.Card card = new com.seerbit.model.Card();
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
                    System.out.println("================== start initiate account ==================");
                    AccountService accountService = new AccountServiceImpl(client, token);
                    Map<String, Object> accountPayload = new HashMap<>(MIN_VALUE.getValue());
                    accountPayload.put("fullname", "Brown Steve");
                    accountPayload.put("email", "sbtest@mailinator.com");
                    accountPayload.put("mobile", "08067238238");
                    accountPayload.put("public_key", client.getConfig().getPublicKey());
                    accountPayload.put("channelType", "ACCOUNT");
                    accountPayload.put("deviceType", "nokia 33");
                    accountPayload.put("sourceIP", "1.0.1.0");
                    accountPayload.put("type", "3DSECURE");
                    accountPayload.put("reference", "sijeij3847748e8");
                    accountPayload.put("currency", "NGN");
                    accountPayload.put("description", "pilot test account");
                    accountPayload.put("country", "NG");
                    accountPayload.put("fee", "1.00");
                    accountPayload.put("amount", "100.00");
                    accountPayload.put("clientappcode", "app1");
                    accountPayload.put("callbackurl", "http://testing-test.surge.sh");
                    accountPayload.put("redirecturl", "http://bc-design.surge.sh");
                    Account account = new Account();
                    account.setSender("1234567890");
                    account.setName("Moses Victor Esther");
                    account.setSenderBankCode("215");
                    account.setSenderDateOfBirth("04011984");
                    account.setBvn("12341741835");
                    accountPayload.put("account", account);
                    System.out.println("Request Body: " + GSON.toJson(accountPayload));
                    json = accountService.doAuthorize(accountPayload);
                    System.out.print("Initiate Account Response: ");
                    System.out.println(json);
                    System.out.println("================== end initiate account ==================");
                    System.out.println();
                    System.out.println();
                    System.out.println("================== start validate account ==================");
                    Map<String, Object> validatePayload = new HashMap<>(MIN_VALUE.getValue());
                    validatePayload.put("linkingreference", "F582528181578659061354");
                    validatePayload.put("otp","123456");
                    json = accountService.doValidateTransaction(validatePayload);
                    System.out.println("Validate Account Request: " + GSON.toJson(validatePayload));
                    System.out.print("Validate Account Response: ");
                    System.out.println(json);
                    System.out.println("================== end validate account ==================");
                    System.out.println();
                    System.out.println();
                }
            }
        } catch (JsonSyntaxException exception) {
            log.error(exception.getMessage());
        }
    }
}
