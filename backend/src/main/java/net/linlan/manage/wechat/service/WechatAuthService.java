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
package net.linlan.manage.wechat.service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import cn.hutool.http.HttpUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import jakarta.annotation.Resource;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import net.linlan.commons.core.ObjectUtils;
import net.linlan.commons.core.RandomUtils;
import net.linlan.frame.FrameUserDetails;
import net.linlan.frame.comm.service.TokenService;
import net.linlan.frame.comm.vo.AppLoginInfo;
import net.linlan.social.manage.constant.OpenTypeEnum;
import net.linlan.social.manage.vo.ThirdUserInfo;
import net.linlan.social.manage.vo.ThirdUserVo;
import net.linlan.social.permission.service.ThirdPermissionService;
import net.linlan.social.permission.service.UserRangeOrganService;
import net.linlan.social.third.dto.ThirdUserDto;
import net.linlan.social.third.entity.ThirdMember;
import net.linlan.social.third.entity.ThirdMemberBind;
import net.linlan.social.third.service.ThirdMemberBindService;
import net.linlan.social.third.service.ThirdMemberService;
import net.linlan.social.third.service.ThirdMemberTokenService;
import net.linlan.sys.base.constant.CreateTypeEnum;
import net.linlan.sys.base.constant.DelFlagEnum;
import net.linlan.sys.base.constant.SrcCodeEnum;
import net.linlan.sys.base.entity.BaseUser;
import net.linlan.sys.base.entity.BaseUserExt;
import net.linlan.sys.base.service.BaseUserExtService;
import net.linlan.sys.base.service.BaseUserService;
import net.linlan.sys.core.entity.CoreAccount;
import net.linlan.sys.core.service.CoreAccountService;
import net.linlan.sys.web.KernelConstant;
import net.linlan.sys.web.RedisService;
import net.linlan.utils.ServletUtils;
import net.linlan.utils.bean.BeanUtils;
import net.linlan.utils.constant.CacheConstants;
import net.linlan.utils.crypt.ShaUtils;
import net.linlan.utils.exception.SupportException;
import net.linlan.utils.ip.IPUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class WechatAuthService {

    @Resource
    private RedisService            redisService;
    @Resource
    private CoreAccountService      coreAccountService;
    @Resource
    private ThirdMemberService      thirdMemberService;
    @Resource
    private ThirdMemberBindService  thirdMemberBindService;
    @Resource
    private UserRangeOrganService   userRangeOrganService;
    @Resource
    private ThirdPermissionService  thirdPermissionService;
    @Resource
    private BaseUserService         baseUserService;
    @Resource
    private BaseUserExtService      baseUserExtService;
    @Resource
    private TokenService            tokenService;
    @Resource
    private ThirdMemberTokenService thirdMemberTokenService;

    private final String[]          avatarList = { "https://api.dicebear.com/6.x/pixel-art/svg?seed=Raccoon",
                                                   "https://api.dicebear.com/6.x/pixel-art/svg?seed=Kitty",
                                                   "https://api.dicebear.com/6.x/pixel-art/svg?seed=Puppy",
                                                   "https://api.dicebear.com/6.x/pixel-art/svg?seed=Bunny",
                                                   "https://api.dicebear.com/6.x/pixel-art/svg?seed=Fox" };

    public String getWechatLoginCode() {
        //随机获取4位数字
        String code = "DL" + RandomUtils.randomNumbers(4);
        redisService.set(CacheConstants.WX_LOGIN_USER_CODE + code, "NOT-LOGIN",
            KernelConstant.ONE_MINUTE_EXPIRE);
        return code;
    }

    public ThirdUserInfo getWechatIsLogin(String loginCode) {
        ThirdUserInfo loginUserInfo = redisService.get(CacheConstants.WX_LOGIN_USER + loginCode);

        if (loginUserInfo == null) {
            throw new SupportException("登录失败");
        }
        return loginUserInfo;
    }

    public String wechatMpLogin(WxMpXmlMessage message) {
        String code = message.getContent().toUpperCase();
        //先判断登录码是否已过期
        Object e = redisService.hasKey(CacheConstants.WX_LOGIN_USER_CODE + code);
        if (e == null) {
            return "验证码已过期";
        }
        ThirdUserInfo loginUserInfo = wechatMpLogin(null, message.getFromUser());
        //修改redis缓存 以便监听是否已经授权成功
        redisService.set(CacheConstants.WX_LOGIN_USER + code, loginUserInfo,
            KernelConstant.ONE_MINUTE_EXPIRE);
        return "麟览信息数据服务网登录成功！";
    }

    private ThirdUserInfo wechatMpLogin(String unionId, String openId) {
        String openType = OpenTypeEnum.WECHAT_MP.getKey();
        // 查询用户
        return getThirdUserInfoForWechat(openType, unionId, openId);
    }

    public AppLoginInfo miniLogin(String platformType, String code) {
        String openType = "";
        if (platformType.startsWith("wechat")) {
            openType = OpenTypeEnum.WECHAT_MINI.getKey();
        }
        CoreAccount coreAccount = coreAccountService.getByServerType(openType);
        if (ObjectUtils.isEmpty(coreAccount)) {
            return null;
        }
        String url = coreAccount.getServerUrl();

        // 使用示例
        url = buildWechatUrl(url, coreAccount.getClientId(), coreAccount.getClientSecret(), code);
        String result = HttpUtil.get(url);
        JSONObject jsonObject = JSON.parseObject(result);
        log.info("--------" + jsonObject.toString());
        Object openid = jsonObject.get("openid");
        if (openid == null) {
            throw new SupportException("登录失败");
        }
        Object unionid = jsonObject.get("unionid");
        if (unionid == null) {
            throw new SupportException("登录失败");
        }
        ThirdUserInfo thirdUserInfo = getThirdUserInfoForWechat(openType, unionid.toString(),
            openid.toString());
        if (thirdUserInfo == null) {
            return null;
        }
        FrameUserDetails loginUser = thirdPermissionService.createLoginUser(thirdUserInfo);
        // 生成token
        AppLoginInfo appLoginInfo = tokenService.createToken(loginUser);
        //单独保存会员用户TOKEN
        thirdMemberTokenService.saveOrUpdateFromThird(appLoginInfo, loginUser);
        if (loginUser.getMobile() == null) {
            appLoginInfo.setMobileConfirm(false);
        }
        return appLoginInfo;
    }

    private ThirdUserInfo getThirdUserInfoForWechat(String openType, String unionId,
                                                    String openId) {
        // 查询用户微信的小程序和公众号同一个unionId，需要通过openId和openType查询
        ThirdUserDto thirdUserDto = null;
        if (thirdUserDto == null) {
            thirdUserDto = thirdMemberService.getThirdUserByOpenId(openId, openType);
        }
        if (ObjectUtils.isEmpty(thirdUserDto)) {
            //当前用户不存在，添加用户
            thirdUserDto = bindFromMpMini(openType, unionId, openId);
            //添加用户角色信息
            userRangeOrganService.saveOrUpdateFromMpMini(openType, openId);
        } else {
            if (thirdUserDto.getDelFlag() != DelFlagEnum.NORMAL.getKey()) {
                throw new SupportException("账号已被禁用，请联系管理员");
            }
        }
        if (thirdUserDto == null) {
            throw new SupportException("账号绑定失败，请稍后再试");
        }
        ThirdUserVo thirdUserVo = (ThirdUserVo) ThirdUserVo.DTO.apply(thirdUserDto);
        // 角色集合，前台用户的角色通过关联的公共组进行读取
        Set<Long> roles = thirdPermissionService.getPublicGroupPermission(thirdUserDto.getId());
        // 权限集合，前台用户权限集合可为空
        Set<String> permissions = new HashSet<>();
        ThirdUserInfo thirdUserInfo = new ThirdUserInfo();
        thirdUserInfo.setUser(thirdUserVo);
        thirdUserInfo.setRoles(roles);
        thirdUserInfo.setPermissions(permissions);
        return thirdUserInfo;
    }

    public String buildWechatUrl(String url, String clientId, String clientSecurity, String code) {
        return String.format(url, clientId, clientSecurity, code);
    }

    @Transactional
    public ThirdUserDto bindFromMpMini(String platformType, String unionId, String openId) {
        //保存ThirdMember
        String userId = "";
        Long memberId = RandomUtils.randomLid();
        CoreAccount coreAccount = coreAccountService.getByServerType(platformType);
        if (coreAccount == null) {
            return null;
        }
        String avatar = avatarList[(int) (Math.random() * avatarList.length)];
        String nickname = "小程序用户" + RandomUtils.randomNumbers(8);
        String mobile = "";
        if (OpenTypeEnum.WECHAT_MP.getKey().equals(platformType)) {
            nickname = "微信用户" + RandomUtils.randomNumbers(8);
        }
        ThirdMember thirdMember = null;
        //unionId可能为空，需要判断一下
        if (ObjectUtils.isNotEmpty(platformType) && ObjectUtils.isNotEmpty(openId)) {
            thirdMember = thirdMemberService.findByOpenId(openId, platformType);
        }
        BaseUser baseUser = null;
        BaseUserExt baseUserExt = null;
        if (thirdMember == null) {
            //当前会员用户为新用户，先判断基础用户是否存在，不存在则创建
            if (ObjectUtils.isNotEmpty(unionId)) {
                baseUser = baseUserService.getByUnionId(unionId);
            }
            //通过openId查询username
            if (baseUser == null) {
                //如果openId查询没有记录，则在通过手机号查询，保证baseUser内为同一个人
                if (ObjectUtils.isNotEmpty(openId)) {
                    baseUser = baseUserService.getByOpenId(openId);
                }
            }
            if (baseUser == null) {
                //当前用户不存在，则全部保存
                userId = RandomUtils.UUID32();
                baseUser = new BaseUser();
                baseUser.setId(userId);
                baseUser.setSource(coreAccount.getServerType());
                baseUser.setCreateType(CreateTypeEnum.AUTH.getKey());
                baseUser.setSrcCode(SrcCodeEnum.SRC_CODE_THIRD.getKey());
                baseUser.setUsername(openId);
                baseUser.setMobile(mobile);
                baseUser
                    .setPassword(ShaUtils.encryptPassword(KernelConstant.DEFAULT_PASSWORD_NORMAL));
                baseUser.setSpare1(unionId);
                baseUser.setSpare2(openId);
                baseUser.setLastLoginIp(IPUtils.getIpAddr(ServletUtils.getRequest()));
                baseUser.setLastLoginTime(new Date());
                baseUser.setLoginCount(1);
                baseUser.setCurrentLoginIp(IPUtils.getIpAddr(ServletUtils.getRequest()));
                baseUser.setCurrentLoginTime(new Date());
                baseUserService.save(baseUser);
                //保存Ext扩展信息
                baseUserExt = new BaseUserExt();
                baseUserExt.setId(userId);
                baseUserExt.setNickname(nickname);
                baseUserExt.setImagePath(avatar);
                baseUserExtService.save(baseUserExt);
            } else {
                userId = baseUser.getId();
                //微信支付宝绑定同一个人的处理
                baseUser.setId(userId);
                //原有信息如果已经存在，则不更新
                if (baseUser.getSource() == null) {
                    baseUser.setSource(coreAccount.getServerType());
                }
                baseUser.setCreateType(CreateTypeEnum.AUTH.getKey());
                baseUser.setSrcCode(SrcCodeEnum.SRC_CODE_THIRD.getKey());
                if (unionId != null) {
                    if (baseUser.getSpare1() == null) {
                        baseUser.setSpare1(unionId);
                    } else {
                        if (!baseUser.getSpare1().contains(unionId)) {
                            baseUser.setSpare1(baseUser.getSpare1() + ";" + unionId);
                        }
                    }
                }
                if (baseUser.getSpare2() == null) {
                    baseUser.setSpare2(openId);
                } else {
                    if (!baseUser.getSpare2().contains(openId)) {
                        baseUser.setSpare2(baseUser.getSpare2() + ";" + openId);
                    }
                }
                baseUser.setLastLoginIp(IPUtils.getIpAddr(ServletUtils.getRequest()));
                baseUser.setLastLoginTime(new Date());
                baseUser.setCurrentLoginIp(IPUtils.getIpAddr(ServletUtils.getRequest()));
                baseUser.setCurrentLoginTime(new Date());
                baseUserService.update(baseUser);
                //不更新扩展信息
                mobile = baseUser.getMobile();
            }
            thirdMember = new ThirdMember();
            thirdMember.setId(memberId);
            thirdMember.setUserId(userId);
            thirdMember.setUnionId(unionId);
            thirdMember.setOpenId(openId);
            thirdMember.setAccountId(coreAccount.getId());
            thirdMember.setAccountType(platformType);
            thirdMember.setMobile(mobile);
            thirdMember.setNickName(nickname);
            thirdMember.setAvatarUrl(avatar);
            thirdMemberService.save(thirdMember);
        } else {
            //当前会员用户之前已经保存过信息
            userId = thirdMember.getUserId();
            memberId = thirdMember.getId();
            thirdMember.setAccountId(coreAccount.getId());
            thirdMember.setNickName(nickname);
            thirdMember.setAvatarUrl(avatar);
            thirdMember.setDelFlag(DelFlagEnum.NORMAL.getKey());
            thirdMemberService.update(thirdMember);

            //userId不为空，则当前基础用户一定存在
            baseUser = baseUserService.findById(userId);
            baseUser.setSource(coreAccount.getServerType());
            baseUser.setCreateType(CreateTypeEnum.AUTH.getKey());
            if (unionId != null) {
                if (baseUser.getSpare1() == null) {
                    baseUser.setSpare1(unionId);
                } else {
                    if (!baseUser.getSpare1().contains(unionId)) {
                        baseUser.setSpare1(baseUser.getSpare1() + ";" + unionId);
                    }
                }
            }
            if (baseUser.getSpare2() == null) {
                baseUser.setSpare2(openId);
            } else {
                if (!baseUser.getSpare2().contains(openId)) {
                    baseUser.setSpare2(baseUser.getSpare2() + ";" + openId);
                }
            }
            baseUser.setLastLoginIp(IPUtils.getIpAddr(ServletUtils.getRequest()));
            baseUser.setLastLoginTime(new Date());
            baseUser.setCurrentLoginIp(IPUtils.getIpAddr(ServletUtils.getRequest()));
            baseUser.setCurrentLoginTime(new Date());
            baseUserService.update(baseUser);
            //不更新扩展信息

            //保存绑定记录
            ThirdMemberBind thirdMemberBind = new ThirdMemberBind();
            thirdMemberBind.setMemberId(memberId);
            thirdMemberBind.setUserId(userId);
            thirdMemberBind.setUsername(openId);
            thirdMemberBind.setAccountId(coreAccount.getId());
            thirdMemberBind.setAccountKey(openId);
            thirdMemberBind.setBindTime(new Date());
            thirdMemberBind.setDelFlag(DelFlagEnum.NORMAL.getKey());
            thirdMemberBindService.save(thirdMemberBind);

        }

        ThirdUserDto result = new ThirdUserDto();
        BeanUtils.copyProperties(thirdMember, result);
        result.setBaseUser(baseUser);
        result.setBaseUserExt(baseUserExt);
        return result;
    }

}
