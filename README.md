# 【SSM】模仿天猫后台
## 主要功能
### 分类管理（Category）
- 分类查询
- 新增分类
- 编辑分类
- 删除分类
- 分类图片展示
### 属性管理（Property）
- 属性查询
- 新增属性
- 删除属性
- 编辑属性
### 产品管理（Product）
- 产品查询
- 图片管理
- 编辑产品
- 删除产品
### 用户管理（User）
- 查询用户
### 订单管理（Order）
- 查询订单
- 查看订单详情

### 分页功能（PageHelper）

## 技术要求
- Spring+SpringMVC+Mybatis 整合
- JavaScript/jQuery
- HTML/CSS
- Tomcat
- MySQL

## 使用工具
- InteliJ Idea
- Navicat
- Sublime Text3

## 未实现功能
- 订单中的订单状态对应码（需要跟前端用户的操作联系起来）
- 订单管理的发货操作（需要根据订单状态控制）
- 产品管理中批量新增、批量编辑属性（一个新增的产品需要新增属性、已有的需要批量修改属性）