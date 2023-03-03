package net.requestingkidney.ticketsplease.item.ticket;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

import com.simibubi.create.AllBlocks;

public class TicketItem extends Item {
    public TicketItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();

            Block block = pContext.getLevel().getBlockState(positionClicked).getBlock();

            if (AllBlocks.SEATS.contains(block)) {
                player.sendMessage(new TextComponent("You found a seat"), player.getUUID());

            }

        }
        return super.useOn(pContext);
     }

}