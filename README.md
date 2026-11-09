# linlan-xiaolan

简体中文 | [English](./README.en.md)

> **TL;DR** · 「览生活 / 小览生活」全栈工程：Spring Boot 后端服务（xiaolan-backend）+ uni-app x 移动端（mini），覆盖官网内容、记账本、心愿、提醒、微信登录等能力。
> **Keywords**: Spring Boot, Java 17, MyBatis, eframe, uni-app x, UTS, Vue3, WeChat Mini Program
> **AI 入口**: [llms.txt](./llms.txt) · [AGENTS.md](./AGENTS.md)

[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](./LICENSE)
![java](https://img.shields.io/badge/Java-17-orange.svg)
![spring boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)
![uni-app x](https://img.shields.io/badge/uni--app%20x-UTS-blue.svg)

## 简介

麟览信息数据服务网旗下「览生活 / 小览生活」业务工程，包含服务端与移动端两个子工程：

- **backend**：基于 EFRAME 企业框架的 Spring Boot 服务端，提供官网内容、记账本、分类、心愿、提醒、微信登录等接口
- **mini**：基于 uni-app x（UVue + UTS）的移动端应用，面向 Android 与微信小程序

## 版本历史

全部版本变更见 [CHANGELOG](./CHANGELOG.md)。后端版本 1.2.2，移动端 `manifest.json` 版本 `0.0.1`。

## 目录

- [1. 仓库组成](#1-仓库组成)
- [2. 快速开始](#2-快速开始)
- [3. 后端说明](#3-后端说明)
- [4. 移动端说明](#4-移动端说明)
- [5. 配置与端口](#5-配置与端口)
- [6. 开发约定](#6-开发约定)
- [7. 文档索引](#7-文档索引)
- [参与贡献](#参与贡献)
- [版权](#版权)

---

## 1. 仓库组成

| 目录 | 说明 | 技术栈 |
| ---- | ---- | ---- |
| [backend](./backend) | 小览生活服务端（`net.linlan.plat:xiaolan-backend`） | Java 17 + Spring Boot + Spring Security + MyBatis + Druid + EFRAME 1.2.1 |
| [mini](./mini) | 小览生活移动端 | uni-app x（UVue + UTS）+ 45 个 uni_modules |

业务范围：
- 官网内容：新闻、服务、解决方案、联系我们（`net.linlan.www.comm`）
- 小览生活：记账本、分类、心愿、提醒、心情记录（`net.linlan.xiaolan`）
- 微信能力：公众号、小程序、开放平台（`net.linlan.manage.wechat`）
- 平台状态：版本状态监控接口（`net.linlan.stage`）

## 2. 快速开始

### 2.1 环境要求

- JDK 17、Maven 3.8+
- MySQL（默认 `basedb_1.0` / `xiaolandb_1.0`）
- HBuilderX（运行 uni-app x 移动端，`mini/` 为目标工程）
- 已安装 EFRAME 1.2.1 到本地 Maven 仓库（`linlan-eframe/backend` 执行 `mvn clean install`）

### 2.2 后端构建与启动

```bash
cd backend
mvn clean install
mvn clean package -DskipTests

# 运行
java -jar target/xiaolan-backend.jar --spring.profiles.active=dev
```

启动类：`backend/src/main/java/net/linlan/FrameApplication.java`

### 2.3 移动端运行

1. 使用 HBuilderX 打开 `mini/` 目录
2. 选择运行到 Android 真机 / 模拟器，或微信小程序
3. 接口地址由 `mini/config/index.uts` 中的 `API_BASE_URL` 控制，默认 `https://linlan.net/xiaolan_backend`

> `mini/` 未提供 npm 脚本，构建与运行以 HBuilderX 的 uni-app x 工具链为准。

## 3. 后端说明

### 3.1 包结构

```text
backend/src/main/java/net/linlan/
├── FrameApplication.java      启动类
├── config/                    配置与属性类
├── status/                    状态相关
├── stage/                     平台阶段：security、api（状态监控）
├── www/comm/                  官网内容：新闻、服务、解决方案、联系
├── manage/wechat/             微信：公众号、小程序、开放平台
└── xiaolan/
    ├── comm/                  分类、记账本等公共业务
    └── my/                    我的：BookInfo、MindRecord、CheckRecord、WishInfo
```

### 3.2 分层约定

- `entity` / `dto` / `param` / `dao` / `service` / `controller` 分层组织
- Mapper XML 位于 `backend/src/main/resources/mapper/`，按包路径镜像
- 接口统一返回框架封装；状态码使用整型（1.1.3 起）
- 默认查询当前用户自己的记录（1.1.2 起）

### 3.3 数据与脚本

- SQL 脚本：`backend/scripts/sql/`（`basedb_1.0.sql`、`xiaolan_initial.sql`、`xiaolan_structure_1.0.sql`、`linlan_initial.sql`、`linlannet.sql`）
- 开发数据源：`basedb_1.0`（第一数据源）、`xiaolandb_1.0`（第二数据源，默认关闭）
- 代码风格：`backend/scripts/codestyle/`

后端详细说明：[backend/README.md](./backend/README.md) · [backend/CHANGELOG.md](./backend/CHANGELOG.md) · [backend/FAQ.md](./backend/FAQ.md)

## 4. 移动端说明

- 框架：uni-app x，页面使用 `.uvue`，逻辑使用 `.uts`
- 页面：`pages/tabbar/home`、`pages/tabbar/finance`（记账本、日历、新增记录）、`pages/tabbar/my`（心愿、账户、励志语录）、`pages/tabbar/index`、组件示例页
- 接口封装：`mini/api/modules/`（`commBook.uts`、`finance.uts`、`platform.uts`、`user`、`wxmini.uts`）
- 请求与登录：`mini/utils/request.uts`、`mini/utils/login.uts`
- 状态管理：`mini/store/index.uts`
- 配置：`mini/config/index.uts`（API 地址、超时、存储键前缀）
- 原生能力：`uni_modules/`（45 个模块）
- 接口文档：`mini/doc/plat-xiaolan.html` / `mini/doc/plat-xiaolan.html.md`

## 5. 配置与端口

| 配置 | 文件 | 值 |
| --- | --- | --- |
| 默认 profile | `backend/src/main/resources/application.yml` | `prod` |
| 开发端口 / 上下文 | `application-dev.yml` | `8081` / `/xiaolan_backend` |
| 生产端口 / 上下文 | `application-prod.yml` | `8310` / `/xiaolan_backend` |
| 第一数据源 | `application-dev.yml` | `jdbc:mysql://localhost:3306/basedb_1.0` |
| 第二数据源 | `application-dev.yml` | `jdbc:mysql://localhost:3306/xiaolandb_1.0`（`enabled: false`） |
| 移动端 API | `mini/config/index.uts` | `https://linlan.net/xiaolan_backend` |

> 生产环境请通过外部配置注入数据库账号、Redis 密码与微信 AppID/Secret，不要提交到仓库。默认 profile 为 `prod`，本地开发请显式指定 `--spring.profiles.active=dev`。

## 6. 开发约定

- 后端新增业务按 `entity` / `dto` / `param` / `dao` / `service` / `controller` 分层，并同步 Mapper XML
- 涉及用户数据的查询默认限定当前用户范围
- 微信相关配置集中在 `manage/wechat`，新增能力需同步 `WxServiceConfig`
- 移动端接口统一走 `mini/api/modules/`，页面不直接拼接请求地址
- 数据库变更同步更新 `backend/scripts/sql/` 与 `backend/CHANGELOG.md`
- `README.md` 与 `README.en.md` 同步维护；版本变更记录到根 `CHANGELOG.md` 与子工程 CHANGELOG

## 7. 文档索引

| 文档 | 内容 |
| --- | --- |
| [CHANGELOG.md](./CHANGELOG.md) | 仓库级版本索引与后端 / 移动端变更 |
| [AGENTS.md](./AGENTS.md) | AI Agent 使用的结构、命令与约束 |
| [llms.txt](./llms.txt) | 机器可读的项目摘要与命令索引 |
| [backend/README.md](./backend/README.md) | 后端子工程说明 |
| [backend/CHANGELOG.md](./backend/CHANGELOG.md) | 后端详细版本记录 |
| [backend/FAQ.md](./backend/FAQ.md) | 分支与常见问题 |
| [mini/doc/plat-xiaolan.html.md](./mini/doc/plat-xiaolan.html.md) | 平台接口文档 |

## 参与贡献

1. Fork 本仓库
2. 新建 `Feat_1.0.0` 分支
3. 提交代码
4. 新建 Pull Request

```bash
git config user.name linlaninfo
git config user.email linlannet@163.com

本地编译：mvn clean install
发布中央库：mvn clean deploy
```

## 版权

[Apache 2.0 © linlan-xiaolan](./LICENSE)