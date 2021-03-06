<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 21-1-19
  Time: 下午7:14
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
                <a><cite>月份出租价格区间统计</cite></a>
            </span>
        </div>
        <div style="background: #fff; margin: 15px; padding: 20px" class="layui-row">
            <form class="layui-form layui-col-md4">
                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">起始时间</label>
                        <div class="layui-input-inline">
                            <input type="text" autocomplete="off"  name="startDate" id="startDate" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">结束时间</label>
                        <div class="layui-input-inline">
                            <input type="text" autocomplete="off"  name="endDate" id="endDate" class="layui-input">
                        </div>
                    </div>
                    <button type="button" class="layui-btn layui-btn-normal" id="search">查询</button>
                </div>
            </form>
            <div class="layui-col-md8" id="main" style="width: 820px;height: 425px;margin-top: 20px"></div>
        </div>
    </div>
    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - 高端汽车管理系统
    </div>
</div>
<script src="webjars/echarts/4.9.0/echarts.min.js"></script>
<script>

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
            }
        },
        yAxis: {
            type: 'value'
        },
        legend: {
            orient: 'vertical',
            left: 'right'
        },
        series: [
            {
                name: '1-499',
                type: 'bar',
                barWidth: '10%',
                itemStyle: {
                    barBorderRadius: 5
                }
            },
            {
                name: '500-1499',
                type: 'bar',
                barWidth: '10%',
                itemStyle: {
                    barBorderRadius: 5
                }
            },
            {
                name: '>1500',
                type: 'bar',
                barWidth: '10%',
                itemStyle: {
                    barBorderRadius: 5
                }
            }
        ]
    })


    let element
        , laydate
        , $
        , layer;
    layui.use(['form', 'element', 'laydate', 'layer'], function(){
        element = layui.element;
        laydate = layui.laydate;
        $ = layui.$;
        layer = layui.layer;
        laydate.render({
            elem: '#startDate',
            type: 'month'
        });
        laydate.render({
            elem: '#endDate',
            type: 'month'
        });
    });

    document.getElementById('search').onclick = function() {
        $.ajax({
            url:'/statistics/rentOutLog/priceRange',
            data: {startDate: $('#startDate').val().length === 0 ? '' : $('#startDate').val() + "-01", endDate: $('#endDate').val().length === 0 ? '' : $('#endDate').val() + "-01"},
            method: 'post',
            success: function({code, msg, data}) {
                if (code < 0) {
                    layer.msg(msg);
                } else {
                    let xData = [];
                    let seriesOne = [];
                    let seriesTwo = [];
                    let seriesThree = [];
                    for(let key in data) {
                        xData.push(key);
                        seriesOne.push(data[key][0]);
                        seriesTwo.push(data[key][1]);
                        seriesThree.push(data[key][2]);
                    }
                    let series = myChart.getOption().series;
                    series[0].data = seriesOne;
                    series[1].data = seriesTwo;
                    series[2].data = seriesThree;
                    myChart.setOption({xAxis: {data:xData}, series});
                }
            }
        })
    }
</script>
</body>
</html>

