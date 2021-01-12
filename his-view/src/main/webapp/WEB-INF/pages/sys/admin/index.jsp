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
              <a href="index">HIS</a>
              <a><cite>用户管理</cite></a>
            </span>
        </div>

        <%-- main --%>
        <div style="background: #fff; margin: 15px; padding: 20px">
            <%-- 筛选条件 --%>
            <form class="layui-form search" lay-filter="searchForm">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">店面</label>
                        <div class="layui-input-block">
                            <select name="adminShop">
                                <option value="-1">全部店面</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">登录名</label>
                        <div class="layui-input-block">
                            <input type="text" name="loginName" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">手机</label>
                        <div class="layui-input-block">
                            <input type="text" name="phone" maxlength="11" placeholder="请输入" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <button class="layui-btn" type="button" id="user-search">
                            <i class="layui-icon layui-icon-search"></i>
                        </button>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn layui-btn-warm" type="reset">
                            <i class="layui-icon layui-icon-refresh"></i>
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
        © his.com - 高端汽车管理系统
    </div>
</div>


<table id="roleTable" lay-filter="roleTableFilter" style="display: none"></table>

<div id="userBox" style="padding: 15px">
    <form class="layui-form" lay-filter="userBoxFilter">
        <input type="hidden" name="adminId"/>
        <div class="layui-form-item">
            <label class="layui-form-label">登录帐号</label>
            <div class="layui-input-block">
                <input type="text" name="adminUsername" maxlength="16" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <input type="text" name="adminPass" maxlength="16" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="adminName" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="adminSex" value="true" title="男" checked>
                <input type="radio" name="adminSex" value="false" title="女">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
                <input type="text" name="adminPhone" maxlength="11" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">店面</label>
            <div class="layui-input-block">
                <select name="adminShop"></select>
            </div>
        </div>
    </form>
