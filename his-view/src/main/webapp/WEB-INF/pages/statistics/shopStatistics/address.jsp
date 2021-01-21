<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 21-1-19
  Time: 下午4:16
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
              <a href="index">HIS</a>
              <a><cite>门店地区统计</cite></a>
            </span>
        </div>
        <div style="background: #fff; margin: 15px; padding: 20px">
            <div id="map" style="width: 1200px; height: 500px; padding-left: 30px"></div>
        </div>
    </div>
    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - 高端汽车管理系统
    </div>
</div>
<script src="webjars/echarts/4.9.0/echarts.min.js"></script>
<script src="js/china.js"></script>
<script>
    layui.use('element');

    let map = echarts.init(document.getElementById('map'));

    // 定义颜色
    let colors = ['#faebd2', '#e9a188', '#d56355', '#bb3937', '#772526', '#480f10']

    // 定义数据
    let dataList = [];
    layui.use(['jquery', 'layer'], function() {
        let $ = layui.$;
        $.post('/statistics/shop/chinaDistributed')
            .done(({data}) => {
                for (let item in data) {
                    dataList.push({name: item, value: data[item]});
                }
            });
    })


    let option = {
        title: {
            text: '门店分布图',
            left: 'center'
        },
        backgroundColor: '#fff',
        tooltip: { // 提示框组件
            formatter: (params) => params.seriesName + '<br/>' + params.name + ':' + (isNaN(params.value) ? 0 : params.value)
        },
        visualMap: { // 视觉映射组件， 用于进行「视觉编码」，
            type: 'piecewise',  // 分段型视觉映射组件
            orient: 'vertical', // 垂直方向
            padding: 10,
            pieces: [ // 自定义「分段式视觉映射组件」 的每一段的范围， 以及每一段的文字， 以及颜色
                {min: 1, max: 9, color: colors[0]},
                {min: 10, max: 49, color: colors[1]},
                {min: 50, max: 99, color: colors[2]},
                {min: 100, max: 199, color: colors[3]},
                {min: 200, max: 499, color: colors[4]},
                {min: 500, color: colors[5]},
            ]
        },
        geo: { // 地理坐标系组件用于地图的绘制
            map: 'china',
            emphasis: {
                itemStyle: {
                    areaColor: '#F3B329', // 鼠标选择的颜色
                    shadowOffsetX: 0,
                    shadowOffsetY: 0,
                    shadowBlur: 20,
                    borderWidth: 0,
                    shadowColor: 'rgba(0, 0, 0, .5)',
                }
            }
        },
        series: { // 系列列表(图标)
            name: '门店数量',
            type: 'map', // 图表类型
            geoIndex: 0,
            data: dataList
        }
    }
    setTimeout(() => {
        map.setOption(option);
    }, 200)
</script>
</body>
</html>