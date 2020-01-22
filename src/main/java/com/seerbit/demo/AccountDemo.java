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
package com.seerbit.demo;

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

/**
 *
 * @author Seerbit
 */
@Log4j2
public class AccountDemo implements NumericConstants {

    private static String token;
    private static TransactionAuthenticationImpl authService;
    private static Seerbit seerbitApp;
    private static Client client;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    public static void doAccountDemo() {
        try {
            System.out.println("================== start authentication ==================");
            token = null;
           
            seerbitApp = new SeerbitImpl();
            client = new Client();
            
            client.setTimeout(20);
            client.setAPIBase(seerbitApp.getApiBase());
            client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
            client.setPublicKey("public_key");
            client.setPrivateKey("private_key");
            client.setTimeout(20);
            
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
                    AccountDetail accountDetail = new AccountDetail();
                    accountDetail.setSender("1234567890");
                    accountDetail.setName("Sambo Eze Ayodele");
                    accountDetail.setSenderBankCode("215");
                    accountDetail.setSenderDateOfBirth("04011984");
                    accountDetail.setBvn("12341741835");
                    
                    Account account = new Account();
                    account.setFullname("Musa Chukwuma Adetutu");
                    account.setEmail("musa@example.com");
                    account.setMobile("08012345678");
                    account.setPublicKey(client.getConfig().getPublicKey());
                    account.setChannelType("account");
                    account.setDeviceType("nokia 33");
                    account.setSourceIP("1.0.1.0");
                    account.setType("3DSECURE");
                    account.setReference("trx0001");
                    account.setCurrency("NGN");
                    account.setDescription("put a description here");
                    account.setCountry("NG");
                    account.setFee("1.00");
                    account.setAmount("100.00");
                    account.setClientAppCode("app1");
                    account.setCallbackUrl("http://www.example.com");
                    account.setRedirectUrl("http://www.example.com");
                    account.setAccount(accountDetail);
                    
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> payload = mapper.convertValue(account, Map.class);
                    System.out.println("Request Body: \n" + GSON.toJson(payload));
                    json = accountService.doAuthorize(account);
                    jsonString = String.format(
                            "Initiate account response: \n%s",
                            GSON.toJson(GSON.fromJson(json.toString(), Map.class))
                    );
                    
                    System.out.println(jsonString);
                    System.out.println("================== end initiate account ==================");
                    System.out.println();
                    System.out.println();
                    
                    System.out.println("================== start validate account ==================");
                    OTP otp = new OTP();
                    otp.setLinkingReference("F582528181578659061354");
                    otp.setOtp("123456");
                    json = accountService.doValidateTransaction(otp);
                    payload = mapper.convertValue(otp, Map.class);
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
    
    public static void main(String... args) {
        doAccountDemo();
    }
}
