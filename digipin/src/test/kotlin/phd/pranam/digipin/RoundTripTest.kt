/*
 * Copyright (c) 2026 Pranam.
 * ORCID: https://orcid.org/0009-0007-9316-3616
 *
 * This code is licensed under the Apache License, Version 2.0.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

package phd.pranam.digipin

import kotlin.math.abs
import kotlin.test.Test
import kotlin.test.assertTrue

class RoundTripTest {
    @Test
    fun roundtripAcrossMultipleLocations() {
        val locations = listOf(
            Location(2.5, 63.5), Location(28.622788, 77.213033), Location(38.5, 99.5)
        )

        for (location in locations) {
            val digipin = encode(location)
            val decoded = decode(digipin)

            assertTrue(
                abs(decoded.latitude - location.latitude) <= DIGIPIN_MAX_DECODE_ERROR_DEGREES
            )
            assertTrue(
                abs(decoded.longitude - location.longitude) <= DIGIPIN_MAX_DECODE_ERROR_DEGREES
            )
        }
    }
}
