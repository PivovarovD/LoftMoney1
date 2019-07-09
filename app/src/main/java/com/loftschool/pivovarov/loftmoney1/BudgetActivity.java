package com.loftschool.pivovarov.loftmoney1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

public class BudgetActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1001;
    private ItemsAdapter mItemsAdapter;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        mItemsAdapter = new ItemsAdapter();

        recyclerView.setAdapter(mItemsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        mItemsAdapter.addItem(new Item("Молоко", 70));
        mItemsAdapter.addItem(new Item("Зубная щетка", 70));
        mItemsAdapter.addItem(new Item("Сковородка с антипригарным покрытием", 1670));

        Button openAddScreenButton = findViewById(R.id.open_add_screen);
        openAddScreenButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(BudgetActivity.this, AddItemActivity.class), REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            Item item = new Item(data.getStringExtra("name"), Integer.parseInt(data.getStringExtra("price")));
            mItemsAdapter.addItem(item);
        }
    }
}

