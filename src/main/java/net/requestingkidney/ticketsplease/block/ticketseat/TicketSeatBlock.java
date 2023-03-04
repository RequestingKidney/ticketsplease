package net.requestingkidney.ticketsplease.block.ticketseat;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.simibubi.create.content.contraptions.components.actors.SeatBlock;
import com.simibubi.create.content.contraptions.components.actors.SeatEntity;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.EnumProperty;

public class TicketSeatBlock extends SeatBlock {

    public static final EnumProperty<TicketIds> TICKET_IDS = EnumProperty.create("ticketids", TicketIds.class);

    public TicketSeatBlock(Properties pProperties, DyeColor color, boolean inCreativeTab) {
        super(pProperties, color, inCreativeTab);
    }
    
    @Override
    protected void createBlockStateDefinition(Builder<Block, BlockState> pBuilder) {
        pBuilder.add(TICKET_IDS);
        super.createBlockStateDefinition(pBuilder);
    }

    public enum TicketIds implements StringRepresentable {
        ID(new ArrayList<>());
        
        private final List<UUID> ids;

        public List<UUID> getIds(){
            return ids;
        }

        private TicketIds(List<UUID> ticketIds) {
            this.ids = ticketIds;
        }

        @Override
        public String getSerializedName() {
            return "ticketids";
        }
    }
}
