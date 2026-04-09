package com.sculkman.bugaton.items;

import com.sculkman.bugaton.Bugaton;
import com.sculkman.bugaton.entity.BugatonEntities;
import com.sculkman.bugaton.entity.NightmareEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class BugatonItems {

    public static final Item NIGHTMARE_SPAWN_EGG = registerItem("nightmare_spawn_egg", new SpawnEggItem(BugatonEntities.NIGHTMARE, 4411786, 8978176, new FabricItemSettings()));
    public static final Item NIGHTMANE = registerItem("nightmane", new NightmaneItem(new FabricItemSettings().rarity(Rarity.COMMON).food(BugatonFoodComponents.NIGHTMANE)));
    public static final Item MODIFIED_SPYGLASS = registerItem("modified_spyglass", new ModifiedSpyglassItem(new FabricItemSettings().maxDamage(50)));
    public static final Item NIGHTMANE_STEW = registerItem("nightmane_stew", new NightmaneStewItem(new FabricItemSettings().food(BugatonFoodComponents.NIGHTMANE_STEW).rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item PHANTASM = registerItem("phantasm", new PhantasmItem(new FabricItemSettings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item ABHORRENT_DELIGHT = registerItem("abhorrent_delight", new Item(new FabricItemSettings().maxCount(8).rarity(Rarity.RARE).food(BugatonFoodComponents.ABHORRENT_DELIGHT)));
    public static final Item UNKNOWABLE_EMBRYO = registerItem("unknowable_embryo", new Item(new FabricItemSettings().maxCount(16).rarity(Rarity.COMMON)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(Bugaton.MOD_ID, name), item);
    }

    public static void registerModItems() {
    }
}
