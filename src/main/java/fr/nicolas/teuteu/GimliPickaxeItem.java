package fr.nicolas.teuteu;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class GimliPickaxeItem extends Item {
    private static boolean isMiningExtraBlocks = false;

    public GimliPickaxeItem(Properties properties) {
        super(properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity miningEntity) {
        if (!level.isClientSide() && miningEntity instanceof Player player && !isMiningExtraBlocks) {
            isMiningExtraBlocks = true;

            try {
                mine3x3((ServerLevel) level, player, pos, player.getLookAngle());
            } finally {
                isMiningExtraBlocks = false;
            }
        }

        return super.mineBlock(stack, level, state, pos, miningEntity);
    }

    private static void mine3x3(ServerLevel level, Player player, BlockPos center, Vec3 look) {
        double absX = Math.abs(look.x);
        double absY = Math.abs(look.y);
        double absZ = Math.abs(look.z);

        if (absY > absX && absY > absZ) {
            minePlaneXZ(level, player, center);
        } else if (absX > absZ) {
            minePlaneYZ(level, player, center);
        } else {
            minePlaneXY(level, player, center);
        }
    }

    private static void minePlaneXY(ServerLevel level, Player player, BlockPos center) {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                breakExtraBlock(level, player, center.offset(x, y, 0), center);
            }
        }
    }

    private static void minePlaneYZ(ServerLevel level, Player player, BlockPos center) {
        for (int y = -1; y <= 1; y++) {
            for (int z = -1; z <= 1; z++) {
                breakExtraBlock(level, player, center.offset(0, y, z), center);
            }
        }
    }

    private static void minePlaneXZ(ServerLevel level, Player player, BlockPos center) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                breakExtraBlock(level, player, center.offset(x, 0, z), center);
            }
        }
    }

    private static void breakExtraBlock(ServerLevel level, Player player, BlockPos pos, BlockPos center) {
        if (pos.equals(center)) {
            return;
        }

        BlockState state = level.getBlockState(pos);

        if (state.isAir()) {
            return;
        }

        if (state.getDestroySpeed(level, pos) < 0) {
            return;
        }

        level.destroyBlock(pos, true, player);
    }
}