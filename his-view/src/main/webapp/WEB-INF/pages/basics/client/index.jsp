<%--
  Created by IntelliJ IDEA.
  User: spring
  Date: 20-12-12
  Time: 下午3:17
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
              <a><cite>顾客管理</cite></a>
            </span>
        </div>

        <%-- main --%>
        <div style="background: #fff; margin: 15px; padding: 20px">
            <%-- 筛选条件 --%>
            <form class="layui-form search" lay-filter="searchForm">

                <div class="layui-form-item">
                    <div class="layui-inline">
                        <label class="layui-form-label">身份证号</label>
                        <div class="layui-input-block">
                            <input type="text" name="clientIdNumber" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">顾客姓名</label>
                        <div class="layui-input-block">
                            <input type="text" name="clientName" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
                    <div class="layui-inline">
                        <label class="layui-form-label">顾客电话</label>
                        <div class="layui-input-block">
                            <input type="text" name="clientPhone" maxlength="11" placeholder="请输入" class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline" style="margin-left: 90px">
                        <button class="layui-btn" type="button" id="user-search">
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
                        <label class="layui-form-label">顾客地址</label>
                        <div class="layui-input-block">
                            <input type="text" name="clientAddress" maxlength="11" placeholder="请输入"
                                   class="layui-input">
                        </div>
                    </div>

                    <div class="layui-inline">
                        <label class="layui-form-label">性别</label>
                        <div class="layui-input-block">
                            <input type="radio" name="clientSex" value="true" title="男">
                            <input type="radio" name="clientSex" value="false" title="女">
                        </div>
                    </div>
                </div>
            </form>

            <%-- table --%>
            <div>
                <table id="clientTable" lay-filter="clientTableFilter"></table>
            </div>
        </div>

    </div>
    <div class="layui-footer" style="background: #fff">
        <!-- 底部固定区域 -->
        © his.com - HIS医院
    </div>
</div>

<div id="clientAddBox" style="padding: 15px">
    <form class="layui-form" lay-filter="clientAddBoxFilter">
        <div class="layui-form-item">
            <label class="layui-form-label">身份证</label>
            <div class="layui-input-block">
                <input type="text" name="clientIdNumber" maxlength="18" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="clientName" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="clientSex" value="true" title="男" checked>
                <input type="radio" name="clientSex" value="false" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
                <input type="text" name="clientPhone" maxlength="11" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="clientAddress" placeholder="请输入" class="layui-input">
            </div>
        </div>
    </form>
</div>

<div id="clientEditBox" style="padding: 15px">
    <form class="layui-form" lay-filter="clientEditBoxFilter">
        <input type="hidden" name="clientIdNumber">
        <input type="hidden" name="createTime">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="clientName" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="clientSex" value="true" title="男" checked>
                <input type="radio" name="clientSex" value="false" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机</label>
            <div class="layui-input-block">
                <input type="text" name="clientPhone" maxlength="11" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">地址</label>
            <div class="layui-input-block">
                <input type="text" name="clientAddress" placeholder="请输入" class="layui-input">
            </div>
        </div>
    </form>
