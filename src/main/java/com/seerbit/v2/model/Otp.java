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
public class Otp implements Serializable {

  private static final long serialVersionUID = 1L;

  @NotBlank(message = "\"linkingReference\" cannot be blank")
  @NotNull(message = "\"linkingReference\" cannot be null")
  private String linkingReference;

  @NotBlank(message = "\"otp\" cannot be blank")
  @NotNull(message = "\"otp\" cannot be null")
  private String otp;
}
