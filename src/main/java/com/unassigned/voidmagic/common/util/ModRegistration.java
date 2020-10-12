package com.unassigned.voidmagic.common.util;

import com.unassigned.voidmagic.common.blocks.*;
import com.unassigned.voidmagic.common.blocks.BlockVoidInfuser;
import com.unassigned.voidmagic.common.container.ContainerCoalGenerator;
import com.unassigned.voidmagic.common.container.ContainerTestBlock;
import com.unassigned.voidmagic.common.items.ItemTransmuteStone;
import com.unassigned.voidmagic.common.items.TestItem;
import com.unassigned.voidmagic.common.blocks.tile.TileCoalGenerator;
import com.unassigned.voidmagic.common.blocks.tile.TileVoidStand;
import com.unassigned.voidmagic.common.blocks.tile.TileTestBlock;
import com.unassigned.voidmagic.common.blocks.tile.TileVoidInfuser;
import net.minecraft.block.Block;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import static com.unassigned.voidmagic.VoidMagic.MODID;

public class ModRegistration {

    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);
    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, MODID);
    private static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

    public static void init() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        TILES.register(FMLJavaModLoadingContext.get().getModEventBus());
        CONTAINERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final RegistryObject<TestBlock> TESTBLOCK = BLOCKS.register("testblock", TestBlock::new);
    public static final RegistryObject<Item> TESTBLOCK_ITEM = ITEMS.register("testblock", () -> new BlockItem(TESTBLOCK.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<TileTestBlock>> TESTBLOCK_TILE =
            TILES.register("testblock", () -> TileEntityType.Builder.create(TileTestBlock::new, TESTBLOCK.get()).build(null));
    public static final RegistryObject<ContainerType<ContainerTestBlock>> TESTBLOCK_CONTAINER =
            CONTAINERS.register("testblock", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new ContainerTestBlock(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<BlockCoalGenerator> COALGENERATOR = BLOCKS.register("coalgenerator", BlockCoalGenerator::new);
    public static final RegistryObject<Item> COALGENERATOR_ITEM = ITEMS.register("coalgenerator", () -> new BlockItem(COALGENERATOR.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<TileCoalGenerator>> COALGENERATOR_TILE =
            TILES.register("coalgenerator", () -> TileEntityType.Builder.create(TileCoalGenerator::new, COALGENERATOR.get()).build(null));
    public static final RegistryObject<ContainerType<ContainerCoalGenerator>> COALGENERATOR_CONTAINER =
            CONTAINERS.register("coalgenerator", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new ContainerCoalGenerator(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<BlockVoidInfuser> VOIDINFUSER = BLOCKS.register("voidinfuser", BlockVoidInfuser::new);
    public static final RegistryObject<Item> VOIDINFUSER_ITEM = ITEMS.register("voidinfuser", () -> new BlockItem(VOIDINFUSER.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<TileVoidInfuser>> VOIDINFUSER_TILE =
            TILES.register("voidinfuser", () -> TileEntityType.Builder.create(TileVoidInfuser::new, VOIDINFUSER.get()).build(null));

    public static final RegistryObject<BlockVoidInfuser> VOIDSTAND = BLOCKS.register("voidstand", BlockVoidInfuser::new);
    public static final RegistryObject<Item> VOIDSTAND_ITEM = ITEMS.register("voidstand", () -> new BlockItem(VOIDSTAND.get(), new Item.Properties().group(ModSetup.ITEM_GROUP)));
    public static final RegistryObject<TileEntityType<TileVoidStand>> VOIDSTAND_TILE =
            TILES.register("voidstand", () -> TileEntityType.Builder.create(TileVoidStand::new, VOIDSTAND.get()).build(null));

    public static final RegistryObject<TestItem> TESTITEM = ITEMS.register("testitem", TestItem::new);
    public static final RegistryObject<ItemTransmuteStone> TRANSMUTESTONE = ITEMS.register("transmutestone", ItemTransmuteStone::new);

}

