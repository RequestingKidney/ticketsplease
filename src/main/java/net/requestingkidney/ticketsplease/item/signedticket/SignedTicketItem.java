package net.requestingkidney.ticketsplease.item.signedticket;

import com.simibubi.create.AllBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;

public class SignedTicketItem extends Item {

    public SignedTicketItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Block block = pContext.getLevel().getBlockState(positionClicked).getBlock();

            if (AllBlocks.SEATS.contains(block)) {
                Player player = pContext.getPlayer();

                if(player == null){
                    return super.useOn(pContext);
                }
                
            }

        }
        return super.useOn(pContext);
     }

}
