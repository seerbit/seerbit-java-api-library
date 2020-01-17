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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.seerbit.NumericConstants;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Seerbit
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Dispute implements NumericConstants {
    
    @Builder.Default
    private List<Evidence> evidence = new ArrayList<>(MIN_SIZE);
    
    private String resolution;
    private String amount;
    
    @JsonProperty("resolution_image")
    private String resolutionImage;
    
    @JsonProperty("merchant_id")
    private String merchantId;
    
    @JsonProperty("customer_email")
    private String customerEmail;
}
