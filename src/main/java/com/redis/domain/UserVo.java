package com.redis.domain;

/**
 * @ClassName UserVo
 * @Author Jumy
 * @Description //TODO
 * @Date 2019/6/5 10:37
 * @Version 1.0
 **/
public class UserVo {
    private String name;//姓名
    private Integer age;//年龄
    private String address;//地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
