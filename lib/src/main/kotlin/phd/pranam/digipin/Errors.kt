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
 * Errors returned by DIGIPIN operations.
 *
 * This sealed class defines the complete set of failure conditions
 * that may occur during validation, encoding, and decoding.
 */
public sealed class DigipinError(
    override val message: String
) : IllegalArgumentException(message) {

    /** Latitude is outside geodetic bounds. */
    public class InvalidLatitude : DigipinError(
        "Latitude is outside geodetic bounds."
    )

    /** Longitude is outside geodetic bounds. */
    public class InvalidLongitude : DigipinError(
        "Longitude is outside geodetic bounds."
    )

    /** Location is outside the DIGIPIN supported territory. */
    public class OutsideSupportedTerritory : DigipinError(
        "Location is outside the DIGIPIN supported territory."
    )

    /** DIGIPIN does not conform to required length. */
    public class InvalidDigipinLength : DigipinError(
        "DIGIPIN does not conform to required length."
    )

    /** DIGIPIN contains invalid symbols or format. */
    public class InvalidDigipinFormat : DigipinError(
        "DIGIPIN contains invalid symbols or format."
    )

}
