package com.quandoo.sodaoud.app.viewmodel;

import android.databinding.BaseObservable;
import android.view.View;

import com.quandoo.sodaoud.app.model.Table;
import com.quandoo.sodaoud.app.view.TablesAdapter;

import io.realm.Realm;

/**
 * Created by sofiane on 10/26/16.
 */

public class TableViewModel extends BaseObservable {

    private Table table;
    private TablesAdapter.SelectionListener selectionListener;

    public void setTable(Table table) {
        this.table = table;
        notifyChange();
    }

    public Table getTable() {
        return table;
    }

    public String getNumberString() {
        return String.format("# %s",table.getNumber());
    }

    public void onClick(View view) {
        if (table.getAvailable()) {
            Realm realm = Realm.getDefaultInstance();
            realm.beginTransaction();
            table.setAvailable(false);
            realm.copyToRealmOrUpdate(table);
            realm.commitTransaction();
            selectionListener.onTableSelected(table);
        }
    }

    public void setSelectionListener(TablesAdapter.SelectionListener selectionListener) {
        this.selectionListener = selectionListener;
    }
}
