package com.sonbaty.foods.view.category;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.sonbaty.foods.R;
import com.sonbaty.foods.Utils;
import com.sonbaty.foods.adapter.RecyclerViewMealByCategory;
import com.sonbaty.foods.model.Meals;
import com.sonbaty.foods.view.detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.sonbaty.foods.view.home.HomeActivity.EXTRA_DETAIL;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment implements CategoryView {

    AlertDialog.Builder descDialog;
    @BindView(R.id.Category_Fragment_ImageView_CategoryBg)
    ImageView CategoryFragmentImageViewCategoryBg;
    @BindView(R.id.Category_Fragment_ImageView_Category)
    ImageView CategoryFragmentImageViewCategory;
    @BindView(R.id.Category_Fragment_TextView_Category)
    TextView CategoryFragmentTextViewCategory;
    @BindView(R.id.Category_Fragment_CardView_Category)
    CardView CategoryFragmentCardViewCategory;
    @BindView(R.id.Category_Fragment_RecyclerView)
    RecyclerView CategoryFragmentRecyclerView;
    @BindView(R.id.Category_Fragment_ProgressBar)
    ProgressBar CategoryFragmentProgressBar;
    Unbinder unbinder;
    private RecyclerViewMealByCategory mealByCategoryAdapter;
    private GridLayoutManager gridLayoutManager;

    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        unbinder = ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            CategoryFragmentTextViewCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Picasso.get().load(getArguments().getString("EXTRA_DATA_IMAGE")).into(CategoryFragmentImageViewCategory);
            Picasso.get().load(getArguments().getString("EXTRA_DATA_IMAGE")).into(CategoryFragmentImageViewCategoryBg);
            descDialog = new AlertDialog
                    .Builder(getActivity())
                    .setTitle(getArguments().getString("EXTRA_DATA_NAME"))
                    .setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            CategoryPresenter categoryPresenter = new CategoryPresenter(this);
            categoryPresenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }
    }

    @Override
    public void showLoading() {
        CategoryFragmentProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        CategoryFragmentProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        mealByCategoryAdapter = new RecyclerViewMealByCategory(getActivity(), meals);
        gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        CategoryFragmentRecyclerView.setLayoutManager(gridLayoutManager);
        CategoryFragmentRecyclerView.setClipToPadding(false);
        CategoryFragmentRecyclerView.setAdapter(mealByCategoryAdapter);
        mealByCategoryAdapter.notifyDataSetChanged();

        mealByCategoryAdapter.setOnItemClickListener((view, position) -> {
            TextView mealName = (TextView) view.findViewById(R.id.mealName);
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
            startActivity(intent);
        });

    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error", message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.Category_Fragment_CardView_Category)
    public void onViewClicked() {
        descDialog.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        descDialog.show();
    }
}
