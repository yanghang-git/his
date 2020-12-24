<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 20-12-24
  Time: 下午1:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <a><cite>客户地区统计</cite></a>
            </span>
        </div>

        <div class="layui-row">
            <div class="layui-col-md6 layui-form-item" style="margin-top: 50px">
                <form>
                    <div class="layui-inline">
                        <label class="layui-form-label">地址</label>
                        <div class="layui-input-inline">
                            <input type="text" name="keyword" disabled class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <button type="button" id="show-btn" class="layui-btn">查看</button>
                    </div>
                    <div class="layui-inline">
                        <button type="reset" class="layui-btn">重置</button>
                    </div>
                    <div class="layui-inline">
                        <button type="button" id="unstack" class="layui-btn">上一个</button>
                    </div>
                </form>

            </div>
            <div class="layui-col-md6" id="main" style="width: 600px;height: 450px;margin-top: 20px"></div>
        </div>
    </div>

    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - HIS医院
    </div>
</div>
<script src="webjars/echarts/4.9.0/echarts.min.js"></script>
<script>
    layui.use('element');
    let addressDataStack = [];
    let myChart = echarts.init(document.getElementById('main'));

    document.getElementById('unstack').onclick = function() {
        if (addressDataStack.length > 0) {
            myChart.setOption({series: [{data: addressDataStack.pop()}]});
        } else {
            reload();
        }
    }

    document.getElementById('show-btn').onclick = function() {
        let keyword = document.getElementsByName('keyword')[0].value;
        reload(keyword);
        if (oldData !== undefined) {
            console.log(oldData);
            addressDataStack.push(oldData);
        }
    }
    let oldData = undefined;


    function reload(keyword) {
        layui.use(['jquery', 'layer'], function () {
            let layer = layui.layer;
            layui.$.ajax({
                url: '/statistics/client/address',
                method: 'post',
                data: {keyword},
                async: false,
                success: function ({code, data, msg}) {
                    if (code !== -1) {
                        oldData = data;
                        myChart.setOption({series: [{data}]});
                    } else {
                        oldData = undefined;
                        layui.layer.msg(msg)
                    }
                },
                error: function(resp) {
                    layer.msg(resp.status + " " + resp.statusMessage)
                }
            })
        })
    }
    reload();

    myChart.on('click', function ({data}) {
        document.getElementsByName('keyword')[0].value = data.name;
    })

    myChart.setOption({
        title: {
            text: '客户地区统计',
            left: 'center'
        },
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            orient: 'vertical',
            left: 'right'
        },
        series: [
            {
                name: '客户数量(占比)',
                type: 'pie',
                radius: '55%',
                center: ['50%', '60%'],
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    });


</script>
</body>
</html>
