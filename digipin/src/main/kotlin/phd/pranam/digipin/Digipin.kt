/*
 * Copyright (c) 2026 Pranam.
 * ORCID: https://orcid.org/0009-0007-9316-3616
 *
 * This code is licensed under the Apache License, Version 2.0.
 *
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 */

@file:JvmName("Digipin")

package phd.pranam.digipin

/**
 * DIGIPIN – Digital Postal Index Number
 *
 * This library provides functionality to encode geographic locations
 * into a canonical 10-character DIGIPIN string and decode DIGIPIN
 * strings back into geographic locations.
 *
 * DIGIPIN represents a fixed-resolution spatial grid covering a defined
 * geographic territory. Each DIGIPIN uniquely identifies a grid cell.
 *
 * ## Public API
 * - [encode] – Converts geodetic coordinates into a DIGIPIN
 * - [decode] – Converts a DIGIPIN into geodetic coordinates
 * - [Location] – Value type representing latitude and longitude
 * - [DigipinError] – Error hierarchy for DIGIPIN operations
 *
 * ## Thread safety
 * All operations are pure and thread-safe.
 *
 * ## Usage
 *
 * ### Kotlin
 * ```kotlin
 * import phd.pranam.digipin.encode
 * import phd.pranam.digipin.decode
 * import phd.pranam.digipin.Location
 *
 * fun main() {
 *      val location = Location(
 *          latitude = 28.622788,
 *          longitude = 77.213033
 *      )
 *
 *      try {
 *          val digipin = encode(location)
 *          val decoded = decode(digipin)
 *
 *          println(digipin)
 *          println(decoded)
 *      } catch (e: DigipinError) {
 *          println("DIGIPIN error: ${e.message}")
 *      }
 * }
 * ```
 *
 * ### Java
 * ```java
 * import phd.pranam.digipin.Location;
 * import phd.pranam.digipin.DigipinError;
 *
 * import static phd.pranam.digipin.DecodeKt.decode;
 * import static phd.pranam.digipin.EncodeKt.encode;
 *
 * public class Example {
 *      static void main() {
 *          Location location = new Location(28.622788, 77.213033);
 *
 *          try {
 *              String digipin = Digipin.encode(location);
 *              Location decoded = Digipin.decode(digipin);
 *
 *              System.out.println(digipin);
 *              System.out.println(decoded);
 *          } catch (DigipinError e) {
 *              System.out.println("DIGIPIN error: " + e.getMessage());
 *          }
 *      }
 * }
 * ```
 */
