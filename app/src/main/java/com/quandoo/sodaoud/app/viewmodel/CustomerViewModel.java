package com.quandoo.sodaoud.app.viewmodel;

import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.quandoo.sodaoud.app.model.Customer;
import com.quandoo.sodaoud.app.view.TableSelectionActivity;

/**
 * Created by sofiane on 10/25/16.
 */

public class CustomerViewModel extends BaseObservable {
    private Customer customer;

    public CustomerViewModel() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        notifyChange();
    }


    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), TableSelectionActivity.class);
        intent.putExtra(TableSelectionActivity.CUSTOMER_EXTRA, customer);
        view.getContext().startActivity(intent);
    }
}
