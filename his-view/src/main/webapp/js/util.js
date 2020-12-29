// 根据给定的数组去转换成一个map。 key = [key]   value = this
function getMapByArr(arr, key) {
    let map = new Map();
    for(let item of arr) {
        map.set(item[key] + "", item);
    }
    return map;
}

function getMapKeys(map) {
    let keys = [];
    map.forEach((value, key) => {
        keys.push(key);
    });
    return keys;
}

function getMapValues(map) {
    let values = [];
    map.forEach((value) => {
        values.push(value);
    });
    return values;
}