</div>
<script src="js/util.js"></script>
<script>
    // 侧边栏需要element模块的支持
    layui.use(['element']);

    // clientTable的表格相关操作
    layui.use(['table'], function () {
        let table = layui.table;
        table.render({
            id: 'clientTableId',
            elem: '#clientTable',
            page: true,
            url: '/client/getPage', //数据接口parsererror
            toolbar: '#clientTableToolbar',
            cols: [[ //表头
                {type: 'checkbox'},
                {field: 'clientIdNumber', title: '身份证'},
                {field: 'clientName', title: '姓名'},
                {field: 'clientSex', title: '性别', templet: d => d.clientSex ? "男" : "女"},
                {field: 'clientPhone', title: '手机'},
                {field: 'clientAddress', title: '地址'},
                {field: 'createTime', title: '录入时间'},
                {toolbar: '#clientTableToolb', title: '操作'}
            ]]
        });


        table.on('tool(clientTableFilter)', function ({event, data}) {
            switch (event) {
                case "security":
                    security(data.clientIdNumber);
                    break;
                case "edit":
                    edit(data);
                    break;
                case "remove":
                    remove([data.clientIdNumber]);
                    break;
            }
        })

        table.on('toolbar(clientTableFilter)', function ({event}) {
            switch (event) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove(table.checkStatus('adminTableId').data.map(data => data.clientIdNumber));
                    break;
            }
        });
    });


    // 添加user
    function add() {
        document.querySelector('#clientAddBox form').reset();
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            layer.open({
                type: 1,
                content: $('#clientAddBox'),
                auto: ['300px'],
                btn: ['保存', '取消'],
                btn1: function (index) {
                    let data = form.val('clientAddBoxFilter');
                    if (data.clientIdNumber === '' || data.clientName === '' || data.clientPhone === '' || data.clientAddress === '') {
                        layer.msg('请填写完整的信息以后再次确认');
                    } else if (!/^1\d{16}[\d|x|X]$/.test(data.clientIdNumber)) {
                        layer.msg("请输入正确的身份证");
                    } else if (!/^1[3|5]\d{9}/.test(data.clientPhone)) {
                        layer.msg('手机号必须以13/15开头')
                    } else if(!/^(.+省.+市|北京市).+区$/.test(data.clientAddress)) {
                        layer.msg('请输入正确的地址')
                    } else {
                        $.post('/client/save', data)
                            .done(function ({code, msg}) {
                                if (code > 0) {
                                    if ($('.layui-laypage-skip input').val() !== 1) {
                                        $('.layui-laypage-skip input').val(99999);
                                        $('.layui-laypage-skip .layui-laypage-btn').click();
                                    } else {
                                        table.reload('clientTableId');
                                    }
                                }
                                layer.msg(msg);
                            });
                        layer.close(index);
                    }
                }
            });
        })
    }

    // 修改user
    function edit(data) {
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            form.val("clientEditBoxFilter", data);
            $('#clientEditBox input[value=' + data.clientSex + '').attr('checked', 'checked').next().click();
            layer.open({
                type: 1,
                content: $('#clientEditBox'),
                auto: ['300px'],
                btn: ['修改', '取消'],
                btn1: function (index) {
                    let data = form.val('clientEditBoxFilter');
                    if (data.clientName === '' || data.clientPhone === '' || data.clientAddress === '') {
                        layer.msg('请填写完整的信息以后再次确认');
                    } else if (!/^1[3|5]\d{9}/.test(data.clientPhone)) {
                        layer.msg('手机号必须以13/15开头')
                    } else if(!/^(.+省.+市|北京市).+区$/.test(data.clientAddress)) {
                        layer.msg('请输入正确的地址')
                    } else {
                        $.post('/client/edit', data)
                            .done(function ({code, msg}) {
                                if (code > 0) {
                                    table.reload('clientTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                                }
                                layer.msg(msg);
                            });
                        layer.close(index);
                    }
                }
            });
        })
    }

    // 删除user
    function remove(clientIdArr) {
        if (clientIdArr.length === 0) {
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
                    url: '/client/remove',
                    method: 'post',
                    data: {ids: clientIdArr},
                    traditional: true,
                    success: function ({code, msg}) {
                        if (code > 0) {
                            table.reload('clientTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
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

    // 点击查询
    layui.use(['form', 'jquery', 'table'], function () {
        let form = layui.form;
        let $ = layui.$;
        let table = layui.table;
        $("#user-search").click(function () {
            table.reload("clientTableId", {where: form.val("searchForm")})
        });
    })

</script>
</body>
</html>
<script type="text/html" id="clientTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="layui-icon"></i></button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i></button>
    </div>
</script>
<script type="text/html" id="clientTableToolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add"><i class="layui-icon">&#xe654;</i>新增
        </button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>删除
        </button>
    </div>
</script>