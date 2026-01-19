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
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNotNull

class EncodeTest {
    @Test
    fun encodeRejectsOutsideTerritory() {
        val location = Location(latitude = 0.0, longitude = 0.0)
        assertFailsWith<DigipinError> { encode(location) }
    }

    @Test
    fun boundarySouthWest() {
        val location = Location(latitude = 2.5, longitude = 63.5)
        assertNotNull(encode(location))
    }

    @Test
    fun boundaryNorthEast() {
        val location = Location(latitude = 38.5, longitude = 99.5)
        assertNotNull(encode(location))
    }

    @Test
    fun encodeRejectsLatitudeBelowMin() {
        val location = Location(latitude = 2.48, longitude = 70.0)
        assertFailsWith<DigipinError> { encode(location) }
    }

    @Test
    fun encodeRejectsLongitudeAboveMax() {
        val location = Location(latitude = 20.0, longitude = 99.51)
        assertFailsWith<DigipinError> { encode(location) }
    }

    @Test
    fun encodeIsDeterministic() {
        val location = Location(28.6, 77.2)
        val first = encode(location)

        repeat(10) {
            assertEquals(first, encode(location))
        }
    }

    @Test
    fun encodeAlwaysProducesCorrectLength() {
        val location = Location(28.6, 77.2)
        assertEquals(DIGIPIN_LENGTH, encode(location).length)
    }
}
