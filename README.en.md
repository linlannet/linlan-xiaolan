# linlan-xiaolan

[简体中文](./README.md) | English

> **TL;DR** · The "Xiaolan Life" full-stack project: a Spring Boot service (`xiaolan-backend`) plus a uni-app x mobile app (`mini`), covering website content, bookkeeping, wishes, reminders and WeChat login.
> **Keywords**: Spring Boot, Java 17, MyBatis, eframe, uni-app x, UTS, Vue3, WeChat Mini Program
> **AI entry**: [llms.txt](./llms.txt) · [AGENTS.md](./AGENTS.md)

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](./LICENSE)
![java](https://img.shields.io/badge/Java-17-orange.svg)
![spring boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![uni-app x](https://img.shields.io/badge/uni--app%20x-UTS-blue.svg)

## Introduction

The "Xiaolan Life" business project under the Linlan Information Data Service Network, consisting of a server and a mobile app:

- **backend**: a Spring Boot service built on the EFRAME enterprise framework, providing website content, bookkeeping, categories, wishes, reminders and WeChat login APIs
- **mini**: a uni-app x (UVue + UTS) mobile app targeting Android and WeChat Mini Program

## Version History

All changes are recorded in [CHANGELOG](./CHANGELOG.md). Back end version 1.2.2; mobile `manifest.json` version `0.0.1`.

## Table of Contents

- [1. Repository Structure](#1-repository-structure)
- [2. Quick Start](#2-quick-start)
- [3. Back-end Guide](#3-back-end-guide)
- [4. Mobile Guide](#4-mobile-guide)
- [5. Configuration and Ports](#5-configuration-and-ports)
- [6. Conventions](#6-conventions)
- [7. Documentation Index](#7-documentation-index)
- [Contributing](#contributing)
- [License](#license)

---

## 1. Repository Structure

| Directory | Description | Stack |
| ---- | ---- | ---- |
| [backend](./backend) | Xiaolan Life server (`net.linlan.plat:xiaolan-backend`) | Java 17 + Spring Boot + Spring Security + MyBatis + Druid + EFRAME 1.2.1 |
| [mini](./mini) | Xiaolan Life mobile app | uni-app x (UVue + UTS) + 45 uni_modules |

Scope:
- Website content: news, services, solutions, contact (`net.linlan.www.comm`)
- Xiaolan Life: bookkeeping, categories, wishes, reminders, mind records (`net.linlan.xiaolan`)
- WeChat: official account, mini program, open platform (`net.linlan.manage.wechat`)
- Platform status: version/status monitor APIs (`net.linlan.stage`)

## 2. Quick Start

### 2.1 Requirements

- JDK 17, Maven 3.8+
- MySQL (default `basedb_1.0` / `xiaolandb_1.0`)
- HBuilderX to run the uni-app x app (`mini/`)
- EFRAME 1.2.1 installed in the local Maven repository (`cd linlan-eframe/backend && mvn clean install`)

### 2.2 Back-end Build and Run

```bash
cd backend
mvn clean install
mvn clean package -DskipTests

# run
java -jar target/xiaolan-backend.jar --spring.profiles.active=dev
```

Main class: `backend/src/main/java/net/linlan/FrameApplication.java`

### 2.3 Mobile Run

1. Open `mini/` with HBuilderX
2. Run on an Android device / emulator, or WeChat Mini Program
3. The API base URL is controlled by `API_BASE_URL` in `mini/config/index.uts` (default `https://linlan.net/xiaolan_backend`)

> `mini/` has no npm scripts; build and run through the HBuilderX uni-app x toolchain.

## 3. Back-end Guide

### 3.1 Package Layout

```text
backend/src/main/java/net/linlan/
├── FrameApplication.java      Entry point
├── config/                    Configuration and properties
├── status/                    Status support
├── stage/                     Platform stage: security, api (status monitor)
├── www/comm/                  Website content: news, services, solutions, contact
├── manage/wechat/             WeChat: official account, mini program, open platform
└── xiaolan/
    ├── comm/                  Categories, bookkeeping and shared business
    └── my/                    BookInfo, MindRecord, CheckRecord, WishInfo
```

### 3.2 Layering

- `entity` / `dto` / `param` / `dao` / `service` / `controller`
- Mapper XML under `backend/src/main/resources/mapper/`, mirroring the package path
- Responses use the framework envelope with integer status codes (since 1.1.3)
- Queries default to the current user's own records (since 1.1.2)

### 3.3 Data and Scripts

- SQL scripts: `backend/scripts/sql/` (`basedb_1.0.sql`, `xiaolan_initial.sql`, `xiaolan_structure_1.0.sql`, `linlan_initial.sql`, `linlannet.sql`)
- Dev data sources: `basedb_1.0` (primary), `xiaolandb_1.0` (secondary, disabled by default)
- Code style: `backend/scripts/codestyle/`

Details: [backend/README.en.md](./backend/README.en.md) · [backend/CHANGELOG.md](./backend/CHANGELOG.md) · [backend/FAQ.md](./backend/FAQ.md)

## 4. Mobile Guide

- Framework: uni-app x; pages use `.uvue`, logic uses `.uts`
- Pages: `pages/tabbar/home`, `pages/tabbar/finance` (bookkeeping, calendar, add record), `pages/tabbar/my` (wishes, account, motivational quotes), `pages/tabbar/index`, component demos
- API modules: `mini/api/modules/` (`commBook.uts`, `finance.uts`, `platform.uts`, `user`, `wxmini.uts`)
- Request and login: `mini/utils/request.uts`, `mini/utils/login.uts`
- State: `mini/store/index.uts`
- Config: `mini/config/index.uts` (API URL, timeout, storage key prefix)
- Native modules: `uni_modules/` (45 modules)
- API docs: `mini/doc/plat-xiaolan.html` / `mini/doc/plat-xiaolan.html.md`

## 5. Configuration and Ports

| Item | File | Value |
| --- | --- | --- |
| Default profile | `backend/src/main/resources/application.yml` | `prod` |
| Dev port / context | `application-dev.yml` | `8081` / `/xiaolan_backend` |
| Prod port / context | `application-prod.yml` | `8310` / `/xiaolan_backend` |
| Primary datasource | `application-dev.yml` | `jdbc:mysql://localhost:3306/basedb_1.0` |
| Secondary datasource | `application-dev.yml` | `jdbc:mysql://localhost:3306/xiaolandb_1.0` (`enabled: false`) |
| Mobile API | `mini/config/index.uts` | `https://linlan.net/xiaolan_backend` |

> Inject database credentials, Redis password and WeChat AppID/Secret externally; never commit them. The default profile is `prod`; use `--spring.profiles.active=dev` locally.

## 6. Conventions

- New back-end features follow the `entity` / `dto` / `param` / `dao` / `service` / `controller` layering and update Mapper XML
- Queries involving user data default to the current user scope
- WeChat configuration stays in `manage/wechat`; new capabilities update `WxServiceConfig`
- Mobile API calls go through `mini/api/modules/`; pages must not build URLs directly
- Database changes update `backend/scripts/sql/` and `backend/CHANGELOG.md`
- Keep `README.md` and `README.en.md` in sync; record changes in the root `CHANGELOG.md` and the sub-project changelog

## 7. Documentation Index

| Document | Content |
| --- | --- |
| [CHANGELOG.md](./CHANGELOG.md) | Repository index and back-end / mobile changes |
| [AGENTS.md](./AGENTS.md) | Layout, commands and constraints for AI agents |
| [llms.txt](./llms.txt) | Machine-readable summary and command index |
| [backend/README.en.md](./backend/README.en.md) | Back-end sub-project guide |
| [backend/CHANGELOG.md](./backend/CHANGELOG.md) | Detailed back-end changelog |
| [backend/FAQ.md](./backend/FAQ.md) | Branches and FAQ |
| [mini/doc/plat-xiaolan.html.md](./mini/doc/plat-xiaolan.html.md) | Platform API docs |

## Contributing

1. Fork the repository
2. Create a `Feat_1.0.0` branch
3. Commit your changes
4. Open a Pull Request

```bash
git config user.name linlaninfo
git config user.email linlannet@163.com

Local build: mvn clean install
Deploy:      mvn clean deploy
```

## License

[Apache 2.0 © linlan-xiaolan](./LICENSE)