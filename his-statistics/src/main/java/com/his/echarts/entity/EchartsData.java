package com.his.echarts.entity;


import java.io.Serializable;
import java.util.Objects;

/**
 * Description: Echarts的实体类。 用与展示数据。  用于echarts options:{series:[data:[ EchartsClient, EchartsClient ]]}
 * Date: 20-12-24
 *
 * @author yh
 */
public class EchartsData implements Serializable {
    private static final long serialVersionUID = -8775616691560251183L;
    private String name;
    private Integer value;

    public EchartsData() {
        value = 0;
    }

    public EchartsData(String name, Integer value) {
        this.name = name;
        this.value = value;
    }

    public void valuePlusOne() {
        this.value++;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EchartsData that = (EchartsData) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "EchartsData{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}