/*
 * Copyright (c) 2026 Pranam.
 * ORCID: https://orcid.org/0009-0007-9316-3616
 *
 * This code is licensed under the Apache License, Version 2.0.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package phd.pranam.digipin

import phd.pranam.digipin.DigipinError.InvalidDigipinFormat

/**
 * Decode a DIGIPIN into geodetic coordinates.
 *
 * This function reverses the hierarchical DIGIPIN grid encoding process
 * and derives the geographic centroid of the represented grid cell.
 *
 * @param digipin DIGIPIN string to decode. Separator characters are allowed
 * and case is ignored.
 *
 * @return A Location representing the centroid latitude and longitude
 * in decimal degrees using the WGS-84 coordinate system.
 *
 * @throws DigipinError.InvalidDigipinLength
 * if the DIGIPIN does not have the required length.
 *
 * @throws DigipinError.InvalidDigipinFormat
 * if the DIGIPIN contains invalid symbols.
 *
 */
public fun decode(digipin: String): Location {
    val normalized = normalize(digipin)
    validateDigipinFormat(normalized)

    var latitudeMin = DIGIPIN_LATITUDE_MIN
    var latitudeMax = DIGIPIN_LATITUDE_MAX
    var longitudeMin = DIGIPIN_LONGITUDE_MIN
    var longitudeMax = DIGIPIN_LONGITUDE_MAX

    for (symbol in normalized) {
        val (row, column) = DIGIPIN_SYMBOL_TO_INDEX[symbol] ?: throw InvalidDigipinFormat()

        val latitudeStep = (latitudeMax - latitudeMin) / DIGIPIN_GRID_SIZE
        val longitudeStep = (longitudeMax - longitudeMin) / DIGIPIN_GRID_SIZE

        latitudeMax -= row * latitudeStep
        latitudeMin = latitudeMax - latitudeStep

        longitudeMin += column * longitudeStep
        longitudeMax = longitudeMin + longitudeStep
    }

    return Location(
        latitude = (latitudeMin + latitudeMax) / 2.0, longitude = (longitudeMin + longitudeMax) / 2.0
    )
}
