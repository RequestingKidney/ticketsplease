package net.requestingkidney.ticketsplease.block.entity.ticketseat;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.requestingkidney.ticketsplease.block.entity.ModBlockEntities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class TicketSeatBlockEntity extends BlockEntity {

    List<Long> idList = new ArrayList<>();
    UUID ownerId = UUID.randomUUID();

    public TicketSeatBlockEntity(BlockEntityType<?> pType, BlockPos pPos, BlockState pBlockState) {
        super(pType, pPos, pBlockState);
    }

    public TicketSeatBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.TICKET_SEAT_ENTITY.get(), pPos, pBlockState);
    }

    @Override
    public void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putLongArray("idList", this.idList);
        pTag.putUUID("ownerId", this.ownerId);
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        List<Long> temp = new ArrayList<>();
        for (long id : pTag.getLongArray("idList"))
            temp.add(id);
        this.idList = temp;

        if(pTag.contains("ownerId")){
            this.ownerId = pTag.getUUID("ownerId");
        }
    }
}
