package com.seerbit.test;

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
import org.junit.Test;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class CardTest {

    private String token;
    private TransactionAuthenticationImpl authService;
    private Seerbit seerbitApp;
    private Client client;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void doTestCardOperations() {
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
            CardDetail cardDetail = new CardDetail();
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
                    AccountDetail account = new AccountDetail();
                    account.setName("AYODELE PRAISE EREMA");
                    account.setBvn("22141741835");
                    account.setSender("0038721434");
                    account.setSenderBankCode("214");
                    cardDetail.setCvv("100");
                    cardDetail.setNumber("5123450000000008");
                    cardDetail.setExpirymonth("05");
                    cardDetail.setExpiryyear("21");
                    cardDetail.setPin("1234");
                    account.setSenderDateOfBirth("04011984");
                    Card cardPayload = new Card();
                    cardPayload.setFullname("Aminu Grod");
                    cardPayload.setPublicKey(client.getConfig().getPublicKey());
                    cardPayload.setTransactionReference("TQ14611X3213323312PR");
                    cardPayload.setEmail("kolawolesam@gmail.com");
                    cardPayload.setMobile("08030540611");
                    cardPayload.setChannelType("ACCOUNT");
                    cardPayload.setDeviceType("Nokia 3310");
                    cardPayload.setSourceIP("127.0.0.20");
                    cardPayload.setType("3DSECURE");
                    cardPayload.setCurrency("NGN");
                    cardPayload.setDescription("Live Test Card");
                    cardPayload.setCountry("NG");
                    cardPayload.setFee("1.00");
                    cardPayload.setAmount("150.00");
                    cardPayload.setClientAppCode("appl");
                    cardPayload.setCallbackUrl("http://testing-test.surge.sh");
                    cardPayload.setRedirectUrl("http://bc-design.surge.sh");
                    cardPayload.setCard(cardDetail);
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> payload = mapper.convertValue(cardPayload, Map.class);
                    System.out.println("Request Body: \n" + GSON.toJson(payload));
                    json = cardService.doAuthorize(cardPayload);
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
}
