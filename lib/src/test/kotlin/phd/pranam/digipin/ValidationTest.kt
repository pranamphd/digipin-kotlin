/*
 * Copyright (c) 2026 Pranam.
 * ORCID: https://orcid.org/0009-0007-9316-3616
 *
 * This code is licensed under the Apache License, Version 2.0.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package phd.pranam.digipin

import kotlin.test.Test
import kotlin.test.assertFailsWith

class ValidationTest {
    @Test
    fun acceptsValidGeodeticCoordinates() {
        validateGeodeticCoordinates(Location(28.6, 77.2))
    }

    @Test
    fun acceptsExactGeodeticBoundaries() {
        validateGeodeticCoordinates(
            Location(GEODETIC_LATITUDE_MIN, GEODETIC_LONGITUDE_MIN)
        )
        validateGeodeticCoordinates(
            Location(GEODETIC_LATITUDE_MAX, GEODETIC_LONGITUDE_MAX)
        )
    }

    @Test
    fun rejectsInvalidLatitude() {
        assertFailsWith<DigipinError> {
            validateGeodeticCoordinates(Location(120.0, 77.0))
        }
    }

    @Test
    fun rejectsInvalidLongitude() {
        assertFailsWith<DigipinError> {
            validateGeodeticCoordinates(Location(28.0, 200.0))
        }
    }

    @Test
    fun rejectsOutsideTerritory() {
        assertFailsWith<DigipinError> {
            validateDigipinTerritory(Location(51.5, -0.1))
        }
    }

    @Test
    fun acceptsValidDigipinFormat() {
        validateDigipinFormat("39J49LL8T4")
    }

    @Test
    fun rejectsUnnormalizedDigipin() {
        assertFailsWith<DigipinError> {
            validateDigipinFormat("39J-49L-L8T4")
        }
    }

    @Test
    fun rejectsInvalidLength() {
        assertFailsWith<DigipinError> {
            validateDigipinFormat("39J")
        }
    }

    @Test
    fun rejectsInvalidSymbols() {
        assertFailsWith<DigipinError> {
            validateDigipinFormat("34SUYDV5GH")
        }
    }
}
