<%--
  Created by IntelliJ IDEA.
  perm: spring
  Date: 20-12-12
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
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
              <a><cite>权限管理</cite></a>
            </span>
        </div>

        <%-- main --%>
        <div style="background: #fff; margin: 15px; padding: 20px">
            <%-- 筛选条件 --%>
            <form class="layui-form" lay-filter="searchForm">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">客户姓名：</label>
                        <div class="layui-input-block">
                            <input type="text" name="clientName" placeholder="请输入" class="layui-input">
                        </div>

                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">订单号：</label>
                        <div class="layui-input-block">
                            <input type="text" name="oddNumbers" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <input type="hidden" id="shopId" name="shopId" placeholder="请输入" class="layui-input">
                    <div class="layui-inline">
                        <button class="layui-btn" type="button" id="perm-search">
                            <i class="layui-icon layui-icon-search"></i>
                        </button>
                    </div>
                </div>

            </form>
            <%-- table --%>
            <div>

                <table id="permTable" lay-filter="permTableFilter"></table>
            </div>
        </div>

    </div>
    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - HIS医院
    </div>
</div>

<div id="permTree" style="display: none; padding: 15px"></div>


<div id="vehicleTypeBox" style="padding: 15px; display: none">
    <form class="layui-form" lay-filter="rentOutBoxFilter">
<%--        起租时间--%>
        <input type="hidden" name="ofTheTime" value="" id="ofTheTime">
<%--    预计还车时间--%>
        <input type="hidden" name="predictReturnTime" value="" id="predictReturnTime">
<%--    预计出租价格--%>
        <input type="hidden" name="predictRentOut" value="" id="rentOutTotalMoney">
<%--    实际收款价格--%>
        <input type="hidden" name="actualCollectionMoney" value="" id="actualCollectionMoney">
<%--车牌号--%>
    <input type="hidden" name="licensePlateNumber" value="" id="licensePlateNumber">
<%--    车辆类型--%>
    <input type="hidden" name="vehicleType" value="" id="vehicleType">
<%--    车辆描述--%>
    <input type="hidden" name="vehicleDesc" value="" id="vehicleDesc">
<%--    客户姓名--%>
        <input type="hidden" name="clientName" value="" id="clientName">
<%--    客户电话--%>
        <input type="hidden" name="clientPhone" value="" id="clientPhone">
<%--    客户性别--%>
        <input type="hidden" name="clientSex" value="" id="clientSex">
<%--    标明那个门店的出租订单--%>
        <input type="hidden" name="logShop" value="" id="logShop">
<%--    操作者--%>
        <input type="hidden" name="operator" value="" id="operator">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">出租单号:</label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="logNumbers" name="logNumbers"></label>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">操作员:</label>
                <div class="layui-input-inline">
                    <input class="layui-input" id="czz" name="czz"></label>
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">检查时间:</label>
                <div class="layui-input-inline">
                    <input type="text" id="actualReturnTime" name="actualReturnTime" placeholder="请输入" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">实退金额:</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="text" id="realRefundAmount" name="realRefundAmount">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">实收金额:</label>
                <div class="layui-input-inline">
                    <input class="layui-input" type="text" id="SS">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">是否赔付:</label>
                <input type="radio" lay-filter="whether" name="whether" title="需要">
                <input type="radio" lay-filter="whether" name="whether" title="无需">
            </div>
        </div>
        <div class="layui-form-item" id="xianShi" style="display: none;">
            <div class="layui-inline">
                <label class="layui-form-label" style="margin-bottom:10px; ">赔付金额:</label>
                <div class="layui-input-inline">
                    <input type="text" id="compensatePrice" name="compensatePrice" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-inline" style="width: 100%;">
                <label class="layui-form-label">问题描述:</label>
                <div class="layui-input-block" >
                    <textarea id="existingProblem"  name="existingProblem" placeholder="请输入" class="layui-input" style="height: 54px;"></textarea>
                </div>
            </div>
        </div>
    </form>

</div>

