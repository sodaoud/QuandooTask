package com.quandoo.sodaoud.app.model;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by sofiane on 10/26/16.
 */

public class Tables extends RealmObject {

    @PrimaryKey
    int id;

    RealmList<Table> availabilities;

    public RealmList<Table> getAvailabilities() {
        return availabilities;
    }

    public void setAvailabilities(RealmList<Table> availabilities) {
        this.availabilities = availabilities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
