/*
 * Copyright (C) 2020 Seerbit
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
package com.seerbit.v2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/** @author Seerbit */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscription implements Serializable {

  private static final long serialVersionUID = 1L;

  private String publicKey;
  private String paymentReference;
  private String planId;
  private String cardNumber;
  private String expiryMonth;
  private String callbackUrl;
  private String expiryYear;
  private String cvv;
  private String amount;
  private String currency;
  private String productDescription;
  private String productId;
  private String country;
  private String startDate;
  private String cardName;
  private String billingCycle;
  private String email;
  private String mobileNumber;
  private String customerId;
  private String billingPeriod;
  private String billingId;
  private String status;
  private boolean subscriptionAmount;
}
