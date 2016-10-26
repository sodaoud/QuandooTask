package com.quandoo.sodaoud.app.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quandoo.sodaoud.app.R;
import com.quandoo.sodaoud.app.databinding.CustomerItemBinding;
import com.quandoo.sodaoud.app.model.Customer;
import com.quandoo.sodaoud.app.viewmodel.CustomerListViewModel;
import com.quandoo.sodaoud.app.viewmodel.CustomerViewModel;

/**
 * Created by sofiane on 10/25/16.
 */

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CostumerViewHolder> implements CustomerListViewModel.CustomerListener {

    CustomerListViewModel viewModel;


    public CustomerAdapter(CustomerListViewModel viewModel) {
        this.viewModel = viewModel;
        viewModel.setListener(this);
    }


    @Override
    public CostumerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CustomerItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.customer_item,
                parent,
                false);
        return new CostumerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(CostumerViewHolder holder, int position) {
        holder.bindCustomer(viewModel.getCustomers().get(position));
    }

    @Override
    public int getItemCount() {
        return viewModel.getCustomers().size();
    }

    @Override
    public void onDataChanged() {
        notifyDataSetChanged();
    }

    public static class CostumerViewHolder extends RecyclerView.ViewHolder {
        final CustomerItemBinding binding;

        public CostumerViewHolder(CustomerItemBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }

        void bindCustomer(Customer customer) {
            if (binding.getViewModel() == null) {
                binding.setViewModel(new CustomerViewModel());
            }
            binding.getViewModel().setCustomer(customer);

        }
    }
}
