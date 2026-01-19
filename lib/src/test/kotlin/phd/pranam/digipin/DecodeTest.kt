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

class DecodeTest {
    @Test
    fun decodeAcceptsLowercaseAndSeparators() {
        assertNotNull(decode("2c3-4k5-pft9"))
    }

    @Test
    fun decodeAcceptsFormattedDigipin() {
        assertNotNull(decode("2c3-4km-pft9"))
    }

    @Test
    fun decodeRejectsInvalidSymbols() {
        assertFailsWith<DigipinError> {
            decode("39J49YL8T4")
        }

        assertFailsWith<DigipinError> {
            decode("A4D-SK2-S7Z5")
        }
    }

    @Test
    fun decodeEncodeDecodeIsStable() {
        val digipin = "39J49LL8T4"
        val decoded = decode(digipin)
        val encoded = encode(decoded)
        val redecoded = decode(encoded)

        assertEquals(decoded, redecoded)
    }
}
