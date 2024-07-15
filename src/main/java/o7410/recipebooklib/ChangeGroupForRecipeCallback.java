package o7410.recipebooklib;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.RecipeEntry;

import java.util.Optional;

/**
 * Callback for getting the recipe book group for a recipe entry.
 * <p>This is triggered only on the client thread, so it should be called in {@code onInitializeClient}
 * <p>Return {@code null} to not change, return a non-null value to change the group for a recipe
 */
public interface ChangeGroupForRecipeCallback {
    Event<ChangeGroupForRecipeCallback> EVENT = EventFactory.createArrayBacked(ChangeGroupForRecipeCallback.class,
            listeners -> recipeEntry -> {
                for (ChangeGroupForRecipeCallback event : listeners) {
                    /*RecipeBookGroup result = event.getGroupForRecipe(recipeEntry);

                    if (result != null) {
                        return result;
                    }*/

                    Optional<RecipeBookGroup> result = event.getGroupForRecipe(recipeEntry);

                    if (result.isPresent()) {
                        return result;
                    }
                }

                return Optional.empty();
            }
    );

//    @Nullable
//    RecipeBookGroup getGroupForRecipe(RecipeEntry<?> recipeEntry);

    Optional<RecipeBookGroup> getGroupForRecipe(RecipeEntry<?> recipeEntry);
}
