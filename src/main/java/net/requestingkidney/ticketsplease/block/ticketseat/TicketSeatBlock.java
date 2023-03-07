package net.requestingkidney.ticketsplease.block.ticketseat;

import com.mojang.logging.LogUtils;
import com.simibubi.create.content.contraptions.components.actors.SeatBlock;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import net.minecraft.world.phys.BlockHitResult;
import net.requestingkidney.ticketsplease.block.entity.ticketseat.TicketSeatBlockEntity;
import org.slf4j.Logger;

import java.util.Arrays;


public class TicketSeatBlock extends SeatBlock implements EntityBlock {

    private static final Logger LOGGER = LogUtils.getLogger();

    public TicketSeatBlock(Properties pProperties, DyeColor color, boolean inCreativeTab) {
        super(pProperties, color, inCreativeTab);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult p_225533_6_) {
        if(!world.isClientSide()) {
            TicketSeatBlockEntity ticketSeatBlockEntity = (TicketSeatBlockEntity) world.getBlockEntity(pos);
            if(ticketSeatBlockEntity != null) {
                CompoundTag nbtBlockData = new CompoundTag();
                ticketSeatBlockEntity.saveAdditional(nbtBlockData);

                CompoundTag ticketIdTag = player.getItemInHand(hand).getTag();

                if(ticketIdTag != null) {
                    long ticketId = ticketIdTag.getLong("id");
                    for (long id : nbtBlockData.getLongArray("idList")) {
                        if (id == ticketId) {
                            return super.use(state, world, pos, player, hand, p_225533_6_);
                        }
                    }
                }

            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new TicketSeatBlockEntity(pPos, pState);
    }
}
