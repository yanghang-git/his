<%--
  Created by IntelliJ IDEA.
  User: 然
  Date: 2021/1/6
  Time: 12:01
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
                                <input type="text" name="logNumbers" placeholder="请输入" class="layui-input">
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
                                <input type="text" name="ofTheTime" id="dateStart" class="layui-input">
                            </div>
                            <div class="layui-form-mid">-</div>
                            <div class="layui-input-inline" style="width: 120px;">
                                <input type="text" name="actualReturnTime" id="dateEnd" class="layui-input">
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

<script>
    // 使用到 element 模块。 并不需要做操作 function() 可以忽略
    layui.use('element');

    layui.use('table', function(){
        let table = layui.table;
        table.render({
            id: "rentOutTableId",
            elem: "#rentOutTable",
            page: true,
            url: '/orderHistory/searchPage',
            toolbar: '#rentOutTableToolbar',
            cols:[[
                {type: 'numbers', title: '#'},
                {field: 'logNumbers', title: '订单编号',width: '140' },
                {field: 'ofTheTime' ,title: '起租时间' ,width: '145'},
                {field: 'predictReturnTime' ,title: '预计还车时间' ,width: '145'},
                {field: 'actualReturnTime' ,title: '实际还车时间' ,width: '150'},
                {field: 'predictRentOut',title: '预收款',width: '87'},
                {field: 'actualCollectionMoney',title: '实收款',width: '87'},
                {field: 'licensePlateNumber',title: '车牌号',width: '87'},
                {field: 'vehicleType',title: '车辆类型',width: '87'},
                {field: 'vehicleDesc',title: '车辆描述',width: '87'},
                {field: 'clientName',title: '姓名',width: '68'},
                {field: 'clientPhone',title: '电话',width: '120'},
                {field: 'clientSex' ,title: '性别',width:'68' ,templet:d => d.clientSex ? '男' : '女',},
                {field: 'existingProblem',title: '问题简介',width: '100'},
                {field: 'compensatePrice',title: '问题金额',width: '87'},
                {field: 'operator',title: '操作者',width: '87'},
                // {field: 'clientId' ,title: '客户姓名',width:'120' , templet: d => adminCache.has(d.clientId) ? adminCache.get(d.clientId) : ""},
            ]]
        });
    })

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

    //模糊查询
    document.getElementById('search').onclick = function(){
        layui.use(['form', 'table'], function(){
            layui.table.reload('rentOutTableId', {where: layui.form.val("searchForm")});
        });
    }
</script>
</body>
</html>