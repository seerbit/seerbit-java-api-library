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
package com.seerbit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Seerbit
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    @JsonIgnore
    private static final long serialVersionUID = 1L;

    private String fullname;
    private String email;
    private String mobile;
    
    @JsonProperty("public_key")
    private String publicKey;
    
    private AccountDetail account;
    private String channelType;
    private String deviceType;
    private String sourceIP;
    private String type;
    private String reference;
    private String currency;
    private String description;
    private String country;
    private String fee;
    private String amount;
    
    @JsonProperty("clientappcode")
    private String clientAppCode;
    
    @JsonProperty("callbackurl")
    private String callbackUrl;
    
    @JsonProperty("redirecturl")
    private String redirectUrl;
}
