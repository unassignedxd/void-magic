package com.unassigned.voidmagic.datagen;

import com.unassigned.voidmagic.common.blocks.ModBlocks;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class DataRecipes extends RecipeProvider {

    public DataRecipes(DataGenerator genIn) { super(genIn); }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.coalGenerator)
                .patternLine("xxx")
                .patternLine("x2x")
                .patternLine("xxx")
                .key('x', Blocks.COBBLESTONE)
                .key('2', Items.COAL)
                .setGroup("voidmagic")
                .addCriterion("cobblestone", InventoryChangeTrigger.Instance.forItems(Blocks.COBBLESTONE))
                .build(consumer);
    }
}
