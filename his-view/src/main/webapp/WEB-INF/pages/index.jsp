<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 20-12-10
  Time: 下午5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="module/head.jsp"/>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%-- 标头 --%>
    <jsp:include page="module/header.jsp"/>
    <%-- 侧边栏 --%>
    <jsp:include page="module/sidebar.jsp"/>
    <div class="layui-body" style="background: #f2f2f2">
        <div class="layui-row" style="background: #fff; padding: 10px 30px">
            <span class="layui-breadcrumb">
              <a><cite>HIS</cite></a>
            </span>
        </div>
    </div>

    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - HIS医院
    </div>
</div>
<script>
    //JavaScript代码区域
    let element;
    layui.use('element', function(){
        element = layui.element;
    });
</script>
</body>
</html>
