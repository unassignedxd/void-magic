package com.unassigned.voidmagic.common.events;

import com.unassigned.voidmagic.VoidMagic;
import com.unassigned.voidmagic.common.blocks.*;
import com.unassigned.voidmagic.common.container.ContainerCoalGenerator;
import com.unassigned.voidmagic.common.container.ContainerTestBlock;
import com.unassigned.voidmagic.common.items.ItemTransmuteStone;
import com.unassigned.voidmagic.common.items.TestItem;
import com.unassigned.voidmagic.common.tileentity.TileCoalGenerator;
import com.unassigned.voidmagic.common.tileentity.TileVoidStand;
import com.unassigned.voidmagic.common.tileentity.TileTestBlock;
import com.unassigned.voidmagic.common.tileentity.TileVoidInfuser;
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
public class RegistryEvents {

    @SubscribeEvent
    public static void onBlockRegistry(final RegistryEvent.Register<Block> register) {
        register.getRegistry().register(new TestBlock());
        register.getRegistry().register(new BlockCoalGenerator());
        register.getRegistry().register(new BlockVoidInfuser());
        register.getRegistry().register(new BlockVoidStand());
    }

    @SubscribeEvent
    public static void onItemRegistry(final RegistryEvent.Register<Item> register) {
        Item.Properties properties = new Item.Properties().group(VoidMagic.setup.itemGroup);

        //items
        register.getRegistry().register(new TestItem());
        register.getRegistry().register(new ItemTransmuteStone());

        //item blocks
        register.getRegistry().register(new BlockItem(ModBlocks.testBlock, properties).setRegistryName("testblock"));
        register.getRegistry().register(new BlockItem(ModBlocks.coalGenerator, properties).setRegistryName("coalgenerator"));
        register.getRegistry().register(new BlockItem(ModBlocks.voidInfuser, properties).setRegistryName("voidinfuser"));
        register.getRegistry().register(new BlockItem(ModBlocks.voidStand, properties).setRegistryName("voidstand"));
    }

    @SubscribeEvent
    public static void onTileTypeRegistry(final RegistryEvent.Register<TileEntityType<?>> register) {
        register.getRegistry().register(TileEntityType.Builder.create(TileTestBlock::new, ModBlocks.testBlock).build(null).setRegistryName("testblock"));
        register.getRegistry().register(TileEntityType.Builder.create(TileCoalGenerator::new, ModBlocks.coalGenerator).build(null).setRegistryName("coalgenerator"));
        register.getRegistry().register(TileEntityType.Builder.create(TileVoidInfuser::new, ModBlocks.voidInfuser).build(null).setRegistryName("voidinfuser"));
        register.getRegistry().register(TileEntityType.Builder.create(TileVoidStand::new, ModBlocks.voidStand).build(null).setRegistryName("voidstand"));
    }

    @SubscribeEvent
    public static void onContainerRegistry(final RegistryEvent.Register<ContainerType<?>> register) {
        register.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            return new ContainerTestBlock(windowId, VoidMagic.proxy.getClientWorld(), pos, inv, VoidMagic.proxy.getClientPlayer()); //called on client
        })).setRegistryName("testblock"));

        register.getRegistry().register(IForgeContainerType.create(((windowId, inv, data) -> {
            BlockPos pos = data.readBlockPos();
            return new ContainerCoalGenerator(windowId, VoidMagic.proxy.getClientWorld(), pos, inv, VoidMagic.proxy.getClientPlayer());
        })).setRegistryName("coalgenerator"));
    }

}

