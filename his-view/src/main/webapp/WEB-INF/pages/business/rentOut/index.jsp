<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 20-12-26
  Time: 下午1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<jsp:include page="../../module/head.jsp"/>
<style type="text/css">
    #text-search+div {
        position: absolute;
        left: 0;
        top: 42px;
        padding: 5px 0;
        z-index: 899;
        min-width: 100%;
        border: 1px solid #d2d2d2;
        max-height: 300px;
        overflow-y: auto;
        background-color: #fff;
        border-radius: 2px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, .12);
        box-sizing: border-box;
    }
    #text-search+div>div {
        padding: 0 10px;
        line-height: 36px;
        white-space: nowrap;
        overflow: hidden;
        text-overflow: ellipsis;
        cursor:pointer;
    }
    #text-search+div>div:hover {
        background: #f2f2f2;
    }

    @media print {
        html,body {
        //A4默认为210mm*287mm
        width:210mm
        height:297mm
        }
        .page{
            font-size:10pt;
            width:initial;
        }
        　@page {
            size: A4;
            margin: 1cm;
        }
    }
</style>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%-- 标头 --%>
    <jsp:include page="../../module/header.jsp"/>
    <%-- 侧边栏 --%>
    <jsp:include page="../../module/sidebar.jsp"/>
    <div class="layui-body" style="background: #f2f2f2">
        <div class="layui-row" style="background: #fff; padding: 10px 30px">
            <span class="layui-breadcrumb">
              <a href="index">HIS</a>
              <a><cite>出租车辆</cite></a>
            </span>
        </div>
        <div style="background: #fff; margin: 15px; padding: 20px">
            <div class="layui-row">
                <form class="layui-form search" lay-filter="searchForm">

                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">车牌号</label>
                            <div class="layui-input-block">
                                <input type="text" maxlength="7" autocomplete="off" name="licensePlateNumber" placeholder="请输入" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">车辆颜色</label>
                            <div class="layui-input-block">
                                <input type="text" autocomplete="off" name="vehicleColor" placeholder="请输入" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <label class="layui-form-label">车辆类型</label>
                            <div class="layui-input-block" style="width: 183px">
                                <select name="vehicleType" id="vehicleTypeSelect" lay-search="">
                                    <option value="">全部</option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-inline" style="margin-left: 90px">
                            <button class="layui-btn" type="button" id="rentOut-search">
                                <i class="layui-icon layui-icon-search"></i>
                            </button>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn layui-btn-warm" type="reset">
                                <i class="layui-icon layui-icon-refresh"></i>
                            </button>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">车辆描述</label>
                            <div class="layui-input-block">
                                <input type="text" name="vehicleDesc" placeholder="请输入" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-inline">
                            <label class="layui-form-label">出租价格</label>
                            <div class="layui-input-inline" style="width: 78px;">
                                <input type="text" autocomplete="off" name="rentOutStart" class="layui-input">
                            </div>
                            <div class="layui-form-mid">-</div>
                            <div class="layui-input-inline" style="width: 78px;">
                                <input type="text" autocomplete="off" name="rentOutEnd" class="layui-input">
                            </div>
                        </div>
<%--                        <div class="layui-inline" style="margin-left: 30px">
                            <input type="checkbox" value="true" name="isAllShop" title="查看全部门店">
                        </div>--%>
                        <div class="layui-inline" style="margin-left: 390px;">
                            <button class="layui-btn layui-btn-normal" id="rentOutList-btn" type="button">
                                <i class="layui-icon layui-icon-form"></i>租借列表<span class="layui-badge layui-bg-gray" id="rentOutListSize" style="display: none"></span>
                            </button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="layui-row">
                <table id="rentOutTable" lay-filter="rentOutTableFilter"></table>
            </div>
        </div>
    </div>


        <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - 高端汽车管理系统
    </div>
</div>


