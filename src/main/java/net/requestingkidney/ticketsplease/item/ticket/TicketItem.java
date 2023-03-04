package net.requestingkidney.ticketsplease.item.ticket;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.requestingkidney.ticketsplease.item.ModItems;

import java.util.UUID;

import com.simibubi.create.AllBlocks;

public class TicketItem extends Item {
    public TicketItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (!pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Level level = pContext.getLevel();
            BlockState blockState = level.getBlockState(positionClicked);
            Block block = blockState.getBlock();

            if (AllBlocks.SEATS.contains(block)) {
                Player player = pContext.getPlayer();

                if(player == null){
                    return super.useOn(pContext);
                }

                Inventory inventory = player.getInventory();
                ItemStack ticketItemstack = pContext.getItemInHand();
                int ticketSlot = inventory.findSlotMatchingItem(ticketItemstack);

                ItemStack signedTicketItemStack = new ItemStack(ModItems.SIGNED_TICKET.get());
                addNbtDataToSignedTicket(signedTicketItemStack);
                inventory.removeItem(ticketItemstack);
                inventory.add(ticketSlot, signedTicketItemStack);
            }

        }
        return super.useOn(pContext);
    }

    private void addNbtDataToSignedTicket(ItemStack signedTicketItemStack){
        CompoundTag nbtData = new CompoundTag();
        UUID uuid = UUID.randomUUID();
        nbtData.putUUID("id", uuid);

        signedTicketItemStack.setTag(nbtData);

    }

}