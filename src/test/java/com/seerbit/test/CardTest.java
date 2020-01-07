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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.seerbit.Client;
import com.seerbit.Seerbit;
import com.seerbit.SeerbitImpl;
import com.seerbit.enums.EnvironmentEnum;
import com.seerbit.model.Account;
import com.seerbit.model.Transaction;
import com.seerbit.service.TransactionAuthentication;
import com.seerbit.service.CardService;
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
public class CardTest {

    private String token;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void doTestCardOperations() {
        try {
            System.out.println("================== start authentication ==================");
            token = null;
            Seerbit seerbitApp = new SeerbitImpl();
            Client client = new Client();
            client.setTimeout(20);
            client.setAPIBase(seerbitApp.getApiBase());
            client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
            client.setPublicKey("SBTESTPUBK_PjQ5dFOi522L383MlsQYUMAe6cZYviTF");
            client.setPrivateKey("SBTESTSECK_9CDyHxbubCHnqJba5iiIytD5TLyySiHNvBY1UhPX");
            client.setTimeout(20);
            com.seerbit.model.Card card = new com.seerbit.model.Card();
            TransactionAuthentication authService = new TransactionAuthentication(client);
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
                    System.out.println("================== start initiate card ==================");
                    CardService cardService = new CardService(client, token);
                    Account account = new Account();
                    account.setName("AYODELE PRAISE EREMA");
                    account.setBvn("22141741835");
                    account.setSender("0038721434");
                    account.setSenderBankCode("214");
                    card.setCvv("100");
                    card.setNumber("5061040201593455366");
                    card.setExpirymonth("05");
                    card.setExpiryyear("21");
                    card.setPin("8319");
                    account.setSenderDateOfBirth("04011984");
                    Map<String, Object> cardPayload = new HashMap<>(MIN_VALUE.getValue());
                    cardPayload.put("fullname", "Aminu Grod");
                    cardPayload.put("public_key", client.getConfig().getPublicKey());
                    cardPayload.put("tranref", "TQ14611X32131PR");
                    cardPayload.put("email", "kolawolesam@gmail.com");
                    cardPayload.put("mobile", "08030540611");
                    cardPayload.put("channelType", "ACCOUNT");
                    cardPayload.put("deviceType", "Nokia 3310");
                    cardPayload.put("sourceIP", "127.0.0.20");
                    cardPayload.put("type", "3DSECURE");
                    cardPayload.put("currency", "NGN");
                    cardPayload.put("description", "Pilot Test Account");
                    cardPayload.put("country", "NG");
                    cardPayload.put("fee", "1.00");
                    cardPayload.put("amount", "150.00");
                    cardPayload.put("clientappcode", "appl");
                    cardPayload.put("callbackurl", "http://testing-test.surge.sh");
                    cardPayload.put("redirecturl", "http://bc-design.surge.sh");
                    cardPayload.put("account", account);
                    cardPayload.put("card", card);
                    System.out.println("Request Body: " + GSON.toJson(cardPayload));
                    json = cardService.doAuthorize(cardPayload);
                    System.out.print("Initiate Card Response: ");
                    System.out.println(json);
                    System.out.println("================== end initiate card ==================");
                    System.out.println();
                    System.out.println();
                    System.out.println("================== start validate otp ==================");
                    Map<String, Object> otpPayload = new HashMap<>(MIN_VALUE.getValue());
                    Transaction transaction = new Transaction();
                    transaction.setOtp("458504");
                    transaction.setLinkingReference("F771181731576846159311");
                    otpPayload.put("transaction", transaction);
                    json = cardService.doValidateOTP(otpPayload);
                    System.out.println("Validate OTP Request: " + GSON.toJson(otpPayload));
                    System.out.print("Validate OTP Response: ");
                    System.out.println(json);
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
