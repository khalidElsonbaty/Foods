package com.sonbaty.foods.view.home;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.sonbaty.foods.R;
import com.sonbaty.foods.Utils;
import com.sonbaty.foods.adapter.RecyclerViewHomeAdapter;
import com.sonbaty.foods.adapter.ViewPagerHeaderAdapter;
import com.sonbaty.foods.model.Categories;
import com.sonbaty.foods.model.Meals;
import com.sonbaty.foods.view.category.CategoryActivity;
import com.sonbaty.foods.view.detail.DetailActivity;

import java.io.Serializable;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements HomeView {
    @BindView(R.id.Home_ViewPager_Header)
    ViewPager HomeViewPagerHeader;
    @BindView(R.id.Home_RecyclerView_Category)
    RecyclerView HomeRecyclerViewCategory;

    HomePresenter presenter;
    private ViewPagerHeaderAdapter headerAdapter;
    private RecyclerViewHomeAdapter homeAdapter;
    private GridLayoutManager layoutManager;

    public static final String EXTRA_CATEGORY = "category";
    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_DETAIL = "detail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        presenter = new HomePresenter(this);
        presenter.getMeals();
        presenter.getCategories();
    }

    @Override
    public void showLoading() {
        findViewById(R.id.Home_ShimmerMeal).setVisibility(View.VISIBLE);
        findViewById(R.id.Home_ShimmerCategory).setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        findViewById(R.id.Home_ShimmerMeal).setVisibility(View.GONE);
        findViewById(R.id.Home_ShimmerCategory).setVisibility(View.GONE);
    }

    @Override
    public void setMeal(List<Meals.Meal> meal) {
        /*for (Meals.Meal mealResult : meal) {
            Log.w("meal name :", mealResult.getStrMeal());
        }Test*/
        headerAdapter = new ViewPagerHeaderAdapter(meal, this);
        HomeViewPagerHeader.setAdapter(headerAdapter);
        HomeViewPagerHeader.setPadding(20, 0, 150, 0);
        headerAdapter.notifyDataSetChanged();
        headerAdapter.setOnItemClickListener((v, position) -> {
            TextView mealName = (TextView) v.findViewById(R.id.mealName);
            Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL,mealName.getText().toString());
            startActivity(intent);
        });
    }

    @Override
    public void setCategory(List<Categories.Category> category) {
        homeAdapter = new RecyclerViewHomeAdapter(category, this);
        HomeRecyclerViewCategory.setAdapter(homeAdapter);
        layoutManager = new GridLayoutManager(this, 3, GridLayoutManager.VERTICAL, false);
        HomeRecyclerViewCategory.setLayoutManager(layoutManager);
        HomeRecyclerViewCategory.setNestedScrollingEnabled(true);
        homeAdapter.notifyDataSetChanged();
        homeAdapter.setOnItemClickListener((view, position) -> {
            Intent intent = new Intent(this, CategoryActivity.class);

            intent.putExtra(EXTRA_CATEGORY, (Serializable) category);
            intent.putExtra(EXTRA_POSITION, position);
            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error", message);
    }
}
