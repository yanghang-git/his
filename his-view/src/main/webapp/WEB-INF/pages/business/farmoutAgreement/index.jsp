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
    <form class="layui-form" lay-filter="rentOutBoxFilter">
        <input type="hidden" name="typeId"/>
        <div class="layui-form-item">
            <label class="layui-form-label">种类名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeName" placeholder="请输入" class="layui-input">
            </div>
        </div>
    </form>
</div>

<div id="rentOutInfo" style="display: none;padding:15px">
    <div class="layui-row" style="padding: 15px">
        <h3>订单信息</h3>
        <div style="padding: 5px 0 0 25px">
            <div class="layui-row" style="padding-top: 5px">
                <div><div style="width: 70px; text-align: right; display: inline-block;">订单编号：</div><span id="info-oddNumbers"></span></div>
            </div>
            <div class="layui-row" style="padding-top: 5px">
                <div class="layui-col-md6"><div style="width: 70px; text-align: right; display: inline-block;">总租金：</div><span id="info-totalRent"></span></div>
                <div class="layui-col-md6"><div style="width: 70px; text-align: right; display: inline-block;">总押金：</div><span id="info-totalDeposit"></span></div>
            </div>
            <div class="layui-row" style="padding-top: 5px">
                <div class="layui-col-md6"><div style="width: 70px; text-align: right; display: inline-block;">起租时间：</div><span id="info-startDate"></span></div>
                <div class="layui-col-md6"><div style="width: 70px; text-align: right; display: inline-block;">结束时间：</div><span id="info-endDate"></span></div>
            </div>
        </div>
    </div>
    <div class="layui-row"  style="padding: 15px">
        <h3>客户信息</h3>
        <div style="padding: 5px 0 0 25px">
            <div class="layui-row">
                <div class="layui-col-md6"><div style="width: 70px; text-align: right; display: inline-block;">顾客姓名：</div><span id="info-clientName"></span></div>
                <div class="layui-col-md6"><div style="width: 70px; text-align: right; display: inline-block;">顾客电话：</div><span id="info-clientPhone"></span></div>
            </div>
        </div>
    </div>
    <div class="layui-row" style="padding: 15px">
        <h3>车辆信息</h3>
        <div style="padding: 5px 0 0 25px" id="info-vehicle"></div>
    </div>
</div>


