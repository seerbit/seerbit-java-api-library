package com.seerbit.demo;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.seerbit.Client;
import com.seerbit.Seerbit;
import com.seerbit.impl.SeerbitImpl;
import com.seerbit.enums.EnvironmentEnum;
import com.seerbit.model.AccountDetail;
import com.seerbit.model.Card;
import com.seerbit.model.CardDetail;
import com.seerbit.model.Transaction;
import com.seerbit.model.TransactionDetail;
import com.seerbit.service.CardService;
import com.seerbit.service.impl.TransactionAuthenticationImpl;
import com.seerbit.service.impl.CardServiceImpl;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class CardDemo {

    private static String token;
    private static TransactionAuthenticationImpl authService;
    private static Seerbit seerbitApp;
    private static Client client;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void doCardDemo() {
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
                    System.out.println("================== start initiate card ==================");
                    CardService cardService = new CardServiceImpl(client, token);
                    
                    AccountDetail accountDetail = new AccountDetail();
                    accountDetail.setName("Sambo Chukwuma Adetutu");
                    accountDetail.setBvn("1234567890");
                    accountDetail.setSender("1234567890");
                    accountDetail.setSenderBankCode("214");
                    accountDetail.setSenderDateOfBirth("04011984");
                      
                    CardDetail cardDetail = new CardDetail();
                    cardDetail.setCvv("100");
                    cardDetail.setNumber("5123450000000008");
                    cardDetail.setExpirymonth("05");
                    cardDetail.setExpiryyear("21");
                    cardDetail.setPin("1234");
                    
                    Card card = new Card();
                    card.setFullname("Aminu Grod");
                    card.setPublicKey(client.getConfig().getPublicKey());
                    card.setTransactionReference("trx0001");
                    card.setEmail("demo@example.com");
                    card.setMobile("08012345678");
                    card.setChannelType("account");
                    card.setDeviceType("Nokia 3310");
                    card.setSourceIP("127.0.0.20");
                    card.setType("3DSECURE");
                    card.setCurrency("NGN");
                    card.setDescription("demo");
                    card.setCountry("NG");
                    card.setFee("1.00");
                    card.setAmount("150.00");
                    card.setClientAppCode("appl");
                    card.setCallbackUrl("http://www.example.com");
                    card.setRedirectUrl("http://www.example.com");
                    card.setCard(cardDetail);
                    
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> payload = mapper.convertValue(card, Map.class);
                    System.out.println("Request Body: \n" + GSON.toJson(payload));
                    
                    json = cardService.doAuthorize(card);
                    jsonString = String.format(
                            "Initiate Card response: \n%s",
                            GSON.toJson(GSON.fromJson(json.toString(), Map.class))
                    );
                    System.out.println(jsonString);
                    
                    System.out.println("================== end initiate card ==================");
                    System.out.println();
                    System.out.println();
                    
                    System.out.println("================== start validate otp ==================");
                    
                    if (Objects.nonNull(json.getAsJsonObject("transaction"))) {
                        TransactionDetail transactionDetail = new TransactionDetail();
                        Transaction transaction = new Transaction();
                        transactionDetail.setOtp("12345");
                        transactionDetail.setLinkingReference(
                                String.valueOf(
                                        json.getAsJsonObject("transaction").get("linkingreference").getAsString()
                                )
                        );
                        transaction.setTransaction(transactionDetail);
                        json = cardService.doValidateOTP(transaction);
                        payload = mapper.convertValue(transaction, Map.class);
                        System.out.println("Validate OTP Request: \n" + GSON.toJson(payload));
                        jsonString = String.format(
                            "Validate OTP response: \n%s",
                            GSON.toJson(GSON.fromJson(json.toString(), Map.class))
                        );
                        System.out.println(jsonString);
                    } else {
                        System.out.println("Null Pointer Exception on JSON Object");
                    }
                    
                    System.out.println("================== end validate otp ==================");
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
    
    public static void main(String... args) {
        doCardDemo();
    }
}
