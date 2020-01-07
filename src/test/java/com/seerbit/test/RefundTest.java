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
import com.seerbit.service.MerchantAuthentication;
import com.seerbit.service.RefundService;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

import static com.seerbit.enums.NumericConstantsEnum.MIN_VALUE;
import static com.seerbit.enums.RefundTypeEnum.PARTIAL_REFUND;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class RefundTest {

    private String token;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void doRefundTestOperations() {
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
                    System.out.println("================== start do refund ==================");
                    RefundService refundService = new RefundService(client, token);
                    Map<String, Object> refundPayload = new HashMap<>(MIN_VALUE.getValue());
                    refundPayload.put("type", PARTIAL_REFUND);
                    refundPayload.put("amount", "10");
                    refundPayload.put("transactionRef", "PUBK_PJQ5D1577092649489");
                    refundPayload.put("description", "Buyer genuinely did not receive value");
                    json = refundService.doRefund("00000013", refundPayload);
                    System.out.println("Refund Request: " + GSON.toJson(refundPayload));
                    System.out.print("Refund Response: ");
                    System.out.println(json.toString());
                    System.out.println("================== end do refund ==================");
                    System.out.println();
                    System.out.println();
                    System.out.println("================== start get all refund ==================");
                    json = refundService.getAllRefund("00000013", 0, 10);
                    System.out.print("All Refund Response: ");
                    System.out.println(json.toString());
                    System.out.println("================== end get all refund ==================");
                    System.out.println();
                    System.out.println();
                    System.out.println("================== start get refund ==================");
                    json = refundService.getRefund("00000013", "77d000d9b9f44400a6536ea03c65279d");
                    System.out.println(json.toString());
                    System.out.println("================== end get refund ==================");
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
