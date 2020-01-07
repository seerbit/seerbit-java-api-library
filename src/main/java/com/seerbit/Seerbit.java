/*
 * Copyright (C) 2019 Seerbit
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.seerbit;

import static com.seerbit.enums.ClientConstantsEnum.LIVE_API_BASE;
import static com.seerbit.enums.ClientConstantsEnum.PILOT_API_BASE;

/**
 *
 * @author Seerbit
 */
public interface Seerbit {

    static final String API_BASE = LIVE_API_BASE.getValue();
    static final String API_BASE_PILOT = PILOT_API_BASE.getValue();

    /**
     *
     * @return apiBase
     */
    public default String getApiBase() {
        return Seerbit.API_BASE;
    }

    /**
     *
     * @return apiBasePilot
     */
    public default String getApiBasePilot() {
        return Seerbit.API_BASE_PILOT;
    }

    /**
     *
     * @return apiVersion
     */
    String getApiVersion();

    /**
     *
     * @param apiVersion
     */
    static void setApiVersion(final String apiVersion){}
}
