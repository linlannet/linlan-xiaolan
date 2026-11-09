/*
 * xiaolan-backend - 小览生活服务端
 * Copyright © 2020-2025 Linlan (open@linlan.net)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.linlan.manage.wechat;

import java.util.*;

import org.springframework.web.bind.annotation.*;

import cn.binarywang.wx.miniapp.api.WxMaService;

import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Resource;
import net.linlan.commons.core.annotation.PlatLog;
import net.linlan.commons.core.http.ResponseEntity;
import net.linlan.frame.comm.vo.AppLoginInfo;
import net.linlan.manage.wechat.config.WxConfig;
import net.linlan.manage.wechat.service.WechatAuthService;
import net.linlan.manage.wechat.vo.WechatModifyMobile;
import net.linlan.social.manage.constant.OpenTypeEnum;
import net.linlan.social.manage.vo.ThirdUserInfo;
import net.linlan.sys.web.RedisService;

/**
 * 微信小程序接口相关控制器
 * 
 * @author Linlan
 */
@Slf4j
@RestController
@RequestMapping("/api/open/wxmini")
public class WechatMiniController {

    @Resource
    private WechatAuthService wechatAuthService;
    @Resource
    private RedisService      redisService;
    @Resource
    private WxMaService       maService;
    @Resource
    private WxConfig          wxConfig;

    /**
     * 获取微信扫码登录验证码
     *
     * @return 微信登录扫码结果
     */
    @PlatLog(value = "获取微信扫码登录验证码", category = 10, srcCode = 1)
    @GetMapping("/getCode")
    public ResponseEntity<String> getWechatLoginCode() {
        return ResponseEntity.ok(wechatAuthService.getWechatLoginCode());
    }

    /**
     * 获取微信扫码是否已登录
     * @param loginCode 登录代码
     * @return 微信登录扫码状态
     */
    @PlatLog(value = "获取微信扫码是否已登录", category = 10, srcCode = 1)
    @GetMapping("/isLogin/{loginCode}")
    public ResponseEntity<ThirdUserInfo> getWechatIsLogin(@PathVariable String loginCode) {
        return ResponseEntity.ok(wechatAuthService.getWechatIsLogin(loginCode));
    }

    /**
     * 微信小程序登录
     * @param code 代码
     * @return 微信小程序登录结果
     */
    @PlatLog(value = "微信小程序登录", category = 10, srcCode = 1)
    @GetMapping("/login/{code}")
    public ResponseEntity<AppLoginInfo> miniLogin(@PathVariable String code) {
        return ResponseEntity
            .ok(wechatAuthService.miniLogin(OpenTypeEnum.WECHAT_MINI.getKey(), code));
    }

    /**
     * 微信小程序登录
     * @param input 输入对象
     * @return 微信小程序更新手机号
     */
    @PlatLog(value = "微信小程序登录", category = 10, srcCode = 1)
    @GetMapping("/login/getMobile")
    public ResponseEntity<Object> getMobile(@RequestBody WechatModifyMobile input) {
        return ResponseEntity.ok(input);
    }

