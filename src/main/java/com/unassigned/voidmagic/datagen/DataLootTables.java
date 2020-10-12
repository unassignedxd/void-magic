package com.unassigned.voidmagic.datagen;

import com.unassigned.voidmagic.common.util.ModRegistration;
import com.unassigned.voidmagic.datagen.providers.CustomLootTableProvider;
import net.minecraft.data.DataGenerator;

public class DataLootTables extends CustomLootTableProvider {

    public DataLootTables(DataGenerator genIn) { super(genIn); }

    @Override
    protected void addTables() {
        lootTables.put(ModRegistration.COALGENERATOR.get(), createStandardTable("coalgenerator", ModRegistration.COALGENERATOR.get()));
        lootTables.put(ModRegistration.TESTBLOCK.get(), createStandardTable("testblock", ModRegistration.TESTBLOCK.get()));
    }
}
