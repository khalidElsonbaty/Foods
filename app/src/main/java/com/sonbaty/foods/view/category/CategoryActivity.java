package com.sonbaty.foods.view.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.sonbaty.foods.R;
import com.sonbaty.foods.adapter.ViewPagerCategoryAdapter;
import com.sonbaty.foods.model.Categories;
import com.sonbaty.foods.view.home.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryActivity extends AppCompatActivity {

    @BindView(R.id.Category_ToolBar)
    Toolbar CategoryToolBar;
    @BindView(R.id.Category_TabLayout)
    TabLayout CategoryTabLayout;
    @BindView(R.id.Category_AppBar)
    AppBarLayout CategoryAppBar;
    @BindView(R.id.Category_ViewPager)
    ViewPager CategoryViewPager;
    private ViewPagerCategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        ButterKnife.bind(this);

        initActionBar();
        initIntent();
        //TODO 11. Declare fragment viewPager adapter
    }

    private void initIntent() {
        Intent intent = getIntent();
        List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);
        int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION,0);


        categoryAdapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(),categories);
        CategoryViewPager.setAdapter(categoryAdapter);
        CategoryTabLayout.setupWithViewPager(CategoryViewPager);
        CategoryViewPager.setCurrentItem(position,true);
        categoryAdapter.notifyDataSetChanged();
    }

    private void initActionBar() {
        setSupportActionBar(CategoryToolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
