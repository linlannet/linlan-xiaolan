# plat-xiaolan
Version |  Update Time  | Status | Author |  Description
---|---|---|---|---
1.0|2025-08-24 10:30|update|Linlan|desc



# default
# 平台通用接口API
## 平台当前监控状态
### 每次发版前，将版本号进行调整，以明确当前发布的版本和启动的版本是一致的
**URL:** https://linlan.net/xiaolan_backend/api/stage/getStatus.do

**Type:** GET

**Author:** Linlan
CreateTime:12/19/17 9:00 PM

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 每次发版前，将版本号进行调整，以明确当前发布的版本和启动的版本是一致的

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
any object|object|any object.|false|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/stage/getStatus.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─any object|object|any object.|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "mapKey": {
      "waring": "You may use java.util.Object for Map value; doc-generate can't be handle."
    }
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

# 一体化平台接口API
# 小览生活接口API
## 公共记账本读写控制类
### 获取公共记账本列表或分页, 返回CommBook对象.
**URL:** https://linlan.net/xiaolan_backend/api/comm/book/list.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取公共记账本列表或分页, 返回CommBook对象.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
q|string|关键字：查询输入条件q，可匹配name、title、keywords等文本信息|false|-
orderBy|int32|排序，默认主键倒序|false|-
startTime|string|开始时间|false|-
endTime|string|结束时间|false|-
page|int32|分页信息：默认第1页|false|-
limit|int32|分页每页条数，默认10条|false|-
id|int64|记账本台账主键ID|false|-
templateId|string|模板ID|false|-
type|string|记账本台账类型，TIXING提醒，ZIXUN咨询|false|-
name|string|记账本台账名称|false|-
code|string|记账本台账代码|false|-
status|string|状态，0异常关闭1正常2升级3锁定|false|-
creatorId|string|创建者ID|false|-
creatorName|string|创建者显示名称|false|-
delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
description|string|描述|false|-
spare1|string|备用1|false|-
spare2|string|备用2|false|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/book/list.do?page=1&type=2sjo7d&creatorId=54&id=63&status=qe13v9&startTime=2025-11-17 17:31:11&spare1=pn8vr5&description=e2vqb2&limit=10&templateId=54&q=mvi6k4&orderBy=682&code=60627&endTime=2025-11-17 17:31:11&delFlag=2&creatorName=lanny.hartmann&spare2=un8nle&name=lanny.hartmann
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|array|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|记账本台账主键ID|-
└─templateId|string|模板ID|-
└─type|string|记账本台账类型，TIXING提醒，ZIXUN咨询|-
└─name|string|记账本台账名称|-
└─code|string|记账本台账代码|-
└─status|string|状态，0异常关闭1正常2升级3锁定|-
└─priority|int32|排序|-
└─creatorName|string|创建者显示名称|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": [
    {
      "creatorId": "54",
      "createTime": "2025-11-17 17:31:38",
      "lastTime": "2025-11-17 17:31:38",
      "id": 423,
      "templateId": "54",
      "type": "k8vrbo",
      "name": "lanny.hartmann",
      "code": "60627",
      "status": "ljkurn",
      "priority": 160,
      "creatorName": "lanny.hartmann",
      "delFlag": 2,
      "description": "qmct81",
      "spare1": "bics7w",
      "spare2": "7fj53r"
    }
  ],
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 主键获取公共记账本详细信息.
**URL:** https://linlan.net/xiaolan_backend/api/comm/book/{id}.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 主键获取公共记账本详细信息.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int64|记账本台账主键ID|true|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/book/1.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|记账本台账主键ID|-
└─templateId|string|模板ID|-
└─type|string|记账本台账类型，TIXING提醒，ZIXUN咨询|-
└─name|string|记账本台账名称|-
└─code|string|记账本台账代码|-
└─status|string|状态，0异常关闭1正常2升级3锁定|-
└─priority|int32|排序|-
└─creatorName|string|创建者显示名称|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:38",
    "lastTime": "2025-11-17 17:31:38",
    "id": 413,
    "templateId": "54",
    "type": "kuhebn",
    "name": "lanny.hartmann",
    "code": "60627",
    "status": "w717fk",
    "priority": 761,
    "creatorName": "lanny.hartmann",
    "delFlag": 2,
    "description": "5xgzkm",
    "spare1": "agod5j",
    "spare2": "236jok"
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 新增公共记账本数据操作逻辑，根据操作类型，执行新增操作.
**URL:** https://linlan.net/xiaolan_backend/api/comm/book/save.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/json; charset=utf-8

**Description:** 新增公共记账本数据操作逻辑，根据操作类型，执行新增操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|记账本台账主键ID|false|-
└─templateId|string|模板ID|false|-
└─type|string|记账本台账类型，TIXING提醒，ZIXUN咨询|false|-
└─name|string|记账本台账名称|false|-
└─code|string|记账本台账代码|false|-
└─status|string|状态，0异常关闭1正常2升级3锁定|false|-
└─priority|int32|排序|false|-
└─creatorName|string|创建者显示名称|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/book/save.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:38",
    "lastTime": "2025-11-17 17:31:38",
    "id": 0,
    "templateId": "54",
    "type": "058ir4",
    "name": "lanny.hartmann",
    "code": "60627",
    "status": "gl5t0k",
    "priority": 471,
    "creatorName": "lanny.hartmann",
    "delFlag": 2,
    "description": "yqg3oh",
    "spare1": "5uupow",
    "spare2": "tp5d5a"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "tq5hhg",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 修改公共记账本数据操作逻辑，根据操作类型，执行修改操作.
**URL:** https://linlan.net/xiaolan_backend/api/comm/book/update.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/json; charset=utf-8

**Description:** 修改公共记账本数据操作逻辑，根据操作类型，执行修改操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|记账本台账主键ID|false|-
└─templateId|string|模板ID|false|-
└─type|string|记账本台账类型，TIXING提醒，ZIXUN咨询|false|-
└─name|string|记账本台账名称|false|-
└─code|string|记账本台账代码|false|-
└─status|string|状态，0异常关闭1正常2升级3锁定|false|-
└─priority|int32|排序|false|-
└─creatorName|string|创建者显示名称|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/book/update.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:38",
    "lastTime": "2025-11-17 17:31:38",
    "id": 791,
    "templateId": "54",
    "type": "crx6bm",
    "name": "lanny.hartmann",
    "code": "60627",
    "status": "pjp94u",
    "priority": 236,
    "creatorName": "lanny.hartmann",
    "delFlag": 2,
    "description": "a0qd0h",
    "spare1": "423pbk",
    "spare2": "zly7cc"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "9qbz0s",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 删除公共记账本数据操作逻辑，根据操作类型，执行删除操作.
**URL:** https://linlan.net/xiaolan_backend/api/comm/book/delete/{ids}.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 删除公共记账本数据操作逻辑，根据操作类型，执行删除操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
ids|array|记账本台账主键ID:null;1;1,2,3,[array of int64]|true|

**Request-example:**
```
curl -X POST -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/book/delete/.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "0zsvvz",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 微信小程序接口相关控制器
### 获取微信扫码登录验证码
**URL:** https://linlan.net/xiaolan_backend/api/open/wxmini/getCode.do

**Type:** GET

**Author:** Linlan

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取微信扫码登录验证码

**Request-example:**
```
curl -X GET -k -i https://linlan.net/xiaolan_backend/api/open/wxmini/getCode.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "7idja9",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 获取微信扫码是否已登录
**URL:** https://linlan.net/xiaolan_backend/api/open/wxmini/isLogin/{loginCode}.do

