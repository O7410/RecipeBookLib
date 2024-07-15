package o7410.recipebooklib.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.recipe.book.RecipeBookOptions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;

@Mixin(RecipeBookOptions.class)
public interface RecipeBookOptionsInvoker {
    @Mutable
    @Accessor("CATEGORY_OPTION_NAMES")
    static void setCategoryOptionNames(Map<RecipeBookCategory, Pair<String, String>> searchMap) {
        throw new IllegalStateException();
    }

    @Mutable
    @Accessor("CATEGORY_OPTION_NAMES")
    static Map<RecipeBookCategory, Pair<String, String>> getCategoryOptionNames() {
        throw new IllegalStateException();
    }
}
