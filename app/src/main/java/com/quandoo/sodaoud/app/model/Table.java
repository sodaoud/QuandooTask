package com.quandoo.sodaoud.app.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sofiane on 10/26/16.
 */

public class Table extends RealmObject {

    @PrimaryKey
    Integer number;
    Boolean available;

    public Table() {
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
