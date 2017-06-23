package com.twisstosin.udacitybaking.Activities;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.twisstosin.udacitybaking.DataModel.Recipe;
import com.twisstosin.udacitybaking.Fragments.RecipeListFragment;
import com.twisstosin.udacitybaking.Presenter.RecipeListPresenter;
import com.twisstosin.udacitybaking.Widgets.RecipeWidgetService;


public class MainActivity extends BaseActivity implements RecipeListPresenter.Callbacks {
    @Override
    protected Fragment createFragment() {
        return new RecipeListFragment();
    }

    @Override
    public void recipeSelected(Recipe recipe) {
        Intent intent = RecipeDetailActivity.newIntent(this, recipe);
        RecipeWidgetService.startActionUpdateRecipeWidgets(this, recipe);
        startActivity(intent);
    }
}
