<%--
  Created by IntelliJ IDEA.
  role: spring
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
              <a><cite>角色管理</cite></a>
            </span>
        </div>

        <%-- main --%>
        <div style="background: #fff; margin: 15px; padding: 20px">
            <%-- 筛选条件 --%>
            <form class="layui-form" lay-filter="searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">查询内容</label>
                        <div class="layui-input-block">
                            <input type="text" name="keyword" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn" type="button" id="role-search">
                            <i class="layui-icon layui-icon-search"></i>
                        </button>
                    </div>
                </div>


            </form>
            <%-- table --%>
            <div>
                <table id="roleTable" lay-filter="roleTableFilter"></table>
            </div>
        </div>

    </div>
    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - HIS医院
    </div>
</div>


<div id="permTree" style="display: none"></div>

<div id="roleBox" style="padding: 15px; display: none">
    <form class="layui-form" lay-filter="roleBoxFilter">
        <input type="hidden" name="roleId"/>
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="roleLabel" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Code</label>
            <div class="layui-input-block">
                <input type="text" name="roleCode" placeholder="请输入" class="layui-input">
            </div>
        </div>
    </form>
</div>
<script src="js/layuiTreeUtil.js"></script>
<script>
    // 侧边栏需要element模块的支持
    layui.use(['element']);

    // roleTable的表格相关操作
    layui.use(['table'], function () {
        let table = layui.table;
        table.render({
            id: 'roleTableId',
            elem: '#roleTable',
            page: true,
            url: '/role/getPage', //数据接口
            toolbar: '#roleTableToolbar',
            cols: [[ //表头
                {type: 'checkbox'},
                {type: "numbers", title: '#'},
                {field: 'roleLabel', title: '名称'},
                {field: 'roleCode', title: 'Code'},
                {toolbar: '#roleTableToolb', title: '操作'}
            ]]
        });

        table.on('tool(roleTableFilter)', function ({event, data}) {
            switch (event) {
                case "security":
                    security(data.roleId);
                    break;
                case "edit":
                    edit(data);
                    break;
                case "remove":
                    remove([data.roleId]);
                    break;
            }
        })

        table.on('toolbar(roleTableFilter)', function ({event}) {
            switch (event) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove(table.checkStatus('roleTableId').data.map(data => data.roleId));
                    break;
            }
        });
    });

    // 添加role
    function add() {
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            layer.open({
                type: 1,
                content: $('#roleBox'),
                auto: ['300px'],
                btn: ['保存', '取消'],
                btn1: function (index) {
                    let data = form.val('roleBoxFilter');
                    if (data['roleLabel'].trim() === '' || data['roleCode'].trim() === '') {
                        layer.msg('请填写完整的信息后再次点击添加');
                        return;
                    }
                    $.post("/role/save", data)
                        .done(function ({code, msg}) {
                            if (code > 0) {
                                $('.layui-laypage-skip input').val(99999);
                                if ($('.layui-laypage-skip input').val() === 1) {
                                    table.reload('userTableId');
                                } else {
                                    $('.layui-laypage-skip .layui-laypage-btn').click();
                                }
                            }
                            layer.msg(msg);
                        });
                    $('#roleBox form')[0].reset();
                    layer.close(index);
                }
            });
        })
    }

    // 修改role
    function edit(data) {
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            form.val("roleBoxFilter", data)
            layer.open({
                type: 1,
                content: $('#roleBox'),
                auto: ['300px'],
                btn: ['修改', '取消'],
                btn1: function (index) {
                    let data = form.val('roleBoxFilter');
                    if (data['roleLabel'].trim() === '' || data['roleCode'].trim() === '') {
                        layer.msg('请填写完整的信息后再次点击修改');
                        return;
                    }
                    $.post("/role/edit", data)
                        .done(function ({code, msg}) {
                            if (code > 0) {
                                table.reload('roleTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                            }
                            layer.msg(msg);
                        });
                    $('#roleBox form')[0].reset();
                    layer.close(index);
                }
            });
        })
    }

    // 删除role
    function remove(roleIdArr) {
        if (roleIdArr.length === 0) {
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
            }, function (index) {
                $.ajax({
                    url: '/role/remove',
                    method: 'post',
                    data: {ids: roleIdArr},
                    traditional: true,
                    success: function ({code, msg}) {
                        if (code > 0) {
                            table.reload('roleTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
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


    // 改修role角色权限
    function security(roleId) {
        layui.use(['tree', 'jquery', 'layer'], function () {
            let $ = layui.$;
            let tree = layui.tree;
            let layer = layui.layer;
            $.post('perm/searchPermTreeByRoleId', {roleId})
                .done(function ({data}) {
                    tree.render({
                        id: 'roleTreeId',
                        elem: '#permTree',
                        showCheckbox: true,
                        accordion: true,
                        data
                    });
                })
            layer.open({
                type: 1,
                area: ['280px', '420px'],
                btn: ['保存', '取消'],
                content: $('#permTree'),
                yes: function (index) {
                    let permIds = getLayuiTreeCheckedId(tree.getChecked('roleTreeId'));
                    $.ajax({
                        url: 'role/changePerm',
                        method: 'post',
                        data: {permIds, roleId},
                        traditional: true,
                        success: function ({msg}) {
                            layer.msg(msg);
                        },
                        error: function (resp) {
                            layer.msg(resp.status + " " + resp.statusMessage);
                        }
                    });
                    layer.close(index);
                }
            });
        });
    }


    // 点击查询
    layui.use(['form', 'jquery', 'table'], function () {
        let form = layui.form;
        let $ = layui.$;
        let table = layui.table;

        $("#role-search").click(function () {
            table.reload("roleTableId", {where: form.val("searchForm")})
        });
    })

</script>
</body>
</html>
<script type="text/html" id="roleTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs" lay-event="security"><i class="layui-icon">&#xe672;</i></button>
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="layui-icon"></i></button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i></button>
    </div>
</script>
<script type="text/html" id="roleTableToolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add"><i class="layui-icon">&#xe654;</i>新增
        </button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>删除
        </button>
    </div>
</script>