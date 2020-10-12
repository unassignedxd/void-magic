package com.unassigned.voidmagic.api.recipe;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class RecipeVoidInfusion {
    private final ResourceLocation id;
    private final Ingredient input;
    private final Ingredient[] modifiers;
    private final int voidUsage;
    private final Ingredient output;

    public RecipeVoidInfusion(ResourceLocation id, Ingredient input, Ingredient[]  modifiers, int voidUsage, Ingredient output) {
        this.id = id;
        this.input = input;
        this.modifiers = modifiers;
        this.voidUsage = voidUsage;
        this.output = output;
    }

    public boolean matches(ItemStack in, ItemStack... givenMods) {
        if(input.test(in)) {
            List<Ingredient> ing = Arrays.asList(modifiers);
            for(ItemStack given : givenMods) {
                if(given.isEmpty()) break;

                boolean matched = false;
                Iterator<Ingredient> iter = ing.iterator();
                while(iter.hasNext()) {
                    Ingredient mod = iter.next();
                    if(mod.test(given)) {
                        iter.remove();
                        matched=true;
                        break;
                    }
                }

                if(!matched) return false;
            }

            return ing.isEmpty();
        }
        return false;
    }

    public ResourceLocation getId() {
        return id;
    }

    public Ingredient getInput() {
        return input;
    }

    public Ingredient[]  getModifiers() {
        return modifiers;
    }

    public int getVoidUsage() {
        return voidUsage;
    }

    public Ingredient getOutput() {
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeVoidInfusion recipe = (RecipeVoidInfusion) o;
        return input.equals(recipe.input) &&
                Arrays.equals(modifiers, recipe.modifiers) &&
                output.equals(recipe.output);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(input, output);
        result = 31 * result + Arrays.hashCode(modifiers);
        return result;
    }
}
