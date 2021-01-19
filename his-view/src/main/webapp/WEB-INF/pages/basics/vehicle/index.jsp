<%--
  Created by IntelliJ IDEA.
  User: l
  Date: 2020/12/21
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
              <a><cite>车辆管理</cite></a>
            </span>
        </div>

        <%-- main --%>
        <div style="background: #fff; margin: 15px; padding: 20px">
            <%-- 筛选条件 --%>
            <form class="layui-form" lay-filter="vehicle-search">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">车牌号:</label>
                        <div class="layui-input-block">
                            <input type="text" name="plateNumber" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">车辆类型:</label>
                        <div class="layui-input-block search">
                            <select name="type" style="width: 50px;" name="adminShop">
                                <option value="">请选择车辆类型</option>
                            </select>
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">车辆颜色:</label>
                        <div class="layui-input-block">
                            <input type="text" name="color" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">车辆描述:</label>
                        <div class="layui-input-block">
                            <input type="text" name="desc" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">是否出租:</label>
                        <input type="radio" name="outState" value="true" title="未出租">
                        <input type="radio" name="outState" value="false" title="已出租">
                    </div>
                    <div class="layui-inline" style="margin-left: 90px">
                        <button class="layui-btn" type="button" id="vehicle-search-btn">
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
        <input type="hidden" name="shop"class="layui-input" id="shop">
        <input type="hidden" name="operator" class="layui-input" id="operator">
        <input type="hidden" name="vehicleImg" value="空">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">车牌号</label>
                <div class="layui-input-inline">
                    <input type="text" name="licensePlateNumber" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">车辆类型:</label>
                <div class="layui-input-inline search">
                    <select name="vehicleType" style="width: 50px;" name="adminShop">
                        <option value="">请选择车辆类型</option>
                    </select>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">车辆颜色:</label>
                <div class="layui-input-inline">
                    <input type="text" name="vehicleColor" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">车辆价格:</label>
                <div class="layui-input-inline">
                    <input type="text" name="vehiclePrice" placeholder="请输入" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">出租价格:</label>
                <div class="layui-input-inline">
                    <input type="text" name="rentOut" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">出租押金:</label>
                <div class="layui-input-inline">
                    <input type="text" name="rentalDeposit" placeholder="请输入" class="layui-input">
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">车架号:</label>
                <div class="layui-input-inline">
                    <input type="text" name="vehicleIdNumber" placeholder="请输入" class="layui-input">
                </div>
            </div>

        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">车辆描述:</label>
            <div class="layui-input-inline">
                <textarea rows="5" cols="68" name="vehicleDescribe" placeholder="请输入"></textarea>
            </div>
        </div>
    </form>
