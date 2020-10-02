package com.seerbit.v2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StandardCheckout implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "\"publicKey\" cannot be blank")
  @NotNull(message = "\"publicKey\" cannot be null")
  private String publicKey;

  @NotBlank(message = "\"amount\" cannot be blank")
  @NotNull(message = "\"amount\" cannot be null")
  private String amount;

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

  @NotBlank(message = "\"productId\" cannot be blank")
  @NotNull(message = "\"productId\" cannot be null")
  private String productId;

  @NotBlank(message = "\"productDescription\" cannot be blank")
  @NotNull(message = "\"productDescription\" cannot be null")
  private String productDescription;

  @NotBlank(message = "\"callbackUrl\" cannot be blank")
  @NotNull(message = "\"callbackUrl\" cannot be null")
  private String callbackUrl;

  private String hash;
  private String hashType;
}
