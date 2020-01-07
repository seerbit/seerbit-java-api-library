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

import java.util.ArrayList;
import java.util.List;
import lombok.Builder;
import lombok.Data;

import static com.seerbit.enums.NumericConstantsEnum.MIN_VALUE;

/**
 *
 * @author Seerbit
 */
@Data
@Builder
public class Evidence {
    @Builder.Default
    private final List<Image> images = new ArrayList<Image>(MIN_VALUE.getValue());
    private String message;
}
