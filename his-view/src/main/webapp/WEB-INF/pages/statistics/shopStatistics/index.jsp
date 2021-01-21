<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 21-1-20
  Time: 上午11:52
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
              <a><cite>门店统计</cite></a>
            </span>
        </div>
        <%-- main --%>
        <div style="background: #fff; margin: 15px; padding: 20px">


            <div>
                <form class="layui-form" lay-filter="searchForm">
                    <div class="layui-form-item">
                        <div class="layui-inline">
                            <label class="layui-form-label">月份区间:</label>
                            <div class="layui-input-inline" style="width: 120px;">
                                <input type="text" name="startDate" id="dateStart" class="layui-input">
                            </div>
                            <div class="layui-form-mid">-</div>
                            <div class="layui-input-inline" style="width: 120px;">
                                <input type="text" name="endDate" id="dateEnd" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-inline">
                            <button class="layui-btn" type="button" id="role-search">
                                <i class="layui-icon layui-icon-search"></i>
                            </button>
                        </div>
                    </div>
                </form>
                <%-- echarts --%>
                <div class="layui-btn-group layui-col-md8 layui-col-md-offset10" style="z-index: 4111">
                    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" id="rentOutMoney">
                        金额
                    </button>
                    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" id="rentOut">
                        订单
                    </button>
                    <button type="button" class="layui-btn layui-btn-primary layui-btn-sm" id="newClient">
                        用户
                    </button>
                </div>
                <div>
                    <div id="main" style="width: 1260px;height: 425px;margin-top: 20px"></div>
                </div>
                <%-- table --%>
                <div style="padding-top: 20px">
                    <table id="roleTable" lay-filter="roleTableFilter"></table>
                </div>
            </div>
        </div>
    </div>
    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - 高端汽车管理系统
    </div>
</div>

<div id="employeeBox" style="display:none;">
    <div class="layui-collapse employeeMain" style="padding: 0 15px">
    </div>
</div>
<div id="address" style=" display:none; width: 870px; height: 440px"></div>
<script src="webjars/echarts/4.9.0/echarts.min.js"></script>
<script type="text/javascript"
        src="//api.map.baidu.com/api?type=webgl&v=1.0&ak=ylRsDnCtETZ2fIhG9yXbjpFY0UUDxwgK"></script>