**Type:** GET

**Author:** Linlan

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取微信扫码是否已登录

**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
loginCode|string|登录代码|true|-

**Request-example:**
```
curl -X GET -k -i https://linlan.net/xiaolan_backend/api/open/wxmini/isLogin/60627.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─user|object|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─id|int64|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─userId|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─xzqhId|int64|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─source|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─username|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─mobile|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─email|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─password|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─organId|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─foreignId|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─accountId|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─accountType|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─unionId|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─openId|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─nickName|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─avatarUrl|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─registerTime|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─lastLoginTime|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─lastLoginIp|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─loginCount|int32|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─lockTime|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─activation|boolean|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─activationCode|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─priority|int32|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─createTime|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─delFlag|int32|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─description|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─spare1|string|No comments found.|-
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;└─spare2|string|No comments found.|-
└─roles|array|No comments found.|-
└─permissions|array|No comments found.|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "user": {
      "id": 114,
      "userId": "54",
      "xzqhId": 989,
      "source": "ytqtoz",
      "username": "lanny.hartmann",
      "mobile": "585.505.4669",
      "email": "buck.berge@hotmail.com",
      "password": "ev730u",
      "organId": "54",
      "foreignId": "54",
      "accountId": "54",
      "accountType": "863m1b",
      "unionId": "54",
      "openId": "54",
      "nickName": "angella.hahn",
      "avatarUrl": "www.keena-wolff.name",
      "registerTime": "2025-11-17 17:31:38",
      "lastLoginTime": "2025-11-17 17:31:38",
      "lastLoginIp": "8.248.82.176",
      "loginCount": 519,
      "lockTime": "2025-11-17 17:31:38",
      "activation": true,
      "activationCode": "60627",
      "priority": 268,
      "createTime": "2025-11-17 17:31:38",
      "delFlag": 2,
      "description": "qrszt7",
      "spare1": "qpxzuw",
      "spare2": "wo4ga8"
    },
    "roles": [
      {
        "object": "any object"
      }
    ],
    "permissions": [
      {
        "object": "any object"
      }
    ]
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 微信小程序登录
**URL:** https://linlan.net/xiaolan_backend/api/open/wxmini/login/{code}/{mobile}.do

**Type:** GET

**Author:** Linlan

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 微信小程序登录

**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
code|string|代码|true|-
mobile|string|手机号码|true|-

**Request-example:**
```
curl -X GET -k -i https://linlan.net/xiaolan_backend/api/open/wxmini/login/60627/585.505.4669.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─userId|string|用户ID|-
└─token|string|令牌TOKEN|-
└─updateTime|string|更新时间|-
└─expireTime|string|过期时间|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "userId": "54",
    "token": "ffhx0z",
    "updateTime": "2025-11-17 17:31:38",
    "expireTime": "2025-11-17 17:31:38"
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 微信开放平台相关控制器
### 微信公众号token验证
**URL:** https://linlan.net/xiaolan_backend/api/open/wechat/.do

**Type:** GET

**Author:** Linlan

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 微信公众号token验证

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
signature|string|签名|false|-
timestamp|string|时间|false|-
nonce|string|有效期|false|-
echostr|string|响应字符串|false|-

**Request-example:**
```
curl -X GET -k -i https://linlan.net/xiaolan_backend/api/open/wechat/.do?signature=e4q33d&timestamp=2025-11-17 17:31:11&echostr=7dfob8&nonce=u6mvpy
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "y93iaz",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 微信公众号消息对接
**URL:** https://linlan.net/xiaolan_backend/api/open/wechat/.do

**Type:** POST

**Author:** Linlan

**Content-Type:** application/json; charset=utf-8

**Description:** 微信公众号消息对接

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
signature|string|签名|true|-
timestamp|string|时间|true|-
nonce|string|有效期|true|-
encrypt_type|string|加密类型|false|-
msg_signature|string|消息签名|false|-

**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -i https://linlan.net/xiaolan_backend/api/open/wechat/.do?nonce=27u532&timestamp=2025-11-17 17:31:11&msg_signature=23sl2g&encrypt_type=z217dx&signature=16bt14 --data '{
  "requestBody": "xom5jp"
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "5erphy",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 微信服务号公众号相关控制器
### 微信公众号 获取oauth2完整授权地址
**URL:** https://linlan.net/xiaolan_backend/api/open/wxmp/getRedirect.do

**Type:** GET

**Author:** Linlan

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 微信公众号 获取oauth2完整授权地址

**Request-example:**
```
curl -X GET -k -i https://linlan.net/xiaolan_backend/api/open/wxmp/getRedirect.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─any object|object|any object.|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {},
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 微信公众号 oauth2授权后 获取用户信息
**URL:** https://linlan.net/xiaolan_backend/api/open/wxmp/login.do

**Type:** GET

**Author:** Linlan

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 微信公众号 oauth2授权后 获取用户信息

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
code|string| 代码|false|-

**Request-example:**
```
curl -X GET -k -i https://linlan.net/xiaolan_backend/api/open/wxmp/login.do?code=60627
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─any object|object|any object.|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {},
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 微信公众号 页面端原生能力调用
**URL:** https://linlan.net/xiaolan_backend/api/open/wxmp/getJsapiTicket.do

**Type:** GET

**Author:** Linlan

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 微信公众号 页面端原生能力调用

**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
url|string|  路径URL|true|-

**Request-example:**
```
curl -X GET -k -i https://linlan.net/xiaolan_backend/api/open/wxmp/getJsapiTicket.do?url=www.keena-wolff.name
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─any object|object|any object.|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {},
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 心愿提醒任务记录读写控制类
### 获取心愿提醒任务记录列表或分页, 返回MindRecord对象.
**URL:** https://linlan.net/xiaolan_backend/api/my/mindrecord/list.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取心愿提醒任务记录列表或分页, 返回MindRecord对象.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
q|string|关键字：查询输入条件q，可匹配name、title、keywords等文本信息|false|-
orderBy|int32|排序，默认主键倒序|false|-
startTime|string|开始时间|false|-
endTime|string|结束时间|false|-
page|int32|分页信息：默认第1页|false|-
limit|int32|分页每页条数，默认10条|false|-
id|int64|提醒主键ID|false|-
scheduleId|string|任务ID|false|-
wishId|int64|心愿目标主键ID|false|-
memberId|int64|会员主键ID|false|-
viewName|string|创建者显示名称|false|-
accountType|string|第三方身份类型，wechat_mini|false|-
accountKey|string|第三方OpenId|false|-
messageTplId|string|消息模板|false|-
title|string|消息标题|false|-
content|string|消息内容|false|-
status|int32|任务状态:0未启动1正常2暂停|false|-
description|string|描述|false|-
spare1|string|备用1|false|-
spare2|string|备用2|false|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/mindrecord/list.do?spare2=oanubu&page=1&startTime=2025-11-17 17:31:11&viewName=lanny.hartmann&scheduleId=54&limit=10&id=336&q=jxi9sd&memberId=4&accountType=fi442b&title=t4cjsu&orderBy=534&status=895&accountKey=6ndq3k&description=ti75u0&content=d19j9p&endTime=2025-11-17 17:31:11&messageTplId=54&spare1=pw9gmt&wishId=757
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|array|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|提醒主键ID|-
└─scheduleId|string|任务ID|-
└─wishId|int64|心愿目标主键ID|-
└─memberId|int64|会员主键ID|-
└─userId|string|创建者ID|-
└─viewName|string|创建者显示名称|-
└─accountType|string|第三方身份类型，wechat_mini|-
└─accountKey|string|第三方OpenId|-
└─messageTplId|string|消息模板|-
└─title|string|消息标题|-
└─content|string|消息内容|-
└─status|int32|任务状态:0未启动1正常2暂停|-
└─priority|int32|排序|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": [
    {
      "creatorId": "54",
      "createTime": "2025-11-17 17:31:40",
      "lastTime": "2025-11-17 17:31:40",
      "id": 915,
      "scheduleId": "54",
      "wishId": 917,
      "memberId": 953,
      "userId": "54",
      "viewName": "lanny.hartmann",
      "accountType": "ozcf6r",
      "accountKey": "2ypko3",
      "messageTplId": "54",
      "title": "xoftdg",
      "content": "zolf93",
      "status": 360,
      "priority": 125,
      "description": "l4u5p9",
      "spare1": "uz2sbn",
      "spare2": "le9yzo"
    }
  ],
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 主键获取心愿提醒任务记录详细信息.
**URL:** https://linlan.net/xiaolan_backend/api/my/mindrecord/{id}.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 主键获取心愿提醒任务记录详细信息.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int64|提醒主键ID|true|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/mindrecord/1.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|提醒主键ID|-
└─scheduleId|string|任务ID|-
└─wishId|int64|心愿目标主键ID|-
└─memberId|int64|会员主键ID|-
└─userId|string|创建者ID|-
└─viewName|string|创建者显示名称|-
└─accountType|string|第三方身份类型，wechat_mini|-
└─accountKey|string|第三方OpenId|-
└─messageTplId|string|消息模板|-
└─title|string|消息标题|-
└─content|string|消息内容|-
└─status|int32|任务状态:0未启动1正常2暂停|-
└─priority|int32|排序|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:40",
    "lastTime": "2025-11-17 17:31:40",
    "id": 981,
    "scheduleId": "54",
    "wishId": 504,
    "memberId": 781,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "accountType": "13hrsl",
    "accountKey": "s3cpt1",
    "messageTplId": "54",
    "title": "cui7f8",
    "content": "ns1vg0",
    "status": 864,
    "priority": 507,
    "description": "lgqjhi",
    "spare1": "3ripie",
    "spare2": "j99hef"
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 新增心愿提醒任务记录数据操作逻辑，根据操作类型，执行新增操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/mindrecord/save.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/json; charset=utf-8

