/*
 * Copyright (c) 2026 Pranam.
 * ORCID: https://orcid.org/0009-0007-9316-3616
 *
 * This code is licensed under the Apache License, Version 2.0.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package phd.pranam.digipin

/**
 * Represents a geographic location with latitude and longitude.
 *
 * Both values are expressed in decimal degrees using the WGS-84
 * coordinate reference system.
 */
public data class Location(
    val latitude: Double,
    val longitude: Double
)
