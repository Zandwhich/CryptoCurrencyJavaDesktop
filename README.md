# CryptoCurrencyJavaDesktop

A Java desktop application that aggregates live cryptocurrency prices from multiple exchange APIs into a single unified view.

## Overview

Built this to solve a real problem: tracking crypto prices meant jumping between Coinbase, CoinMarketCap, and other sites constantly. This app pulls from all of them simultaneously and presents the data in one place.

**Key skills demonstrated:**
- Consuming and integrating multiple third-party REST APIs in Java
- JSON parsing and data normalisation across inconsistent response formats
- Building a desktop UI with Java Swing
- Designing a multi-source data architecture with a shared currency list (`CryptoCurrenciesList.json`)
- Tooling: wrote a Python helper script (`CryptoCurrencyListFormatter.py`) to automate formatting the supported currency list

## Tech Stack

| | |
| Language | Java |
| UI | Java Swing |
| Data format | JSON (via [JSON Simple](https://github.com/fangyidong/json-simple)) |
| Supporting tooling | Python |

## API Integrations

Integrated with 7 exchanges and data providers at the time of development, including Coinbase, CryptoCompare, CoinCap, and CoinMarketCap. Several of these have since shut down (QuadrigaCX, Liqui.io) or deprecated their original endpoints, which is a natural consequence of building against third-party APIs in a fast-moving space.

## License

[GPL-3.0](LICENSE)