**Description:** 新增心愿提醒任务记录数据操作逻辑，根据操作类型，执行新增操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|提醒主键ID|false|-
└─scheduleId|string|任务ID|false|-
└─wishId|int64|心愿目标主键ID|false|-
└─memberId|int64|会员主键ID|false|-
└─userId|string|创建者ID|false|-
└─viewName|string|创建者显示名称|false|-
└─accountType|string|第三方身份类型，wechat_mini|false|-
└─accountKey|string|第三方OpenId|false|-
└─messageTplId|string|消息模板|false|-
└─title|string|消息标题|false|-
└─content|string|消息内容|false|-
└─status|int32|任务状态:0未启动1正常2暂停|false|-
└─priority|int32|排序|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/mindrecord/save.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:40",
    "lastTime": "2025-11-17 17:31:40",
    "id": 801,
    "scheduleId": "54",
    "wishId": 593,
    "memberId": 494,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "accountType": "39icsh",
    "accountKey": "q9ofx4",
    "messageTplId": "54",
    "title": "d7bgdu",
    "content": "m8iqic",
    "status": 561,
    "priority": 537,
    "description": "n59d0c",
    "spare1": "2kwuwa",
    "spare2": "e3yyhb"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "k8ibvs",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 修改心愿提醒任务记录数据操作逻辑，根据操作类型，执行修改操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/mindrecord/update.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/json; charset=utf-8

**Description:** 修改心愿提醒任务记录数据操作逻辑，根据操作类型，执行修改操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|提醒主键ID|false|-
└─scheduleId|string|任务ID|false|-
└─wishId|int64|心愿目标主键ID|false|-
└─memberId|int64|会员主键ID|false|-
└─userId|string|创建者ID|false|-
└─viewName|string|创建者显示名称|false|-
└─accountType|string|第三方身份类型，wechat_mini|false|-
└─accountKey|string|第三方OpenId|false|-
└─messageTplId|string|消息模板|false|-
└─title|string|消息标题|false|-
└─content|string|消息内容|false|-
└─status|int32|任务状态:0未启动1正常2暂停|false|-
└─priority|int32|排序|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/mindrecord/update.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:40",
    "lastTime": "2025-11-17 17:31:40",
    "id": 646,
    "scheduleId": "54",
    "wishId": 226,
    "memberId": 481,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "accountType": "tyjhn2",
    "accountKey": "3vm714",
    "messageTplId": "54",
    "title": "jz65z1",
    "content": "s188u9",
    "status": 749,
    "priority": 691,
    "description": "z7zuv4",
    "spare1": "wn6ap8",
    "spare2": "egqpfr"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "wgjap3",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 删除心愿提醒任务记录数据操作逻辑，根据操作类型，执行删除操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/mindrecord/delete/{ids}.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 删除心愿提醒任务记录数据操作逻辑，根据操作类型，执行删除操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
ids|array|提醒主键ID:null;1;1,2,3,[array of int64]|true|

