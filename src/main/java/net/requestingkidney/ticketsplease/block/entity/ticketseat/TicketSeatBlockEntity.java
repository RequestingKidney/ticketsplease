package net.requestingkidney.ticketsplease.block.entity.ticketseat;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.requestingkidney.ticketsplease.block.entity.ModBlockEntities;

public class TicketSeatBlockEntity extends BlockEntity {

    public TicketSeatBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public TicketSeatBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TICKET_SEAT_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
    }
}
