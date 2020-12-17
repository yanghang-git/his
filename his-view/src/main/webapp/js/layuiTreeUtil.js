function getLayuiTreeCheckedId(treeData) {
    let targetArr = [];
    getLayuiTreeCheckedIdBranch(targetArr, treeData);
    return targetArr;
}

function getLayuiTreeCheckedIdBranch(targetArr, treeData) {
    for (data of treeData) {
        targetArr.push(data.id);
        if (data.children !== null && data.children.length > 0) {
            getLayuiTreeCheckedIdBranch(targetArr, data.children);
        }
    }
}


function getLayuiDateResultFlatMap(layuiData) {
    let permMenuCacheMap = new Map();
    getLayuiDataResultFlatMapBranch(permMenuCacheMap, layuiData);
    return permMenuCacheMap;
}

function getLayuiDataResultFlatMapBranch(permMenuCacheMap, data) {
    for (item of data) {
        if (item.id !== null) {
            permMenuCacheMap.set(item.id + "", item);
        }
        if (item.children !== null && item.children.length > 0) {
            getLayuiDataResultFlatMapBranch(permMenuCacheMap, item.children)
        }
    }
}