**Request-example:**
```
curl -X POST -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/mindrecord/delete/.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "q9yvk7",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 心愿目标池读写控制类
### 获取心愿目标池列表或分页, 返回WishInfo对象.
**URL:** https://linlan.net/xiaolan_backend/api/my/wishinfo/list.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取心愿目标池列表或分页, 返回WishInfo对象.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
q|string|关键字：查询输入条件q，可匹配name、title、keywords等文本信息|false|-
orderBy|int32|排序，默认主键倒序|false|-
startTime|string|开始时间|false|-
endTime|string|结束时间|false|-
page|int32|分页信息：默认第1页|false|-
limit|int32|分页每页条数，默认10条|false|-
id|int64|心愿目标主键ID|false|-
appId|string|应用ID|false|-
templateId|string|心愿目标模板ID|false|-
memberId|int64|主键ID|false|-
viewName|string|创建者显示名称|false|-
bookId|int64|绑定记账本ID|false|-
bookName|string|绑定记账本名称|false|-
type|string|类型，可关联CATEGORY|false|-
name|string|心愿目标名称|false|-
code|string|心愿目标代码|false|-
wichLevel|string|心愿级别，1,2,3,4,5|false|-
beginTime|string|开始时间，默认当前时间|false|-
cycle|string|周期，RI日，ZHOU周，YUE月，JI季度|false|-
ifWeekendEx|boolean|周末是否例外，如果例外则不分摊每日金额，0否1是|false|-
initMoney|double|期初金额|false|-
aimMoney|double|目标金额|false|-
modeTrans|string|月结转移方式|false|-
mindContent|string|提醒配置|false|-
status|string|状态，SAVED暂存START启动PAUSE暂停FINISH已结束|false|-
delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
description|string|描述|false|-
spare1|string|备用1|false|-
spare2|string|备用2|false|-
spare3|string|备用3|false|-
spare4|string|备用4|false|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/wishinfo/list.do?description=kmgm7f&bookName=lanny.hartmann&type=pe7a5m&spare3=iv8viv&viewName=lanny.hartmann&initMoney=48.34&code=60627&cycle=0d8snl&bookId=375&templateId=54&status=2d5et0&memberId=54&endTime=2025-11-17 17:31:11&q=coeu3k&aimMoney=20.70&modeTrans=5mf12z&spare2=r4iarh&appId=54&delFlag=2&limit=10&id=140&beginTime=2025-11-17&mindContent=9f6jq0&wichLevel=7d63vn&spare4=ou35he&orderBy=427&startTime=2025-11-17 17:31:11&page=1&ifWeekendEx=true&spare1=l4qh5x&name=lanny.hartmann
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|array|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|心愿目标主键ID|-
└─appId|string|应用ID|-
└─templateId|string|心愿目标模板ID|-
└─memberId|int64|主键ID|-
└─userId|string|创建者ID|-
└─viewName|string|创建者显示名称|-
└─bookId|int64|绑定记账本ID|-
└─bookName|string|绑定记账本名称|-
└─type|string|类型，可关联CATEGORY|-
└─name|string|心愿目标名称|-
└─code|string|心愿目标代码|-
└─wichLevel|string|心愿级别，1,2,3,4,5|-
└─beginTime|string|开始时间，默认当前时间|-
└─endTime|string|结束时间|-
└─cycle|string|周期，RI日，ZHOU周，YUE月，JI季度|-
└─ifWeekendEx|boolean|周末是否例外，如果例外则不分摊每日金额，0否1是|-
└─initMoney|double|期初金额|-
└─aimMoney|double|目标金额|-
└─modeTrans|string|月结转移方式|-
└─mindContent|string|提醒配置|-
└─status|string|状态，SAVED暂存START启动PAUSE暂停FINISH已结束|-
└─priority|int32|排序|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
└─spare3|string|备用3|-
└─spare4|string|备用4|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": [
    {
      "creatorId": "54",
      "createTime": "2025-11-17 17:31:40",
      "lastTime": "2025-11-17 17:31:40",
      "id": 355,
      "appId": "54",
      "templateId": "54",
      "memberId": 388,
      "userId": "54",
      "viewName": "lanny.hartmann",
      "bookId": 703,
      "bookName": "lanny.hartmann",
      "type": "6kzy12",
      "name": "lanny.hartmann",
      "code": "60627",
      "wichLevel": "pv4ngf",
      "beginTime": "2025-11-17",
      "endTime": "2025-11-17",
      "cycle": "fcy773",
      "ifWeekendEx": true,
      "initMoney": 7.83,
      "aimMoney": 5.29,
      "modeTrans": "51l12f",
      "mindContent": "ggm41h",
      "status": "lwdswt",
      "priority": 826,
      "delFlag": 2,
      "description": "nk1g5n",
      "spare1": "q1yfz5",
      "spare2": "h07zhk",
      "spare3": "x2quk0",
      "spare4": "cqm899"
    }
  ],
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 主键获取心愿目标池详细信息.
**URL:** https://linlan.net/xiaolan_backend/api/my/wishinfo/{id}.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 主键获取心愿目标池详细信息.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int64|心愿目标主键ID|true|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/wishinfo/1.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|心愿目标主键ID|-
└─appId|string|应用ID|-
└─templateId|string|心愿目标模板ID|-
└─memberId|int64|主键ID|-
└─userId|string|创建者ID|-
└─viewName|string|创建者显示名称|-
└─bookId|int64|绑定记账本ID|-
└─bookName|string|绑定记账本名称|-
└─type|string|类型，可关联CATEGORY|-
└─name|string|心愿目标名称|-
└─code|string|心愿目标代码|-
└─wichLevel|string|心愿级别，1,2,3,4,5|-
└─beginTime|string|开始时间，默认当前时间|-
└─endTime|string|结束时间|-
└─cycle|string|周期，RI日，ZHOU周，YUE月，JI季度|-
└─ifWeekendEx|boolean|周末是否例外，如果例外则不分摊每日金额，0否1是|-
└─initMoney|double|期初金额|-
└─aimMoney|double|目标金额|-
└─modeTrans|string|月结转移方式|-
└─mindContent|string|提醒配置|-
└─status|string|状态，SAVED暂存START启动PAUSE暂停FINISH已结束|-
└─priority|int32|排序|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
└─spare3|string|备用3|-
└─spare4|string|备用4|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:40",
    "lastTime": "2025-11-17 17:31:40",
    "id": 130,
    "appId": "54",
    "templateId": "54",
    "memberId": 695,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "bookId": 676,
    "bookName": "lanny.hartmann",
    "type": "xqfhyi",
    "name": "lanny.hartmann",
    "code": "60627",
    "wichLevel": "wj8idr",
    "beginTime": "2025-11-17",
    "endTime": "2025-11-17",
    "cycle": "sju90o",
    "ifWeekendEx": true,
    "initMoney": 73.91,
    "aimMoney": 53.06,
    "modeTrans": "042re6",
    "mindContent": "2kbwmo",
    "status": "vsl3na",
    "priority": 668,
    "delFlag": 2,
    "description": "0i2zz4",
    "spare1": "o1r8am",
    "spare2": "n5ohqr",
    "spare3": "bjqtgf",
    "spare4": "etq5gn"
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 新增心愿目标池数据操作逻辑，根据操作类型，执行新增操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/wishinfo/save.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/json; charset=utf-8

**Description:** 新增心愿目标池数据操作逻辑，根据操作类型，执行新增操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|心愿目标主键ID|false|-
└─appId|string|应用ID|false|-
└─templateId|string|心愿目标模板ID|false|-
└─memberId|int64|主键ID|false|-
└─userId|string|创建者ID|false|-
└─viewName|string|创建者显示名称|false|-
└─bookId|int64|绑定记账本ID|false|-
└─bookName|string|绑定记账本名称|false|-
└─type|string|类型，可关联CATEGORY|false|-
└─name|string|心愿目标名称|false|-
└─code|string|心愿目标代码|false|-
└─wichLevel|string|心愿级别，1,2,3,4,5|false|-
└─beginTime|string|开始时间，默认当前时间|false|-
└─endTime|string|结束时间|false|-
└─cycle|string|周期，RI日，ZHOU周，YUE月，JI季度|false|-
└─ifWeekendEx|boolean|周末是否例外，如果例外则不分摊每日金额，0否1是|false|-
└─initMoney|double|期初金额|false|-
└─aimMoney|double|目标金额|false|-
└─modeTrans|string|月结转移方式|false|-
└─mindContent|string|提醒配置|false|-
└─status|string|状态，SAVED暂存START启动PAUSE暂停FINISH已结束|false|-
└─priority|int32|排序|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-
└─spare3|string|备用3|false|-
└─spare4|string|备用4|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/wishinfo/save.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:40",
    "lastTime": "2025-11-17 17:31:40",
    "id": 255,
    "appId": "54",
    "templateId": "54",
    "memberId": 267,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "bookId": 886,
    "bookName": "lanny.hartmann",
    "type": "dwzyg6",
    "name": "lanny.hartmann",
    "code": "60627",
    "wichLevel": "g0wn97",
    "beginTime": "2025-11-17",
    "endTime": "2025-11-17",
    "cycle": "94xnc8",
    "ifWeekendEx": true,
    "initMoney": 48.13,
    "aimMoney": 27.84,
    "modeTrans": "6y8hl8",
    "mindContent": "w6tm6j",
    "status": "8l5dg6",
    "priority": 63,
    "delFlag": 2,
    "description": "1htdpc",
    "spare1": "rzyhfe",
    "spare2": "tvzmp8",
    "spare3": "hjokqc",
    "spare4": "4ngg2k"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "xw4b7r",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 修改心愿目标池数据操作逻辑，根据操作类型，执行修改操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/wishinfo/update.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/json; charset=utf-8

**Description:** 修改心愿目标池数据操作逻辑，根据操作类型，执行修改操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|心愿目标主键ID|false|-
└─appId|string|应用ID|false|-
└─templateId|string|心愿目标模板ID|false|-
└─memberId|int64|主键ID|false|-
└─userId|string|创建者ID|false|-
└─viewName|string|创建者显示名称|false|-
└─bookId|int64|绑定记账本ID|false|-
└─bookName|string|绑定记账本名称|false|-
└─type|string|类型，可关联CATEGORY|false|-
└─name|string|心愿目标名称|false|-
└─code|string|心愿目标代码|false|-
└─wichLevel|string|心愿级别，1,2,3,4,5|false|-
└─beginTime|string|开始时间，默认当前时间|false|-
└─endTime|string|结束时间|false|-
└─cycle|string|周期，RI日，ZHOU周，YUE月，JI季度|false|-
└─ifWeekendEx|boolean|周末是否例外，如果例外则不分摊每日金额，0否1是|false|-
└─initMoney|double|期初金额|false|-
└─aimMoney|double|目标金额|false|-
└─modeTrans|string|月结转移方式|false|-
└─mindContent|string|提醒配置|false|-
└─status|string|状态，SAVED暂存START启动PAUSE暂停FINISH已结束|false|-
└─priority|int32|排序|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-
└─spare3|string|备用3|false|-
└─spare4|string|备用4|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/wishinfo/update.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:40",
    "lastTime": "2025-11-17 17:31:40",
    "id": 857,
    "appId": "54",
    "templateId": "54",
    "memberId": 337,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "bookId": 544,
    "bookName": "lanny.hartmann",
    "type": "zlm644",
    "name": "lanny.hartmann",
    "code": "60627",
    "wichLevel": "8pspjk",
    "beginTime": "2025-11-17",
    "endTime": "2025-11-17",
    "cycle": "xyvehh",
    "ifWeekendEx": true,
    "initMoney": 58.77,
    "aimMoney": 29.51,
    "modeTrans": "bj8p2f",
    "mindContent": "42ts35",
    "status": "ec9mjh",
    "priority": 8,
    "delFlag": 2,
    "description": "lzeawi",
    "spare1": "tt9v7b",
    "spare2": "zh40af",
    "spare3": "763mt8",
    "spare4": "uguoji"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "grxvxe",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 删除心愿目标池数据操作逻辑，根据操作类型，执行删除操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/wishinfo/delete/{ids}.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 删除心愿目标池数据操作逻辑，根据操作类型，执行删除操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
ids|array|心愿目标主键ID:null;1;1,2,3,[array of int64]|true|

**Request-example:**
```
curl -X POST -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/wishinfo/delete/.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "x429f4",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 提醒分类信息读写控制类
### 获取提醒分类信息列表或分页, 返回CommCategory对象.
**URL:** https://linlan.net/xiaolan_backend/api/comm/category/list.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取提醒分类信息列表或分页, 返回CommCategory对象.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
q|string|关键字：查询输入条件q，可匹配name、title、keywords等文本信息|false|-
orderBy|int32|排序，默认主键倒序|false|-
startTime|string|开始时间|false|-
endTime|string|结束时间|false|-
page|int32|分页信息：默认第1页|false|-
limit|int32|分页每页条数，默认10条|false|-
id|int64|分类主键ID|false|-
parentId|int64|父分类ID|false|-
type|string|分类类型，TIXING提醒，ZIXUN咨询|false|-
bizType|string|分类类型，ZHICHU支出，SHOURU收入|false|-
name|string|分类名称|false|-
code|string|分类代码|false|-
content|string|分类配置JSON|false|-
status|string|状态，0异常关闭1正常2升级3锁定|false|-
creatorId|string|创建者ID|false|-
creatorName|string|创建者显示名称|false|-
delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
description|string|描述|false|-
spare1|string|备用1|false|-
spare2|string|备用2|false|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/category/list.do?page=1&content=ejpjau&parentId=770&orderBy=187&delFlag=2&status=nh8omn&creatorId=54&name=lanny.hartmann&creatorName=lanny.hartmann&description=vw9y31&code=60627&startTime=2025-11-17 17:31:11&endTime=2025-11-17 17:31:11&type=est5jb&q=9t7sy8&limit=10&spare1=vx2c9j&spare2=11jail&id=149&bizType=j3oepq
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|array|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|分类主键ID|-
└─parentId|int64|父分类ID|-
└─type|string|分类类型，TIXING提醒，ZIXUN咨询|-
└─bizType|string|分类类型，ZHICHU支出，SHOURU收入|-
└─name|string|分类名称|-
└─code|string|分类代码|-
└─content|string|分类配置JSON|-
└─status|string|状态，0异常关闭1正常2升级3锁定|-
└─priority|int32|排序|-
└─creatorName|string|创建者显示名称|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": [
    {
      "creatorId": "54",
      "createTime": "2025-11-17 17:31:38",
      "lastTime": "2025-11-17 17:31:38",
      "id": 968,
      "parentId": 666,
      "type": "q5e8b3",
      "bizType": "f32c0c",
      "name": "lanny.hartmann",
      "code": "60627",
      "content": "iel7qz",
      "status": "zvlhx3",
      "priority": 830,
      "creatorName": "lanny.hartmann",
      "delFlag": 2,
      "description": "xq6da9",
      "spare1": "dhhp9y",
      "spare2": "95ev28"
    }
  ],
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 主键获取提醒分类信息详细信息.
**URL:** https://linlan.net/xiaolan_backend/api/comm/category/{id}.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 主键获取提醒分类信息详细信息.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int64|分类主键ID|true|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/category/1.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|分类主键ID|-
└─parentId|int64|父分类ID|-
└─type|string|分类类型，TIXING提醒，ZIXUN咨询|-
└─bizType|string|分类类型，ZHICHU支出，SHOURU收入|-
└─name|string|分类名称|-
└─code|string|分类代码|-
└─content|string|分类配置JSON|-
└─status|string|状态，0异常关闭1正常2升级3锁定|-
└─priority|int32|排序|-
└─creatorName|string|创建者显示名称|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 259,
    "parentId": 651,
    "type": "xikzta",
    "bizType": "gtv517",
    "name": "lanny.hartmann",
    "code": "60627",
    "content": "ldhsfx",
    "status": "zqioyp",
    "priority": 294,
    "creatorName": "lanny.hartmann",
    "delFlag": 2,
    "description": "t5phtz",
    "spare1": "97a1z1",
    "spare2": "8h2yqm"
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 新增提醒分类信息数据操作逻辑，根据操作类型，执行新增操作.
**URL:** https://linlan.net/xiaolan_backend/api/comm/category/save.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/json; charset=utf-8

