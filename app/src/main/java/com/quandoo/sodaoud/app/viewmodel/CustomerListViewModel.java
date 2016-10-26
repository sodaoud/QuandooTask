package com.quandoo.sodaoud.app.viewmodel;

import com.quandoo.sodaoud.app.api.QuandooApiClient;
import com.quandoo.sodaoud.app.model.Customer;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sofiane on 10/25/16.
 */

public class CustomerListViewModel {

    private CustomerListener listener;
    private List<Customer> customers;
    private Subscription subscription;

    public CustomerListViewModel(final CustomerListener listener) {
        this.listener = listener;
        init();
    }

    public CustomerListViewModel() {
        this(null);
    }

    public void setListener(CustomerListener listener) {
        this.listener = listener;
    }

    private void init() {
        final Realm realm = Realm.getDefaultInstance();
        this.customers = realm.where(Customer.class).findAll();
        ((RealmResults<Customer>) customers).addChangeListener(new RealmChangeListener<RealmResults<Customer>>() {
            @Override
            public void onChange(RealmResults<Customer> results) {
                if (listener != null)
                    listener.onDataChanged();
            }
        });
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void loadCustomers() {
        subscription = QuandooApiClient.Factory.createApiClient().getCustomers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Customer>>() {
                    @Override
                    public void onCompleted() {
                        if (listener != null)
                            listener.onDataChanged();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Customer> customers) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.copyToRealmOrUpdate(customers);
                        realm.commitTransaction();
                    }
                });
    }

    public static interface CustomerListener {
        void onDataChanged();
    }

    public void onDestroy() {
        if (subscription != null)
            subscription.unsubscribe();
    }

}
