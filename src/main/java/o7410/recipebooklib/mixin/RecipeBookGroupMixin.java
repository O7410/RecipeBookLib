package o7410.recipebooklib.mixin;

import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.recipe.book.RecipeBookCategory;
import o7410.recipebooklib.RecipeBookLibApi;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(RecipeBookGroup.class)
public class RecipeBookGroupMixin {

    @Inject(method = "getGroups", at = @At("HEAD"), cancellable = true)
    private static void setGroupsForCategory(RecipeBookCategory category, CallbackInfoReturnable<List<RecipeBookGroup>> cir) {
        if (RecipeBookLibApi.CATEGORY_TO_GROUPS.containsKey(category)) {
            cir.setReturnValue(RecipeBookLibApi.CATEGORY_TO_GROUPS.get(category));
        }
    }
}
