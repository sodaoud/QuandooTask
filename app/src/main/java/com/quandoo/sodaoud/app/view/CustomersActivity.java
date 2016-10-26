package com.quandoo.sodaoud.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.quandoo.sodaoud.app.R;
import com.quandoo.sodaoud.app.databinding.ActivityCustomersBinding;
import com.quandoo.sodaoud.app.viewmodel.CustomerListViewModel;


public class CustomersActivity extends AppCompatActivity {

    CustomerListViewModel viewModel;

    private ActivityCustomersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new CustomerListViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_customers);
        binding.customerRecyclerView.setAdapter(new CustomerAdapter(viewModel));
        binding.customerRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.loadCustomers();
    }

}
