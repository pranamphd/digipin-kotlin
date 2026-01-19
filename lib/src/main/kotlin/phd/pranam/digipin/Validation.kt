/*
 * Copyright (c) 2026 Pranam.
 * ORCID: https://orcid.org/0009-0007-9316-3616
 *
 * This code is licensed under the Apache License, Version 2.0.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package phd.pranam.digipin

import phd.pranam.digipin.DigipinError.*

/**
 * Validate geodetic latitude and longitude.
 *
 * This function verifies that the latitude and longitude values fall
 * within the globally valid WGS-84 geodetic bounds.
 *
 * @param location Geographic location containing latitude and longitude
 * expressed in decimal degrees.
 *
 * @throws DigipinError.InvalidLatitude
 * if the latitude is outside the valid geodetic range.
 *
 * @throws DigipinError.InvalidLongitude
 * if the longitude is outside the valid geodetic range.
 */
internal fun validateGeodeticCoordinates(location: Location) {
    if (location.latitude !in GEODETIC_LATITUDE_MIN..GEODETIC_LATITUDE_MAX
    ) {
        throw InvalidLatitude()
    }

    if (location.longitude !in GEODETIC_LONGITUDE_MIN..GEODETIC_LONGITUDE_MAX
    ) {
        throw InvalidLongitude()
    }
}


/**
 * Validate that a geographic location lies within the supported DIGIPIN territory.
 *
 * This function enforces territorial constraints beyond global geodetic bounds.
 *
 * @param location Geographic location containing latitude and longitude
 * expressed in decimal degrees.
 *
 * @throws DigipinError.OutsideSupportedTerritory
 * if either latitude or longitude lies outside supported bounds.
 */
internal fun validateDigipinTerritory(location: Location) {
    if (location.latitude !in DIGIPIN_LATITUDE_MIN..DIGIPIN_LATITUDE_MAX ||
        location.longitude !in DIGIPIN_LONGITUDE_MIN..DIGIPIN_LONGITUDE_MAX
    ) {
        throw OutsideSupportedTerritory()
    }
}

/**
 * Validate the structural format of a DIGIPIN string.
 *
 * This function validates only the canonical DIGIPIN representation.
 * Input is expected to be normalized before validation.
 *
 * @param digipin Canonical DIGIPIN string with no separators.
 *
 * @throws DigipinError.InvalidDigipinLength
 * if the DIGIPIN length does not match the required value.
 *
 * @throws DigipinError.InvalidDigipinFormat
 * if the DIGIPIN contains unsupported symbols.
 */
internal fun validateDigipinFormat(digipin: String) {
    if (digipin.length != DIGIPIN_LENGTH) {
        throw InvalidDigipinLength()
    }

    if (!digipin.all { it in DIGIPIN_SYMBOLS }) {
        throw InvalidDigipinFormat()
    }
}