    //    /**
    //     * 微信小程序 - 用户登录  code换取openId和session_key等
    //     * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/login.html#%E7%99%BB%E5%BD%95%E6%B5%81%E7%A8%8B%E6%97%B6%E5%BA%8F
    //     *
    //     * @param params
    //     * @return
    //     */
    //    @PostMapping("/h5work/login")
    //    @Encrypt
    //    @AuthIgnore
    //    public ResponseEntity<Object> login(@RequestBody Map<String, Object> params) throws WxErrorException {
    //        String code = StrUtil.toString(params.get("code"));
    //        if (StrUtil.isBlank(code)) {
    //            throw new ApiException(ResultStatus.WX_MA_ERROR_1000);
    //        }
    //        maService.getWxMaConfig().setApiHostUrl(wxConfig.getApiHostUrl());
    //        WxMaJscode2SessionResult result = maService.jsCode2SessionInfo(StrUtil.toString(params.get("code")));
    //        String openId = result.getOpenid();
    //        String sessionKey = result.getSessionKey();
    //        String unionId = result.getUnionid();
    //        log.debug(JSONUtil.toJsonStr(result));
    //
    //        // TODO: 这里用户信息入库  通过openId或unionId 判断用户信息是否绑定 若未绑定，则需要获取微信小程序用户信息进行绑定操作
    //        SqyPerson person = sqyPersonService.findByOpenId(openId);
    //        if (person == null) {
    //            person = SqyPerson.builder()
    //                    .wxOpenId(openId)
    //                    .wxUnionId(unionId)
    //                    .sqyStatus(PlatformConstant.IS_NOT_VALID)
    //                    .lastLoginTime(new Timestamp(System.currentTimeMillis()))
    //                    .build();
    //            try {
    //                sqyPersonService.save(person);
    //            } catch (Exception e) {
    //                person = sqyPersonService.findByOpenId(openId);
    //                if (person == null) {
    //                    throw new ApiException(ResultStatus.WX_MA_ERROR_1004);
    //                }
    //            }
    //        } else {
    //            sqyPersonService.updateLastLoginTime(person);
    //            // redis里塞性别与年龄 == 证件类型为身份证则直接通过身份证塞取
    //            if(StrUtil.isNotEmpty(person.getIdType()) && StrUtil.isNotEmpty(person.getIdCard()) && person.getIdType().equals(CommonConstant.CARD_TYPE_1)) {
    //                buildByIdCard(person, person.getIdCard());
    //            } else {
    //                // 证件类为其他类型且sqPersonId不为空 则查sq库拿SqPersonInfo塞取
    //                if(StrUtil.isNotEmpty(person.getSqPersonId())) {
    //                    SqPersonInfo sqPersonInfo = sqPersonInfoService.findById(person.getSqPersonId());
    //                    if(ObjectUtil.isNotNull(sqPersonInfo) && ObjectUtil.isNotNull(sqPersonInfo.getBirthday())) {
    //                        person.setSex(sqPersonInfo.getSex());
    //                        Date birthday = new Date(sqPersonInfo.getBirthday().getTime());
    //                        buildByBirthday(person, birthday);
    //                    }
    //                }
    //            }
    //        }
    //        // 用户字段统一处理
    //        Map<String, Object> wxSessionInfo = new HashMap<>();
    //        wxSessionInfo.put("openid", openId);
    //        wxSessionInfo.put("sessionKey", sessionKey);
    //        wxSessionInfo.put("unionid", unionId);
    //        JSONObject unionUser = buildUnionUser(person, wxSessionInfo);
    //        String token = tokenProvider.createTokenByJson(JSONUtil.parseObj(unionUser, JSONConfig.create().setIgnoreNullValue(false)));
    //        // 生成刷新令牌
    //        String refreshToken = UUID.randomUUID().toString();
    //        redisService.set("refresh::" + refreshToken, JSONUtil.parseObj(unionUser, JSONConfig.create().setIgnoreNullValue(false)), securityProperties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
    //        // 返回 token
    //        Map<String, Object> authInfo = new HashMap<String, Object>() {{
    //            put("token", securityProperties.getTokenStartWith() + token);
    //            put("refreshToken", refreshToken);
    //        }};
    //        // 验证用户是否需要实名认证
    //        if (StrUtil.isBlank(person.getIdCard())) {
    //            authInfo.put("isNew", true);
    //        } else {
    //            // 缓存数据权限
    //            this.initDataScopesCache(person);
    //        }
    //        return ResponseEntity.ok(authInfo);
    //    }
    //
    //    /**
    //     * 微信小程序 - 获取当前用户信息
    //     *
    //     * @return
    //     */
    //    @GetMapping("/h5work/getMineInfo")
    //    @Encrypt
    //    public ResponseEntity<Object> getMineInfo(HttpServletRequest request) {
    //        JSONObject sessionUser = SecurityH5Utils.getTokenObject(request);
    //        return ResponseEntity.ok(sessionUser);
    //    }
    //
    //
    //    /**
    //     * 微信小程序 获取用户信息
    //     * https://developers.weixin.qq.com/miniprogram/dev/api/open-api/user-info/wx.getUserInfo.html
    //     *
    //     * @param params
    //     * @return
    //     */
    //    @PostMapping("/h5work/info")
    //    @Encrypt
    //    public void info(@RequestBody Map<String, Object> params, HttpServletRequest request) {
    //        String signature = StrUtil.toString(params.get("signature"));
    //        String rawData = StrUtil.toString(params.get("rawData"));
    //        String encryptedData = StrUtil.toString(params.get("encryptedData"));
    //        String iv = StrUtil.toString(params.get("iv"));
    //
    //        WxMaJscode2SessionResult wxMaJscode2SessionResult = JSONUtil.toBean(SecurityH5Utils.getTokenObject(request), WxMaJscode2SessionResult.class);
    //        if (!maService.getUserService().checkUserInfo(wxMaJscode2SessionResult.getSessionKey(), rawData, signature)) {
    //            throw new ApiException(ResultStatus.WX_MA_ERROR_1001);
    //        }
    //        WxMaUserInfo wxMaUserInfo = maService.getUserService().getUserInfo(wxMaJscode2SessionResult.getSessionKey(), encryptedData, iv);
    //        log.info(JSONUtil.toJsonStr(wxMaUserInfo));
    //        // TODO 这里用户信息入库 新增或更新
    //        SqyPerson person = sqyPersonService.findByOpenId(wxMaJscode2SessionResult.getOpenid());
    //        if (person == null) {
    //            person = SqyPerson.builder()
    //                    .sqyHeadPic(wxMaUserInfo.getAvatarUrl()) // 用户头像
    //                    .sqyUserName(wxMaUserInfo.getNickName()) // 用户
    //                    .sqyUserId(wxMaJscode2SessionResult.getOpenid())
    //                    .wxOpenId(wxMaJscode2SessionResult.getOpenid())
    //                    .wxUnionId(wxMaJscode2SessionResult.getUnionid())
    //                    .build();
    //            try {
    //                sqyPersonService.save(person);
    //            } catch (Exception e) {
    //                person = sqyPersonService.findByOpenId(wxMaJscode2SessionResult.getOpenid());
    //                if (person == null) {
    //                    throw new ApiException(ResultStatus.WX_MA_ERROR_1004);
    //                }
    //                // 更新用户头像
    //                person.setSqyHeadPic(wxMaUserInfo.getAvatarUrl());
    //                sqyPersonService.update(person);
    //            }
    //        } else {
    //            // 更新用户头像
    //            person.setSqyHeadPic(wxMaUserInfo.getAvatarUrl());
    //            sqyPersonService.update(person);
    //        }
    //    }
    //
    //    /**
    //     * 微信小程序获取用户手机号
    //     * https://developers.weixin.qq.com/miniprogram/dev/framework/open-ability/getPhoneNumber.html#%E4%BD%BF%E7%94%A8%E6%96%B9%E6%B3%95
    //     *
    //     * @param params
    //     * @return
    //     */
    //    @PostMapping("/h5work/phone")
    //    @Encrypt
    //    public ResponseEntity<Object> phone(@RequestBody Map<String, Object> params, HttpServletRequest request) {
    //        log.info("-----1.进入手机号方法-----");
    //        String encryptedData = StrUtil.toString(params.get("encryptedData"));
    //        String iv = StrUtil.toString(params.get("iv"));
    //        WxMaJscode2SessionResult wxMaJscode2SessionResult = JSONUtil.toBean(SecurityH5Utils.getTokenObject(request), WxMaJscode2SessionResult.class);
    //        log.info("-----2.调用微信方法-----");
    //        WxMaPhoneNumberInfo wxMaPhoneNumberInfo = maService.getUserService().getPhoneNoInfo(wxMaJscode2SessionResult.getSessionKey(), encryptedData, iv);
    //        log.debug("-----3.微信对象转换-----" + JSONUtil.toJsonStr(wxMaPhoneNumberInfo));
    //        Map<String, Object> result = new HashMap<>();
    //        result.put("phone", wxMaPhoneNumberInfo.getPhoneNumber());
    //        // 按照手机号查询是否是1.0已上云用户
    //        try {
    //            LxShangyun lxShangyun = lxShangyunService.findByMobile(wxMaPhoneNumberInfo.getPhoneNumber());
    //            if (lxShangyun == null) {
    //                log.debug("-----4.微信手机号正常返回-----" + wxMaPhoneNumberInfo.getPhoneNumber());
    //                return ResponseEntity.ok(result);
    //            }
    //            result.put("name", lxShangyun.getName());
    //            result.put("idCard", lxShangyun.getIdCard());
    //            result.put("needFaceVerify", lxShangyun.getNeedFaceVerify());
    //            return ResponseEntity.ok(result);
    //        } catch (Exception e) {
    //            log.error("1.0注册上云用户" + params.get("mobile") + "查询出错：" + e.getMessage());
    //            return ResponseEntity.ok(result);
    //        }
    //    }
    //
    //    /**
    //     * 微信小程序 - 获取当前用户信息
    //     *
    //     * @return
    //     */
    //    @PostMapping("/h5work/getUserIdKey")
    //    @Encrypt
    //    public ResponseEntity<Object> getUserIdkey(@RequestBody Map<String, Object> params, HttpServletRequest request) throws WxErrorException {
    //        maService.getWxMaConfig().setApiHostUrl(wxConfig.getApiHostUrl());
    //        String accessToken = maService.getAccessToken();
    //        HttpRequest httpRequest = HttpRequest
    //                .post(wxConfig.getApiHostUrl() + "/cityservice/face/identify/getuseridkey?access_token=" + accessToken)
    //                .header("Content-Type", "application/json")
    //                .setConnectionTimeout(30000)
    //                .body(JSON.toJSONString(params));
    //        String result = httpRequest.execute().body();
    //        com.alibaba.fastjson2.JSONObject dataInfo = com.alibaba.fastjson2.JSONObject.parseObject(result);
    //        return ResponseEntity.ok(dataInfo);
    //    }
    //
    //    /**
    //     * 微信小程序 - 获取当前用户信息
    //     *
    //     * @return
    //     */
    //    @GetMapping("/getInfo")
    //    @Encrypt
    //    public ResponseEntity<Object> getInfo(@RequestParam Map<String, Object> params, HttpServletRequest request) throws WxErrorException {
    //        maService.getWxMaConfig().setApiHostUrl(wxConfig.getApiHostUrl());
    //        String accessToken = maService.getAccessToken();
    //        HttpRequest httpRequest = HttpRequest
    //                .post(wxConfig.getApiHostUrl() + "/cityservice/face/identify/getinfo?access_token=" + accessToken)
    //                .header("Content-Type", "application/json")
    //                .setConnectionTimeout(30000)
    //                .body(JSON.toJSONString(params));
    //        String result = httpRequest.execute().body();
    //        com.alibaba.fastjson2.JSONObject dataInfo = com.alibaba.fastjson2.JSONObject.parseObject(result);
    //        return ResponseEntity.ok(dataInfo);
    //    }
    //
    //
    //    /**
    //     * 微信小程序新用户注册
    //     *
    //     * @param params
    //     * @return
    //     */
    //    @PostMapping("/h5work/register")
    //    @Encrypt
    //    public ResponseEntity<Object> register(@RequestBody Map<String, Object> params, HttpServletRequest request) {
    //        JSONObject personObj = SecurityH5Utils.getTokenObject(request);
    //        String name = StrUtil.toString(params.get("name"));
    //        String phone = StrUtil.toString(params.get("phone"));
    //        String idCard = StrUtil.toString(params.get("idCard"));
    //        String idType = StrUtil.toString(params.get("idType"));
    //        String personType = StrUtil.toString(params.get("personType"));
    //        SqPersonInfo sqPersonInfo = new SqPersonInfo();
    //        // 身份证号码合法性校验
    //        if ("1".equals(idType) && !IdcardUtil.isValidCard(idCard)) {
    //            throw new ApiException(ResultStatus.WX_MA_ERROR_1002);
    //        }
    //        if (!"1".equals(idType)) {
    //            Map<String, Object> map = new HashMap<>();
    //            map.put("cardNo", idCard);
    //            map.put("personType", personType);
    //            sqPersonInfo = sqPersonInfoService.queryPersonByCardAndPersonType(map);
    //            // 不是身份证的先查人 查不到抛错
    //            if (ObjectUtil.isNotEmpty(sqPersonInfo)) {
    //                if (!sqPersonInfo.getName().equals(name)) {
    //                    throw new ApiException(ResultStatus.WX_MA_ERROR_1005);
    //                }
    //                map.put("personId", sqPersonInfo.getPersonId());
    //                List<SqResident> sqResidentList = sqResidentService.getListByPersonId(map);
    //                boolean hasTel = false;
    //                for (SqResident sqResident : sqResidentList) {
    //                    if (StrUtil.equals(phone, sqResident.getTel())) {
    //                        hasTel = true;
    //                    }
    //                }
    //                if (!hasTel) {
    //                    throw new ApiException(ResultStatus.WX_MA_ERROR_1005);
    //                }
    //            } else {
    //                throw new ApiException(ResultStatus.WX_MA_ERROR_1005);
    //            }
    //        }
    //        JSONObject wxUserInfo = JSONUtil.parseObj(params.get("wxUserInfo"), JSONConfig.create().setIgnoreNullValue(false));
    //        SqyPerson person = sqyPersonService.findByIdCard(idCard);
    //        if (ObjectUtil.isNotEmpty(person)) {
    //            if (StrUtil.isNotBlank(person.getWxOpenId())) {
    //                // 用户注册后  根据idCard查询用户信息 有则提示无法重复认证
    //                throw new ApiException(ResultStatus.WX_MA_ERROR_1003);
    //            } else {
    //                // 市民云单点登录用户,不包含微信信息,注册时通过身份证识别
    //                SqyPerson notRealNamePerson = sqyPersonService.findByOpenId(personObj.getStr("openid"));
    //                person.setWxOpenId(personObj.getStr("openid"));
    //                person.setWxUnionId(personObj.getStr("unionid", null));
    //                sqyPersonService.deleteById(notRealNamePerson.getId());
    //                sqyPersonService.update(person);
    //            }
    //        } else {
    //            person = sqyPersonService.findZwwxNotRegistedPerson(name, phone);
    //            Map<String, Object> personMap = new HashMap<>();
    //            personMap.put("cardNo", idCard);
    //            sqPersonInfo = sqPersonInfoService.queryPersonByCard(personMap);
    //            if (ObjectUtil.isNotEmpty(person)) {
    //                // 库内有政务微信用户且该用户未在小程序注册过
    //                person.setWxOpenId(personObj.getStr("openid"));
    //                person.setWxUnionId(personObj.getStr("unionid", null));
    //                person.setIdCard(idCard);
    //                person.setSqyUserName(wxUserInfo.getStr("nickName"));
    //                person.setSqyHeadPic(wxUserInfo.getStr("avatarUrl"));
    //                person.setSqyStatus(PlatformConstant.IS_VALID);
    //                person.setIdType(idType);
    //                if (ObjectUtil.isNotEmpty(sqPersonInfo)) {
    //                    person.setSqPersonId(sqPersonInfo.getPersonId());
    //                }
    //                SqyPerson notRealNamePerson = sqyPersonService.findByOpenId(personObj.getStr("openid"));
    //                if (notRealNamePerson != null) {
    //                    sqyPersonService.deleteById(notRealNamePerson.getId());
    //                }
    //                sqyPersonService.update(person);
    //            } else {
    //                person = sqyPersonService.findByOpenId(personObj.getStr("openid"));
    //                person.setWxUnionId(personObj.getStr("unionid", null));
    //                person.setName(name);
    //                person.setMobile(phone);
    //                person.setIdCard(idCard);
    //                person.setSqyUserName(wxUserInfo.getStr("nickName"));
    //                person.setSqyHeadPic(wxUserInfo.getStr("avatarUrl"));
    //                person.setSqyStatus(PlatformConstant.IS_VALID);
    //                person.setIdType(idType);
    //                person.setRegTime(new Timestamp(System.currentTimeMillis()));
    //                if (ObjectUtil.isNotEmpty(sqPersonInfo)) {
    //                    person.setSqPersonId(sqPersonInfo.getPersonId());
    //                }
    //                sqyPersonService.update(person);
    //            }
    //            if (ObjectUtil.isNotEmpty(sqPersonInfo)) {
    //                // 打上云标签 更新房间上云人数
    //                sqPersonInfoService.updateCloudData(sqPersonInfo);
    //            }
    //        }
    //        // 删除白名单
    //        try {
    //            lxShangyunService.deleteById(person.getMobile());
    //        } catch (Exception e) {
    //            log.error("1.0注册上云用户" + person.getMobile() + "删除出错：" + e.getMessage());
    //        }
    //        // 缓存数据权限
    //        this.initDataScopesCache(person);
    //        // TODO: 圈子用户现在这里入库
    //        ccUserInfoService.saveFromSmy((JSONObject) JSONUtil.parse(person));
    //        // 更新redis 缓存信息
    //        Map<String, Object> wxSessionInfo = new HashMap<>();
    //        wxSessionInfo.put("openid", personObj.getStr("openid"));
    //        wxSessionInfo.put("sessionKey", personObj.getStr("sessionKey"));
    //        wxSessionInfo.put("unionid", personObj.getStr("unionid"));
    //        if (CommonConstant.CARD_TYPE_1.equals(idType)) {
    //            buildByIdCard(person, idCard);
    //        } else {
    //            if(ObjectUtil.isNotNull(sqPersonInfo) && ObjectUtil.isNotNull(sqPersonInfo.getBirthday())) {
    //                person.setSex(sqPersonInfo.getSex());
    //                Date birthday = new Date(sqPersonInfo.getBirthday().getTime());
    //                buildByBirthday(person, birthday);
    //            }
    //        }
    //        JSONObject unionUser = buildUnionUser(person, wxSessionInfo);
    //        tokenProvider.updateTokenJson(request, unionUser);
    //        return ResponseEntity.ok(HttpStatus.OK);
    //    }
    //
    //    /**
    //     * @desc sqPerson 里塞性别和年龄
    //     */
    //    void buildByIdCard(SqyPerson sqPerson, String idCard) {
    //        String birthday = idCard.substring(6, 14);
    //        String year = birthday.substring(0, 4);
    //        String month = birthday.substring(4, 6);
    //        String day = birthday.substring(6, 8);
    //        Date date = DateUtil.parse(year + "-" + month + "-" + day, "yyyy-MM-dd");
    //        buildByBirthday(sqPerson, date);
    //        String sex = idCard.substring(16, 17);
    //        if (Integer.parseInt(sex) % 2 == 0) {
    //            sex = "2";
    //        } else {
    //            sex = "1";
    //        }
    //        sqPerson.setSex(sex);
    //    }
    //
    //    /**
    //     * @desc sqPerson里塞年龄
    //     */
    //    void buildByBirthday(SqyPerson sqPerson, Date birthday) {
    //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //        try {
    //            Date birthDayAge = sdf.parse(sdf.format(birthday));
    //            Calendar cal = Calendar.getInstance();
    //            int yearNow = cal.get(Calendar.YEAR);
    //            int monthNow = cal.get(Calendar.MONTH);
    //            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
    //            cal.setTime(birthDayAge);
    //            int yearBirth = cal.get(Calendar.YEAR);
    //            int monthBirth = cal.get(Calendar.MONTH);
    //            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
    //            int age = yearNow - yearBirth;
    //            if (monthNow <= monthBirth) {
    //                if (monthNow == monthBirth) {
    //                    if (dayOfMonthNow < dayOfMonthBirth) {
    //                        age--;
    //                    }
    //                } else {
    //                    age--;
    //                }
    //            }
    //            sqPerson.setAge(age);
    //        } catch (ParseException ex) {
    //            log.error("注入年龄失败", ex);
    //        }
    //    }
    //
    //    /**
    //     * @desc 初始化数据权限
    //     */
    //    private void initDataScopesCache(SqyPerson person) {
    //        List<Long> liveDeptIds = new ArrayList<>();
    //        if (StrUtil.isNotBlank(person.getSqPersonId())) {
    //            Map<String, Object> params = new HashMap<>();
    //            params.put("personId", person.getSqPersonId());
    //            List<SqResident> sqResidentList = sqResidentService.getList(params);
    //            List<String> liveDeptIdStrs = sqResidentList.stream().map(SqResident::getJwId).collect(Collectors.toList());
    //            liveDeptIds.addAll(liveDeptIdStrs.stream().map(Long::valueOf).collect(Collectors.toList()));
    //        }
    //        List<Long> manageJwIds = sqyPersonService.getManageJwIds(person.getId());
    //        List<Long> dataScopes = new ArrayList<Long>(){{
    //            this.addAll(liveDeptIds);
    //            this.addAll(manageJwIds);
    //        }};
    //        dataScopes = dataScopes.stream().distinct().collect(Collectors.toList());
    //        redisService.set("user::dataScopes::" + SecurityH5Utils.PLATFORM_WECHAT_MINI_PROGRAM + person.getId(), dataScopes);
    //    }
    //
    //    /**
    //     * 微信小程序token验证
    //     *
    //     * @return
    //     */
    //    @GetMapping("/h5work/token")
    //    @AuthIgnore
    //    @OriginalResponse
    //    public String token(String signature, String timestamp, String nonce, String echostr) {
    //        if (maService.checkSignature(timestamp, nonce, signature)) {
    //            return echostr;
    //        }
    //        return null;
    //    }
    //
    //    /**
    //     * 刷新token
    //     *
    //     * @param params refreshToken
    //     * @return
    //     */
    //    @GetMapping(value = "/h5work/refreshToken")
    //    @Encrypt
    //    @AuthIgnore
    //    public ResponseEntity<Object> refreshToken(@RequestParam Map<String, Object> params) {
    //        Object oldRefreshToken = params.get("token");
    //        if (oldRefreshToken == null) {
    //            throw new ApiException(ResultStatus.BAD_REQUEST);
    //        }
    //        // 验证refreshToken
    //        JSONObject data = (JSONObject) redisService.get("refresh::" + StrUtil.toString(oldRefreshToken));
    //        if (JSONUtil.isNull(data)) {
    //            throw new ApiException(ResultStatus.UNAUTHORIZED);
    //        }
    //        try {
    //            // 生成新的Token
    //            String token = tokenProvider.createToken(data);
    //            // 生成刷新令牌
    //            String newRefreshToken = UUID.randomUUID().toString();
    //            redisService.set("refresh::" + newRefreshToken, data, securityProperties.getTokenValidityInSeconds(), TimeUnit.MILLISECONDS);
    //
    //            // 返回 token
    //            Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
    //                put("token", securityProperties.getTokenStartWith() + token);
    //                put("refreshToken", newRefreshToken);
    //            }};
    //
    //            // 已使用过的refreshToken失效
    //            redisService.del("refresh::" + oldRefreshToken);
    //            return ResponseEntity.ok(authInfo);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            log.error(e.getMessage());
    //            throw new ApiException(ResultStatus.BAD_REQUEST);
    //        }
    //    }
    //
    //    public JSONObject buildUnionUser(SqyPerson person, Map<String, Object> info) {
    //        // 构造token缓存用户信息
    //        JSONObject personObj = JSONUtil.parseObj(person);
    //        // 用户字段统一处理
    //        JSONObject unionUser = JSONUtil.createObj();
    //        unionUser.set("status", StrUtil.isBlank(person.getIdCard()) ? "0" : "1"); //是否实名 0.非实名 1.实名
    //        unionUser.set("email", ""); //邮箱地址
    //        unionUser.set("head_pic", person.getSqyHeadPic()); //头像地址
    //        unionUser.set("sscard", "");  //社保卡号
    //        unionUser.set("name", person.getName()); //用户姓名
    //        unionUser.set("userId", person.getId()); //用户 ID
    //        unionUser.set("idcard", person.getIdCard()); //身份证号码
    //        unionUser.set("userName", person.getId()); //用户名 唯一标识
    //        unionUser.set("mobile", person.getMobile()); //手机号码
    //        unionUser.set("workHeadPic", person.getWorkHeadPic());//工作照
    //        unionUser.set("sex", person.getSex()); //性别
    //        unionUser.set("age", person.getAge()); //年龄
    //        unionUser.set("platform", SecurityH5Utils.PLATFORM_WECHAT_MINI_PROGRAM);
    //        unionUser.set("platformUserInfo", personObj);
    //        // 微信session信息也需要记录
    //        unionUser.putAll(info);
    //        // 用户的personId单独记录
    //        unionUser.set("personId", person.getId());
    //        unionUser.set("sqPersonId", person.getSqPersonId());
    //        return unionUser;
    //    }
    //
    //    /**
    //     * 获取小程序订阅消息模板ID
    //     *
    //     * @return
    //     */
    //    @GetMapping("/h5work/message/template")
    //    @Encrypt
    //    public ResponseEntity<Object> getMessageTemplate() {
    //        return ResponseEntity.ok(sqyMessageConfig.getMiniAppTemplateIds());
    //    }
    //
    //    /**
    //     * @desc 刷新用户权限范围
    //     * @author Linlan
    //     * @date 2022-08-03 14:32
    //     * @version 1.0
    //     * @Param httpServletRequest
    //     * @return org.springframework.http.ResponseEntity<java.lang.Object>
    //     */
    //    @GetMapping("/h5work/refreshDataScopes")
    //    @Encrypt
    //    public ResponseEntity<Object> refreshDataScopes(HttpServletRequest request) {
    //        SqyPerson sqyPerson = sqyPersonService.findById(SecurityH5Utils.getCurrentUserId(request));
    //        if (ObjectUtil.isEmpty(sqyPerson)) {
    //            throw new ApiException(ResultStatus.UNAUTHORIZED);
    //        }
    //        this.initDataScopesCache(sqyPerson);
    //        return ResponseEntity.ok(HttpStatus.OK);
    //    }

}
