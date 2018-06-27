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

## 已解决的难点
- 产品展示时如何加载第一张缩略图？
1. 给Product实体增加firstProductImage属性及get/set方法；
`private Productimage firstProductImage;`
2. ProductServiceImpl.java中定义bindImage()方法，调用产品图片ProductimageServiceImpl中的list，获取集合的首个Productimage实体；
3. 在ProductServiceImpl.java的查询List时，调用绑定缩略图bindImage()，给firstProductImage赋值。
4. 在订单管理中查看详情时展示购买产品也需要展示缩略图，所以产品单条查询的方法get()也需要调用bindImage()。
```
    public Product get(int id) {
        Product product = productMapper.selectByPrimaryKey(id);
        bindImage(product);
        return product;
    }
```
- 订单查询时如何显示“金额”、“商品数量”、“买家名称”等表里没有的字段？
1. “买家名称”字段在User表，所以给Order实体增加User属性，在OrderService查询时调用setUser()方法，通过uid获取购买User；
2. “商品数量”根据Orderitem中数量之和计算得到，而总“金额”需要每件产品的单价*购买数量，再计算所有之和。点击查看详情时需要展示产品，
实体增加Product属性。在OrderController查询时调用OrderitemService的fill()方法，计算这个值。
```
   public void fill(Order order) {
        int num = 0;
        float tmoney = 0.00f;

        OrderitemExample orderitemExample = new OrderitemExample();
        orderitemExample.createCriteria().andOidEqualTo(order.getId());
        orderitemExample.setOrderByClause("id desc");
        oilist = orderitemMapper.selectByExample(orderitemExample);

        /*遍历订单详情*/
        for(Orderitem or : oilist){
            /*获取当前购买产品实体并赋值给orderitem*/
            Product p = productService.get(or.getPid());
            or.setProduct(p);

            /*累加计算每笔订单商品总量*/
            num += or.getNumber();

            /*订单总金额+=每笔商品单价*购买数量*/
            Float op = p.getPromotePrice();
            tmoney += or.getNumber()*op;
        }

        order.setOrder_num(num);
        order.setOrder_money(tmoney);
        order.setOrderitems(oilist);
    }
```