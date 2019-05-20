package com.sonbaty.foods.view.detail;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sonbaty.foods.R;
import com.sonbaty.foods.Utils;
import com.sonbaty.foods.model.Meals;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.sonbaty.foods.view.home.HomeActivity.EXTRA_DETAIL;

public class DetailActivity extends AppCompatActivity implements DetailView {

    @BindView(R.id.Detail_ImageView_MealThumb)
    ImageView DetailImageViewMealThumb;
    @BindView(R.id.Detail_Toolbar)
    Toolbar DetailToolbar;
    @BindView(R.id.Detail_Collapsing_Toolbar)
    CollapsingToolbarLayout DetailCollapsingToolbar;
    @BindView(R.id.Detail_AppBar)
    AppBarLayout DetailAppBar;
    @BindView(R.id.Detail_ProgressBar)
    ProgressBar DetailProgressBar;
    @BindView(R.id.Detail_TextView_TextCategory)
    TextView DetailTextViewTextCategory;
    @BindView(R.id.Detail_TextView_Category)
    TextView DetailTextViewCategory;
    @BindView(R.id.Detail_TextView_Instructions)
    TextView DetailTextViewInstructions;
    @BindView(R.id.Detail_TextView_Ingredient)
    TextView DetailTextViewIngredient;
    @BindView(R.id.Detail_TextView_Measure)
    TextView DetailTextViewMeasure;
    @BindView(R.id.Detail_TextView_Youtube)
    TextView DetailTextViewYoutube;
    @BindView(R.id.Detail_TextView_Source)
    TextView DetailTextViewSource;
    @BindView(R.id.Detail_TextView_TextCountry)
    TextView DetailTextViewTextCountry;
    @BindView(R.id.Detail_TextView_Country)
    TextView DetailTextViewCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        setupActionBar();

        Intent intent = getIntent();
        String mealName = (String) intent.getStringExtra(EXTRA_DETAIL);