**Description:** 新增提醒分类信息数据操作逻辑，根据操作类型，执行新增操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|分类主键ID|false|-
└─parentId|int64|父分类ID|false|-
└─type|string|分类类型，TIXING提醒，ZIXUN咨询|false|-
└─bizType|string|分类类型，ZHICHU支出，SHOURU收入|false|-
└─name|string|分类名称|false|-
└─code|string|分类代码|false|-
└─content|string|分类配置JSON|false|-
└─status|string|状态，0异常关闭1正常2升级3锁定|false|-
└─priority|int32|排序|false|-
└─creatorName|string|创建者显示名称|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/category/save.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 738,
    "parentId": 483,
    "type": "l1bwdg",
    "bizType": "sqbli9",
    "name": "lanny.hartmann",
    "code": "60627",
    "content": "oy0i4n",
    "status": "a4eptc",
    "priority": 751,
    "creatorName": "lanny.hartmann",
    "delFlag": 2,
    "description": "ksk0lb",
    "spare1": "80q08p",
    "spare2": "cgjv6x"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "6l4fba",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 修改提醒分类信息数据操作逻辑，根据操作类型，执行修改操作.
**URL:** https://linlan.net/xiaolan_backend/api/comm/category/update.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/json; charset=utf-8

**Description:** 修改提醒分类信息数据操作逻辑，根据操作类型，执行修改操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|分类主键ID|false|-
└─parentId|int64|父分类ID|false|-
└─type|string|分类类型，TIXING提醒，ZIXUN咨询|false|-
└─bizType|string|分类类型，ZHICHU支出，SHOURU收入|false|-
└─name|string|分类名称|false|-
└─code|string|分类代码|false|-
└─content|string|分类配置JSON|false|-
└─status|string|状态，0异常关闭1正常2升级3锁定|false|-
└─priority|int32|排序|false|-
└─creatorName|string|创建者显示名称|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/category/update.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 313,
    "parentId": 449,
    "type": "fgnxl0",
    "bizType": "utgxu0",
    "name": "lanny.hartmann",
    "code": "60627",
    "content": "7gejtt",
    "status": "avvyeg",
    "priority": 950,
    "creatorName": "lanny.hartmann",
    "delFlag": 2,
    "description": "jyf77l",
    "spare1": "vxzkzm",
    "spare2": "ju1cz5"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "h3ia9g",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 删除提醒分类信息数据操作逻辑，根据操作类型，执行删除操作.
