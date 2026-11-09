# 版本

> 本文件是 linlan-xiaolan 仓库级变更索引；后端明细以 `backend/CHANGELOG.md` 为准，移动端以 `mini/manifest.json` 的 `versionName` 为准。
> **变更记录约定**
> - 每个版本标注：版本号、日期、变更类型、影响范围、更新内容
> - 变更类型：`新增` / `变更` / `修复` / `移除` / `文档` / `构建`
> - 影响范围使用 `backend` / `mini` / `文档`
> - 历史条目保留原始事实描述；详细描述引用 `backend/CHANGELOG.md`，不重复扩写未经确认的细节
> - AI Agent 修改代码后，应同步更新本文件与对应子工程 CHANGELOG

## 版本索引

| 版本 | 日期 | 影响范围 | 摘要 |
| --- | --- | --- | --- |
| 1.2.3 | 2026-11-09 | backend、mini | 数据库访问方式、账号权限收敛、EFRAME 1.2.3，mini用户功能调整 |
| 1.2.2 | 2026-07-12 | backend | 数据库访问方式、账号权限收敛、EFRAME 1.2.1 |
| 1.2.1 | 2026-03-09 | backend | 官网四类资源接入、四张表放入第一数据源 |
| 1.2.0 | 2026-03-09 | backend | EFRAME 升级、JDK 17、微信配置 |
| 1.1.3 | 2025-11-25 | backend | code 改为 int、ResponseEntity |
| 1.1.2 | 2025-11-25 | backend | 小程序登录、unionId/openId、默认查询本人记录 |
| 1.1.1 | 2025-11-19 | backend | 枚举、微信/支付宝整合、记账本查询 |
| 1.1.0 | 2025-11-17 | backend、mini | 分类/心愿/提醒表、小程序代码、doc 参数 |
| 1.0.0 | 2025-11-12 | backend、文档 | 初始化工程与 README/CHANGELOG |
| mini 0.0.1 | — | mini | 移动端初始版本（`manifest.json`） |
| mini 0.0.1-lib | 2026-11-08 | mini | 接入 lib-vue-base UTS 请求/登录模板，统一鉴权与错误处理 |

## 版本号：1.2.3
- 更新日期：2026-11-09
- 变更类型：变更
- 影响范围：backend、mini
- 更新内容：
1. 更新数据库访问方式
2. 调整数据库 lldba 权限
3. 同步更新eframe版本
4. 更新mini版本和资源

## 版本号：1.2.2
- 更新日期：2026-07-12
- 变更类型：变更
- 影响范围：backend
- 更新内容：
1. 更新数据库访问方式
2. 调整数据库 root 和 lldba 权限
3. 将 admin 用户调整为仅 eframedb_1.0，控制风险
4. eframe 版本调整为 1.2.1
- 详见：`backend/CHANGELOG.md`

## 版本号：1.2.1
- 更新日期：2026-03-09
- 变更类型：新增、变更
- 影响范围：backend
- 更新内容：
1. 网站的四类资源添加到应用
2. 启动并进行测试
3. 四张数据表，放到第一个数据源里面

## 版本号：1.2.0
- 更新日期：2026-03-09
- 变更类型：变更、新增
- 影响范围：backend
- 更新内容：
1. 更新 eframe 版本
2. jdk 升级为 17
3. 增加 WxServiceConfig 文件，配置微信公众号和小程序

## 版本号：1.1.3
- 更新日期：2025-11-25
- 变更类型：变更
- 影响范围：backend
- 更新内容：
1. 更新 eframe 版本，接口返回 code 调整为 int
2. ResponseResult 修改为 ResponseEntity

## 版本号：1.1.2
- 更新日期：2025-11-25
- 变更类型：新增、变更、修复
- 影响范围：backend
- 更新内容：
1. 增加小程序登录处理逻辑
2. 更新 unionId 和 openId 处理
3. 对登录部分功能进行优化调整
4. xiaolan 内默认查询自己的记录
5. entity 内新增默认用户的处理方法
6. list 内新增默认查询的参数设定

## 版本号：1.1.1
- 更新日期：2025-11-19
- 变更类型：新增、变更
- 影响范围：backend
- 更新内容：
1. 新增 Enum 类
2. 整合优化微信、支付宝的代码
3. 修改记账本的方法，增加字段和日期区间查询逻辑

## 版本号：1.1.0
- 更新日期：2025-11-17
- 变更类型：新增、变更
- 影响范围：backend、mini、文档
- 更新内容：
1. 创建分类、心愿、提醒等表及资源
2. 修改微信小程序代码
3. 调整应用的 doc 文档生成参数

## 版本号：1.0.0
- 更新日期：2025-11-12
- 变更类型：新增、文档
- 影响范围：backend、文档
- 更新内容：
1. 初始化工程目录
2. 增加根目录的 README、CHANGELOG

## 移动端版本：0.0.1
- 变更类型：新增
- 影响范围：mini
- 更新内容：
1. 初始化 uni-app x 工程（`manifest.json` 的 `versionName` 为 `0.0.1`）
2. 提供首页、记账本、我的等 tabbar 页面
3. 提供 commBook、finance、platform、user、wxmini 接口模块
4. 集成 45 个 uni_modules
- 备注：移动端未单独维护 CHANGELOG，后续版本请在此追加并同步 `manifest.json`
## 版本号：mini 0.0.1-lib
- 更新日期：2026-11-08
- 变更类型：变更、修复
- 影响范围：mini
- 更新内容：
1. `mini/utils/request.uts` 改为统一请求模板：令牌头、超时、GET 防缓存、业务码、401 跳转、加载与错误提示集中处理
2. `mini/utils/login.uts` 改为统一登录工具：令牌与用户信息键与 `mini/config/index.uts` 保持一致（`linlan_token`、`linlan_user_info`）
3. 新增 `mini/utils/config.uts` 请求环境配置，支持 `initRequestEnv` 与 `setRequestEnv` 部分覆盖
4. `mini/main.uts` 启动时使用 `config/index.uts` 的 `API_BASE_URL` 初始化请求根地址
5. 保持页面与 `api/modules` 的既有调用方式与返回结构（返回完整 `ApiResponse`）
- 验证方式：HBuilderX 打开 `mini/` 运行到 Android 或微信小程序
