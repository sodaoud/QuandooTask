package com.quandoo.sodaoud.app.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.quandoo.sodaoud.app.R;
import com.quandoo.sodaoud.app.databinding.TableBinding;
import com.quandoo.sodaoud.app.model.Table;
import com.quandoo.sodaoud.app.viewmodel.TableViewModel;
import com.quandoo.sodaoud.app.viewmodel.TablesViewModel;

/**
 * Created by sofiane on 10/26/16.
 */

public class TablesAdapter extends RecyclerView.Adapter<TablesAdapter.TablesViewHolder> implements TablesViewModel.LoadTablesListener {
    TablesViewModel viewModel;
    SelectionListener listener;

    public TablesAdapter(TablesViewModel viewModel, SelectionListener listener) {
        this.viewModel = viewModel;
        this.listener = listener;
        viewModel.setListener(this);
    }


    @Override
    public TablesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TableBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.table,
                parent,
                false);
        return new TablesViewHolder(binding, listener);
    }

    @Override
    public void onBindViewHolder(TablesViewHolder holder, int position) {
        holder.bindTable(viewModel.getTables().get(position));
    }

    @Override
    public int getItemCount() {
        return viewModel.getTables().size();
    }

    @Override
    public void onDataChanged() {
        notifyDataSetChanged();
    }

    public static class TablesViewHolder extends RecyclerView.ViewHolder {
        final TableBinding binding;
        private final SelectionListener listener;

        public TablesViewHolder(TableBinding binding, SelectionListener listener) {
            super(binding.cardView);
            this.binding = binding;
            this.listener=listener;
        }

        void bindTable(Table table) {
            if (binding.getTable() == null) {
                binding.setTable(new TableViewModel());
            }
            binding.getTable().setTable(table);
            binding.getTable().setSelectionListener(listener);

        }
    }

    public static interface SelectionListener {
        void onTableSelected(Table table);
    }
}
