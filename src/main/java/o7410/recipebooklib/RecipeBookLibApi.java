package o7410.recipebooklib;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.recipebook.RecipeBookGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.book.RecipeBookCategory;
import o7410.recipebooklib.mixin.RecipeBookCategoryInvoker;
import o7410.recipebooklib.mixin.RecipeBookGroupInvoker;
import o7410.recipebooklib.mixin.RecipeBookOptionsInvoker;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class RecipeBookLibApi {
    public static final Map<RecipeBookCategory, List<RecipeBookGroup>> CATEGORY_TO_GROUPS = new HashMap<>();

    /**
     * Registers a new recipe book category to use in a custom crafting screen handler
     * @param name The name of the new category in UPPER_SNAKE_CASE
     * @param groups A list of groups to show in the recipe book for this category
     * @return The registered category
     */
    public static RecipeBookCategory registerCategory(String name, List<RecipeBookGroup> groups) {
        int ordinal = RecipeBookCategory.values().length;
        RecipeBookCategory recipeBookCategory = RecipeBookCategoryInvoker.invokeInit(name, ordinal);
        RecipeBookCategory[] newValues = Arrays.copyOf(RecipeBookCategory.values(), RecipeBookCategory.values().length + 1);
        newValues[recipeBookCategory.ordinal()] = recipeBookCategory;
        RecipeBookCategoryInvoker.setValues(newValues);

        CATEGORY_TO_GROUPS.put(recipeBookCategory, groups);

        RecipeBookOptionsInvoker.setCategoryOptionNames(ImmutableMap.<RecipeBookCategory, Pair<String, String>>builder()
                .putAll(RecipeBookOptionsInvoker.getCategoryOptionNames())
                .put(recipeBookCategory, Pair.of("is" + name + "GuiOpen", "is" + name + "FilteringCraftable"))
                .build());
        return recipeBookCategory;
    }

    /**
     * Registers a new recipe book group to use in {@link #registerCategory}
     * <p>This method registers the group with 1 icon, for 2 icons, see {@link #registerGroup(String, ItemStack, ItemStack)}
     * @param name The name of the new group in UPPER_SNAKE_CASE
     * @param icon The itemstack to use as icon
     * @return The registered group
     * @see #registerGroup(String, ItemStack, ItemStack)
     */
    public static RecipeBookGroup registerGroup(String name, ItemStack icon) {
        return registerGroup(name, new ItemStack[] { icon });
    }

    /**
     * Registers a new recipe book group to use in {@link #registerCategory}
     * <p>This method registers the group with 2 icons, for 1 icon, see {@link #registerGroup(String, ItemStack)}
     * @param name The name of the new group in UPPER_SNAKE_CASE
     * @param icon1 The left itemstack to use as icon
     * @param icon2 The right itemstack to use as icon
     * @return The registered group
     * @see #registerGroup(String, ItemStack)
     */
    public static RecipeBookGroup registerGroup(String name, ItemStack icon1, ItemStack icon2) {
        return registerGroup(name, new ItemStack[] { icon1, icon2 });
    }

    private static RecipeBookGroup registerGroup(String name, ItemStack... icons) {
        int ordinal = RecipeBookGroup.values().length;
        RecipeBookGroup recipeBookGroup = RecipeBookGroupInvoker.invokeInit(name, ordinal, icons);
        RecipeBookGroup[] newValues = Arrays.copyOf(RecipeBookGroup.values(), RecipeBookGroup.values().length + 1);
        newValues[recipeBookGroup.ordinal()] = recipeBookGroup;
        RecipeBookGroupInvoker.setValues(newValues);
        return recipeBookGroup;
    }

    /**
     * Registers the groups to search in for a search group
     * @param searchGroup The group that will have a search bar
     * @param groupsToSearchIn The groups this group will give results from
     */
    public static void registerSearchGroup(RecipeBookGroup searchGroup, List<RecipeBookGroup> groupsToSearchIn) {
        RecipeBookGroupInvoker.setSearchMap(ImmutableMap.<RecipeBookGroup, List<RecipeBookGroup>>builder()
                .putAll(RecipeBookGroup.SEARCH_MAP)
                .put(searchGroup, groupsToSearchIn)
                .build());
    }
}