        DetailPresenter detailPresenter = new DetailPresenter(this);
        detailPresenter.getMealById(mealName);
    }

    private void setupActionBar() {
        setSupportActionBar(DetailToolbar);
        DetailCollapsingToolbar.setContentScrimColor(getResources().getColor(R.color.colorWhite));
        DetailCollapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorPrimary));
        DetailCollapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorWhite));
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    void setupColorActionBarIcon(Drawable favoriteItemColor) {
        DetailAppBar.addOnOffsetChangedListener((appBarLayout, verticalOffset) -> {
            if ((DetailCollapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(DetailCollapsingToolbar))) {
                if (DetailToolbar.getNavigationIcon() != null)
                    DetailToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorPrimary),
                        PorterDuff.Mode.SRC_ATOP);

            } else {
                if (DetailToolbar.getNavigationIcon() != null)
                    DetailToolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.colorWhite), PorterDuff.Mode.SRC_ATOP);
                favoriteItemColor.mutate().setColorFilter(getResources().getColor(R.color.colorWhite),
                        PorterDuff.Mode.SRC_ATOP);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        MenuItem favoriteItem = menu.findItem(R.id.favorite);
        Drawable favoriteItemColor = favoriteItem.getIcon();
        setupColorActionBarIcon(favoriteItemColor);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {
        DetailProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        DetailProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setMeal(Meals.Meal meal) {
        Picasso.get().load(meal.getStrMealThumb()).into(DetailImageViewMealThumb);
        DetailCollapsingToolbar.setTitle(meal.getStrMeal());
        DetailTextViewCategory.setText(meal.getStrCategory());
        DetailTextViewCountry.setText(meal.getStrArea());
        DetailTextViewInstructions.setText(meal.getStrInstructions());
        setupActionBar();

        //===

        if (!meal.getStrIngredient1().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient1());
        }
        if (!meal.getStrIngredient2().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient2());
        }
        if (!meal.getStrIngredient3().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient3());
        }
        if (!meal.getStrIngredient4().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient4());
        }
        if (!meal.getStrIngredient5().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient5());
        }
        if (!meal.getStrIngredient6().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient6());
        }
        if (!meal.getStrIngredient7().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient7());
        }
        if (!meal.getStrIngredient8().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient8());
        }
        if (!meal.getStrIngredient9().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient9());
        }
        if (!meal.getStrIngredient10().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient10());
        }
        if (!meal.getStrIngredient11().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient11());
        }
        if (!meal.getStrIngredient12().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient12());
        }
        if (!meal.getStrIngredient13().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient13());
        }
        if (!meal.getStrIngredient14().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient14());
        }
        if (!meal.getStrIngredient15().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient15());
        }
        if (!meal.getStrIngredient16().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient16());
        }
        if (!meal.getStrIngredient17().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient17());
        }
        if (!meal.getStrIngredient18().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient18());
        }
        if (!meal.getStrIngredient19().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient19());
        }
        if (!meal.getStrIngredient20().isEmpty()) {
            DetailTextViewIngredient.append("\n \u2022 " + meal.getStrIngredient20());
        }

        if (!meal.getStrMeasure1().isEmpty() && !Character.isWhitespace(meal.getStrMeasure1().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure1());
        }
        if (!meal.getStrMeasure2().isEmpty() && !Character.isWhitespace(meal.getStrMeasure2().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure2());
        }
        if (!meal.getStrMeasure3().isEmpty() && !Character.isWhitespace(meal.getStrMeasure3().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure3());
        }
        if (!meal.getStrMeasure4().isEmpty() && !Character.isWhitespace(meal.getStrMeasure4().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure4());
        }
        if (!meal.getStrMeasure5().isEmpty() && !Character.isWhitespace(meal.getStrMeasure5().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure5());
        }
        if (!meal.getStrMeasure6().isEmpty() && !Character.isWhitespace(meal.getStrMeasure6().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure6());
        }
        if (!meal.getStrMeasure7().isEmpty() && !Character.isWhitespace(meal.getStrMeasure7().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure7());
        }
        if (!meal.getStrMeasure8().isEmpty() && !Character.isWhitespace(meal.getStrMeasure8().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure8());
        }
        if (!meal.getStrMeasure9().isEmpty() && !Character.isWhitespace(meal.getStrMeasure9().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure9());
        }
        if (!meal.getStrMeasure10().isEmpty() && !Character.isWhitespace(meal.getStrMeasure10().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure10());
        }
        if (!meal.getStrMeasure11().isEmpty() && !Character.isWhitespace(meal.getStrMeasure11().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure11());
        }
        if (!meal.getStrMeasure12().isEmpty() && !Character.isWhitespace(meal.getStrMeasure12().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure12());
        }
        if (!meal.getStrMeasure13().isEmpty() && !Character.isWhitespace(meal.getStrMeasure13().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure13());
        }
        if (!meal.getStrMeasure14().isEmpty() && !Character.isWhitespace(meal.getStrMeasure14().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure14());
        }
        if (!meal.getStrMeasure15().isEmpty() && !Character.isWhitespace(meal.getStrMeasure15().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure15());
        }
        if (!meal.getStrMeasure16().isEmpty() && !Character.isWhitespace(meal.getStrMeasure16().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure16());
        }
        if (!meal.getStrMeasure17().isEmpty() && !Character.isWhitespace(meal.getStrMeasure17().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure17());
        }
        if (!meal.getStrMeasure18().isEmpty() && !Character.isWhitespace(meal.getStrMeasure18().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure18());
        }
        if (!meal.getStrMeasure19().isEmpty() && !Character.isWhitespace(meal.getStrMeasure19().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure19());
        }
        if (!meal.getStrMeasure20().isEmpty() && !Character.isWhitespace(meal.getStrMeasure20().charAt(0))) {
            DetailTextViewMeasure.append("\n : " + meal.getStrMeasure20());
        }


        DetailTextViewYoutube.setOnClickListener(v -> {
            Intent intentYoutube = new Intent(Intent.ACTION_VIEW);
            intentYoutube.setData(Uri.parse(meal.getStrYoutube()));
            startActivity(intentYoutube);
        });

        DetailTextViewSource.setOnClickListener(v -> {
            Intent intentSource = new Intent(Intent.ACTION_VIEW);
            intentSource.setData(Uri.parse(meal.getStrSource()));
            startActivity(intentSource);
        });


    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(this, "Error", message);
    }

}
