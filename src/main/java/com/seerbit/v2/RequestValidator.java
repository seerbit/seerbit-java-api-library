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
package com.seerbit.v2;

import com.seerbit.v2.exception.SeerbitException;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * @author centricgateway
 */
public class RequestValidator {

	/**
	 * @param bean A non-optional generic object, the bean class
	 */
	public static <T> void doValidate(T bean) {
		Validator validator;
		ValidatorFactory factory;
		StringBuilder errorMessageBuilder;
		Set<ConstraintViolation<T>> violations;
		ParameterMessageInterpolator parameterMessageInterpolator;

		parameterMessageInterpolator = new ParameterMessageInterpolator();
		factory = Validation
			.byDefaultProvider()
			.configure()
			.messageInterpolator(parameterMessageInterpolator)
			.buildValidatorFactory();
		validator = factory.getValidator();
		violations = validator.validate(bean);

		if (violations.size() != 0) {
			errorMessageBuilder = new StringBuilder();

			for (ConstraintViolation<T> violation : violations) {
				errorMessageBuilder
					.append("Constraint Violation Found: ")
					.append(violation.getMessage())
					.append(System.lineSeparator());
			}

			throw new SeerbitException(errorMessageBuilder.toString());
		}
	}
}
