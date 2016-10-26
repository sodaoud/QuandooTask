package com.quandoo.sodaoud.app.job;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Build;

import com.quandoo.sodaoud.app.api.QuandooApiClient;
import com.quandoo.sodaoud.app.model.Customer;
import com.quandoo.sodaoud.app.model.Table;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by sofiane on 10/26/16.
 */

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class ReservationScheduler extends JobService {
    QuandooApiClient api;


    private Subscription resSubscription;
    private Subscription tableSubscription;

    @Override
    public void onCreate() {
        super.onCreate();
        api = QuandooApiClient.Factory.createApiClient();
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        resSubscription = api.getCustomers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<Customer>>() {
                    @Override
                    public void call(List<Customer> customers) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(customers);
                        realm.commitTransaction();
                    }
                });


        tableSubscription = api.getTables()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Action1<List<Boolean>>() {
                    @Override
                    public void call(List<Boolean> availabilities) {
                        Realm realm = Realm.getDefaultInstance();
                        // TODO replace with stream
                        realm.where(Table.class).findAll().deleteAllFromRealm();
                        List<Table> tableList = new ArrayList<>();
                        for (int i=0; i<availabilities.size();i++) {
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
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        resSubscription.unsubscribe();
        tableSubscription.unsubscribe();
        return true;
    }
}
