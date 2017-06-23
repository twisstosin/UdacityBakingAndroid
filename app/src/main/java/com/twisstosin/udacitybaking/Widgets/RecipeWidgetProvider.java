package com.twisstosin.udacitybaking.Widgets;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.twisstosin.udacitybaking.Activities.RecipeDetailActivity;
import com.twisstosin.udacitybaking.DataModel.Ingredient;
import com.twisstosin.udacitybaking.DataModel.Recipe;
import com.twisstosin.udacitybaking.R;

public class RecipeWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                Recipe recipe, int appWidgetId) {

        Intent intent = RecipeDetailActivity.newIntent(context, recipe);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.removeAllViews(R.id.ll_recipe_widget_ingredient_list);
        views.setTextViewText(R.id.recipe_widget_title, recipe.getName());
        views.setOnClickPendingIntent(R.id.recipe_widget_holder, pendingIntent);

        for(Ingredient ingredient : recipe.getIngredients()) {
            RemoteViews rvIngredient = new RemoteViews(context.getPackageName(),
                    R.layout.recipe_widget_list_item);
            rvIngredient.setTextViewText(R.id.tv_recipe_widget_ingredient_item,
                    String.valueOf(ingredient.getQuantity()) +
                    String.valueOf(ingredient.getMeasure()) + " " + ingredient.getIngredient());
            views.addView(R.id.ll_recipe_widget_ingredient_list, rvIngredient);
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    public static void updateRecipeWidgets(Context context, AppWidgetManager appWidgetManager,
                                           Recipe recipe, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, recipe, appWidgetId);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {}

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {}

    @Override
    public void onEnabled(Context context) {}

    @Override
    public void onDisabled(Context context) {}

}