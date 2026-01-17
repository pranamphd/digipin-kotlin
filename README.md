# digipin-kotlin

DIGIPIN (Digital Postal Index Number) is a national-level, geo-coded addressing grid developed by the **Department of Posts, Ministry of Communications, Government of India**. It provides a deterministic and reversible method to represent geographic locations in India using a **10-character alphanumeric code derived from latitude and longitude**.

This repository contains a **Kotlin implementation of the DIGIPIN algorithm**, intended to enable consistent and correct adoption across platforms, applications, and systems.

![DIGIPIN Logo](/digipin-social-preview.png)

## What this repository provides

- Deterministic conversion from **latitude / longitude → DIGIPIN**
- Reversible conversion from **DIGIPIN → geographic coordinates**
- Comprehensive tests to ensure correctness
- Implementation aligned with the **final DIGIPIN Technical Specification (March 2025)**

---

## Specification reference

This project implements the **DIGIPIN (Digital Postal Index Number)** algorithm strictly according to the official technical specification published by the **Department of Posts, Ministry of Communications, Government of India**.

The authoritative specification is available at:
[https://www.indiapost.gov.in/digipin](https://www.indiapost.gov.in/digipin)

All implementations in this repository aim to faithfully reproduce the behavior described in the final DIGIPIN Technical Document (March 2025).

> This repository provides an independent implementation of the DIGIPIN specification and is not an official distribution of the Department of Posts unless explicitly stated otherwise.

---

## Design principles

- **Specification-first**: Implementation strictly follows the DIGIPIN technical specification.
- **Deterministic**: The same input always produces the same output, regardless of language or platform.
- **No external dependencies**: Core logic is self-contained.

---

## Status

This repository is under active development.
The library interfaces (function and type signatures) may evolve until the first stable release (`v1.0.0`).

---

## License

This project is licensed under the **Apache License, Version 2.0**.
See the [LICENSE](LICENSE) file for details.