<script>
    // 侧边栏需要element模块的支持
    layui.use(['element']);

    let myChart = echarts.init(document.getElementById('main'));
    myChart.setOption({
        title: {
            text: '月出租价格区间',
            left: 'center'
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                type: 'line'        // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '8%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: {
            type: 'category',
            axisTick: {
                alignWithLabel: true
            },
        },
        yAxis: {
            type: 'value'
        },
        legend: {
            orient: 'vertical',
            left: 'right',
            padding: [30, 0, 0, 0]
        },
        series: [
            {
                name: '新增顾客',
                type: 'bar',
                barWidth: '8%',
                itemStyle: {
                    barBorderRadius: 5
                },
            },
            {
                name: '出租数量',
                type: 'bar',
                barWidth: '8%',
                itemStyle: {
                    barBorderRadius: 5
                },
            },
            {
                name: '出租金额 (k)',
                type: 'bar',
                barWidth: '8%',
                itemStyle: {
                    barBorderRadius: 5
                },
            }
        ]
    })


    layui.use(['laydate', 'layer'], function () {
        let laydate = layui.laydate;
        laydate.render({
            elem: '#dateStart',
            done: function (data) {
                let endDate = Date.parse(document.getElementById('dateEnd').value);
                let startDate = Date.parse(data);
                if (startDate > endDate) {
                    layer.msg("请选择正确的时间")
                }
            }
        });
        laydate.render({
            elem: '#dateEnd',
            done: function (data) {
                let startDate = Date.parse(document.getElementById('dateStart').value);
                let endDate = Date.parse(data);
                if (startDate > endDate) {
                    layer.msg("请选择正确的时间")
                }
            }
        });
    });

    let map = new BMapGL.Map('address');
    map.centerAndZoom(new BMapGL.Point(116.331398, 39.897445), 12);
    map.enableScrollWheelZoom();
    // 创建地址解析器实例
    let myGeo = new BMapGL.Geocoder();
    // roleTable的表格相关操作
    layui.use(['table', 'jquery', 'layer'], function () {
        let table = layui.table;
        let $ = layui.$;
        table.render({
            id: 'roleTableId',
            elem: '#roleTable',
            url: '/statistics/shop/searchPage', //数据接口
            toolbar: '',
            cols: [[ //表头
                {field: "shopId", title: '编号', width: 59},
                {field: 'shopName', title: '店名'},
                {
                    field: 'managers', title: '店长', templet: d => {
                        let resultStr = '';
                        for (let i = 0; i < d.managers.length; i++) {
                            resultStr += d.managers[i];
                            if (i !== d.managers.length - 1) {
                                resultStr += ',';
                            }
                        }
                        return resultStr;
                    }
                },
                {field: 'newClientSize', title: '新增用户', sort: true},
                {field: 'rentOutSize', title: '出租次数', sort: true},
                {field: 'rentOutTotalMoney', title: '累计金额', sort: true},
                {toolbar: '#roleTableToolb', title: '操作', fixed: 'right'}
            ]],
            done: function ({data}) {
                let shopNameArr = [];
                let newClientSizeArr = [];
                let rentOutSizeArr = [];
                let rentOutTotalMoneyArr = [];
                for (let shop of data) {
                    shopNameArr.push(shop.shopName);
                    newClientSizeArr.push(shop.newClientSize);
                    rentOutSizeArr.push(shop.rentOutSize);
                    rentOutTotalMoneyArr.push(shop.rentOutTotalMoney/1000);
                    myGeo.getPoint(shop.shopAddress, function (point) {
                        if (point) {
                            map.addOverlay(new BMapGL.Marker(point, {title: shop.shopAddress}))
                        } else {
                            alert('门店地址解析错误！');
                        }
                    }, '北京市');
                }
                let series = myChart.getOption().series;
                series[0].data = newClientSizeArr;
                series[1].data = rentOutSizeArr;
                series[2].data = rentOutTotalMoneyArr;
                myChart.setOption({xAxis: {data:shopNameArr}, series});
            }
        });


        table.on('tool(roleTableFilter)', function ({event, data}) {
            switch (event) {
                case "address":
                    address(data.shopAddress, data.shopName);
                    break;
                case 'employee':
                    employee(data.shopId);
                    break;
            }
        })

    });

    function swap(arr, i, j) {
        let temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }

    // 出租金额排序
    document.getElementById('rentOutMoney').onclick = () => {
        let option = myChart.getOption();
        let rentOutTotalMoneyArr = option.series[2].data;
        let size = 0;
        for (let item of rentOutTotalMoneyArr) {
            if (item === 0) {
                size++;
            }
        }
        if (size === rentOutTotalMoneyArr.length) {
            return;
        }
        let shopNameArr = option.xAxis[0].data;
        let newClientSizeArr = option.series[0].data;
        let rentOutSizeArr = option.series[1].data;

        // 选择排序
        for (let i = 0; i < rentOutTotalMoneyArr.length; i++) {
            let maxIndex = i; // 假设当前是最大的
            for (let j = i + 1; j < rentOutTotalMoneyArr.length; j++) {
                if (rentOutTotalMoneyArr[maxIndex] < rentOutTotalMoneyArr[j]) {
                    maxIndex = j;
                }
            }
            swap(rentOutTotalMoneyArr, i, maxIndex);
            swap(rentOutSizeArr, i, maxIndex);
            swap(newClientSizeArr, i, maxIndex);
            swap(shopNameArr, i, maxIndex);
        }

        let series = option.series;
        series[0].data = newClientSizeArr;
        series[1].data = rentOutSizeArr;
        series[2].data = rentOutTotalMoneyArr;
        myChart.setOption({xAxis: {data:shopNameArr}, series});
    }

    // 出租数量排序
    document.getElementById('rentOut').onclick = () => {
        let option = myChart.getOption();
        let rentOutSizeArr = option.series[1].data;
        let size = 0;
        for (let item of rentOutSizeArr) {
            if (item === 0) {
                size++;
            }
        }
        if (size === rentOutSizeArr.length) {
            return;
        }
        let shopNameArr = option.xAxis[0].data;
        let newClientSizeArr = option.series[0].data;
        let rentOutTotalMoneyArr = option.series[2].data;

        // 插入排序
       for (let i = 1; i < rentOutSizeArr.length; i++) {
           // 从第二个开始， 第一个看成一个“有序”数组
           let minLength = i;
           for (let j = i - 1; j >= 0; j--) {
               if  (rentOutSizeArr[minLength] > rentOutSizeArr[j]) {
                   minLength = j;
               }
           }
           swap(rentOutTotalMoneyArr, i, minLength);
           swap(rentOutSizeArr, i, minLength);
           swap(newClientSizeArr, i, minLength);
           swap(shopNameArr, i, minLength);
       }



        let series = option.series;
        series[0].data = newClientSizeArr;
        series[1].data = rentOutSizeArr;
        series[2].data = rentOutTotalMoneyArr;
        myChart.setOption({xAxis: {data:shopNameArr}, series});
    }

    // 新增客户排序
    document.getElementById('newClient').onclick = () => {
        let option = myChart.getOption();
        let newClientSizeArr = option.series[0].data;
        let size = 0;
        for (let item of newClientSizeArr) {
            if (item === 0) {
                size++;
            }
        }
        if (size === newClientSizeArr.length) {
            return;
        }
        let shopNameArr = option.xAxis[0].data;
        let rentOutSizeArr = option.series[1].data;
        let rentOutTotalMoneyArr = option.series[2].data;

        // 希尔排序
        for (let i = newClientSizeArr.length >> 1; i > 0; i >>= 1) {
            for (let j = i; j < newClientSizeArr.length; j++) {
                let minIndex = j;
                for (let k = j; k - i >= 0; k -= i) {
                    if (newClientSizeArr[minIndex] > newClientSizeArr[k - i]) {
                        minIndex = k - i;
                    }
                }
                swap(rentOutTotalMoneyArr, j, minIndex);
                swap(rentOutSizeArr, j, minIndex);
                swap(newClientSizeArr, j, minIndex);
                swap(shopNameArr, j, minIndex);
            }
        }



        let series = option.series;
        series[0].data = newClientSizeArr;
        series[1].data = rentOutSizeArr;
        series[2].data = rentOutTotalMoneyArr;
        myChart.setOption({xAxis: {data:shopNameArr}, series});
    }

    // 查看门店内员工
    function employee(shopId) {
        layui.use(['jquery', 'layer', 'element'], function () {
            let $ = layui.$;
            let element = layui.element;
            let $employeeMain = $('.employeeMain').empty();
            $.post('statistics/shop/shopEmployee', {shopId})
                .done(({data}) => {
                    for (let item in data) {
                        let values = '';
                        for (let v of data[item]) {
                            values += '<p style="padding-left: 50px; line-height: 27px;">' + v + '</p>';
                        }
                        $employeeMain.append($(`<div class="layui-colla-item">
                            <h2 class="layui-colla-title">` + item + `</h2>
                            <div class="layui-colla-content layui-show">` + values + `</div>
                        </div>`));
                    }
                    element.render();
                })
            layer.open({
                type: 1,
                title: '门店人员详情',
                area: ['870px', '484px'],
                content: $('#employeeBox')
            });
        })
    }

    // 查看地址
    function address(shopAddress, shopName) {
        // 将地址解析结果显示在地图上，并调整地图视野
        myGeo.getPoint(shopAddress, function (point) {
            if (point) {
                map.centerAndZoom(point, 16);
                let infoWindow = new BMapGL.InfoWindow(shopName, {title: "门店名称"});
                map.openInfoWindow(infoWindow, map.getCenter());
            } else {
                alert('门店地址解析错误！');
            }
        }, '北京市');
        layui.use(['layer', 'jquery'], function () {
            let $ = layui.jquery;
            layer.open({
                type: 1,
                title: '门店地址',
                area: ['870px', '484px'],
                content: $("#address")
            })
        })
    }

    // 点击查询
    document.getElementById('role-search').onclick = () => {
        layui.use(['form', 'jquery', 'table'], function () {
            let form = layui.form;
            let $ = layui.$;
            let table = layui.table;
            let data = form.val('searchForm');
            if (Date.parse(data.startDate) > Date.parse(data.endDate)) {
                layer.msg("请选择正确的时间")
                data.startDate = '';
                data.endDate = '';
                form.val('searchForm', data);
            } else {
                table.reload("roleTableId", {where: data, page: {curr: 1}})
            }
        })
    }

</script>
</body>
</html>
<script type="text/html" id="roleTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs" lay-event="address"><i class="layui-icon">&#xe715;</i>位置</button>
        <button class="layui-btn layui-btn-xs" lay-event="employee"><i class="layui-icon">&#xe613;</i>员工</button>
    </div>
</script>