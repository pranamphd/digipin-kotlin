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
 * Normalize a DIGIPIN string.
 *
 * Normalization prepares a DIGIPIN for validation and decoding by
 * enforcing a canonical textual representation.
 *
 * @param digipin DIGIPIN string that may contain separators
 * and mixed character casing.
 *
 * @return A normalized DIGIPIN string with separators removed
 * and all characters converted to uppercase.
 */
internal fun normalize(digipin: String): String {
    val builder = StringBuilder(digipin.length)

    for (character in digipin) {
        if (character != '-') {
            builder.append(character.uppercaseChar())
        }
    }

    return builder.toString()
}