<div style="display: none; padding: 15px" id="rentOutListBox">
    <form class="layui-form" lay-filter="rentOutForm">
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">身份证</label>
                <div class="layui-input-block" style="width: 183px">
                    <input type="text" id="text-search" autocomplete="off" maxlength="18" name="clientIdNumber" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">姓名</label>
                <div class="layui-input-block">
                    <input type="text" name="clientName" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">手机号</label>
                <div class="layui-input-block">
                    <input type="text" name="clientPhone" autocomplete="off" maxlength="11" placeholder="请输入" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">地址</label>
                <div class="layui-input-block">
                    <input type="text" name="clientAddress" autocomplete="off" placeholder="请输入" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">起租时间</label>
                <div class="layui-input-block">
                    <input type="text" name="startDate" autocomplete="off" maxlength="19" id="startDate" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">还车时间</label>
                <div class="layui-input-block">
                    <input type="text" name="endDate" autocomplete="off" maxlength="19" id="endDate" class="layui-input">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-inline">
                <label class="layui-form-label">备注</label>
                <div class="layui-input-block">
                    <input type="text" name="comment" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label">价格</label>
                <div class="layui-input-block" style="width: 65px">
                    <input disabled id="rentOutTotalMoney" value="0" type="text" name="rentOutTotalMoney" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <label class="layui-form-label" style="width: 28px;padding-left: 0;padding-right: 0;">押金</label>
                <div class="layui-input-block" style="width: 65px;margin-left: 36px;">
                    <input disabled id="rentalDepositTotalMoney" value="0" type="text"  name="rentOutTotalMoney" class="layui-input">
                </div>
            </div>
        </div>
    </form>

    <table id="rentOutListTable" lay-filter="rentOutListFilter"></table>
</div>

<div style="display: none" id="currentShopId"><shiro:principal property="adminShop"/></div>


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

<script src="js/util.js"></script>
<script>
    layui.use('jquery', () => {
        let $ = layui.$;
        let $target = $('#text-search');
        let $box = $('<div></div>');
        $box.css({
            width: $target.width()
        });

        $target.focus(() => {
            $target.after($box);
        })
        $('body').on('mousedown', '#text-search+div .item', function () {
            $target.val(this.innerText !== '无' ? this.innerText : '');
        })
        $target.focus(() => {
            if ($box.children('.item').length === 0) {
                $box.append($('<div class="item default" style="color: #999">无</div>'));
            }
            $target.after($box);
        })
        $target.blur(() => {
            $box.remove();
        })

    })
