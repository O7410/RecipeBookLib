# A library for registering new categories and groups to the recipe book

Why does this exist?

Without this library you need multiple mixins, this library handles that for you!

More than 1 mod can use this library at the same time!

Currently, I am not aware of any other libraries that do this

### **Supported platforms**
Currently, this library only supports Fabric 1.20.4, 1.20.6, and 1.21
If you want it for a different version, [create an issue on the GitHub page](https://github.com/O7410/RecipeBookLib/issues/new)

## For players/server owners:
### Install this mod and fabric api 

## For devs:
### Example Usage:

in `onInitializeClient`: 
```java
ChangeGroupForRecipeCallback.EVENT.register(recipeEntry -> {
    if (recipeEntry.value() instanceof CustomRecipe customRecipe) {
        if (customRecipe.getResult(null).isIn(CustomItemTags.FRUIT)) {
            return Optional.of(CustomRecipeBookAdditions.FRUIT_GROUP);
        }
        if (customRecipe.getResult(null).isIn(CustomItemTags.MEAT)) {
            return Optional.of(CustomRecipeBookAdditions.MEAT_GROUP);
        }
        if (customRecipe.getResult(null).isIn(CustomItemTags.METALS) || customRecipe.getResult(null).isIn(CustomItemTags.GEMS)) {
            return Optional.of(CustomRecipeBookAdditions.METALS_AND_GEMS_GROUP);
        }
    }
    return Optional.empty();
});
```
Then, it is recommended to create a new class to store all of the groups and categories, like this:
```java
public class CustomRecipeBookAdditions {
    public static final RecipeBookGroup CUSTOM_SEARCH_GROUP = RecipeBookLibApi.registerGroup("CUSTOM_SEARCH_GROUP", new ItemStack(Items.COMPASS));
    public static final RecipeBookGroup FRUIT_GROUP = RecipeBookLibApi.registerGroup("FRUIT_GROUP", new ItemStack(Items.APPLE));
    public static final RecipeBookGroup MEAT_GROUP = RecipeBookLibApi.registerGroup("MEAT_GROUP", new ItemStack(Items.COOKED_BEEF));
    public static final RecipeBookGroup METALS_AND_GEMS_GROUP = RecipeBookLibApi.registerGroup("METALS_AND_GEMS_GROUP", new ItemStack(Items.IRON_INGOT), new ItemStack(Items.EMERALD));

    public static final List<RecipeBookGroup> CUSTOM_CRAFTING_GROUPS = ImmutableList.of(
            CUSTOM_SEARCH_GROUP,
            FRUIT_GROUP,
            MEAT_GROUP,
            METALS_AND_GEMS_GROUP
    );

    public static final RecipeBookCategory CUSTOM_CRAFTING = RecipeBookLibApi.registerCategory("CUSTOM_CRAFTING", CUSTOM_CRAFTING_GROUPS);
    static {
        RecipeBookLibApi.registerSearchGroup(CUSTOM_SEARCH_GROUP, List.of(
                FRUIT_GROUP,
                MEAT_GROUP,
                METALS_AND_GEMS_GROUP
        ));
    }

    public static void registerRecipeBookAdditions() {
        YourMod.LOGGER.info("Registering recipe book additions for " + YourMod.MOD_ID);
    }
}
```
and don't forget to "wake up" the class by calling `registerRecipeBookAdditions` in `onInitialize`.

Then, in your custom screen handler that extends `AbstractRecipeScreenHandler`, you can use the custom category like this:
```java
@Override
public RecipeBookCategory getCategory() {
    return CustomRecipeBookAdditions.CUSTOM_CRAFTING;
}
```
<p xmlns:cc="http://creativecommons.org/ns#" xmlns:dct="http://purl.org/dc/terms/"><a property="dct:title" rel="cc:attributionURL" href="https://github.com/O7410/RecipeBookLib">RecipeBookLib</a> by <a rel="cc:attributionURL dct:creator" property="cc:attributionName" href="https://github.com/O7410">7410</a> is licensed under <a href="https://creativecommons.org/licenses/by-nc/4.0/?ref=chooser-v1" target="_blank" rel="license noopener noreferrer" style="display:inline-block;">CC BY-NC 4.0<img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/cc.svg?ref=chooser-v1" alt=""><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/by.svg?ref=chooser-v1" alt=""><img style="height:22px!important;margin-left:3px;vertical-align:text-bottom;" src="https://mirrors.creativecommons.org/presskit/icons/nc.svg?ref=chooser-v1" alt=""></a></p> 