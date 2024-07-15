package o7410.recipebooklib.mixin;

import net.minecraft.client.recipebook.ClientRecipeBook;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.RecipeEntry;
import o7410.recipebooklib.ChangeGroupForRecipeCallback;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(ClientRecipeBook.class)
public class ClientRecipeBookMixin {

    @Inject(method = "getGroupForRecipe", at = @At("HEAD"), cancellable = true)
    private static void useCustomRecipeBookGroupForDiamondRecipes(RecipeEntry<?> recipe, CallbackInfoReturnable<RecipeBookGroup> cir) {
        /*RecipeBookGroup changedGroup = ChangeGroupForRecipeCallback.EVENT.invoker().getGroupForRecipe(recipe);
        if (changedGroup != null) {
            cir.setReturnValue(changedGroup);
        }*/
        Optional<RecipeBookGroup> changedGroup = ChangeGroupForRecipeCallback.EVENT.invoker().getGroupForRecipe(recipe);
        changedGroup.ifPresent(cir::setReturnValue);
    }
}
