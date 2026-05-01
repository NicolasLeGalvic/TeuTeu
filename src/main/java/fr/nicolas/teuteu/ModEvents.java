package fr.nicolas.teuteu;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

public class ModEvents {

    public static void onPlayerTick(PlayerTickEvent.Post event) {
        Player player = event.getEntity();

        if (player.level().isClientSide()) {
            return;
        }

        if (hasUniqueRingInHand(player)) {
            player.addEffect(new MobEffectInstance(
                    MobEffects.INVISIBILITY,
                    40,
                    0,
                    false,
                    false,
                    false
            ));
        }
    }

    public static boolean hasUniqueRingInHand(Player player) {
        return player.getMainHandItem().is(ModItems.UNIQUE_RING.get())
                || player.getOffhandItem().is(ModItems.UNIQUE_RING.get());
    }
}