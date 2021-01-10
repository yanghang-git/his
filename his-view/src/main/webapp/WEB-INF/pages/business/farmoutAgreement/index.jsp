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
        <div style="background: #fff; margin: 15px; padding: 20px">
        <div style="padding: 20px">
            <form class="layui-form" lay-filter="searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">订单编号:</label>
                        <div class="layui-input-block">
                            <input type="text" name="oddNumbers" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">客户姓名:</label>
                        <div class="layui-input-block">
                            <input type="text" name="clientName" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">起始日期:</label>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input type="text" name="ofTheTimeStart" id="dateStart" class="layui-input">
                        </div>
                        <div class="layui-form-mid">-</div>
                        <div class="layui-input-inline" style="width: 120px;">
                            <input type="text" name="ofTheTimeEnd" id="dateEnd" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button class="layui-btn" type="button" style="margin-left: 50px;" id="search">
                            <i class="layui-icon layui-icon-search"></i>
                        </button>
                    </div>
                </div>
            </form>

            <table id="rentOutTable" lay-filter="rentOutTableFilter"></table>
        </div>


    </div>
    </div>




    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - YSZ
    </div>
</div>


<div id="vehicleTypeBox" style="padding: 15px; display: none">
<%--    <form class="layui-form" lay-filter="rentOutBoxFilter">--%>
<%--        <input type="hidden" name="typeId"/>--%>
<%--        <div class="layui-form-item">--%>
<%--            <label class="layui-form-label">种类名称</label>--%>
<%--            <div class="layui-input-block">--%>
<%--                <input type="text" name="typeName" placeholder="请输入" class="layui-input">--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </form>--%>
</div>
<script>
    // 使用到 element 模块。 并不需要做操作 function() 可以忽略
    layui.use('element');

    let adminCache = new Map();
    // 初始化归属的门店
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('client/list')
            .done(function({data}) {

                for(let admin of data) {
                    adminCache.set(admin.clientIdNumber, admin.clientName);
                }
            });
    })
    layui.use('table', function(){
        let table = layui.table;
        table.render({
            id: "rentOutTableId",
            elem: "#rentOutTable",
            page: true,
            url: '/orderForm/searchPage',
            toolbar: '#rentOutTableToolbar',
            cols:[[
                {type: 'numbers', title: '#'},
                {field: 'oddNumbers', title: '订单编号',width:'160'},
                {field: 'ofTheTime' ,title: '起租时间',width:'180'},
                {field: 'predictReturnTime' ,title: '预计还车时间',width:'180'},
                {field: 'clientId' ,title: '客户姓名',width:'120' , templet: d => adminCache.has(d.clientId) ? adminCache.get(d.clientId) : ""},
                {field: 'rentOutTotalMoney' ,title: '定金',width:'100'},
                {field: 'isPickUp' ,title: '是否提车',width:'100' ,templet:d => d.isPickUp ? '已提车' : '未提车'},
                {field: 'comment' ,title: '备注',width:'140'},
                {toolbar: '#vehicleTypeTableToolb', title: '操作',width:'180'}
            ]]
        });


        table.on('tool(rentOutTableFilter)', function (obj) {
            switch (obj.event) {
                case "update":
                    update(obj.data);
                    break;
                case "remove":
                    remove(obj.data);
                    break;
            }
        })




    })


    // 取消订单
    function remove(data) {
        layui.use(['layer', 'jquery', 'table'], function () {
            let layer = layui.layer;
            if (data.isPickUp === true){
                layer.msg("当前订单正在进行中,无法取消该订单");
                return;
            }
            let $ = layui.$;
            let table = layui.table;
            layer.confirm('你确定要取消吗?', {
                icon: 3,
                title: '提示',
                btn: ['确认', '取消']
            }, function (index) {
                $.ajax({
                    url: '/orderForm/remove',
                    method: 'post',
                    data: data,
                    success: function ({code, msg}) {
                        if (code > 0) {
                            table.reload('rentOutTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
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

    // 取消订单
    function update(data) {
        layui.use(['layer', 'jquery', 'table'], function () {
            let layer = layui.layer;
            if (data.isPickUp === true){
                layer.msg("当前订单正在进行中");
                return;
            }
            let $ = layui.$;
            let table = layui.table;
            layer.confirm('你确定要出车吗?', {
                icon: 3,
                title: '提示',
                btn: ['出车', '取消']
            }, function (index) {
                $.ajax({
                    url: '/orderForm/update',
                    method: 'post',
                    data: data,
                    success: function ({code, msg}) {
                        if (code > 0) {
                            table.reload('rentOutTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
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

//限制查询日期
    layui.use(['laydate', 'layer'], function(){
        let laydate = layui.laydate;
        laydate.render({
            elem: '#dateStart',
            done: function(data){
                let endDate = Date.parse(document.getElementById('dateEnd').value);
                let startDate = Date.parse(data);
                if (endDate - startDate <= 0) {
                    layer.msg("起租日期必须大于还车时间")
                }
            }
        });
        laydate.render({
            elem: '#dateEnd',
            done: function(data){
                let startDate = Date.parse(document.getElementById('dateStart').value);
                let endDate = Date.parse(data);
                if (endDate - startDate <= 0) {
                    layer.msg("起租日期必须大于还车时间")
                }
            }
        });
    });


    document.getElementById('search').onclick = function(){
        layui.use('form', function(){
            let form = layui.form;
            let data = form.val('searchForm');
            let startDate = Date.parse(data.dateStart);
            let endDate = Date.parse(data.dateEnd);
            if (endDate - startDate <= 0) {
                layer.msg("起租日期必须大于还车时间")
                data.dateStart = '';
                data.dateEnd = '';
                form.val('searchForm', data);
                return;
            }
        })
    }

    // // 修改订单状态update
    // function update(data) {
    //     layui.use(['jquery', 'form', 'layer', 'table'], function () {
    //         let $ = layui.$;
    //         let form = layui.form;
    //         let layer = layui.layer;
    //         let table = layui.table;
    //         if (data.isPickUp === true){
    //             layer.msg("当前订单正在进行中");
    //             return;
    //         }
    //         form.val("vehicleTypeBoxFilter", data)
    //         layer.open({
    //             type: 1,
    //             content: $('#vehicleTypeBox'),
    //             auto: ['300px'],
    //             btn: ['确认出车', '取消'],
    //             btn1: function (index) {
    //                 let data = form.val('vehicleTypeBoxFilter');
    //                 // if (data['typeName'].trim() === '') {
    //                 //     layer.msg('请填写完整的信息后再次点击添加');
    //                 //     return;
    //                 // }
    //                 $.post("/orderForm/update", data)
    //                     .done(function ({code, msg}) {
    //                         if (code > 0) {
    //                             table.reload('vehicleTypeTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
    //                         }
    //                         layer.msg(msg);
    //                     });
    //                 layer.close(index);
    //             }
    //         });
    //     })
    // }


    //模糊查询

    document.getElementById('search').onclick = function(){
        layui.use(['form', 'table'], function(){
            layui.table.reload('rentOutTableId', {where: layui.form.val("searchForm")});
        });
    }

</script>
</body>
</html>
<script type="text/html" id="vehicleTypeTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="update" >出车</button>
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="remove" >取消</button>
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="info">详情</button>
    </div>
</script>
