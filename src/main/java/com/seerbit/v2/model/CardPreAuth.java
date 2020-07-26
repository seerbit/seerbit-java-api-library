/*
 * Copyright (C) 2020 centricgateway
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

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardPreAuth implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "\"paymentReference\" cannot be blank")
	@NotNull(message = "\"paymentReference\" cannot be null")
	private String paymentReference;

	@NotBlank(message = "\"publicKey\" cannot be blank")
	@NotNull(message = "\"publicKey\" cannot be null")
	private String publicKey;


	@NotBlank(message = "\"cardNumber\" cannot be blank")
	@NotNull(message = "\"cardNumber\" cannot be null")
	private String cardNumber;

	@NotBlank(message = "\"cvv\" cannot be blank")
	@NotNull(message = "\"cvv\" cannot be null")
	private String cvv;

	@NotBlank(message = "\"expiryMonth\" cannot be blank")
	@NotNull(message = "\"expiryMonth\" cannot be null")
	private String expiryMonth;

	@NotBlank(message = "\"expiryYear\" cannot be blank")
	@NotNull(message = "\"expiryYear\" cannot be null")
	private String expiryYear;

	@NotBlank(message = "\"currency\" cannot be blank")
	@NotNull(message = "\"currency\" cannot be null")
	private String currency;

	@NotBlank(message = "\"country\" cannot be blank")
	@NotNull(message = "\"country\" cannot be null")
	private String country;

	private String productDescription;

	@NotBlank(message = "\"amount\" cannot be blank")
	@NotNull(message = "\"amount\" cannot be null")
	private String amount;

	@NotBlank(message = "\"email\" cannot be blank")
	@NotNull(message = "\"email\" cannot be null")
	@Email(message = "\"email\" must be a valid email")
	private String email;

	private String fullName;
}
