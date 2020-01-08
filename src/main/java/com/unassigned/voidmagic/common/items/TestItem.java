package com.unassigned.voidmagic.common.items;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.capability.playervoid.CapabilityPlayerVoid;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class TestItem extends Item {

    public TestItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(VoidMagic.setup.itemGroup));
        setRegistryName("testitem");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        CapabilityPlayerVoid.getPlayerVoid(player).ifPresent(cap -> {
            if(!world.isRemote) cap.addVoid(1500);
            player.sendMessage(new StringTextComponent("Client Side?: " + world.isRemote + " | Player's Stored Void is now: " + cap.getVoidStored()));
        });
        return new ActionResult<>(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
}
