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


<div id="userBox" style="padding: 15px">
    <form class="layui-form">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="title" required  lay-verify="required" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="text" name="title" required  lay-verify="required" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
                <input type="text" name="title" required  lay-verify="required" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
                <input type="text" name="title" required  lay-verify="required" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">加入时间</label>
            <div class="layui-input-block">
                <input type="text" name="title" id="date" required  lay-verify="required" placeholder="请输入" class="layui-input">
            </div>
        </div>
    </form>
</div>
<script>
    // 侧边栏需要element模块的支持
    layui.use(['element']);

    // 使用时间模块
    layui.use('laydate', function() {
        layui.laydate.render({elem: '#date'})
    })


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
                {field: 'username', title: '姓名'},
                {field: 'loginName', title: '登录名'},
                {field: 'gender', title: '性别', templet: d => d.gender === null ? "" : d.gender ? "男" : "女"},
                {field: 'email', title: '邮箱'},
                {field: 'mobileNo', title: '手机'},
                {field: 'birthDate', title: '加入时间', sort: 'true'},
                {toolbar: '#userTableToolb', title: '操作'}
            ]]
        });

        table.on('tool(userTableFilter)', function ({event, data}) {
            console.log(data);
            switch (event) {
                case "security":
                    // security();
                    break;
                case "edit":
                    // edit();
                    break;
                case "remove":
                    remove();
                    break;
            }
        })

        table.on('toolbar(userTableFilter)', function({event}) {
            switch (event) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove(table.checkStatus('userTableId').data.map(data => data.userId));
                    break;
            }
        });
    });

    // 添加user
    function add() {
        layui.use(['jquery', 'form', 'layer'], function() {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            layer.open({
                type: 1,
                content: $('#userBox'),
                auto: ['300px'],
                btn: ['保存', '取消'],
                btn1: function(index) {
                    layer.close(index);
                }
            });
        })
    }

    // 删除user
    function remove(userIdArr) {
        console.log(userIdArr);
    }

    // 点击查询
    layui.use(['form', 'jquery', 'table'], function () {
        let form = layui.form;
        let $ = layui.$;
        let table = layui.table;

        $("#user-search").click(function () {
            table.reload("userTableId", {where: form.val("searchForm")})
        });
    })

</script>
</body>
</html>
<script type="text/html" id="userTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs" lay-event="security"><i class="layui-icon">&#xe672;</i></button>
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="layui-icon"></i></button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i></button>
    </div>
</script>
<script type="text/html" id="userTableToolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add"><i class="layui-icon">&#xe654;</i>新增
        </button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>删除
        </button>
    </div>
</script>