<div style="display: none" id="adminId"><shiro:principal property="adminId"/></div>
<div style="display: none" id="shop"><shiro:principal property="adminShop"/></div>
<script src="js/layuiTreeUtil.js"></script>
<script src="js/util.js"></script>
<script>
    document.getElementsByName("whether")[1].setAttribute("checked","true");
    document.getElementById("realRefundAmount").setAttribute("readonly","readonly");
    document.getElementById("logNumbers").setAttribute("readonly","readonly");
    document.getElementById("czz").setAttribute("readonly","readonly");
    document.getElementById("actualReturnTime").setAttribute("readonly","readonly");
    document.getElementById("SS").setAttribute("readonly","readonly");
    layui.use('form', function () {
        layui.form.on('radio(whether)', function(data){
            if(document.getElementsByName("whether")[0].checked == true){
                document.getElementById("xianShi").removeAttribute("style");
                document.getElementById("xianShi").setAttribute("style","display: block;");
            }else{
                document.getElementById("xianShi").removeAttribute("style");
                document.getElementById("xianShi").setAttribute("style","display: none;");
            }
        });
    })

    // 侧边栏需要element模块的支持
    layui.use(['element']);
    // 使用时间模块
    layui.use('laydate', function () {
        layui.laydate.render({elem: '#date'})
    })
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
    let adminCache1 = new Map();
    // 初始化归属的门店
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('admin/list')
            .done(function({data}) {
                for(let admin of data) {
                    adminCache1.set(admin.adminId, admin.adminName);
                }
            });
    })
    let shopMap = new Map();
    // 初始化归属的门店
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('shop/list')
            .done(function({data}) {
                for(let shop of data) {
                    shopMap.set(shop.shopId, shop.shopName);
                }
            });
    })
    // 车辆的出租价格
    let rentOutMap = new Map();
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('vehicle/list1')
            .done(function({data}) {
                for(let rentOut of data) {
                    rentOutMap.set(rentOut.licensePlateNumber ,rentOut.rentOut);
                }
            });
    })
    let searchRentOutVehicleMap = new Map();
        // 该订单中每个车的出租价格
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('orderForm/searchRentOutVehicle1')
            .done(function({data}) {
                for(let i = 0;i<data.length;i++){
                    searchRentOutVehicleMap.set(i,data[i]);
                }
            });
    })
    //查询客户全部信息
    let keHuMap = new Map();
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('client/list')
            .done(function({data}) {
                for(let i = 0;i<data.length;i++){
                    keHuMap.set(i,data[i]);
                }
            });
    })
    //查询车辆全部信息
    let cheLiangMap = new Map();
    layui.use(['jquery', 'form'], function () {
        let $ = layui.$;
        $.post('vehicle/list1')
            .done(function({data}) {
                for(let i = 0;i<data.length;i++){
                    cheLiangMap.set(i,data[i]);
                }
            });
    })
    // permTable的表格相关操作
    layui.use(['table'], function () {
        let permCacheMap = new Map();
        let table = layui.table;

        table.render({
            id: 'permTableId',
            elem: '#permTable',
            page: true,
            url: '/orderForm/searchPageDouble', //数据接口
            toolbar: '#permTableToolbar',
            cols: [[ //表头
                {type: 'checkbox'},
                {field: 'oddNumbers', title: '出租单号', width: '160'},
                {field: 'ofTheTime', title: '起租时间', width: '160'},
                {field: 'predictReturnTime', title: '预计还车时间', width: '160'},
                {field: 'clientId', title: '客户姓名', width: '100',templet: d => adminCache.has(d.clientId) ? adminCache.get(d.clientId) : ""},
                {field: 'rentOutTotalMoney', title: '总金额', width: '100'},
                {field: 'rentOutShop', title: '门店', width: '130',templet: d => shopMap.has(d.rentOutShop) ? shopMap.get(d.rentOutShop) : ""},
                {field: 'operator', title: '操作者', width: '120'},
                {field: 'comment', title: '备注', width: '175'},
                {toolbar: '#vehicleTypeTableToolb', title: '操作', width: '100'}
            ]]
        });
        table.on('tool(permTableFilter)', function (obj) {
            switch (obj.event) {
                case "add":
                    add(obj.data);
                    break;
                case "update":
                    update(obj.data);
                    break;
                case "remove":
                    remove(obj.data);
                    break;
            }
        });
    });
    // 点击查询
    layui.use(['form', 'jquery', 'table'], function () {
        let form = layui.form;
        let $ = layui.$;
        let table = layui.table;
        $("#perm-search").click(function () {
            document.getElementById('shopId').value = document.getElementById('shop').innerText;
            console.log(form.val("searchForm"));
            table.reload("permTableId", {where: form.val("searchForm")})
        });
    });
    // 添加role
    function add(data) {
        console.log(data);
        console.log(cheLiangMap);
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            let date = new Date();
            let seperator1 = "-";
            let seperator2 = ":";
            let month = date.getMonth() + 1;
            let strDate = date.getDate();
            if (month >= 1 && month <= 9) {
                month = "0" + month;
            }
            if (strDate >= 0 && strDate <= 9) {
                strDate = "0" + strDate;
            }
            let hours = date.getHours();
            if(hours >=0 && hours <=9){
                hours = "0" + hours;
            }
            let minutes = date.getMinutes();
            if(minutes >=0 && minutes <=9){
                minutes = "0" + minutes;
            }
            let currentdate = date.getFullYear() + seperator1 + month + seperator1 + strDate
                + " " + hours + seperator2 + minutes;
            document.getElementById('actualReturnTime').value = currentdate;
            document.getElementById('logNumbers').value = data.oddNumbers;
            document.getElementById('czz').value = adminCache1.get(parseInt(document.getElementById('adminId').innerText));
            //起始出租时间
            document.getElementById('ofTheTime').value = data.ofTheTime;
            //预计还车时间
            document.getElementById('predictReturnTime').value = data.predictReturnTime;
            //预计出租总金额
            document.getElementById('rentOutTotalMoney').value = data.rentOutTotalMoney;
            //出租门店
            document.getElementById('logShop').value = data.rentOutShop;
            document.getElementById('operator').value = document.getElementById('adminId').innerText;
            for(let i = 0;i<keHuMap.size;i++){
                if(data.clientId == keHuMap.get(i).clientIdNumber) {
                    //客户姓名
                    document.getElementById('clientName').value = keHuMap.get(i).clientName;
                    //客户电话
                    document.getElementById('clientPhone').value = keHuMap.get(i).clientPhone;
                    //客户性别
                    document.getElementById('clientSex').value = keHuMap.get(i).clientSex;
                }
            }
            let licensePlateNumber =[];
            let vehicleType = [];
            let vehicleDescribe = [];
                for(let i = 0;i<searchRentOutVehicleMap.size;i++){
                    if(data.oddNumbers == searchRentOutVehicleMap.get(i).oddNumbers) {
                        for(let a = 0;a<cheLiangMap.size;a++){
                            if(cheLiangMap.get(a).licensePlateNumber == searchRentOutVehicleMap.get(i).vehiclePlateNumber){
                                licensePlateNumber.push(cheLiangMap.get(a).licensePlateNumber);
                                vehicleType.push(cheLiangMap.get(a).vehicleType);
                                vehicleDescribe.push(cheLiangMap.get(a).vehicleDescribe);
                            }
                    }
                }
            }
            let new_date = new Date(currentdate); //新建一个日期对象，默认现在的时间
            let old_date = new Date(data.predictReturnTime); //设置过去的一个时间点，"yyyy-MM-dd HH:mm:ss"格式化日期
            let difftime = (old_date - new_date)/1000; //计算时间差
            let days = parseInt(difftime/86400); // 天  24*60*60*1000
            let data1 = form.val('rentOutBoxFilter');
            let price = 0.0;
            if(days>0){
                document.getElementById("SS").value = "超过预计还车时间所付金额";
                let num = 0.0;
                for(let i = 0;i<searchRentOutVehicleMap.size;i++){
                    if(data.oddNumbers == searchRentOutVehicleMap.get(i).oddNumbers) {
                        num += days * rentOutMap.get(searchRentOutVehicleMap.get(i).vehiclePlateNumber);
                    }
                }
                document.getElementById("realRefundAmount").value = num;
                price = document.getElementById("realRefundAmount").value;
            }else{
                document.getElementById("realRefundAmount").value = "未超过预计还车时间所付金额";
                let num1 = 0.0;
                for(let i = 0;i<searchRentOutVehicleMap.size;i++){
                    if(data.oddNumbers == searchRentOutVehicleMap.get(i).oddNumbers){
                        num1 += searchRentOutVehicleMap.get(i).rentOutMoney - (-days * rentOutMap.get(searchRentOutVehicleMap.get(i).vehiclePlateNumber));
                    }
                }
                document.getElementById("SS").value = num1;
                price = document.getElementById("SS").value;
            }
            layer.open({
                type: 1,
                content: $('#vehicleTypeBox'),
                area: ['1020px', '400px'],
                btn: ['保存', '取消'],
                btn1: function (index) {
                    let vo ={
                        // RentOutLog
                        'rentOutLog': {
                            'logNumbers': data.oddNumbers,//出租单号
                            'ofTheTime': data.ofTheTime,//起租时间
                            'predictReturnTime': data.predictReturnTime, //预计还车时间
                            'predictRentOut': data.rentOutTotalMoney,//预计出租价格
                            'actualReturnTime': currentdate,//实际还车时间
                            'actualCollectionMoney': price,//实际收款价格（实退- 实收+）
                            'clientName': document.getElementById('clientName').value,//客户姓名
                            'clientPhone': document.getElementById('clientPhone').value,//客户电话
                            'clientSex': document.getElementById('clientSex').value,//客户性别
                            'existingProblem': document.getElementById('existingProblem').value,//问题简介
                            'logShop': adminCache1.get(document.getElementById('logShop').value),//标明那个门店的出租订单
                            'compensatePrice': document.getElementById('compensatePrice').value,//问题金额
                            'operator': document.getElementById('adminId').innerText//操作者
                        },
                        'licensePlateNumberList' : licensePlateNumber,
                        'vehicleTypeList' : vehicleType,
                        'vehicleDescribeList' : vehicleDescribe
                    }
                    console.log(vo);
                    $.ajax({
                        url:'/rentOutLog/add',
                        type:'post',
                        dataType:'json',
                        data:JSON.stringify(vo),
                        contentType: 'application/json;charset=UTF-8',
                        success:function({msg}){
                            table.reload('permTableId')
                            layer.msg(msg);
                            $('#vehicleTypeBox form')[0].reset();
                        }
                    });
                    layer.close(index);
                }
            });
        })
    }
</script>
</body>
</html>
<script type="text/html" id="vehicleTypeTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add" >还车</button>
    </div>
</script>