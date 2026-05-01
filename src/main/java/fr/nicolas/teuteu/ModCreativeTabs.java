package fr.nicolas.teuteu;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TeuTeu.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TEUTEU_TAB =
            CREATIVE_TABS.register("teuteu_tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.teuteu"))
                    .icon(() -> new ItemStack(ModItems.ELYTRA_WING.get()))
                    .displayItems((parameters, output) -> {
                        output.accept(ModItems.ELYTRA_WING.get());
                        output.accept(ModItems.TEUTEU_CHESTPLATE.get());
                        output.accept(ModItems.UNIQUE_RING.get());
                        output.accept(ModItems.GIMLI_PICKAXE.get());
                    })
                    .build()
            );

    public static void register(IEventBus eventBus) {
        CREATIVE_TABS.register(eventBus);
    }
}