</div>
<div style="display: none" id="currentShopId"><shiro:principal property="adminShop"/></div>
<div style="display: none" id="adminId"><shiro:principal property="adminId"/></div>
<script>
    // 侧边栏需要element模块的支持
    layui.use(['element']);
    let vehicleTypeCache = new Map();
    // 初始化归属的门店
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('vehicle/type/list')
            .done(function({data}) {
                let $select = $('select');
                for(let type of data) {
                    vehicleTypeCache.set(type.typeId, type.typeName);
                    $select.append("<option value='" + type.typeId + "'>" + type.typeName + "</option>");
                }
                layui.form.render('select');
            });
    })
    let adminCache = new Map();
    // 初始化归属的门店
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('admin/list')
            .done(function({data}) {

                for(let admin of data) {
                    adminCache.set(admin.adminId, admin.adminName);
                }
                console.log(adminCache);
            });
    })

    // roleTable的表格相关操作
    layui.use(['table'], function () {
        let table = layui.table;

        table.render({
            id: 'roleTableId',
            elem: '#roleTable',
            page: true,
            url: '/vehicle/getPage', //数据接口
            toolbar: '#roleTableToolbar',
            cols: [[ //表头
                {type: 'checkbox'},
                {field: "licensePlateNumber", title: '车牌号', width: '100'},
                {field: "vehicleIdNumber", title: '车架号', width: '100'},
                {field: 'vehicleType', title: '类型', templet: d => vehicleTypeCache.has(d.vehicleType) ? vehicleTypeCache.get(d.vehicleType) : ""},
                {field: 'vehicleColor', title: '车辆颜色', width: '100'},
                {field: 'vehiclePrice', title: '车辆价格', width: '100'},
                {field: 'rentOut', title: '出租价格', width: '100'},
                {field: 'rentalDeposit', title: '出租押金', width: '100'},
                {field: 'rentOutState', title: '出租状态', width: '100',templet: d =>d.vehicleState == true ? "未出租":"已出租"},
                {field: 'vehicleDescribe', title: '车辆描述', width: '100'},
                {field: 'time', title: '录入时间', width: '200'},
                {field: 'operator', title: '录入操作员', width: '100', templet: d => adminCache.has(d.operator) ? adminCache.get(d.operator) : ""},
                {toolbar: '#roleTableToolb', title: '操作', width: '200'}
            ]]
        });


        table.on('tool(roleTableFilter)', function ({event, data}) {
            switch (event) {
                case "security":
                    security(data.licensePlateNumber);
                    break;
                case "edit":
                    edit(data);
                    break;
                case "remove":
                    console.log(data);
                    remove(data.licensePlateNumber);
                    break;
            }
        })

        table.on('toolbar(roleTableFilter)', function ({event}) {
            switch (event) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove(table.checkStatus('roleTableId').data.map(data => data.licensePlateNumber));
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
                area: ['800px', '300px'],
                btn: ['保存', '取消'],
                btn1: function (index) {
                    let currentShopId = document.getElementById('currentShopId').innerText;
                    let adminId = document.getElementById('adminId').innerText;
                    console.log(currentShopId+""+adminId);
                    document.getElementById('shop').value = currentShopId;
                    document.getElementById('operator').value = adminId;
                    let data = form.val('roleBoxFilter');
                    console.log(data);
                    if (data['licensePlateNumber'].trim() === '' || data['vehicleType'].trim() === '' || data['vehicleColor'].trim() === '' || data['vehiclePrice'].trim() === '' || data['rentOut'].trim() === '' || data['rentalDeposit'].trim() === '' || data['vehicleDescribe'].trim() === '') {
                        layer.msg('请填写完整的信息后再次点击添加');
                        return;
                    }
                    $.post("/vehicle/add", data)
                        .done(function ({code, msg}) {
                            if (code > 0) {
                                $('.layui-laypage-skip input').val(99999);
                                if ($('.layui-laypage-skip input').val() === 1) {
                                    table.reload('roleTableId');
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
                area: ['800px', '300px'],
                auto: ['300px'],
                btn: ['修改', '取消'],
                btn1: function (index) {
                    let data = form.val('roleBoxFilter');
                    if (data['licensePlateNumber'].trim() === '' || data['vehicleType'].trim() === '' || data['vehicleColor'].trim() === '' || data['vehiclePrice'].trim() === '' || data['rentOut'].trim() === '' || data['rentalDeposit'].trim() === '' || data['vehicleDescribe'].trim() === '') {
                        layer.msg('请填写完整的信息后再次点击修改');
                        return;
                    }
                    $.post("/vehicle/update", data)
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
        layui.use(['layer', 'jquery', 'table','form'], function () {
            let layer = layui.layer;
            let $ = layui.$;
            let table = layui.table;
            layer.confirm('你确定要删除吗?', {
                icon: 3,
                title: '提示',
                btn: ['删除', '取消']
            }, function (index) {
                $.ajax({
                    url: '/vehicle/remove',
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

        $("#vehicle-search-btn").click(function () {
            console.log(form.val("vehicle-search"));
            table.reload("roleTableId", {where: form.val("vehicle-search")})
        });
    })

</script>
</body>
</html>
<script type="text/html" id="roleTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="layui-icon"></i></button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i></button>
    </div>
</script>
<script type="text/html" id="roleTableToolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add"><i class="layui-icon">&#xe654;</i>新增
        </button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>批量删除
        </button>
    </div>
</script>