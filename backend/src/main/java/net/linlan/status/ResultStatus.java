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
package net.linlan.status;

/**
 * 接口返回状态码
 * 200成功
 * 401未登录授权或授权已过期
 * 500服务内部错误
 */
public enum ResultStatus {
                          // 系统级别
                          OK(200,
                             "成功"), BAD_REQUEST(400,
                                                "服务正在维护，请稍后再试"), UNAUTHORIZED(401,
                                                                              "未登录授权或授权已过期"), ERROR(500,
                                                                                                    "服务内部错误"),

                          // Oauth自定义异常信息
                          OAUTH_CLIENT_AUTHENTICATION_FAILED(1001,
                                                             "客户端认证失败"), OAUTH_USERNAME_OR_PASSWORD_ERROR(1002,
                                                                                                          "用户名或密码错误"), OAUTH_UNSUPPORTED_GRANT_TYPE(1003,
                                                                                                                                                    "不支持的认证模式"), OAUTH_UNSUPPORTED_SCOPE(1004,
                                                                                                                                                                                         "SCOPE错误"), OAUTH_INVALID_CLIENT_ID(1005,
                                                                                                                                                                                                                             "无效的CLIENTID"), OAUTH_INVALID_PARAMS(1006,
                                                                                                                                                                                                                                                                  "无效参数"),

                          // 微信小程序
                          WX_MA_ERROR_1000(1000,
                                           "用户未授权登录"), WX_MA_ERROR_1001(1001,
                                                                        "用户信息校验失败"), WX_MA_ERROR_1002(1002,
                                                                                                      "身份证号不合法"), WX_MA_ERROR_1003(1003,
                                                                                                                                   "已绑定其他微信账号"), WX_MA_ERROR_1004(1000,
                                                                                                                                                                  "用户登录失败"), WX_MA_ERROR_1005(1005,
                                                                                                                                                                                              "未查询到您居住的社区，请联系居住地居委会"), WX_MA_ERROR_1008(1008,
                                                                                                                                                                                                                                        "证件类型请选择身份证"),

                          // 微信支付
                          WX_PAY_ERROR_1000(1000, "生成预订单失败"),
                          //头像修改
                          HEAD_UPDATE_ERROR(2013, "修改失败"),
                          // 居委审核
                          JW_UPDATE_ERROR(2013, "此居家隔离证明申请已被处理！"),

                          // 业务级别 - CA
                          BUSI_CA_ERROR_5000(5000,
                                             "CA获取ACCESS_TOKEN失败"), BUSI_CA_ERROR_5001(5001,
                                                                                       "CA创建组织失败"), BUSI_CA_ERROR_5002(5002,
                                                                                                                       "CA更新组织失败"), BUSI_CA_ERROR_5003(5003,
                                                                                                                                                       "CA删除组织失败"), BUSI_CA_ERROR_5004(5004,
                                                                                                                                                                                       "CA创建账户人员失败"), BUSI_CA_ERROR_5005(5005,
                                                                                                                                                                                                                         "CA更新账户人员失败"), BUSI_CA_ERROR_5006(5006,
                                                                                                                                                                                                                                                           "CA删除账户人员失败"), BUSI_CA_ERROR_5007(5007,
                                                                                                                                                                                                                                                                                             "居村委用户同步至政务微信用户数不可超过2个"), BUSI_CA_ERROR_5008(5008,
                                                                                                                                                                                                                                                                                                                                          "CA查询账户人员失败"), BUSI_CA_ERROR_5099(5099,
                                                                                                                                                                                                                                                                                                                                                                            "CA调用接口失败"),

    ;

    private int    status;
    private String message;

    ResultStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