<div class="page" id="contract" style="margin: 5px; padding-top: 20px; display: none;">
    <h2 style="text-align: center">租车合同</h2>
    <div style="padding: 15px">
        <p>甲方（出租方）:<span style="text-decoration:underline">洲洲汽车租赁</span>，统一社会信用代：<span style="text-decoration:underline">92510113M123456789</span></p>
        <p>乙方（承租放）:<span style="text-decoration:underline" id="clientName"></span>，身份证:<span id="clientIdNumber" style="text-decoration:underline"></span></p>
        <p style="text-indent:2em">经双方协商达成以下协议</p>
        <p style="text-indent:2em">一、出租方根据承租方需要，同意将：</p>
        <div style="text-indent:6em" id="vehicle"></div>
        <p style="text-indent:4em">租给承租方使用</p>
        <p style="text-indent:2em">二、承租方负责对所租借的车辆进行维护保养，在退租时如给车辆设备造成损坏，承租方应负责修复原装或赔偿。因承租方造成损坏、违章、违法、交通事故、欠款等一切应由事宜由承租方自行承担看，出租方概不负责。</p>
        <p style="text-indent:2em">三、租用期，自 <span id="rentOutStartDate" style="text-decoration:underline">yyyy-HH-mm</span> 起至 <span id="rentOutEndDate" style="text-decoration:underline">yyyy-HH-mm</span> 止，承租方如果继续使用或停用应提前向出租方提出协商。</p>
        <p style="text-indent:2em">四、租金共计为：<span style="text-decoration:underline" id="totalMoney"></span>元，从合同生成时间起计，不足一天按一天收费。</p>
        <p style="text-indent: 2em">五、所有燃料由承租方负责。</p>
        <p style="text-indent: 2em">六、违约责任。出租方不得擅自将车辆调回，否则将按照租金的双倍赔偿承租方。承租方必须按照合同规定的时间和租金付款，否则，每逾期一天加罚一天的租金。</p>
        <p style="text-indent: 2em">七、本合同一式两份，出租方、承租方各执一份。本协议自签字之时生效，事后如有纠纷，可通过法律途径解决。</p>
        <div style="margin-top: 20px; text-indent: 2em" class="layui-row sign">
            <div class="layui-col-md4">
                甲方：<span style="margin-right:50px; text-decoration:underline">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </div>
            <div class="layui-col-md4">
                乙方：<span style="text-decoration:underline">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </div>
            <div class="layui-col-md4">
                时间：<span style="text-decoration:underline" id="nowDate"></span>
            </div>
        </div>
    </div>
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
                    adminCache.set(admin.clientIdNumber, admin);
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
                {field: 'clientId' ,title: '客户姓名',width:'120' , templet: d => adminCache.has(d.clientId) ? adminCache.get(d.clientId).clientName : ""},
                {field: 'rentOutTotalMoney' ,title: '金额',width:'100'},
                {field: 'isPickUp' ,title: '是否提车',width:'100' ,templet:d => d.isPickUp ? '已提车' : '未提车'},
                {field: 'comment' ,title: '备注',width:'140'},
                {toolbar: '#vehicleTypeTableToolb', title: '操作',width:'180'}
            ]]
        });


        table.on('tool(rentOutTableFilter)', function ({event, data}) {
            switch (event) {
                case "update":
                    update(data);
                    break;
                case "remove":
                    remove(data);
                    break;
                    case "info":
                        info(data);
            }
        })
    })

    function info (data) {
        layui.use(['layer', 'jquery'], function() {
            let $ = layui.jquery;
            let totalDeposit = 0
                , totalRent = 0;
            $('#info-oddNumbers').text(data.oddNumbers);
            $('#info-startDate').text(data.ofTheTime);
            $('#info-endDate').text(data.predictReturnTime);
            $('#info-clientName').text(adminCache.get(data.clientId).clientName);
            $('#info-clientPhone').text(adminCache.get(data.clientId).clientPhone);
            $.post('orderForm/searchRentOutVehicle', {oddNumbers: data.oddNumbers})
                .done(function({data}) {
                    let $vehicle = $('#info-vehicle').empty();
                    for(let item of data) {
                        totalDeposit = totalDeposit + parseFloat(item.rentalDeposit);
                        totalRent = totalRent + parseFloat(item.rentOutMoney);
                        $.ajax({
                            url: 'orderForm/searchVehicleDesc',
                            method: 'post',
                            data: {vehiclePlateNumber: item.vehiclePlateNumber},
                            success: function({msg}) {
                                $vehicle.append($(`<div class="layui-row" style="padding-top: 10px;">
                                                        <div class="layui-col-md4">车牌：<span>` + item.vehiclePlateNumber + `</span></div>
                                                        <div class="layui-col-md4">描述：<span>` + msg + `</span></div>
                                                        <div class="layui-col-md2">租金：<span>` + item.rentOutMoney + `元</span></div>
                                                        <div class="layui-col-md2">押金：<span>` + item.rentalDeposit + `元</span></div>
                                                  </div>`));
                            }
                        })
                    }
                    $('#info-totalDeposit').text(totalDeposit + '元');
                    $('#info-totalRent').text(totalRent + '元');
                })
            layer.open({
                type: 1,
                title: '订单详情',
                area: ['800px', '500px'],
                content: $('#rentOutInfo')
            });

        })
    }


    // 取消订单
    function remove(data) {
        console.log(data);
        layui.use(['layer', 'jquery', 'table'], function () {
            let layer = layui.layer;
            let $ = layui.$;
            let table = layui.table;
            layer.confirm('你确定要取消吗?', {
                icon: 3,
                title: '提示',
                btn: ['确认', '取消']
            }, function (index) {
                $.post('/orderForm/cancel', data)
                    .done(function({code, msg}) {
                        if (code > 0) {
                            table.reload('rentOutTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                        }
                        layer.msg(msg);
                    })
                layer.close(index);
            })
        });
    }

    // 修改update
    function update(data) {
        layui.use(['layer', 'jquery', 'table'], function() {
            let $ = layui.jquery;
/*            if (new Date() < new Date(data.ofTheTime)) {
                layer.msg("未到预约时间, 无法出车");
            } else {*/
                $.post('/orderForm/outVehicle', {oddNumbers: data.oddNumbers})
                    .done(function ({code, msg}) {
                        if (code > 0) {
                            layui.table.reload('rentOutTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                            let nowDate = new Date();
                            $('#rentOutStartDate').text(data.ofTheTime);
                            $('#rentOutEndDate').text(data.predictReturnTime);
                            $('#clientName').text(adminCache.get(data.clientId).clientName);
                            $('#clientIdNumber').text(data.clientId);
                            $('#totalMoney').text(data.rentOutTotalMoney);
                            $('#nowDate').text(nowDate.getFullYear() + "年" + (nowDate.getMonth() + 1) + "月" + nowDate.getDate() + "日");
                            $.post('orderForm/searchVehicle', {oddNumbers: data.oddNumbers})
                                .done(function({data}) {
                                    let $vehicle = $('#vehicle').empty();
                                    for (let item of data) {
                                        $vehicle.append(`<p>
                                    <span style="text-decoration:underline">` + item.vehicleDescribe + `</span>，
                                    车架号：<span style="text-decoration:underline">` + item.vehicleIdNumber + `</span>，
                                    车牌号：<span style="text-decoration:underline">` + item.licensePlateNumber + `</span>
                                </p>`);
                                    }
                                })
                            layer.open({
                                type:1,
                                title: '合同',
                                area: ['595px', '600px'],
                                content: $("#contract"),
                                btn: ["打印"],
                                btn1: function(index) {
                                    document.body.innerHTML = document.getElementById("contract").innerHTML;//需要打印的页面
                                    document.body.style.margin = "50px 30px 0";
                                    let pTagName = document.body.getElementsByTagName("p");
                                    for (let p of pTagName) {
                                        p.style.margin = "10px 0";
                                    }
                                    let signClassName = document.body.getElementsByClassName('sign')[0];
                                    signClassName.style.position = "absolute";
                                    signClassName.style.bottom = "50px";
                                    signClassName.style.right = "50px";
                                    let divTagName = signClassName.getElementsByTagName("div");
                                    for (let div of divTagName) {
                                        div.style.margin = "10px 0";
                                    }

                                    window.print();
                                    window.history.go(0);
                                    layer.close(index);
                                }
                            });
                            layer.alert(msg);
                        } else {
                            layer.msg(msg);
                        }
                    });
            // }
        })
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

    //模糊查询
   document.getElementById('search').onclick = function(){
        layui.use(['form', 'table'], function(){
            let form = layui.form;
            let data = form.val('searchForm');
            if (Date.parse(data.ofTheTimeEnd) - Date.parse(data.ofTheTimeStart) <= 0) {
                layer.msg("起租日期必须大于还车时间")
                data.ofTheTimeStart = '';
                data.ofTheTimeEnd = '';
                form.val('searchForm', data);
            } else if (data.oddNumbers !== '' && data.oddNumbers.length !== 12) {
                layer.msg("请输入正确的订单号")
            } else {
                layui.table.reload('rentOutTableId', {where: layui.form.val("searchForm"), page: {curr: 1}});
            }
        });
    }

</script>
</body>
</html>
<script type="text/html" id="vehicleTypeTableToolb">
    <div class="layui-btn-container">
        {{# if(!d.isPickUp){ }}
            <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="update">出车</button>
            <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="remove">取消</button>
        {{# } }}
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="info">详情</button>
    </div>
</script>
