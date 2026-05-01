package fr.nicolas.teuteu;

import net.minecraft.core.component.DataComponents;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Unit;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.equipment.ArmorMaterials;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TeuTeu.MOD_ID);

    public static final ToolMaterial GIMLI_MATERIAL = new ToolMaterial(
            BlockTags.INCORRECT_FOR_NETHERITE_TOOL,
            2031,
            9.0F,
            4.0F,
            15,
            Tags.Items.INGOTS_NETHERITE
    );

    public static final DeferredItem<Item> ELYTRA_WING = ITEMS.registerSimpleItem(
            "elytra_wing",
            () -> new Item.Properties()
    );

    public static final DeferredItem<Item> TEUTEU_CHESTPLATE = ITEMS.registerItem(
            "teuteu_chestplate",
            props -> new Item(
                    props.humanoidArmor(ArmorMaterials.NETHERITE, ArmorType.CHESTPLATE)
                            .component(DataComponents.GLIDER, Unit.INSTANCE)
            )
    );

    public static final DeferredItem<Item> UNIQUE_RING = ITEMS.registerSimpleItem(
            "unique_ring",
            () -> new Item.Properties().stacksTo(1)
    );

    public static final DeferredItem<Item> GIMLI_PICKAXE = ITEMS.registerItem(
            "gimli_pickaxe",
            props -> new GimliPickaxeItem(
                    props.pickaxe(GIMLI_MATERIAL, 1.0F, -2.8F)
            )
    );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}