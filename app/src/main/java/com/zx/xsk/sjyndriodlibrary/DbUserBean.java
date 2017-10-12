package com.zx.xsk.sjyndriodlibrary;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sjy on 2017/10/9.
 */

public class DbUserBean extends RealmObject {
    @PrimaryKey
    private long id;

    private String name;
    private int age;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
