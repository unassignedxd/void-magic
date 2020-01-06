package com.unassigned.voidmagic.common.blocks;

import com.unassigned.voidmagic.common.container.ContainerCoalGenerator;
import com.unassigned.voidmagic.common.container.ContainerTestBlock;
import com.unassigned.voidmagic.common.tileentity.TileCoalGenerator;
import com.unassigned.voidmagic.common.tileentity.TileTestBlock;
import com.unassigned.voidmagic.common.tileentity.TileVoidInfuser;
import com.unassigned.voidmagic.common.tileentity.TileVoidStand;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.registries.ObjectHolder;

public class ModBlocks {

    @ObjectHolder("voidmagic:testblock")
    public static TestBlock testBlock;
    @ObjectHolder("voidmagic:testblock")
    public static TileEntityType<TileTestBlock> testBlockTile;
    @ObjectHolder("voidmagic:testblock")
    public static ContainerType<ContainerTestBlock> testBlockContainer;

    @ObjectHolder("voidmagic:coalgenerator")
    public static BlockCoalGenerator coalGenerator;
    @ObjectHolder("voidmagic:coalgenerator")
    public static TileEntityType<TileCoalGenerator> coalGeneratorTile;
    @ObjectHolder("voidmagic:coalgenerator")
    public static ContainerType<ContainerCoalGenerator> coalGeneratorContainer;

    @ObjectHolder("voidmagic:voidinfuser")
    public static BlockVoidInfuser voidInfuser;
    @ObjectHolder("voidmagic:voidinfuser")
    public static TileEntityType<TileVoidInfuser> voidInfuserTile;

    @ObjectHolder("voidmagic:voidstand")
    public static BlockVoidStand voidStand;
    @ObjectHolder("voidmagic:voidstand")
    public static TileEntityType<TileVoidStand> voidStandTile;

}
