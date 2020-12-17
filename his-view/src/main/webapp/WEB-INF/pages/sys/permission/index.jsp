<%--
  Created by IntelliJ IDEA.
  perm: spring
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
                        <label class="layui-form-label">查询关键字</label>
                        <div class="layui-input-block">
                            <input type="text" name="loginName" placeholder="请输入" class="layui-input">
                        </div>
                    </div>
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

<div id="permAddBox" style="padding: 15px">
    <form class="layui-form" lay-filter="permAddBoxFilter">
        <input type="hidden" name="permId"/>
        <input type="hidden" name="parentId"/>
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="permName" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Code</label>
            <div class="layui-input-block">
                <input type="text" name="permCode" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Url</label>
            <div class="layui-input-block">
                <input type="text" name="permUrl"  placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">归属</label>
            <div class="layui-input-block"  onclick="showPermissionTree()">
                <input type="text" id="permTitle" disabled placeholder="默认为一级菜单" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">顺序</label>
            <div class="layui-input-block">
                <input type="text" name="sort"  placeholder="数量越大越往后" class="layui-input">
            </div>
        </div>
    </form>
</div>


<div id="permEditBox" style="padding: 15px">
    <form class="layui-form" lay-filter="permEditBoxFilter">
        <input type="hidden" name="permId"/>
        <input type="hidden" name="parentId"/>
        <div class="layui-form-item">
            <label class="layui-form-label">名称</label>
            <div class="layui-input-block">
                <input type="text" name="permName" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Code</label>
            <div class="layui-input-block">
                <input type="text" name="permCode" placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">Url</label>
            <div class="layui-input-block">
                <input type="text" name="permUrl"  placeholder="请输入" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">顺序</label>
            <div class="layui-input-block">
                <input type="text" name="sort"  placeholder="数量越大越往后" class="layui-input">
            </div>
        </div>
    </form>
