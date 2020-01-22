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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.seerbit.Client;
import com.seerbit.NumericConstants;
import com.seerbit.Seerbit;
import com.seerbit.impl.SeerbitImpl;
import com.seerbit.enums.EnvironmentEnum;
import com.seerbit.service.RefundService;
import com.seerbit.service.impl.MerchantAuthenticationImpl;
import com.seerbit.service.impl.RefundServiceImpl;
import java.util.Map;
import java.util.Objects;
import lombok.extern.log4j.Log4j2;

/**
 *
 * @author Seerbit
 */
@Log4j2
public class RefundDemo implements NumericConstants {

    private static String token;
    private static Seerbit seerbitApp;
    private static Client client;
    private static MerchantAuthenticationImpl authService;

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static void doRefundDemo() {
        try {
            System.out.println("================== start authentication ==================");
            token = null;
            
            seerbitApp = new SeerbitImpl();
            client = new Client();
            client.setEnvironment(EnvironmentEnum.LIVE.getEnvironment());
            client.setUsername("username");
            client.setPassword("password");
            client.setAPIBase(seerbitApp.getApiBase());
            client.setTimeout(20);
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
                    
                    System.out.println("================== start get all refund ==================");
                    RefundService refundService = new RefundServiceImpl(client, token);
                    json = refundService.getAllRefund("00000001", 0, 10);
                    System.out.print("All Refund Response: ");
                    System.out.println(json.toString());
                    System.out.println("================== end get all refund ==================");
                    System.out.println();
                    System.out.println();
                    
                    System.out.println("================== start get refund ==================");
                    json = refundService.getRefund("00000001", "01");
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
    
    public static void main(String... args) {
        doRefundDemo();
    }
}
