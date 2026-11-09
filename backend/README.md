# xianlan_backend

## 介绍

小览生活后台服务端


前端工程路径：

**VUE+TS前端小程序地址：** <a target="_blank">https://github.com/linlannet/xianlan_mini
**VUE+TS前端管理地址：** <a target="_blank">https://github.com/linlannet/xianlan_admin


## 版本历史

[CHANGELOG](./CHANGELOG.md)


## 技术路径
### 软件架构
```
Springboot + Framework + Security + Mybatis
```

### 安装教程
1. mvn clean install
2. mvn clean deploy

### 使用说明
1. 通过依赖使用
```
   <dependency>
      <groupId>net.linlan.plat</groupId>
      <artifactId>xiaolan-backend</artifactId>
      <version>1.2.3</version>
   </dependency>
```
2. 直接拷贝打包后的jar包

## 参与贡献
1.  Fork 本仓库
2.  新建 Feat_1.0.0 分支
3.  提交代码
```
git config user.name linlaninfo
git config user.email linlannet@163.com
git config --global --list
git config --list
```
4. 新建 Pull Request
5. 创建和提交tag
```
创建
git tag -a 1.0.0 -m "初始化版本"
查看
git tag
提交
git push origin --tags
删除
git tag -d 1.0.0

本地编译：mvn clean install
发布到maven中央库：mvn clean deploy

```
6. 其他

## 版权

[Apache 2.0 © xianlan_backend 2023](./LICENSE)
