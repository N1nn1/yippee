package com.ninni.yippee.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.List;

public class TacoBellBlockEntity extends BlockEntity{
    private long lastRingTime;
    public int ringTicks;
    public boolean ringing;
    public Direction lastSideHit;
    private List<LivingEntity> hearingEntities;
    private boolean resonating;
    private int resonateTime;

    public TacoBellBlockEntity(BlockPos pos, BlockState state) { super(YippeeBlockEntityType.TACO_BELL, pos, state); }

    @Override
    public boolean onSyncedBlockEvent(int type, int data) {
        if (type == 1) {
            this.notifyMemoriesOfBell();
            this.resonateTime = 0;
            this.lastSideHit = Direction.byId(data);
            this.ringTicks = 0;
            this.ringing = true;
            return true;
        } else return super.onSyncedBlockEvent(type, data);
    }

    private static void tick(TacoBellBlockEntity blockEntity) {
        if (blockEntity.ringing) {
            ++blockEntity.ringTicks;
        }

        if (blockEntity.ringTicks >= 50) {
            blockEntity.ringing = false;
            blockEntity.ringTicks = 0;
        }

        if (blockEntity.resonating) {
            if (blockEntity.resonateTime < 40) {
                ++blockEntity.resonateTime;
            } else {
                blockEntity.resonating = false;
            }
        }

    }

    @SuppressWarnings("unused")
    public static void clientTick(World world, BlockPos pos, BlockState state, TacoBellBlockEntity blockEntity) {
        tick(blockEntity);
    }

    @SuppressWarnings("unused")
    public static void serverTick(World world, BlockPos pos, BlockState state, TacoBellBlockEntity blockEntity) {
        tick(blockEntity);
    }

    public void activate(Direction direction) {
        BlockPos blockPos = this.getPos();
        this.lastSideHit = direction;
        if (this.ringing) this.ringTicks = 0;
        else this.ringing = true;
        assert this.world != null;
        this.world.addSyncedBlockEvent(blockPos, this.getCachedState().getBlock(), 1, direction.getId());
    }

    private void notifyMemoriesOfBell() {
        BlockPos blockPos = this.getPos();
        assert this.world != null;
        if (this.world.getTime() > this.lastRingTime + 60L || this.hearingEntities == null) {
            this.lastRingTime = this.world.getTime();
            Box box = (new Box(blockPos)).expand(48.0);
            this.hearingEntities = this.world.getNonSpectatingEntities(LivingEntity.class, box);
        }
        if (!this.world.isClient) {
            for (LivingEntity livingEntity : this.hearingEntities) {
                if (livingEntity.isAlive() && !livingEntity.isRemoved() && blockPos.isWithinDistance(livingEntity.getPos(), 32.0)) {
                    livingEntity.getBrain().remember(MemoryModuleType.HEARD_BELL_TIME, this.world.getTime());
                }
            }
        }
    }
}