**URL:** https://linlan.net/xiaolan_backend/api/comm/category/delete/{ids}.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:57:19

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 删除提醒分类信息数据操作逻辑，根据操作类型，执行删除操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
ids|array|分类主键ID:null;1;1,2,3,[array of int64]|true|

**Request-example:**
```
curl -X POST -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/comm/category/delete/.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "5rzp2q",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 记账打卡记录读写控制类
### 获取记账打卡记录列表或分页, 返回CheckRecord对象.
**URL:** https://linlan.net/xiaolan_backend/api/my/checkrecord/list.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取记账打卡记录列表或分页, 返回CheckRecord对象.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
q|string|关键字：查询输入条件q，可匹配name、title、keywords等文本信息|false|-
orderBy|int32|排序，默认主键倒序|false|-
startTime|string|开始时间|false|-
endTime|string|结束时间|false|-
page|int32|分页信息：默认第1页|false|-
limit|int32|分页每页条数，默认10条|false|-
id|int64|记账打卡主键ID|false|-
categoryId|int64|分类主键ID|false|-
categoryName|string|分类名称|false|-
infoId|int64|主键ID|false|-
bookName|string|记账本台账名称|false|-
wishId|int64|心愿目标主键ID|false|-
wishName|string|心愿目标名称|false|-
memberId|int64|主键ID|false|-
viewName|string|创建者显示名称|false|-
type|string|记账打卡类型，JIZHANG记账，MUBIAO目标|false|-
bizType|string|记账打卡类型，ZHICHU支出，SHOURU收入，DAKA打卡|false|-
name|string|记账打卡名称|false|-
code|string|记账打卡代码|false|-
content|string|记账打卡配置JSON|false|-
status|string|状态，0异常关闭1正常2升级3锁定|false|-
creatorId|string|创建者ID|false|-
delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
description|string|描述|false|-
spare1|string|备用1|false|-
spare2|string|备用2|false|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/checkrecord/list.do?infoId=862&endTime=2025-11-17 17:31:11&categoryId=553&delFlag=2&wishId=431&creatorId=54&q=83xm0b&bizType=s6has1&type=b9l2yq&id=631&spare2=o9elck&content=gee9uq&categoryName=lanny.hartmann&code=60627&name=lanny.hartmann&orderBy=251&viewName=lanny.hartmann&limit=10&page=1&bookName=lanny.hartmann&description=dcp6g6&wishName=lanny.hartmann&startTime=2025-11-17 17:31:11&spare1=rufz9f&memberId=911&status=npb32b
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|array|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|记账打卡主键ID|-
└─categoryId|int64|分类主键ID|-
└─categoryName|string|分类名称|-
└─infoId|int64|主键ID|-
└─bookName|string|记账本台账名称|-
└─wishId|int64|心愿目标主键ID|-
└─wishName|string|心愿目标名称|-
└─memberId|int64|主键ID|-
└─viewName|string|创建者显示名称|-
└─type|string|记账打卡类型，JIZHANG记账，MUBIAO目标|-
└─bizType|string|记账打卡类型，ZHICHU支出，SHOURU收入，DAKA打卡|-
└─name|string|记账打卡名称|-
└─code|string|记账打卡代码|-
└─content|string|记账打卡配置JSON|-
└─status|string|状态，0异常关闭1正常2升级3锁定|-
└─priority|int32|排序|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": [
    {
      "creatorId": "54",
      "createTime": "2025-11-17 17:31:39",
      "lastTime": "2025-11-17 17:31:39",
      "id": 757,
      "categoryId": 10,
      "categoryName": "lanny.hartmann",
      "infoId": 827,
      "bookName": "lanny.hartmann",
      "wishId": 768,
      "wishName": "lanny.hartmann",
      "memberId": 751,
      "viewName": "lanny.hartmann",
      "type": "jmpa51",
      "bizType": "bfpat3",
      "name": "lanny.hartmann",
      "code": "60627",
      "content": "t65qjm",
      "status": "zn0hsc",
      "priority": 903,
      "delFlag": 2,
      "description": "xwy0u2",
      "spare1": "lvqc14",
      "spare2": "ag66ae"
    }
  ],
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 主键获取记账打卡记录详细信息.
**URL:** https://linlan.net/xiaolan_backend/api/my/checkrecord/{id}.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 主键获取记账打卡记录详细信息.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int64|记账打卡主键ID|true|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/checkrecord/1.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|记账打卡主键ID|-
└─categoryId|int64|分类主键ID|-
└─categoryName|string|分类名称|-
└─infoId|int64|主键ID|-
└─bookName|string|记账本台账名称|-
└─wishId|int64|心愿目标主键ID|-
└─wishName|string|心愿目标名称|-
└─memberId|int64|主键ID|-
└─viewName|string|创建者显示名称|-
└─type|string|记账打卡类型，JIZHANG记账，MUBIAO目标|-
└─bizType|string|记账打卡类型，ZHICHU支出，SHOURU收入，DAKA打卡|-
└─name|string|记账打卡名称|-
└─code|string|记账打卡代码|-
└─content|string|记账打卡配置JSON|-
└─status|string|状态，0异常关闭1正常2升级3锁定|-
└─priority|int32|排序|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 838,
    "categoryId": 378,
    "categoryName": "lanny.hartmann",
    "infoId": 802,
    "bookName": "lanny.hartmann",
    "wishId": 450,
    "wishName": "lanny.hartmann",
    "memberId": 261,
    "viewName": "lanny.hartmann",
    "type": "em8myd",
    "bizType": "8jt2ga",
    "name": "lanny.hartmann",
    "code": "60627",
    "content": "em0dty",
    "status": "q8drjv",
    "priority": 765,
    "delFlag": 2,
    "description": "5l39c1",
    "spare1": "vhnq7p",
    "spare2": "kzo91w"
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 新增记账打卡记录数据操作逻辑，根据操作类型，执行新增操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/checkrecord/save.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/json; charset=utf-8

**Description:** 新增记账打卡记录数据操作逻辑，根据操作类型，执行新增操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|记账打卡主键ID|false|-
└─categoryId|int64|分类主键ID|false|-
└─categoryName|string|分类名称|false|-
└─infoId|int64|主键ID|false|-
└─bookName|string|记账本台账名称|false|-
└─wishId|int64|心愿目标主键ID|false|-
└─wishName|string|心愿目标名称|false|-
└─memberId|int64|主键ID|false|-
└─viewName|string|创建者显示名称|false|-
└─type|string|记账打卡类型，JIZHANG记账，MUBIAO目标|false|-
└─bizType|string|记账打卡类型，ZHICHU支出，SHOURU收入，DAKA打卡|false|-
└─name|string|记账打卡名称|false|-
└─code|string|记账打卡代码|false|-
└─content|string|记账打卡配置JSON|false|-
└─status|string|状态，0异常关闭1正常2升级3锁定|false|-
└─priority|int32|排序|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/checkrecord/save.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 220,
    "categoryId": 492,
    "categoryName": "lanny.hartmann",
    "infoId": 299,
    "bookName": "lanny.hartmann",
    "wishId": 53,
    "wishName": "lanny.hartmann",
    "memberId": 872,
    "viewName": "lanny.hartmann",
    "type": "gp5qmz",
    "bizType": "eqq3ys",
    "name": "lanny.hartmann",
    "code": "60627",
    "content": "d6kmhm",
    "status": "axsmbj",
    "priority": 405,
    "delFlag": 2,
    "description": "dkub7z",
    "spare1": "qjk1tm",
    "spare2": "8qv5p0"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "s4wow3",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 修改记账打卡记录数据操作逻辑，根据操作类型，执行修改操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/checkrecord/update.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/json; charset=utf-8

**Description:** 修改记账打卡记录数据操作逻辑，根据操作类型，执行修改操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|记账打卡主键ID|false|-
└─categoryId|int64|分类主键ID|false|-
└─categoryName|string|分类名称|false|-
└─infoId|int64|主键ID|false|-
└─bookName|string|记账本台账名称|false|-
└─wishId|int64|心愿目标主键ID|false|-
└─wishName|string|心愿目标名称|false|-
└─memberId|int64|主键ID|false|-
└─viewName|string|创建者显示名称|false|-
└─type|string|记账打卡类型，JIZHANG记账，MUBIAO目标|false|-
└─bizType|string|记账打卡类型，ZHICHU支出，SHOURU收入，DAKA打卡|false|-
└─name|string|记账打卡名称|false|-
└─code|string|记账打卡代码|false|-
└─content|string|记账打卡配置JSON|false|-
└─status|string|状态，0异常关闭1正常2升级3锁定|false|-
└─priority|int32|排序|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/checkrecord/update.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 729,
    "categoryId": 592,
    "categoryName": "lanny.hartmann",
    "infoId": 777,
    "bookName": "lanny.hartmann",
    "wishId": 859,
    "wishName": "lanny.hartmann",
    "memberId": 182,
    "viewName": "lanny.hartmann",
    "type": "5334mk",
    "bizType": "69nred",
    "name": "lanny.hartmann",
    "code": "60627",
    "content": "5spjvc",
    "status": "s6mlyt",
    "priority": 843,
    "delFlag": 2,
    "description": "fyds0x",
    "spare1": "qu9po4",
    "spare2": "39n50q"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "tpb3t9",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 删除记账打卡记录数据操作逻辑，根据操作类型，执行删除操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/checkrecord/delete/{ids}.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 删除记账打卡记录数据操作逻辑，根据操作类型，执行删除操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
ids|array|记账打卡主键ID:null;1;1,2,3,[array of int64]|true|

**Request-example:**
```
curl -X POST -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/checkrecord/delete/.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "idcm6h",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 记账本台账读写控制类
### 获取记账本台账列表或分页, 返回BookInfo对象.
**URL:** https://linlan.net/xiaolan_backend/api/my/bookinfo/list.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 获取记账本台账列表或分页, 返回BookInfo对象.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Query-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
q|string|关键字：查询输入条件q，可匹配name、title、keywords等文本信息|false|-
orderBy|int32|排序，默认主键倒序|false|-
startTime|string|开始时间|false|-
endTime|string|结束时间|false|-
page|int32|分页信息：默认第1页|false|-
limit|int32|分页每页条数，默认10条|false|-
id|int64|主键ID|false|-
memberId|int64|会员主键ID|false|-
viewName|string|创建者显示名称|false|-
bookId|int64|记账本ID|false|-
bookName|string|记账本台账名称|false|-
securityLevel|int32|安全级别，1,2,3,4,5|false|-
ifStat|boolean|是否纳入统计，0否1是|false|-
status|string|状态，1正常|false|-
delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
description|string|描述|false|-
spare1|string|备用1|false|-
spare2|string|备用2|false|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/bookinfo/list.do?page=1&id=925&limit=10&delFlag=2&ifStat=true&viewName=lanny.hartmann&description=n87030&spare2=80a1gp&bookName=lanny.hartmann&endTime=2025-11-17 17:31:11&startTime=2025-11-17 17:31:11&securityLevel=436&memberId=494&q=2s7426&orderBy=765&bookId=664&status=kqkvwn&spare1=pf58kq
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|array|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|主键ID|-
└─memberId|int64|会员主键ID|-
└─userId|string|创建者ID|-
└─viewName|string|创建者显示名称|-
└─bookId|int64|记账本ID|-
└─bookName|string|记账本台账名称|-
└─securityLevel|int32|安全级别，1,2,3,4,5|-
└─ifStat|boolean|是否纳入统计，0否1是|-
└─status|string|状态，1正常|-
└─priority|int32|排序|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": [
    {
      "creatorId": "54",
      "createTime": "2025-11-17 17:31:39",
      "lastTime": "2025-11-17 17:31:39",
      "id": 818,
      "memberId": 632,
      "userId": "54",
      "viewName": "lanny.hartmann",
      "bookId": 346,
      "bookName": "lanny.hartmann",
      "securityLevel": 653,
      "ifStat": true,
      "status": "bgod32",
      "priority": 41,
      "delFlag": 2,
      "description": "ei733t",
      "spare1": "szew2e",
      "spare2": "u509jl"
    }
  ],
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 主键获取记账本台账详细信息.
**URL:** https://linlan.net/xiaolan_backend/api/my/bookinfo/{id}.do