</script>
<script>
    let rentOutListMap = new Map();
    let clientListMap = new Map();
    let day = 0;
    layui.use('element');
    layui.use('jquery', () => {
        let $ = layui.$;
        $('#text-search').bind('input', function() {
            let $target = $('#text-search+div');
            if (this.value.length > 6) {
                $.post('/client/getListByClientIdNumber', {clientIdNumber: this.value, size: 5})
                    .done(({data}) => {
                        $target.empty();
                        if (data.length === 0) {
                            $target.append($('<div class="item default" style="color: #999">无</div>'));
                        } else {
                            for (let client of data) {
                                $target.append('<div class="item">' + client.clientIdNumber + '</div>')
                                clientListMap.set(client.clientIdNumber.toString(), client);
                            }
                        }
                        if (this.value.length === 18)
                            fullClientInfo(this.value);
                    })

            } else {
                $target.empty();
                $target.append($('<div class="item default" style="color: #999">无</div>'));
            }
        }).bind('keydown', ({keyCode}) => {
            if (keyCode === 9 || keyCode === 13) {
                fullClientInfo($('#text-search+div .item:eq(0)').text());
            }
        });



        $('body').on('mousedown', '#text-search+div .item', function() {fullClientInfo(this.innerText)})


        function fullClientInfo(clientIdNumber) {
            if (clientListMap.has(clientIdNumber)) {
                let client = clientListMap.get(clientIdNumber);
                document.getElementsByName('clientIdNumber')[0].value = client.clientIdNumber;
                document.getElementsByName('clientName')[0].value = client.clientName;
                document.getElementsByName('clientPhone')[0].value = client.clientPhone;
                document.getElementsByName('clientAddress')[0].value = client.clientAddress;
            }
        }

    });


    layui.use(['laydate', 'layer', 'form'], function() {
        let laydate = layui.laydate;
        let form = layui.form;
        let date = new Date();
        let nowDate = date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate();
        laydate.render({
            elem: '#startDate',
            type:"datetime",
            format: 'yyyy-MM-dd HH:mm',
            value: nowDate + " " + date.getHours() + (':00'),
            max: date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + (date.getDate() + 6) + " " + date.getHours() + (':00:00'),
            min: nowDate + " " + date.getHours() + (':00:00'),
            done: function(value) {
                let endDate = document.getElementById('endDate').value;
                if (endDate === '') return;
                let end = Date.parse(endDate);
                let start = Date.parse(value);
                let difftime = (end - start) / 1000;
                let days = difftime / 86400;
                console.log(days);
                if (days <= 0) {
                    layer.msg('请选择正确的租车时长')
                    days = 0;
                } else if (days < 1) {
                    days = 1;
                    layer.msg('租借时间少于一天按一天算')
                }
                day = Math.ceil(days);
                let rentOutTotalMoney = 0;
                rentOutListMap.forEach(value => {
                    value.rentOutMoney = day * value.rentOut;
                    rentOutTotalMoney += value.rentOutMoney;
                })
                document.getElementById('rentOutTotalMoney').value = rentOutTotalMoney;
                layui.table.reload('rentOutListTableId', {data: getMapValues(rentOutListMap)});
            }
        });



        laydate.render({
            elem: '#endDate',
            type: 'datetime',
            format: 'yyyy-MM-dd HH:mm',
            min: nowDate + " " + date.getHours() + (':00:00'),
            done: function(value) {
                let startDate = document.getElementById('startDate').value;
                if (startDate === '') return;
                let start = Date.parse(startDate);
                let end = Date.parse(value);
                let difftime = (end - start) / 1000;
                let days = difftime / 86400;
                if (days <= 0) {
                    layer.msg('请选择正确的租车时长')
                    days = 0;
                } else if (days < 1) {
                    days = 1;
                    layer.msg('租借时间少于一天按一天算')
                }
                day = Math.ceil(days);
                let rentOutTotalMoney = 0;
                rentOutListMap.forEach(value => {
                    value.rentOutMoney = day * value.rentOut;
                    rentOutTotalMoney += value.rentOutMoney;
                })
                document.getElementById('rentOutTotalMoney').value = rentOutTotalMoney;
                layui.table.reload('rentOutListTableId', {data: getMapValues(rentOutListMap)});
            }
        });

    })


    layui.use(['table', 'jquery', 'form'], function() {
        let $ = layui.$;
        let table = layui.table;
        let currentShopId = document.getElementById('currentShopId').innerText;
        let vehicleTypeCache = new Map();
        let shopCache = new Map();
        $.post('vehicle/type/list')
        .done(function({data}) {
            let $select = $('#vehicleTypeSelect');
            for(let type of data) {
                vehicleTypeCache.set(type.typeId, type.typeName);
                $select.append("<option value='" + type.typeId + "'>" + type.typeName + "</option>");
            }
            layui.form.render('select');
        });

        table.render({
            id: 'rentOutTableId',
            elem: '#rentOutTable',
            page: true,
            url: '/vehicle/searchPage',
            cols: [[ //表头
                {field: 'licensePlateNumber', title: '车牌号'},
                {field: 'vehicleType', title: '类型', templet: d => vehicleTypeCache.has(d.vehicleType) ? vehicleTypeCache.get(d.vehicleType) : ""},
                {field: 'vehicleColor', title: '颜色'},
                {field: 'vehiclePrice', title: '汽车原价', sort: true},
                {field: 'rentOut', title: '出租价格(日)', sort: true},
                {field: 'rentalDeposit', title: '出租押金', sort: true},
                {field: 'vehicleDescribe', title: '车辆描述', templet: d => d.vehicleDescribe + (d.shop + "" !== currentShopId ? "(非本店)" : "")},
                {toolbar: '#rentOutTableTool', title: '操作'}
            ]]
        })

        table.on('tool(rentOutTableFilter)', ({event, data}) => {
            switch(event) {
                case 'add':
                    rentOutListAdd(data, currentShopId);
                    break;
            }
        })


        table.render({
            id: 'rentOutListTableId',
            elem: '#rentOutListTable',
            cols: [[ //表头
                {field: 'licensePlateNumber', title: '车牌号', width: 100},
                {field: 'vehicleType', title: '类型', width: 100, templet: d => vehicleTypeCache.has(d.vehicleType) ? vehicleTypeCache.get(d.vehicleType) : ""},
                {field: 'vehicleColor', title: '颜色', width: 100},
                {field: 'rentOutMoney', title: '价格', width: 80, sort: true},
                {field: 'rentalDeposit', title: '押金', width: 80, sort: true},
                {field: 'vehicleDescribe', title: '描述', width: 150},
                {toolbar: '#rentOutListTableTool', title: '操作', width: 58}
            ]]
        })


        table.on('tool(rentOutListFilter)', ({event, data}) => {
            switch(event) {
                case 'remove':
                    rentOutListRemove(data.licensePlateNumber);
                    break;
            }
        })
    })
    //
    function rentOutListRemove(licensePlateNumber) {
        let deleteRentalDepositMoney = rentOutListMap.get(licensePlateNumber).rentalDeposit;
        let deleteRentOutMoney = rentOutListMap.get(licensePlateNumber).rentOut * day;
        rentOutListMap.delete(licensePlateNumber);
        layui.use(['layer', 'jquery'], () => {
            layer.msg('移除成功。车牌号:' + licensePlateNumber);
            layui.table.reload('rentOutListTableId', {data: getMapValues(rentOutListMap)});
            let rentOutListSize = document.getElementById('rentOutListSize');
            if (rentOutListMap.size === 0) {
                rentOutListSize.style.display = 'none';
                document.getElementById('rentOutTotalMoney').value = 0;
                document.getElementById('rentalDepositTotalMoney').value = 0;
            } else {
                rentOutListSize.innerText = rentOutListMap.size;
                let rentalDepositTotalMoneyDom = document.getElementById('rentalDepositTotalMoney');
                let rentOutTotalMoneyMoneyDom = document.getElementById('rentOutTotalMoney');
                rentalDepositTotalMoneyDom.value = rentalDepositTotalMoneyDom.value - deleteRentalDepositMoney;
                rentOutTotalMoneyMoneyDom.value = rentOutTotalMoneyMoneyDom.value - deleteRentOutMoney;
            }
        });
    }

