// SPDX-License-Identifier: Apache-2.0
/*
 * Copyright (c) 2026 Pranam.
 * ORCID: https://orcid.org/0009-0007-9316-3616
 *
 * This code is licensed under the Apache License, Version 2.0.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package phd.pranam.digipin

import kotlin.math.floor

/**
 * Encode geographic coordinates into a canonical DIGIPIN.
 *
 * This function applies a hierarchical 4 × 4 grid subdivision over the
 * officially supported territory to deterministically derive a
 * fixed-length DIGIPIN identifier.
 *
 * @param location Geographic location containing latitude and longitude
 * expressed in decimal degrees using the WGS-84 coordinate system.
 *
 * @return A canonical DIGIPIN string consisting of exactly
 * DIGIPIN_LENGTH characters with no separators.
 *
 * @throws DigipinError.InvalidLatitude
 * if the latitude is outside valid geodetic bounds.
 *
 * @throws DigipinError.InvalidLongitude
 * if the longitude is outside valid geodetic bounds.
 *
 * @throws DigipinError.OutsideSupportedTerritory
 * if the location lies outside the officially supported DIGIPIN territory.
 */
public fun encode(location: Location): String {
    validateGeodeticCoordinates(location)
    validateDigipinTerritory(location)

    var latitudeMin = DIGIPIN_LATITUDE_MIN
    var latitudeMax = DIGIPIN_LATITUDE_MAX
    var longitudeMin = DIGIPIN_LONGITUDE_MIN
    var longitudeMax = DIGIPIN_LONGITUDE_MAX

    val digipin = StringBuilder(DIGIPIN_LENGTH)

    repeat(DIGIPIN_LENGTH) {
        val latitudeStep = (latitudeMax - latitudeMin) / DIGIPIN_GRID_SIZE
        val longitudeStep = (longitudeMax - longitudeMin) / DIGIPIN_GRID_SIZE

        var row = floor((latitudeMax - location.latitude) / latitudeStep).toInt()
        var column = floor((location.longitude - longitudeMin) / longitudeStep).toInt()

        // Clamp indices to grid bounds
        row = row.coerceIn(0, DIGIPIN_GRID_SIZE - 1)
        column = column.coerceIn(0, DIGIPIN_GRID_SIZE - 1)

        val symbol = DIGIPIN_LABEL_GRID[row][column]
        digipin.append(symbol)

        latitudeMax -= row * latitudeStep
        latitudeMin = latitudeMax - latitudeStep

        longitudeMin += column * longitudeStep
        longitudeMax = longitudeMin + longitudeStep
    }

    return digipin.toString()
}
