package fr.nicolas.teuteu;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(TeuTeu.MOD_ID)
public class TeuTeu {
    public static final String MOD_ID = "teuteu";

    public TeuTeu(IEventBus modEventBus) {
        ModItems.register(modEventBus);
        ModCreativeTabs.register(modEventBus);

        NeoForge.EVENT_BUS.addListener(ModEvents::onPlayerTick);
    }
}