package com.twisstosin.udacitybaking.Presenter;

import android.support.annotation.StringRes;

import com.twisstosin.udacitybaking.DataModel.Recipe;

import java.util.ArrayList;

public interface RecipeListPresenterViewContract {
    interface View {

        void updateAdapter(ArrayList<Recipe> recipeList);

        void displaySnackbarMessage(@StringRes int stringResId);

        boolean isActive();

    }

    interface Presenter {

        void fetchRecipes();

        void recipeClicked(Recipe recipe, android.view.View view);

        void viewDestroy();

    }
}
