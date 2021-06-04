package com.seerbit.v2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ussd implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "\"publicKey\" cannot be blank")
  @NotNull(message = "\"publicKey\" cannot be null")
  private String publicKey;

  @NotBlank(message = "\"amount\" cannot be blank")
  @NotNull(message = "\"amount\" cannot be null")
  private String amount;

  @NotBlank(message = "\"fullName\" cannot be blank")
  @NotNull(message = "\"fullName\" cannot be null")
  private String fullName;

  @NotBlank(message = "\"mobileNumber\" cannot be blank")
  @NotNull(message = "\"mobileNumber\" cannot be null")
  private String mobileNumber;

  @NotBlank(message = "\"email\" cannot be blank")
  @NotNull(message = "\"email\" cannot be null")
  private String email;

  @NotBlank(message = "\"currency\" cannot be blank")
  @NotNull(message = "\"currency\" cannot be null")
  private String currency;

  @NotBlank(message = "\"country\" cannot be blank")
  @NotNull(message = "\"country\" cannot be null")
  private String country;

  @NotBlank(message = "\"paymentReference\" cannot be blank")
  @NotNull(message = "\"paymentReference\" cannot be null")
  private String paymentReference;

  @NotBlank(message = "\"callbackUrl\" cannot be blank")
  @NotNull(message = "\"callbackUrl\" cannot be null")
  private String callbackUrl;

  @NotBlank(message = "\"redirectUrl\" cannot be blank")
  @NotNull(message = "\"redirectUrl\" cannot be null")
  private String redirectUrl;

  @NotBlank(message = "\"paymentType\" cannot be blank")
  @NotNull(message = "\"paymentType\" cannot be null")
  @Pattern(regexp = "USSD", message = "paymentType must be USSD")
  private String paymentType;

  @NotBlank(message = "\"bankCode\" cannot be blank")
  @NotNull(message = "\"bankCode\" cannot be null")
  private String bankCode;
}
