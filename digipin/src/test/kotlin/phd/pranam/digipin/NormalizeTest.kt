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

class NormalizeTest {
    @Test
    fun removesSeparatorsAndUppercases() {
        assertEquals("39J49LL8T4", normalize("39j-49l-l8t4"))
    }

    @Test
    fun doesNotModifyNormalizedDigipin() {
        assertEquals("39J49LL8T4", normalize("39J49LL8T4"))
    }

    @Test
    fun keepsNonSeparatorCharactersExceptCase() {
        assertEquals("ABC", normalize("a-b-c"))
    }

    @Test
    fun normalizeEmptyString() {
        assertEquals("", normalize(""))
    }

    @Test
    fun normalizePreservesDigits() {
        assertEquals("1239", normalize("1-2-3-9"))
    }
}
