package com.unassigned.voidmagic.common.events;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.blocks.ModBlocks;
import com.unassigned.voidmagic.common.blocks.TestBlock;
import com.unassigned.voidmagic.common.container.ContainerTestBlock;
import com.unassigned.voidmagic.common.items.TestItem;
import com.unassigned.voidmagic.common.tileentity.TileTestBlock;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.extensions.IForgeContainerType;
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

    @SubscribeEvent
    public static void onTileTypeRegistry(final RegistryEvent.Register<TileEntityType<?>> register) {
        register.getRegistry().register(TileEntityType.Builder.create(TileTestBlock::new, ModBlocks.testBlock).build(null).setRegistryName("testblock"));
    }

    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> register) {
        register.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            return new ContainerTestBlock(windowId, VoidMagic.proxy.getClientWorld(), pos, inv, VoidMagic.proxy.getClientPlayer()); //called on client
        })).setRegistryName("testblock"));
    }
}
