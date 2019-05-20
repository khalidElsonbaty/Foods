package com.sonbaty.foods.view.home;

import com.sonbaty.foods.model.Categories;
import com.sonbaty.foods.model.Meals;

import java.util.List;

public interface HomeView {

    void showLoading();
    void hideLoading();
    void setMeal(List<Meals.Meal> meal);
    void setCategory(List<Categories.Category> category);
    void onErrorLoading(String message);
}
