package net.requestingkidney.ticketsplease.block.entity;

import com.tterrag.registrate.util.entry.BlockEntityEntry;

import net.minecraft.world.level.block.entity.BlockEntity;
import net.requestingkidney.ticketsplease.block.entity.ticketseat.TicketSeatBlockEntity;

import static net.requestingkidney.ticketsplease.TicketsPlease.REGISTRATE;

public class ModBlockEntities {
    public static final BlockEntityEntry<BlockEntity> TICKET_SEAT_ENTITY = REGISTRATE.blockEntity("ticketseat_entity", TicketSeatBlockEntity::new)
            .register();

    public static void register() { }
}