</div>
<script src="js/util.js"></script>
<script>
    // 侧边栏需要element模块的支持
    layui.use(['element']);

    // 初始化归属的门店
    layui.use('jquery', function () {
        let $ = layui.$;
        $.ajax({
            url: "/shop/list",
            method: 'post',
            async: false,
            success: function ({data}) {
                let $searchSelect = $('.search select[name=adminShop]');
                let $userBoxSelect = $('#userBox select[name=adminShop]');
                for (let shop of data) {
                    $searchSelect.append(`<option value='` + shop.shopId + `'> ` + shop.shopName + `</option>`)
                    $userBoxSelect.append(`<option value='` + shop.shopId + `'> ` + shop.shopName + `</option>`)
                }
                sessionStorage.setItem("shopCacheMap", JSON.stringify(data));
            }
        })
    })

    // userTable的表格相关操作
    layui.use(['table'], function () {
        let table = layui.table;
        let shopCacheMap = getMapByArr(JSON.parse(sessionStorage.getItem("shopCacheMap")), 'shopId');
        table.render({
            id: 'adminTableId',
            elem: '#userTable',
            page: true,
            url: '/admin/getPage', //数据接口parsererror
            toolbar: '#userTableToolbar',
            cols: [[ //表头
                {type: 'checkbox'},
                {type: "numbers", title: '#'},
                {field: 'adminName', title: '姓名'},
                {field: 'adminUsername', title: '登录名'},
                {field: 'adminSex', title: '性别', templet: d => d.adminSex ? "男" : "女"},
                {field: 'adminPhone', title: '手机'},
                {
                    field: 'adminShop',
                    title: '店面',
                    templet: d => shopCacheMap.has(d.adminShop + "") ? shopCacheMap.get(d.adminShop + "").shopName : ""
                },
                {toolbar: '#userTableToolb', title: '操作'}
            ]]
        });


        // 加载角色的table
        table.render({
            id: 'roleTableId',
            elem: '#roleTable',
            url: '/role/list',
            cols: [[
                {field: 'roleId', hide: true},
                {type: 'checkbox'},
                {field: 'roleLabel', title: '角色名称'},
            ]]
        })

        table.on('tool(userTableFilter)', function ({event, data}) {
            switch (event) {
                case "security":
                    security(data.adminId);
                    break;
                case "edit":
                    edit(data);
                    break;
                case "remove":
                    remove([data.adminId]);
                    break;
            }
        })

        table.on('toolbar(userTableFilter)', function ({event}) {
            switch (event) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove(table.checkStatus('adminTableId').data.map(data => data.adminId));
                    break;
            }
        });
    });


    // 添加user
    function add() {
        document.querySelector('#userBox form').reset();
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            layer.open({
                type: 1,
                content: $('#userBox'),
                auto: ['300px'],
                btn: ['保存', '取消'],
                btn1: function (index) {
                    let data = form.val('userBoxFilter');
                    if (data.adminName === '' || data.adminPass === '' || data.adminPhone === '' || data.adminUsername === '') {
                        layer.msg('请填写完整的信息以后再次确认');
                    } else if (data.adminUsername.length < 6) {
                        layer.msg('登录帐号不允许少与6位');
                    } else if (data.adminPass.length < 6) {
                        layer.msg('登录密码不允许少与6位');
                    } else if (!/^1[3|5]\d{9}/.test(data.adminPhone)) {
                        layer.msg('手机号必须以13/15开头')
                    } else {
                        $.post('/admin/save', form.val('userBoxFilter'))
                            .done(function ({code, msg}) {
                                if (code > 0) {
                                    if ($('.layui-laypage-skip input').val() !== 1) {
                                        $('.layui-laypage-skip input').val(99999);
                                        $('.layui-laypage-skip .layui-laypage-btn').click();
                                    } else {
                                        table.reload('adminTableId');
                                    }
                                }
                                layer.msg(msg);
                            });
                        layer.close(index);
                    }
                }
            });
        })
    }

    // 修改user
    function edit(data) {
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            data.adminPass = '';
            $('#userBox input[name=adminSex]').removeAttr('checked');
            form.val("userBoxFilter", data);
            $('#userBox input[value=' + data.adminSex + '').attr('checked', 'checked').next().click();
            layer.open({
                type: 1,
                title: '只修改填写的， 为空的不修改',
                content: $('#userBox'),
                auto: ['300px'],
                btn: ['修改', '取消'],
                btn1: function (index) {
                    let data = form.val('userBoxFilter');
                    if (data.adminUsername !== '' && data.adminUsername.length < 6) {
                        layer.msg('登录帐号不允许少与6位');
                    } else if (data.adminPass !== '' && data.adminPass.length < 6) {
                        layer.msg('登录密码不允许少与6位');
                    } else if (data.adminPhone !== '' && !/^1[3|5]\d{9}/.test(data.adminPhone)) {
                        layer.msg('手机号必须以13/15开头')
                    } else {
                        $.post('/admin/edit', data)
                            .done(function ({code, msg}) {
                                if (code > 0) {
                                    table.reload('adminTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                                }
                                layer.msg(msg);
                            });
                        layer.close(index);
                    }
                }
            });
        })
    }

    // 删除user
    function remove(adminIdArr) {
        if (adminIdArr.length === 0) {
            layer.alert("请至少选中一条数据");
            return;
        }
        layui.use(['layer', 'jquery', 'table'], function () {
            let layer = layui.layer;
            let $ = layui.$;
            let table = layui.table;
            layer.confirm('你确定要删除吗?', {
                icon: 3,
                title: '提示',
                btn: ['删除', '取消']
            }, function(index) {
                $.ajax({
                    url: '/admin/remove',
                    method: 'post',
                    data: {ids: adminIdArr},
                    traditional: true,
                    success: function ({code, msg}) {
                        if (code > 0) {
                            table.reload('adminTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                        }
                        layer.msg(msg);
                    },
                    error: function (resp) {
                        layer.msg(resp.status + " " + resp.statusMessage);
                    }
                })
                layer.close(index);
            })

        });
    }

    // 改修user角色权限
    function security(adminId) {
        layui.use(['layer', 'table', 'jquery'], function () {
            let layer = layui.layer;
            let table = layui.table;
            let $ = layui.$;
            $.post('/role/getRoleIdByUserId', {adminId})
                .done(function ({data}) {
                    /*
                       未知原因 在需要全选时如果是一个个的点击时，
                           第一访问无事， 第二次则会第一个不会选中  第三次则第二不会选中  以此类推 如果到最后一个 则会回到第一个不会选中  循环

                       解决方法 在得到需要选中的数据时， 判断是否是需要全选。  如果是则直接点击全选按钮即可
                    */
                    let $roleTableTr = $('#roleTable+div tbody tr');
                    let $checkBoxDiv = $(this).children('td:eq(1)').children('div').children('div');
                    if ($roleTableTr.length === data.length) {
                        $roleTableTr.each(function () {
                            // 将所有的多选设置为未选中
                            if ($checkBoxDiv.hasClass('layui-form-checked')) {
                                $checkBoxDiv.children('i').click();
                            }
                        });
                        $('#roleTable+div thead tr th:eq(1) i').click();
                    } else {
                        $roleTableTr.each(function () {
                            let $checkBoxDiv = $(this).children('td:eq(1)').children('div').children('div');
                            // 将所有的多选设置为未选中
                            if ($checkBoxDiv.hasClass('layui-form-checked')) {
                                $checkBoxDiv.children('i').click();
                            }
                            // 将指定数组的内容设置为选中
                            if (!$checkBoxDiv.hasClass('layui-form-checked') && data.indexOf(parseInt($(this).children('td:eq(0)').children('div').text())) !== -1) {
                                console.log($checkBoxDiv[0])
                                $checkBoxDiv.children('i').click();
                            }
                        });
                    }
                });

            layer.open({
                title: '角色列表',
                type: 1,
                content: $('#roleTable').next(),
                btn: ['授权', '取消'],
                btn1: function (index) {
                    let roleIds = table.checkStatus('roleTableId').data.map(role => role.roleId);
                    $.ajax({
                        url: '/admin/changePermission',
                        method: 'post',
                        data: {adminId, roleIds},
                        traditional: true,
                        success: function ({msg}) {
                            layer.msg(msg);
                        },
                        error: function (resp) {
                            layer.msg(resp.status + " " + resp.statusMessage);
                        }
                    })
                    layer.close(index);
                }
            });
        })

    }

    // 点击查询
    layui.use(['form', 'jquery', 'table'], function () {
        let form = layui.form;
        let $ = layui.$;
        let table = layui.table;
        $("#user-search").click(function () {
            table.reload("adminTableId", {where: form.val("searchForm"), page:{curr: 1}})
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