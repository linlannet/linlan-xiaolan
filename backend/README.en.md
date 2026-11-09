# xianlan_backend

## Introduce
Xiaolan Life Java Backend

Front-end engineering path:

**VUE+TS MINI：** <a target="_blank">https://github.com/linlannet/xianlan_mini
**VUE+TS ADMIN：** <a target="_blank">https://github.com/linlannet/xianlan_admin


## Change Log

[CHANGELOG](./CHANGELOG.md)


## Technical Guidelines
### Software Architecture
```
Springboot + Framework + Security + Mybatis
```

### Installation
1. mvn clean install
2. mvn clean deploy

### Instructions
1. use by maven
```
   <dependency>
      <groupId>${groupId}</groupId>
      <artifactId>${artifactId}</artifactId>
      <version>${project.version}</version>
      <scope>test</scope>
   </dependency>
```
2. use direct jar

## Contribution
1.  Fork repository
2.  new Feat_1.0.0 branch
3.  commit
```
git config user.name linlaninfo
git config user.email linlannet@163.com
git config --global --list
git config --list
```
4. new Pull Request
5. create and commit tag
```
create
git tag -a 1.0.0 -m "initial version"
list
git tag
commit
git push origin --tags
delete
git tag -d 1.0.0

install: mvn clean install
deploy: mvn clean deploy

```
6. other

## Licence

[Apache 2.0 © xianlan_backend 2023](./LICENSE)
