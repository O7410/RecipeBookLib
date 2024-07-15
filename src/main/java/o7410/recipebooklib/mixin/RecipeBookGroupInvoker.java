package o7410.recipebooklib.mixin;

import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.List;
import java.util.Map;

@Mixin(RecipeBookGroup.class)
public interface RecipeBookGroupInvoker {

    @Invoker("<init>")
    static RecipeBookGroup invokeInit(String name, int ordinal, ItemStack... icons) {
        throw new IllegalStateException();
    }

    @Mutable
    @Accessor("field_1805")
    static void setValues(RecipeBookGroup[] values) {
        throw new IllegalStateException();
    }

    @Mutable
    @Accessor("SEARCH_MAP")
    static void setSearchMap(Map<RecipeBookGroup, List<RecipeBookGroup>> searchMap) {
        throw new IllegalStateException();
    }
}
