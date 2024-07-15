package o7410.recipebooklib.mixin;

import net.minecraft.recipe.book.RecipeBookCategory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(RecipeBookCategory.class)
public interface RecipeBookCategoryInvoker {

    @Invoker("<init>")
    static RecipeBookCategory invokeInit(String name, int ordinal) {
        throw new IllegalStateException();
    }

    @Mutable
    @Accessor("field_25767")
    static void setValues(RecipeBookCategory[] values) {
        throw new IllegalStateException();
    }
}
