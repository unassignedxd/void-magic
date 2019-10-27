package com.unassigned.voidmagic.common.events;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.blocks.ModBlocks;
import com.unassigned.voidmagic.common.blocks.TestBlock;
import com.unassigned.voidmagic.common.items.TestItem;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = "voidmagic", bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonEvents {

    @SubscribeEvent
    public static void onBlockRegistry(final RegistryEvent.Register<Block> register) {
        register.getRegistry().register(new TestBlock());
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> register) {
        Item.Properties properties = new Item.Properties().group(VoidMagic.setup.itemGroup);

        //items
        register.getRegistry().register(new TestItem());

        //item blocks
        register.getRegistry().register(new BlockItem(ModBlocks.testBlock, properties).setRegistryName("testblock"));
    }
}
