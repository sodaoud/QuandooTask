package com.quandoo.sodaoud.app.viewmodel;

import com.quandoo.sodaoud.app.api.QuandooApiClient;
import com.quandoo.sodaoud.app.model.Table;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by sofiane on 10/26/16.
 */

public class TablesViewModel {

    private LoadTablesListener listener;
    private List<Table> tables;
    private Subscription subscription;


    public TablesViewModel() {
        init();
    }

    public void setListener(LoadTablesListener listener) {
        this.listener = listener;
    }

    private void init() {
        final Realm realm = Realm.getDefaultInstance();
        this.tables = realm.where(Table.class).findAll();
        ((RealmResults<Table>) tables).addChangeListener(new RealmChangeListener<RealmResults<Table>>() {
            @Override
            public void onChange(RealmResults<Table> results) {
                if (listener != null)
                    listener.onDataChanged();
            }
        });
        if (tables.isEmpty()) {
            loadTables();
        }
    }


    public void loadTables() {
        subscription = QuandooApiClient.Factory.createApiClient().getTables()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<Boolean>>() {
                    @Override
                    public void call(List<Boolean> availabilities) {
                        Realm realm = Realm.getDefaultInstance();
                        // TODO replace with stream
                        List<Table> tableList = new ArrayList<>();
                        for (int i = 0; i < availabilities.size(); i++) {
                            Table t = new Table();
                            t.setAvailable(availabilities.get(i));
                            t.setNumber(i);
                            tableList.add(t);
                        }
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(tableList);
                        realm.commitTransaction();
                    }
                });
    }

    public List<Table> getTables() {
        return tables;
    }

    public static interface LoadTablesListener {
        void onDataChanged();
    }

    public void onDestroy() {
        if (subscription != null)
            subscription.unsubscribe();
    }
}
