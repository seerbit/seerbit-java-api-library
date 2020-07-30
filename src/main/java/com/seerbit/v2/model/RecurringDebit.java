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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Seerbit
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecurringDebit implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "\"publicKey\" cannot be blank")
	@NotNull(message = "\"publicKey\" cannot be null")
	private String publicKey;

	@NotBlank(message = "\"paymentReference\" cannot be blank")
	@NotNull(message = "\"paymentReference\" cannot be null")
	private String paymentReference;

	@NotBlank(message = "\"amount\" cannot be blank")
	@NotNull(message = "\"amount\" cannot be null")
	private String amount;

	@NotBlank(message = "\"currency\" cannot be blank")
	@NotNull(message = "\"currency\" cannot be null")
	private String currency;

	@NotBlank(message = "\"email\" cannot be blank")
	@NotNull(message = "\"email\" cannot be null")
	@Email(message = "\"email\" must be a valid email")
	private String email;

	@NotBlank(message = "\"authorizationCode\" cannot be blank")
	@NotNull(message = "\"authorizationCode\" cannot be null")
	private String authorizationCode;
}
