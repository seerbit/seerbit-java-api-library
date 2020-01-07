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
import com.seerbit.model.Evidence;
import com.seerbit.model.Image;
import com.seerbit.service.DisputeService;
import com.seerbit.service.MerchantAuthentication;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
public class DisputeTest {

    private String token;
    private List<Evidence> evidenceList;
    private List<Image> imageList;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Test
    public void doTestDisputeOperations() {
        try {
            System.out.println("================== start authentication ==================");
            token = null;
            evidenceList = new ArrayList<>(MIN_VALUE.getValue());
            imageList = new ArrayList<>(MIN_VALUE.getValue());
            Seerbit seerbitApp = new SeerbitImpl();
            Client client = new Client();
            client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
            client.setUsername("victorighalo@gmail.com");
            client.setPassword("WISdom@1");
            client.setTimeout(20);
            client.setAPIBase(seerbitApp.getApiBase());
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
                    System.out.println("================== start add dispute ==================");
                    DisputeService disputeService = new DisputeService(client, token);
                    Image images = new Image();
                    images.setImage("image.png");
                    imageList.add(images);
                    Evidence evidence = Evidence.builder().message(token).images(imageList).build();
                    evidenceList.add(evidence);
                    Map<String, Object> disputePayload = new HashMap<>(MIN_VALUE.getValue());
                    disputePayload.put("customer_email", "tosyngy@rocketmail.com");
                    disputePayload.put("transaction_ref", "PUBK_PJQ5D1577092649489");
                    disputePayload.put("amount", "10");
                    disputePayload.put("evidence", evidenceList);
                    json = disputeService.add("00000013", disputePayload);
                    System.out.println("Add dispute request: " + GSON.toJson(disputePayload));
                    System.out.print("Add dispute response: ");
                    System.out.println(json.toString());
                    System.out.println("================== end add dispute ==================");
                    System.out.println();
                    System.out.println();
                    System.out.println("================= start get all dispute =================");
                    json = disputeService.getAllDispute("00000013", 0, 10);
                    System.out.print("All dispute response: ");
                    System.out.println(json.toString());
                    System.out.println("================== end get all dispute ==================");
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
