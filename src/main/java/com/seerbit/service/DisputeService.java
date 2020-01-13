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
package com.seerbit.service;

import com.google.gson.JsonObject;
import java.util.Map;

/**
 *
 * @author Seerbit
 */
public interface DisputeService {
    
    JsonObject getAllDispute(final String userId, int from, int to);
    JsonObject getDispute(String userId, String disputeId);
    JsonObject add(String userId, Map<String, Object> payload);
    JsonObject doCloseDispute(String userId, String disputeId, Map<String, Object> payload);
    JsonObject doUpdateDispute(String userId, Map<String, Object> payload);
}
