package com.unassigned.voidmagic.common.items;

import com.unassigned.voidmagic.VoidMagic;
import net.minecraft.item.Item;

public class TestItem extends Item {

    public TestItem() {
        super(new Item.Properties()
                .maxStackSize(1)
                .group(VoidMagic.setup.itemGroup));
        setRegistryName("testitem");
    }
}
