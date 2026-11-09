# AGENTS.md — linlan-xiaolan

## Project
「览生活 / 小览生活」全栈工程：Spring Boot 服务端（`xiaolan-backend` 1.2.2）+ uni-app x 移动端（`mini` 0.0.1）。

## Layout
- `backend/` Java 17 + Spring Boot，parent 为 EFRAME 1.2.1
  - `src/main/java/net/linlan/www/comm` 官网内容（新闻、服务、解决方案、联系）
  - `src/main/java/net/linlan/xiaolan/comm` 分类、记账本
  - `src/main/java/net/linlan/xiaolan/my` BookInfo、MindRecord、CheckRecord、WishInfo
  - `src/main/java/net/linlan/manage/wechat` 公众号 / 小程序 / 开放平台
  - `src/main/java/net/linlan/stage` security、状态监控
  - `src/main/resources/mapper/` MyBatis XML
  - `scripts/sql/` 数据库脚本；`scripts/codestyle/` 代码风格
- `mini/` uni-app x
  - `pages/tabbar/` 首页、记账本（finance）、我的（my）
  - `api/modules/` commBook、finance、platform、user、wxmini
  - `utils/request.uts`、`utils/login.uts`、`store/index.uts`、`config/index.uts`
  - `uni_modules/` 45 个原生模块；`doc/` 接口文档

## Build & Test
- 前置：在 `linlan-eframe/backend` 执行 `mvn clean install`（安装 EFRAME 1.2.1）
- 后端构建：`cd backend && mvn clean install`；打包 `mvn clean package -DskipTests`
- 后端运行：`java -jar backend/target/xiaolan-backend.jar --spring.profiles.active=dev`
- 后端测试：`mvn test`（仓库未包含测试用例时以构建通过为准）
- 移动端：HBuilderX 打开 `mini/`，运行到 Android 或微信小程序；无 npm 脚本

## Runtime
- 默认 profile：`prod`
- dev：8081，上下文 `/xiaolan_backend`
- prod：8310，上下文 `/xiaolan_backend`
- 数据源：`basedb_1.0`（主）、`xiaolandb_1.0`（次，默认关闭）
- 移动端 API：`mini/config/index.uts` 的 `API_BASE_URL`

## Conventions
- 后端分层：entity / dto / param / dao / service / controller，Mapper XML 按包路径镜像
- 用户数据查询默认限定当前用户
- 微信配置集中在 `manage/wechat`，新增能力同步 `WxServiceConfig`
- 移动端接口统一走 `api/modules/`，页面不直接拼接 URL
- 新增表同步 `scripts/sql/` 与 `backend/CHANGELOG.md`
- `README.md` 与 `README.en.md` 同步维护；版本变更同步根 `CHANGELOG.md`

## Do Not
- 不提交 `target/`、`unpackage/`、`.idea/`、`.DS_Store`
- 不提交数据库密码、Redis 密码、微信 AppID/Secret、Token
- 不直接执行生产数据库变更或批量数据操作
- 不把业务逻辑写进 Controller
- 不擅自升级 EFRAME 依赖版本（需与后端 CHANGELOG 一致）

## Key Docs
- `README.md` / `README.en.md` / `llms.txt`
- `backend/README.md` / `backend/CHANGELOG.md` / `backend/FAQ.md`
- `mini/doc/plat-xiaolan.html.md`