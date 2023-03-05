package net.requestingkidney.ticketsplease.item.signedticket;

import com.simibubi.create.AllBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class SignedTicketItem extends Item {

    public SignedTicketItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if (!pLevel.isClientSide() && (pPlayer.getItemInHand(pUsedHand).hasTag() && (pPlayer.getItemInHand(pUsedHand).getTag() != null))){
            pPlayer.sendMessage(new TextComponent("ID: " + pPlayer.getItemInHand(pUsedHand).getTag().getUUID("id").toString()), pPlayer.getUUID());
            
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
