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

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author Seerbit
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDetail implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("linkingreference")
	@NotNull(message = "\"linkingreference\" cannot be null")
	@NotBlank(message = "\"linkingreference\" cannot be blank")
	private String linkingReference;

	@NotNull(message = "\"otp\" cannot be null")
	@NotBlank(message = "\"otp\" cannot be blank")
	private String otp;
}