package com.sculkman.bugaton.items;

import com.sculkman.bugaton.Bugaton;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class BugatonItemGroups {

    public static final ItemGroup BUGATON_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Bugaton.MOD_ID, "bugaton"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.bugaton"))
                    .icon(() -> new ItemStack(BugatonItems.NIGHTMARE_SPAWN_EGG)).entries((displayContext, entries) -> {
                        //Items
                        entries.add(BugatonItems.NIGHTMARE_SPAWN_EGG);
                        entries.add(BugatonItems.NIGHTMANE);
                        entries.add(BugatonItems.MODIFIED_SPYGLASS);
                        entries.add(BugatonItems.NIGHTMANE_STEW);
                    }).build());

    public static void registerItemGroups() {
    }
}