</div>
<script src="js/layuiTreeUtil.js"></script>
<script>
    // 侧边栏需要element模块的支持
    layui.use(['element']);

    // 使用时间模块
    layui.use('laydate', function () {
        layui.laydate.render({elem: '#date'})
    })





    // permTable的表格相关操作
    layui.use(['table'], function () {
        let permCacheMap = new Map();
        let table = layui.table;
        table.render({
            id: 'permTableId',
            elem: '#permTable',
            page: true,
            url: '/perm/getPage', //数据接口
            toolbar: '#permTableToolbar',
            cols: [[ //表头
                {type: 'checkbox'},
                {type: "numbers", title: '#'},
                {field: 'permName', title: '名称'},
                {field: 'permCode', title: 'Code'},
                {field: 'permUrl', title: 'Url'},
                {
                    field: 'parentId', title: '归属', templet: function (d) {
                        if (/menu/.test(d.permCode)) {
                            permCacheMap.set(d.permId + "", d);
                        }
                        return permCacheMap.has(d.parentId + "") ? permCacheMap.get(d.parentId + "").permName : '';
                    }
                },
                {toolbar: '#permTableToolb', title: '操作'}
            ]]
        });

        table.on('tool(permTableFilter)', function ({event, data}) {
            switch (event) {
                case "edit":
                    edit(data);
                    break;
                case "remove":
                    console.log(data)
                    remove([data.permId]);
                    break;
            }
        })

        table.on('toolbar(permTableFilter)', function ({event}) {
            switch (event) {
                case "add":
                    add();
                    break;
                case "remove":
                    remove(table.checkStatus('permTableId').data.map(data => data.permId));
                    break;
            }
        });
    });


    // 因为添加或者修改需要添加使用到Permission的树进行选择。 所以初始化PermissionTree
    // 重新加载Permission树
    reloadPermissionTree();
    function reloadPermissionTree() {
        layui.use(['jquery', 'tree'], function () {
            let $ = layui.jquery;
            let tree = layui.tree;
            $.post('perm/searchPermMenu')
            .done(function({data}) {
                sessionStorage.setItem("permissionMenu", JSON.stringify(data));
            });
            $.ajax({
                url: 'perm/searchPermTree',
                method: 'post',
                success: function ({data}) {
                    tree.render({
                        id: 'permissionTreeId',
                        elem: '#permTree',
                        data,
                        click: function({data}) {
                            $('.layui-layer-close:eq(0)').click();
                            $('#permTitle').val(data.title);
                            $('#permAddBox form input[name=parentId]').val(data.id);
                        }
                    })
                }
            });
        })
    }

    // 显示Permission树供选择
    function showPermissionTree() {
        layui.use(['layer', 'jquery'], function() {
            let layer = layui.layer;
            let $ = layui.jquery;
            layer.open({
                type: 1,
                content: $('#permTree')
            });
        })
    }

    // 根据给定的数组去转换成一个map。 key = [key]   value = this
    function getMapByArr(arr, key) {
        let map = new Map();
        for(let item of arr) {
            map.set(item[key] + "", item);
        }
        return map;
    }


    // 添加perm
    function add() {
        let permCacheMap = getMapByArr(JSON.parse(sessionStorage.getItem('permissionMenu')), 'permId');
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            $('#permAddBox form')[0].reset();
            layer.open({
                type: 1,
                content: $('#permAddBox'),
                auto: ['300px'],
                btn: ['保存', '取消'],
                btn1: function (index) {
                    let data = form.val('permAddBoxFilter');
                    let parenPerm = permCacheMap.get(data.parentId);
                    if (data.permName === '' || data.permCode === '') {
                        layer.msg('名称和Code不允许为空');
                    } else if (data.parentId === '' && data.permUrl !== '') {
                        layer.msg('一级目录的Url不允许存在路径');
                    } else if (data.parentId !== '' && data.permUrl === '' && (parenPerm.permUrl === '' || parenPerm.permUrl === null)) {
                        layer.msg('二级目录必须有Url路径');
                    } else if (data.parentId !== '' && parenPerm.permUrl !== '' && data.permUrl !== '' && permCacheMap.has(parenPerm.parentId + "")) {
                        layer.msg('具体的权限不允许存在路径');
                    } else {
                        $.post('/perm/save', data)
                            .done(function ({code, msg}) {
                                if (code > 0) {
                                    if ($('.layui-laypage-skip input').val() !== 1) {
                                        $('.layui-laypage-skip input').val(99999);
                                        $('.layui-laypage-skip .layui-laypage-btn').click();
                                    } else {
                                        table.reload('permTableId');
                                    }
                                }
                                layer.msg(msg)
                            });
                        reloadPermissionTree();
                        layer.close(index);
                    }
                }
            });
        })
    }

    // 修改perm
    function edit(data) {
        layui.use(['jquery', 'form', 'layer', 'table'], function () {
            let $ = layui.$;
            let form = layui.form;
            let layer = layui.layer;
            let table = layui.table;
            form.val("permEditBoxFilter", data)
            if (data.parentId !== null && /menu/.test(data.permCode)) {
                $('#permEditBox form input[name=permUrl]').removeAttr('disabled').attr('placeholder', '请输入');
            } else {
                $('#permEditBox form input[name=permUrl]').attr('disabled', 'disabled').attr('placeholder', '无法输入');
            }
            layer.open({
                type: 1,
                content: $('#permEditBox'),
                auto: ['300px'],
                btn: ['修改', '取消'],
                btn1: function (index) {
                    $.post('/perm/edit', form.val('permEditBoxFilter'))
                    .done(function({code, msg}) {
                        if (code > 0) {
                            reloadPermissionTree();
                            table.reload('permTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                        }
                        layer.msg(msg);
                    });
                    layer.close(index);
                }
            });
        })
    }

    // 删除perm
    function remove(permIdArr) {
        layui.use(['layer', 'jquery', 'table'], function () {
            let layer = layui.layer;
            let $ = layui.$;
            let table = layui.table;
            if (permIdArr.length === 0) {
                layer.alert("请至少选中一条数据");
                return;
            }
            $.ajax({
                url: '/perm/remove',
                method: 'post',
                data: {ids: permIdArr},
                traditional: true,
                success: function ({code, msg}) {
                    if (code > 0) {
                        reloadPermissionTree();
                        table.reload('permTableId', {page: {curr: $('.layui-laypage-skip input').val()}})
                    }
                    layer.msg(msg);
                },
                error: function (resp) {
                    layer.msg(resp.status + " " + resp.statusMessage);
                }
            })
        });
    }


    // 点击查询
    layui.use(['form', 'jquery', 'table'], function () {
        let form = layui.form;
        let $ = layui.$;
        let table = layui.table;
        $("#perm-search").click(function () {
            table.reload("permTableId", {where: form.val("searchForm")})
        });
    })
</script>
</body>
</html>
<script type="text/html" id="permTableToolb">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="edit"><i class="layui-icon"></i></button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i></button>
    </div>
</script>
<script type="text/html" id="permTableToolbar">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-xs layui-btn-normal" lay-event="add"><i class="layui-icon">&#xe654;</i>新增
        </button>
        <button class="layui-btn layui-btn-xs layui-btn-danger" lay-event="remove"><i class="layui-icon"></i>删除
        </button>
    </div>
</script>