/*    function applyBorrowRecord(licensePlateNumber, beBorrowCarShop, borrowCarShop) {
        layui.use(['layer', 'jquery'], function() {
            layer.confirm('你要申请调用车牌号为：' + licensePlateNumber + ' 的车辆吗?', {
                icon: 3,
                title: '提示',
                btn: ['调用', '取消']
            }, function(index) {
                // 车牌号 licensePlateNumber
                // 被借用门店号  shop
                // 当前门店 currentShopId
                layui.$.post('/vehicle/callRecord/add', {licensePlateNumber, beBorrowCarShop, borrowCarShop })
                .done(function({code, msg}) {
                    layer.msg(msg + (code > 0 ? ',目标门店收到借车请求。 待同意后重新查询再次添加即可' : ''));
                });
                layer.close(index);
            })
        })
    }*/

    function rentOutListAdd(data, currentShopId) {
        layui.use('layer', () => {
            if (data.shop + "" !== currentShopId) {
                applyBorrowRecord(data.licensePlateNumber, data.shop, currentShopId);
            } else if (rentOutListMap.has(data.licensePlateNumber)) {
                layer.msg('租借列表中以包含次车辆，无法添加')
            } else {
                data.rentOutMoney = day * data.rentOut;
                rentOutListMap.set(data.licensePlateNumber, data);
                layer.msg('添加成功。车牌号:' + data.licensePlateNumber);
                let rentOutListSize = document.getElementById('rentOutListSize');
                rentOutListSize.style.display = 'inline-block';
                rentOutListSize.innerText = rentOutListMap.size;

                let rentalDepositTotalMoneyDom = document.getElementById('rentalDepositTotalMoney');
                let rentOutTotalMoneyMoneyDom = document.getElementById('rentOutTotalMoney');
                rentalDepositTotalMoneyDom.value = parseFloat(data.rentalDeposit) + parseFloat(rentalDepositTotalMoneyDom.value);
                rentOutTotalMoneyMoneyDom.value = data.rentOut * day + parseFloat(rentOutTotalMoneyMoneyDom.value);
            }
        })
    }

    document.getElementById('rentOutList-btn').onclick = () => {

        layui.use(['layer', 'jquery', 'table', 'form'], () => {
            let $ = layui.$;
            let table = layui.table;
            let form = layui.form;
            layui.table.reload('rentOutListTableId', {data: getMapValues(rentOutListMap)});

            layer.open({
                type: 1,
                title: '租借列表详细信息',
                area: ['705px', '500px'],
                content: $('#rentOutListBox'),
                btn: ['租借', '取消'],
                btn1: (index) => {
                    let values = getMapValues(rentOutListMap);
                    let data = form.val('rentOutForm');
                    if(values.length === 0) {
                        layer.msg('租借列表为空');
                    } else if (data.clientAddress === '' || data.clientIdNumber === '' || data.clientName === '' || data.clientPhone === '' || data.endDate === '' || data.startDate === '') {
                        layer.msg('请填写完整租车的信息');
                    }  else if (!/^1\d{16}[\d|x|X]$/.test(data.clientIdNumber)) {
                        layer.msg("请输入正确的身份证");
                    } else if (!/^1[3|5]\d{9}/.test(data.clientPhone)) {
                        layer.msg('手机号必须以13/15开头')
                    } else if(!/^(.+省.+市|北京市).+区$/.test(data.clientAddress)) {
                        layer.msg('请输入正确的地址')
                    } else {
                        let difftime = (Date.parse(data.endDate) - Date.parse(data.startDate)) / 1000;
                        if ((difftime / 86400) <= 0) {
                            layer.msg('请选择正确的租车时长');
                        } else {
                            let rentalDepositTotalMoney = document.getElementById('rentalDepositTotalMoney').value;
                            let rentOutTotalMoney = document.getElementById('rentOutTotalMoney').value;
                            let now = new Date();
                            now.setHours(now.getHours() + 1);
                            let isUpdate = false
                                , isAdd = false
                                , isPickUp = now > new Date(data.startDate);
                            let rentOutVehicleList = [];
                            if (clientListMap.has(data.clientIdNumber)) {
                                let client = clientListMap.get(data.clientIdNumber);
                                if (client.clientAddress !== data.clientAddress || client.clientName !== data.clientName || client.clientPhone !== data.clientPhone) {
                                    isUpdate = true;
                                }
                            } else {
                                isAdd = true;
                            }
                            let $vehicle = $('#vehicle').empty();
                            for (let item of values) {
                                $vehicle.append(`<p>
                                    <span style="text-decoration:underline">` + item.vehicleDescribe + `</span>，
                                    车架号：<span style="text-decoration:underline">` + item.vehicleIdNumber + `</span>，
                                    车牌号：<span style="text-decoration:underline">` + item.licensePlateNumber + `</span>
                                </p>`);
                                rentOutVehicleList.push({
                                    'vehiclePlateNumber': item.licensePlateNumber,
                                    'rentOutMoney': item.rentOutMoney,
                                    'rentalDeposit': item.rentalDeposit
                                });
                            }

                            let vo = {
                                // client
                                'client': {
                                    'clientIdNumber': data.clientIdNumber,
                                    'clientAddress': data.clientAddress,
                                    'clientName': data.clientName,
                                    'clientPhone': data.clientPhone
                                },
                                // rentOut
                                'rentOut': {
                                    'ofTheTime': data.startDate,
                                    'predictReturnTime': data.endDate,
                                    'clientId': data.clientIdNumber,
                                    'rentOutTotalMoney': isPickUp ? rentOutTotalMoney : 500,
                                    'isPickUp': isPickUp,
                                    'comment': data.comment,
                                },
                                'rentOutVehicleList' : rentOutVehicleList,
                                'isAdd': isAdd,
                                'isUpdate' : isUpdate
                            }
                            $.ajax({
                                url: '/orderForm/addOrderForm',
                                method: 'post',
                                data: JSON.stringify(vo),
                                contentType: 'application/json;charset=UTF-8',
                                success: function ({code, msg}) {
                                    if (code > 0) {
                                        table.reload('rentOutTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                                    }
                                    if (isPickUp) {
                                        layer.msg(msg);
                                        let nowDate = new Date();
                                        $('#rentOutStartDate').text(data.startDate);
                                        $('#rentOutEndDate').text(data.endDate);
                                        $('#clientName').text(data.clientName);
                                        $('#clientIdNumber').text(data.clientIdNumber);
                                        $('#totalMoney').text(data.rentOutTotalMoney);
                                        $('#nowDate').text(nowDate.getFullYear() + "年" + (nowDate.getMonth() + 1) + "月" + nowDate.getDate() + "日");

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
                                    } else {
                                        layer.alert("预约成功， 请收押金500元。预约取车时间为：" + data.startDate);
                                    }
                                    rentOutListMap.clear();
                                    document.getElementById('rentOutListSize').style.display = 'none';
                                    document.getElementById('rentOutTotalMoney').value = 0;
                                    document.getElementById('rentalDepositTotalMoney').value = 0;
                                    layer.close(index);
                                },
                                error: function (resp) {
                                    layer.msg(resp.status + " " + resp.statusMessage);
                                    layer.close(index);
                                }
                            })
                        }

                    }
                }
            })


        })
    }


    // 查询
    document.getElementById('rentOut-search').onclick = () => {
        layui.use(['form', 'table'], function() {
            let data = layui.form.val('searchForm');
            if (data.isAllShop === undefined) {
                data.isAllShop = null;
            }
            layui.table.reload('rentOutTableId', {where: data, page:{curr: 1}})
        })
    }

</script>
</body>
</html>

<script type="text/html" id="rentOutTableTool">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add"><i class="layui-icon">&#xe654;</i>添加 </button>
    </div>
</script>
<script type="text/html" id="rentOutListTableTool">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon layui-icon-delete"></i></button>
    </div>
</script>
