package com.sonbaty.foods.view.detail;

import com.sonbaty.foods.model.Categories;
import com.sonbaty.foods.model.Meals;

import java.util.List;

public interface DetailView {
    void showLoading();
    void hideLoading();
    void setMeal(Meals.Meal meal);
    void onErrorLoading(String message);
}
