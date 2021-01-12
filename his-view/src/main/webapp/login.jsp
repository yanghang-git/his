<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 20-12-10
  Time: 下午12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.his.util.MvcConstant"%>
<html>
<head>
    <title>Title</title>
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script src="webjars/layui/2.5.5/layui.js"></script>
    <link rel="stylesheet" href="webjars/layui/2.5.5/css/layui.css">
</head>
<body style="background-color: #f2f2f2">

    <div style="padding:14% 0 0 34%; ">
        <div style="padding-left: 98px">
            <h2 style="padding: 0 0 20px 25px; font-size: 28px">高端汽车租赁管理平台</h2>
            <p style="padding: 10px 0 20px 60px"> YSZ 出品 汽车后台管理模板系统</p>
        </div>

        <form action="login" method="post">
        <div>
            <div class="layui-form-item">
                <label class="layui-form-label   layui-icon layui-icon-username" ></label>
                <div class="layui-input-inline">
                    <input style="width: 300px" type="test" name="loginName" required lay-verify="required" placeholder="请输入账号"  class="layui-input" value="admin">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label layui-icon layui-icon-password"></label>
                <div class="layui-input-inline">
                    <input style="width: 300px" type="password" name="password" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="layui-input" value="12345">
                </div>
            </div>

            <div class="layui-form-item" style="padding-left: 110px">
                <button class="layui-btn" style="width: 300px" lay-submit >登 入</button>
            </div>
        </div>
        </form>
    </div>


</div>
</body>
<script>
    layui.use('layer', function(){
        let ex = "${requestScope[MvcConstant.ATTR_NAME_EXCEPTION]}";
        if (ex.length !== 0) {
            layui.layer.msg(ex);
        }

    })
</script>
</html>