**Type:** GET

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 主键获取记账本台账详细信息.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
id|int64|主键ID|true|-

**Request-example:**
```
curl -X GET -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/bookinfo/1.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
└─creatorId|string|创建人id|-
└─createTime|string|创建时间|-
└─lastTime|string|最后修改时间|-
└─id|int64|主键ID|-
└─memberId|int64|会员主键ID|-
└─userId|string|创建者ID|-
└─viewName|string|创建者显示名称|-
└─bookId|int64|记账本ID|-
└─bookName|string|记账本台账名称|-
└─securityLevel|int32|安全级别，1,2,3,4,5|-
└─ifStat|boolean|是否纳入统计，0否1是|-
└─status|string|状态，1正常|-
└─priority|int32|排序|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|-
└─description|string|描述|-
└─spare1|string|备用1|-
└─spare2|string|备用2|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 918,
    "memberId": 536,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "bookId": 297,
    "bookName": "lanny.hartmann",
    "securityLevel": 88,
    "ifStat": true,
    "status": "ope5zl",
    "priority": 23,
    "delFlag": 2,
    "description": "h7apbj",
    "spare1": "6rtyzz",
    "spare2": "aoaoyg"
  },
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 新增记账本台账数据操作逻辑，根据操作类型，执行新增操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/bookinfo/save.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/json; charset=utf-8

**Description:** 新增记账本台账数据操作逻辑，根据操作类型，执行新增操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|主键ID|false|-
└─memberId|int64|会员主键ID|false|-
└─userId|string|创建者ID|false|-
└─viewName|string|创建者显示名称|false|-
└─bookId|int64|记账本ID|false|-
└─bookName|string|记账本台账名称|false|-
└─securityLevel|int32|安全级别，1,2,3,4,5|false|-
└─ifStat|boolean|是否纳入统计，0否1是|false|-
└─status|string|状态，1正常|false|-
└─priority|int32|排序|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/bookinfo/save.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 641,
    "memberId": 251,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "bookId": 339,
    "bookName": "lanny.hartmann",
    "securityLevel": 365,
    "ifStat": true,
    "status": "zu62zi",
    "priority": 872,
    "delFlag": 2,
    "description": "rysn7i",
    "spare1": "lr5h25",
    "spare2": "6noftt"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "2c7gpt",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 修改记账本台账数据操作逻辑，根据操作类型，执行修改操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/bookinfo/update.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/json; charset=utf-8

**Description:** 修改记账本台账数据操作逻辑，根据操作类型，执行修改操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Body-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
data|object|请求数据对象，放入RequestBody内|false|-
└─creatorId|string|创建人id|false|-
└─createTime|string|创建时间|false|-
└─lastTime|string|最后修改时间|false|-
└─id|int64|主键ID|false|-
└─memberId|int64|会员主键ID|false|-
└─userId|string|创建者ID|false|-
└─viewName|string|创建者显示名称|false|-
└─bookId|int64|记账本ID|false|-
└─bookName|string|记账本台账名称|false|-
└─securityLevel|int32|安全级别，1,2,3,4,5|false|-
└─ifStat|boolean|是否纳入统计，0否1是|false|-
└─status|string|状态，1正常|false|-
└─priority|int32|排序|false|-
└─delFlag|int32|删除标记，0正常1已删除2应用内受限3回收站|false|-
└─description|string|描述|false|-
└─spare1|string|备用1|false|-
└─spare2|string|备用2|false|-

**Request-example:**
```
curl -X POST -k -H 'Content-Type: application/json; charset=utf-8' -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/bookinfo/update.do --data '{
  "data": {
    "creatorId": "54",
    "createTime": "2025-11-17 17:31:39",
    "lastTime": "2025-11-17 17:31:39",
    "id": 477,
    "memberId": 343,
    "userId": "54",
    "viewName": "lanny.hartmann",
    "bookId": 435,
    "bookName": "lanny.hartmann",
    "securityLevel": 278,
    "ifStat": true,
    "status": "zvvlee",
    "priority": 767,
    "delFlag": 2,
    "description": "wwqb4z",
    "spare1": "v0pylx",
    "spare2": "smczc3"
  }
}'
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "lxchv4",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

