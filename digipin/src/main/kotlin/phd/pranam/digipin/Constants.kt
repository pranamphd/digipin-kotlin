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
 * Geodetic coordinate limits (WGS-84 compatible).
 *
 * These constants define the full valid range of latitude and longitude
 * values supported by the WGS-84 coordinate reference system.
 */

/** Minimum latitude for geodetic coordinates */
internal const val GEODETIC_LATITUDE_MIN: Double = -90.0

/** Maximum latitude for geodetic coordinates */
internal const val GEODETIC_LATITUDE_MAX: Double = 90.0

/** Minimum longitude for geodetic coordinates */
internal const val GEODETIC_LONGITUDE_MIN: Double = -180.0

/** Maximum longitude for geodetic coordinates */
internal const val GEODETIC_LONGITUDE_MAX: Double = 180.0

/**
 * Official DIGIPIN supported territory bounds.
 *
 * Only geographic coordinates within these bounds are valid inputs
 * for DIGIPIN encoding.
 */

/** Minimum latitude for DIGIPIN supported territory */
internal const val DIGIPIN_LATITUDE_MIN: Double = 2.5

/** Maximum latitude for DIGIPIN supported territory */
internal const val DIGIPIN_LATITUDE_MAX: Double = 38.5

/** Minimum longitude for DIGIPIN supported territory */
internal const val DIGIPIN_LONGITUDE_MIN: Double = 63.5

/** Maximum longitude for DIGIPIN supported territory */
internal const val DIGIPIN_LONGITUDE_MAX: Double = 99.5

/**
 * Official DIGIPIN symbol set (base-16).
 *
 * The order of symbols is significant and MUST NOT be changed.
 * This list is immutable by contract.
 */
internal val DIGIPIN_SYMBOLS: List<Char> = listOf(
    'F', 'C', '9', '8',
    'J', '3', '2', '7',
    'K', '4', '5', '6',
    'L', 'M', 'P', 'T',
)

/**
 * Length of a DIGIPIN code in characters.
 */
internal const val DIGIPIN_LENGTH: Int = 10

/**
 * Size of the DIGIPIN grid along one axis.
 *
 * DIGIPIN uses a fixed 4 × 4 hierarchical grid at each level.
 */
internal const val DIGIPIN_GRID_SIZE: Int = 4

/**
 * 2D array representing the DIGIPIN label grid.
 *
 * - Row index corresponds to latitude subdivision (south → north)
 * - Column index corresponds to longitude subdivision (west → east)
 *
 * Structure and ordering exactly match the specification.
 */
internal val DIGIPIN_LABEL_GRID: Array<CharArray> = arrayOf(
    charArrayOf('F', 'C', '9', '8'),
    charArrayOf('J', '3', '2', '7'),
    charArrayOf('K', '4', '5', '6'),
    charArrayOf('L', 'M', 'P', 'T'),
)

/**
 * Lookup table mapping DIGIPIN symbols to grid coordinates.
 *
 * This map provides a constant-time reverse mapping from a DIGIPIN
 * symbol to its corresponding `(row, column)` position within the
 * DIGIPIN label grid.
 *
 * The mapping is derived directly from DIGIPIN_LABEL_GRID and
 * preserves its ordering and spatial semantics:
 *
 * - `row` corresponds to the latitude subdivision (north → south)
 * - `column` corresponds to the longitude subdivision (west → east)
 *
 * This structure is used during DIGIPIN decoding to efficiently
 * resolve each symbol without performing repeated grid scans.
 *
 * Invariants:
 * - Every symbol present in `DIGIPIN_SYMBOLS` MUST appear exactly once
 *   in this map.
 * - No additional symbols may be present.
 * - The mapping MUST remain consistent with `DIGIPIN_LABEL_GRID`.
 *
 * Any violation of these invariants indicates an internal
 * specification or implementation error.
 */
internal val DIGIPIN_SYMBOL_TO_INDEX: Map<Char, Pair<Int, Int>> = DIGIPIN_LABEL_GRID.flatMapIndexed { rowIndex, row ->
    row.mapIndexed { columnIndex, symbol ->
        symbol to (rowIndex to columnIndex)
    }
}.toMap()

/**
 * Maximum possible positional error after DIGIPIN decoding, in degrees.
 *
 * Derived from the specification:
 *   36° / 4¹⁰ / 2
 *
 * Intended primarily for testing and conformance checks.
 */
internal const val DIGIPIN_MAX_DECODE_ERROR_DEGREES: Double =
    0.0000171661376953125