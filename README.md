### 本项目为阿尔法程序协会人员管理后台
- 开发者：stalary 
- 技术栈：spring-mvc，spring-boot，jpa(该项目jpa全部使用原生sql->类似于mybatis)，swagger2
- 模块划分：登录模块，积分模块，展示模块(项目中的分层不够明显，项目较小，便不再细分)
- 登录验证：使用Token的方式进行校验，增加了安全性，消除了每次请求接口需要传递用户ID的弊端
- 缺点：未严格使用dto对象，未完善统一异常处理
- sql：文件在src/main/resources中，本地使用，请修改application.properties

### 版权声明：源码仅供参考，如有错误请联系本人，转载时请注明出处