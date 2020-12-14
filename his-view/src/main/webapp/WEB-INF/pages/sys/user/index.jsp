<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 20-12-12
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<jsp:include page="../../module/head.jsp"/>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%-- 标头 --%>
    <jsp:include page="../../module/header.jsp"/>
    <%-- 侧边栏 --%>
    <jsp:include page="../../module/sidebar.jsp"/>
    <div class="layui-body" style="background: #f2f2f2">
        <%-- 面包屑 --%>
        <div class="layui-row" style="background: #fff; padding: 10px 15px">
            <span class="layui-breadcrumb">
              <a href="/index">HIS</a>
              <a><cite>用户管理</cite></a>
            </span>
        </div>

        <%-- main --%>
        <div style="background: #fff; margin: 15px; padding: 20px">
            <%-- 筛选条件 --%>
            <form class="layui-form" lay-filter="searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">登录名</label>
                        <div class="layui-input-block">
                            <input type="text" name="loginName" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">邮箱</label>
                        <div class="layui-input-block">
                            <input type="text" name="email" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">手机</label>
                        <div class="layui-input-block">
                            <input type="password" name="phone" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn" type="button" id="user-search">
                            <i class="layui-icon layui-icon-search"></i>
                        </button>
                    </div>
                </div>

            </form>
            <%-- table --%>
            <div>
                <table id="userTable" lay-filter="userTableFilter"></table>
            </div>
        </div>

    </div>
    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - HIS医院
    </div>
</div>
<script>
    // 侧边栏需要element模块的支持
    layui.use(['element']);

    // userTable的表格相关操作
    layui.use(['table'], function () {
        let table = layui.table;
        table.render({
            id: 'userTableId',
            elem: '#userTable',
            page: true,
            url: '/user/getPage', //数据接口
            toolbar: '#userTableToolbar',
            cols: [[ //表头
                {type: 'checkbox'},
                {type: "numbers", title: '#'},
                {field: 'loginName', title: '登录名'},
                {field: 'gender', title: '性别', templet: d => d.gender === null ? "" : d.gender ? "男" : "女"},
                {field: 'email', title: '邮箱'},
                {field: 'mobileNo', title: '手机'},
                {field: 'birthDate', title: '加入时间', sort: 'true'},
                {toolbar: '#userTableToolb', title: '操作'}
            ]]
        });
    });

    // 点击查询
    layui.use(['form', 'jquery', 'table'], function() {
        let form = layui.form;
        let $ = layui.$;
        let table = layui.table;

        $("#user-search").click(function() {
            table.reload("userTableId", {where: form.val("searchForm")})
        });
    })

</script>
</body>
</html>
<script type="text/html" id="userTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs" lay-event="security"><i class="layui-icon">&#xe672;</i>授权</button>
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="layui-icon"></i>修改</button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>删除
        </button>
    </div>
</script>
<script type="text/html" id="userTableToolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="layui-icon">&#xe654;</i>新增</button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>删除
        </button>
    </div>
</script>