package com.ninni.yippee.block.blockentities;

import com.ninni.yippee.init.YippeeBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class TacoBellBlockEntity extends BlockEntity {
    private long lastRingTime;
    public int ringTicks;
    public boolean ringing;
    public Direction lastSideHit;
    private List<LivingEntity> hearingEntities;
    private boolean resonating;
    private int resonateTime;

    public TacoBellBlockEntity(BlockPos pos, BlockState state) { super(YippeeBlockEntityTypes.TACO_BELL.get(), pos, state); }

    @Override
    public boolean triggerEvent(int type, int data) {
        if (type == 1) {
            this.notifyMemoriesOfBell();
            this.resonateTime = 0;
            this.lastSideHit = Direction.from3DDataValue(data);
            this.ringTicks = 0;
            this.ringing = true;
            return true;
        } else return super.triggerEvent(type, data);
    }

    private static void tick(TacoBellBlockEntity blockEntity) {
        if (blockEntity.ringing) ++blockEntity.ringTicks;
        if (blockEntity.ringTicks >= 50) {
            blockEntity.ringing = false;
            blockEntity.ringTicks = 0;
        }
        if (blockEntity.ringTicks >= 5 && blockEntity.resonateTime == 0) {
            blockEntity.resonating = true;
        }
        if (blockEntity.resonating) {
            if (blockEntity.resonateTime < 40) ++blockEntity.resonateTime;
            else blockEntity.resonating = false;
        }

    }

    @SuppressWarnings("unused") public static void clientTick(Level world, BlockPos pos, BlockState state, TacoBellBlockEntity blockEntity) { tick(blockEntity); }
    @SuppressWarnings("unused") public static void serverTick(Level world, BlockPos pos, BlockState state, TacoBellBlockEntity blockEntity) { tick(blockEntity); }

    public void activate(Direction direction) {
        BlockPos blockPos = this.getBlockPos();
        this.lastSideHit = direction;
        if (this.ringing) this.ringTicks = 0;
        else this.ringing = true;
        assert this.level != null;
        this.level.blockEvent(blockPos, this.getBlockState().getBlock(), 1, direction.get3DDataValue());
    }

    private void notifyMemoriesOfBell() {
        BlockPos blockPos = this.getBlockPos();
        assert this.level != null;
        if (this.level.getGameTime() > this.lastRingTime + 60L || this.hearingEntities == null) {
            this.lastRingTime = this.level.getGameTime();
            AABB box = (new AABB(blockPos)).inflate(48.0);
            this.hearingEntities = this.level.getEntitiesOfClass(LivingEntity.class, box);
        }
        if (!this.level.isClientSide) {
            for (LivingEntity livingEntity : this.hearingEntities) {
                if (livingEntity.isAlive() && !livingEntity.isRemoved() && blockPos.closerToCenterThan(livingEntity.position(), 32.0)) {
                    livingEntity.getBrain().setMemory(MemoryModuleType.HEARD_BELL_TIME, this.level.getGameTime());
                }
            }
        }
    }

}
