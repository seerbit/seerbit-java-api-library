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
import com.seerbit.model.Dispute;
import com.seerbit.model.Evidence;
import com.seerbit.model.Image;
import com.seerbit.service.DisputeService;
import com.seerbit.service.impl.DisputeServiceImpl;
import com.seerbit.service.impl.MerchantAuthenticationImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class DisputeTest implements NumericConstants {

    private String token;
    private MerchantAuthenticationImpl authService;
    private Seerbit seerbitApp;
    private Client client;
    private List<Evidence> evidenceList;
    private List<Image> imageList;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().setLenient().create();

    @Test
    public void doTestDisputeOperations() {
        try {
            System.out.println("================== start authentication ==================");
            token = null;
            evidenceList = new ArrayList<>(MIN_SIZE);
            imageList = new ArrayList<>(MIN_SIZE);
            seerbitApp = new SeerbitImpl();
            client = new Client();
            client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
            client.setUsername("okechukwu.diei2@mailinator.com");
            client.setPassword("Centric@123");
            client.setTimeout(20);
            client.setAPIBase(seerbitApp.getApiBase());
            authService = new MerchantAuthenticationImpl(client);
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
                    System.out.println("================= start get all dispute =================");
                    DisputeService disputeService = new DisputeServiceImpl(client, token);
                    json = disputeService.getAllDispute("00000063", 0, 10);
                    jsonString = String.format(
                            "all dispute response: \n%s",
                            GSON.toJson(GSON.fromJson(json.toString(), Map.class))
                    );
                    System.out.println(jsonString);
                    System.out.println("================== end get all dispute ==================");
                    System.out.println();
                    System.out.println();
                    System.out.println("================= start get dispute =================");
                    json = disputeService.getDispute("00000063", "15");
                    jsonString = String.format(
                            "Get dispute response: \n%s",
                            GSON.toJson(GSON.fromJson(json.toString(), Map.class))
                    );
                    System.out.println(jsonString);
                    System.out.println("================== end get dispute ==================");
                    System.out.println();
                    System.out.println();
                    System.out.println("================= start update dispute =================");
                    Image images = new Image();
                    images.setImage("image.png");
                    imageList.add(images);
                    Evidence evidence = Evidence.builder()
                                                .images(imageList)
                                                .message("ok")
                                                .messageSender("merchant")
                                                .build();
                    evidenceList.add(evidence);
                    Dispute disputePayload = Dispute.builder()
                                                    .evidence(evidenceList)
                                                    .resolution("decline")
                                                    .resolutionImage("")
                                                    .merchantId("00000063")
                                                    .amount("102.51")
                                                    .customerEmail("tosyngy@rocketmail.com")
                                                    .build();
                    json = disputeService.doUpdateDispute("00000063", "bf3e2fee9bda491199b15a661cf31713", disputePayload);
                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, Object> payload = mapper.convertValue(disputePayload, Map.class);
                    System.out.println("Update dispute request: \n" + GSON.toJson(payload));
                    jsonString = String.format(
                            "update dispute response: \n%s",
                            GSON.toJson(GSON.fromJson(json.toString(), Map.class))
                    );
                    System.out.println(jsonString);
                    System.out.println("================== end update dispute ==================");
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
