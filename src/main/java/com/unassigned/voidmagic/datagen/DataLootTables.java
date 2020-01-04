package com.unassigned.voidmagic.datagen;

import com.unassigned.voidmagic.common.blocks.ModBlocks;
import com.unassigned.voidmagic.datagen.providers.CustomLootTableProvider;
import net.minecraft.data.DataGenerator;

public class DataLootTables extends CustomLootTableProvider {

    public DataLootTables(DataGenerator genIn) { super(genIn); }


    @Override
    protected void addTables() {
        lootTables.put(ModBlocks.coalGenerator, createStandardTable("coalgenerator", ModBlocks.coalGenerator));
        lootTables.put(ModBlocks.testBlock, createStandardTable("testblock", ModBlocks.testBlock));
    }
}
