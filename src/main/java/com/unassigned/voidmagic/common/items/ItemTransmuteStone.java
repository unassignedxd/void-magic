package com.unassigned.voidmagic.common.items;

import com.unassigned.voidmagic.VoidMagic;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemTransmuteStone extends Item {

    public ItemTransmuteStone() {
        super(new Item.Properties().maxStackSize(1));
    }

    @Override
    public void addInformation(ItemStack item, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        tooltip.add(new StringTextComponent("Allows for transfer of Void Energy between two entities."));
    }
}