### 删除记账本台账数据操作逻辑，根据操作类型，执行删除操作.
**URL:** https://linlan.net/xiaolan_backend/api/my/bookinfo/delete/{ids}.do

**Type:** POST

**Author:** Linlan
CreateTime 2025-11-17 16:59:49

**Content-Type:** application/x-www-form-urlencoded;charset=utf-8

**Description:** 删除记账本台账数据操作逻辑，根据操作类型，执行删除操作.

**Request-headers:**

Header | Type|Description|Required|Since
---|---|---|---|----
token|string|令牌|true|-


**Path-parameters:**

Parameter | Type|Description|Required|Since
---|---|---|---|---
ids|array|主键ID:null;1;1,2,3,[array of int64]|true|

**Request-example:**
```
curl -X POST -k -H 'token:${token}' -i https://linlan.net/xiaolan_backend/api/my/bookinfo/delete/.do
```
**Response-fields:**

Field | Type|Description|Since
---|---|---|---
success|boolean|成功标记:正常为true,错误为false|-
code|string|返回状态码:正常状态为0|-
msg|string|返回消息|-
data|object|返回数据对象|-
requestId|string|请求ID|-
timestamp|string|返回时间戳字符串|-

**Response-example:**
```
{
  "success": true,
  "code": "0",
  "msg": "正常",
  "data": "xzws8m",
  "requestId": "54",
  "timestamp": "2025-11-17 17:31:11"
}
```

## 错误码列表
Error code |Description
---|---
200|Success[正常]
201|None[未查询到数据]
400|Bad Request[请求错误]
401|Unauthorized[TOKEN失效，请重新登录]
403|Forbidden[页面禁止访问]
404|Not Found[页面未找到]
415|Unsupported Media Type[数据类型不支持]
500|Internal Server Error[未知错误]
501|Not Login[用户未登录]
502|Bad Gateway[网关错误]
503|Service Unavailable[服务不可用]
700|Token Error[TOKEN信息错误]
701|Input Error Code[信息传入有误(code)]
702|Input Error Source[信息传入有误(source)]
703|Account Not Exist[对接平台账户不存在]
704|Channel Not Exist[该栏目版面不存在]
711|Parameter Error[传递参数信息错误]
712|Network Error[网络异常，请联系系统管理员]
721|Third Info Error[第三方数据信息验证失败]
731|IP Whitelist Error[白名单校验失败]
741|Info Send Fail[消息推送失败]
1001|Error User Info[用户数据信息验证失败]
1002|Error User SSO[SSO用户数据信息验证失败]
1003|Error User APP[APP用户数据信息验证失败]
1004|Error User APP Step1[APP用户在调取第三方接口时失败]
1005|Error User APP Step2[APP用户数据调取第三方接口成功但是返回的数据不正确]
1006|Error User Out[外部用户数据信息验证失败]
1011|Error User Key[用户关键信息错误]
1012|Error User Password[用户密码信息错误]
1013|Error User Real Name[需要实名认证绑定]
1014|Error User Need Login[请用户重新登录]
1015|Error User Need Face[需要人脸识别二次认证]
1016|Error User Identity[认证失败，请重新认证]
1017|Error User Face[人脸识别信息与系统不一致]
1018|Error User Need Reg[用户信息未注册，请注册]
1019|Error Params Mate[传参不匹配]
1020|Error Return Many[查询出多条数据]
1021|Error Number Format[数字格式转换异常]
1022|Error Data Duplicate[数据重复]
1023|Error Data Format[数据格式错误]
1024|Error Data[数据错误]
1101|Error Address Invalid[无效访问地址]
1102|Error With Upload[上传出现错误，请联系管理员]
1103|Error With Input填写信息错误，请检查]
1104|Error With Password[密码必须为大小写字母,数字,特殊字符中的至少3种且8位-20位之内]
1201|Error With Enterprise Info[企业关键信息错误]
1202|Error With Not Initial[关键信息未初始化]
1301|Error Template Info[模板信息错误]

## 数据字典
### 目录树结构字典

Code |Type|Description
---|---|---
ROLE|string|角色树
MENU|string|菜单树
ALL_POSITION|string|全部岗位树
POSITION|string|岗位树
SINGLE_POSITION|string|单表岗位树
ALL_MENU|string|全部菜单树
ALL_DEPT|string|全部部门树
BASE_XZQH|string|行政区划树
COM_DIC_ECOINDUSTRY|string|所属行业树
CORE_DEPTTYPE|string|条线部门树
CORE_ORGTYPE|string|机构分类树
