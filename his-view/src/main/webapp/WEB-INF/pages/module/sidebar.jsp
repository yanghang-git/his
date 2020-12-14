<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 20-12-10
  Time: 下午5:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.his.util.HisMvcConstant" %>
<div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
        <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
        <ul class="layui-nav layui-nav-tree">
            <c:forEach items="${requestScope[HisMvcConstant.ATTR_MENU]}" var="menu">
                <li class="layui-nav-item">
                        <a class="" href="javascript:;">${menu.key.permName}</a>
                    <dl class="layui-nav-child">
                        <c:forEach items="${menu.value}" var="perm">
                            <dd><a href="/to/${perm.permUrl}">${perm.permName}</a></dd>
                        </c:forEach>
                    </dl>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>