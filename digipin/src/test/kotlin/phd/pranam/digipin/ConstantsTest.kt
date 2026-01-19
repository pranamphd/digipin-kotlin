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

class ConstantsTest {

    @Test
    fun symbolsMatchLabelGrid() {
        val flattened = DIGIPIN_LABEL_GRID
            .flatMap { row -> row.asList() }

        assertEquals(
            DIGIPIN_SYMBOLS,
            flattened,
            "DIGIPIN_SYMBOLS must exactly match DIGIPIN_LABEL_GRID ordering"
        )
    }

    @Test
    fun symbolToIndexMapMatchesGrid() {
        DIGIPIN_LABEL_GRID.forEachIndexed { row, rowValues ->
            rowValues.forEachIndexed { col, symbol ->
                assertEquals(
                    row to col,
                    DIGIPIN_SYMBOL_TO_INDEX[symbol],
                    "Incorrect index mapping for symbol $symbol"
                )
            }
        }
    }


}