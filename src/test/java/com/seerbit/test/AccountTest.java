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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.seerbit.Client;
import com.seerbit.NumericConstants;
import com.seerbit.Seerbit;
import com.seerbit.impl.SeerbitImpl;
import com.seerbit.enums.EnvironmentEnum;
import com.seerbit.model.Account;
import com.seerbit.model.AccountDetail;
import com.seerbit.model.OTP;
import com.seerbit.service.AccountService;
import com.seerbit.service.impl.AccountServiceImpl;
import com.seerbit.service.impl.TransactionAuthenticationImpl;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class AccountTest implements NumericConstants {

    private String token;
    private TransactionAuthenticationImpl authService;
    private Seerbit seerbitApp;
    private Client client;

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
            client.setPublicKey("SBTESTPUBK_9VpeA7NPZKK0bfrt4m8cAJAvJ6LW2Zrd");
            client.setPrivateKey("SBTESTSECK_VsCUvHw88dWDx0j3SCV3Gtkz7dwXuYHbgWQ3iqLg");
            client.setTimeout(20);
            com.seerbit.model.CardDetail card = new com.seerbit.model.CardDetail();
            authService = new TransactionAuthenticationImpl(client);
            JsonObject json = authService.doAuth();
            String jsonString = String.format(
                    "auth response: \n%s",
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
                    Account accountPayload = new Account();
                    accountPayload.setFullname("Brown Steve");
                    accountPayload.setEmail("sbtest@mailinator.com");
                    accountPayload.setMobile("08067238238");
                    accountPayload.setPublicKey(client.getConfig().getPublicKey());
                    accountPayload.setChannelType("ACCOUNT");
                    accountPayload.setDeviceType("nokia 33");
                    accountPayload.setSourceIP("1.0.1.0");
                    accountPayload.setType("3DSECURE");
                    accountPayload.setReference("sijeij3847748e8");
                    accountPayload.setCurrency("NGN");
                    accountPayload.setDescription("pilot test account");
                    accountPayload.setCountry("NG");
                    accountPayload.setFee("1.00");
                    accountPayload.setAmount("100.00");
                    accountPayload.setClientAppCode("app1");
                    accountPayload.setCallbackUrl("http://testing-test.surge.sh");
                    accountPayload.setRedirectUrl("http://bc-design.surge.sh");
                    AccountDetail account = new AccountDetail();
                    account.setSender("1234567890");
                    account.setName("Moses Victor Esther");
                    account.setSenderBankCode("215");
                    account.setSenderDateOfBirth("04011984");
                    account.setBvn("12341741835");
                    accountPayload.setAccount(account);
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> payload = mapper.convertValue(accountPayload, Map.class);
                    System.out.println("Request Body: \n" + GSON.toJson(payload));
                    json = accountService.doAuthorize(accountPayload);
                    jsonString = String.format(
                            "Initiate account response: \n%s",
                            GSON.toJson(GSON.fromJson(json.toString(), Map.class))
                    );
                    System.out.println(jsonString);
                    System.out.println("================== end initiate account ==================");
                    System.out.println();
                    System.out.println();
                    System.out.println("================== start validate account ==================");
                    OTP validatePayload = new OTP();
                    validatePayload.setLinkingReference("F582528181578659061354");
                    validatePayload.setOtp("123456");
                    json = accountService.doValidateTransaction(validatePayload);
                    payload = mapper.convertValue(validatePayload, Map.class);
                    System.out.println("Validate Account Request: \n" + GSON.toJson(payload));
                    jsonString = String.format(
                            "validate account response: \n%s",
                            GSON.toJson(GSON.fromJson(json.toString(), Map.class))
                    );
                    System.out.println(jsonString);
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
