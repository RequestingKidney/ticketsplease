package net.requestingkidney.ticketsplease.block.ticketseat;

import com.simibubi.create.content.contraptions.components.actors.SeatBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.phys.BlockHitResult;
import net.requestingkidney.ticketsplease.block.entity.ticketseat.TicketSeatBlockEntity;


public class TicketSeatBlock extends SeatBlock implements EntityBlock {

    public TicketSeatBlock(Properties pProperties, DyeColor color, boolean inCreativeTab) {
        super(pProperties, color, inCreativeTab);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_225533_6_) {
        return super.use(state, world, pos, player, hand, p_225533_6_);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TicketSeatBlockEntity(pPos, pState);
    }
}
