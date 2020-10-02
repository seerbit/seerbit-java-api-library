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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/** @author Seerbit */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "\"publicKey\" cannot be blank")
  @NotNull(message = "\"publicKey\" cannot be null")
  private String publicKey;

  @NotBlank(message = "\"amount\" cannot be blank")
  @NotNull(message = "\"amount\" cannot be null")
  private String amount;

  private String fee;
  private String fullName;
  private String mobileNumber;

  @NotBlank(message = "\"currency\" cannot be blank")
  @NotNull(message = "\"currency\" cannot be null")
  private String currency;

  @NotBlank(message = "\"country\" cannot be blank")
  @NotNull(message = "\"country\" cannot be null")
  private String country;

  @NotBlank(message = "\"paymentReference\" cannot be blank")
  @NotNull(message = "\"paymentReference\" cannot be null")
  private String paymentReference;

  @NotBlank(message = "\"email\" cannot be blank")
  @NotNull(message = "\"email\" cannot be null")
  private String email;

  private String productId;
  private String productDescription;
  private String clientAppCode;

  @NotBlank(message = "\"channelType\" cannot be blank")
  @NotNull(message = "\"channelType\" cannot be null")
  private String channelType;

  @NotBlank(message = "\"redirectUrl\" cannot be blank")
  @NotNull(message = "\"redirectUrl\" cannot be null")
  private String redirectUrl;

  @NotBlank(message = "\"paymentType\" cannot be blank")
  @NotNull(message = "\"paymentType\" cannot be null")
  private String paymentType;

  private String deviceType;
  private String sourceIP;

  @NotBlank(message = "\"accountName\" cannot be blank")
  @NotNull(message = "\"accountName\" cannot be null")
  private String accountName;

  @NotBlank(message = "\"accountNumber\" cannot be blank")
  @NotNull(message = "\"accountNumber\" cannot be null")
  private String accountNumber;

  @NotBlank(message = "\"bankCode\" cannot be blank")
  @NotNull(message = "\"bankCode\" cannot be null")
  private String bankCode;

  private String bvn;
  private String dateOfBirth;

  @NotBlank(message = "\"retry\" cannot be blank")
  @NotNull(message = "\"retry\" cannot be null")
  private String retry;

  private String invoiceNumber;
}
