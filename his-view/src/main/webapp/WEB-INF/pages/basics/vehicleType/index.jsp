<%--
  Created by IntelliJ IDEA.
  User: 然
  Date: 2020/12/25
  Time: 14:18
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
        <div class="layui-row" style="background: #fff; padding: 10px 30px">
            <span class="layui-breadcrumb">
              <a><cite>HIS</cite></a>
            </span>
        </div>

        <div style="padding: 20px">
            <form class="layui-form" lay-filter="searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">查询内容</label>
                        <div class="layui-input-block">
                            <input type="text" name="keyword" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn" type="button" id="vehicleType-search">
                            <i class="layui-icon layui-icon-search"></i>
                        </button>
                    </div>
                </div>
            </form>
            <table id="vehicleTypeTable" lay-filter="vehicleTypeTableFilter"></table>
        </div>


    </div>



    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - YSZ
    </div>
</div>


<div id="vehicleTypeBox" style="padding: 15px; display: none">
    <form class="layui-form" lay-filter="vehicleTypeBoxFilter">
        <input type="hidden" name="typeId"/>
        <div class="layui-form-item">
            <label class="layui-form-label">种类名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeName" placeholder="请输入" class="layui-input">
            </div>
        </div>
    </form>
</div>
<script>
    // 使用到 element 模块。 并不需要做操作 function() 可以忽略
    layui.use('element');
    layui.use('table', function(){
        let table = layui.table;
        table.render({
            id: "vehicleTypeTableId",
            elem: "#vehicleTypeTable",
            page: true,
            url: '/vehicle/type/searchPage',
            toolbar: '#vehicleTypeTableToolbar',
            cols:[[
                {type: 'checkbox'},
                {type: 'numbers', title: '#'},
                {field: 'typeName', title: '种类名称'},
                {toolbar: '#vehicleTypeTableToolb', title: '操作'}
            ]]
        });
        table.on('tool(vehicleTypeTableFilter)', function (obj) {
            switch (obj.event) {
                case "edit":
                    edit(obj.data);
                    break;
                case "remove":
                    remove([obj.data.typeId]);
                    break;
            }
        })
        table.on('toolbar(vehicleTypeTableFilter)', function (obj) {
            switch (obj.event) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove(table.checkStatus('vehicleTypeTableId').data.map(data => data.typeId));
                    break;
            }
        });
    })
    // 添加role
    function add() {
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            $('#vehicleTypeBox form')[0].reset();
            layer.open({
                type: 1,
                content: $('#vehicleTypeBox'),
                auto: ['300px'],
                btn: ['保存', '取消'],
                btn1: function (index) {
                    let data = form.val('vehicleTypeBoxFilter');
                    if (data['typeName'].trim() === '') {
                        layer.msg('请填写完整的信息后再次点击添加');
                    } else {
                        $.post("/vehicle/type/add", data)
                            .done(function ({code, msg}) {
                                if (code > 0) {
                                    $('.layui-laypage-skip input').val(99999);
                                    if ($('.layui-laypage-skip input').val() === 1) {
                                        table.reload('vehicleTypeTableId');
                                    } else {
                                        $('.layui-laypage-skip .layui-laypage-btn').click();
                                    }
                                }
                                layer.msg(msg);
                                layer.close(index);
                            });
                    }
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
            form.val("vehicleTypeBoxFilter", data)
            layer.open({
                type: 1,
                content: $('#vehicleTypeBox'),
                auto: ['300px'],
                btn: ['修改', '取消'],
                btn1: function (index) {
                    let data = form.val('vehicleTypeBoxFilter');
                    if (data['typeName'].trim() === '') {
                        layer.msg('请填写完整的信息后再次点击添加');
                        return;
                    }
                    $.post("/vehicle/type/update", data)
                        .done(function ({code, msg}) {
                            if (code > 0) {
                                table.reload('vehicleTypeTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                            }
                            layer.msg(msg);
                        });
                    layer.close(index);
                }
            });
        })
    }
    // 删除role
    function remove(ids) {
        if (ids.length === 0) {
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
                    url: '/vehicle/type/remove',
                    method: 'post',
                    data: {ids},
                    traditional: true,
                    success: function ({code, msg}) {
                        if (code > 0) {
                            table.reload('vehicleTypeTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
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
    //模糊查询
    document.getElementById('vehicleType-search').onclick = function(){
        layui.use('table', function(){
            layui.table.reload('vehicleTypeTableId', {where:{keyword: document.getElementsByName('keyword')[0].value}});
        });
    }
</script>
</body>
</html>
<script type="text/html" id="vehicleTypeTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="layui-icon"></i>修改</button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>删除</button>
    </div>
</script>
<script type="text/html" id="vehicleTypeTableToolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add"><i class="layui-icon">&#xe654;</i>新增
        </button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>删除
        </button>
    </div>
</script>