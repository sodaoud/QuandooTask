package com.quandoo.sodaoud.app.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.quandoo.sodaoud.app.R;
import com.quandoo.sodaoud.app.databinding.ActivityTableSelectionBinding;
import com.quandoo.sodaoud.app.model.Table;
import com.quandoo.sodaoud.app.viewmodel.TablesViewModel;

public class TableSelectionActivity extends AppCompatActivity implements TablesAdapter.SelectionListener {

    public static final String CUSTOMER_EXTRA = "CUSTOMER";

    ActivityTableSelectionBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TablesViewModel viewModel = new TablesViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_table_selection);
        binding.recyclerView.setAdapter(new TablesAdapter(viewModel, this));
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 3);
        binding.recyclerView.setLayoutManager(manager);
    }

    @Override
    public void onTableSelected(Table table) {
        Toast.makeText(this, String.format("Table N° %s",table.getNumber()), Toast.LENGTH_LONG).show();
        finish();
    }
}
