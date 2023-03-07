package net.requestingkidney.ticketsplease.item.ticket;

import com.mojang.logging.LogUtils;
import com.simibubi.create.content.contraptions.components.actors.SeatBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.requestingkidney.ticketsplease.TicketsPlease;
import net.requestingkidney.ticketsplease.block.ModBlocks;
import net.requestingkidney.ticketsplease.block.entity.ticketseat.TicketSeatBlockEntity;
import net.requestingkidney.ticketsplease.block.ticketseat.TicketSeatBlock;
import net.requestingkidney.ticketsplease.item.ModItems;

import java.util.*;

import com.simibubi.create.AllBlocks;
import org.slf4j.Logger;

public class TicketItem extends Item {
    public TicketItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Random rand = new Random();
        if (!pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Level level = pContext.getLevel();
            BlockState seatBlockState = level.getBlockState(positionClicked);
            Block seatBlock = seatBlockState.getBlock();

            if (AllBlocks.SEATS.contains(seatBlock)) {
                Player player = pContext.getPlayer();

                if(player == null){
                    return super.useOn(pContext);
                }

                Inventory inventory = player.getInventory();
                ItemStack ticketItemstack = pContext.getItemInHand();
                ItemStack signedTicketItemStack = new ItemStack(ModItems.SIGNED_TICKET.get());
                int ticketSlot = inventory.findSlotMatchingItem(ticketItemstack);
                BlockState ticketSeatBlockState = ModBlocks.TICKET_SEATS.get(((SeatBlock)seatBlock).getColor()).getDefaultState();
                level.setBlockAndUpdate(positionClicked, ticketSeatBlockState);

                CompoundTag nbtItemData = new CompoundTag();
                Long itemId = rand.nextLong();
                nbtItemData.putLong("id", itemId);;
                signedTicketItemStack.setTag(nbtItemData);
                inventory.removeItem(ticketItemstack);
                inventory.add(ticketSlot, signedTicketItemStack);

                CompoundTag nbtBlockData = new CompoundTag();
                List<Long> idList = new ArrayList<Long>();
                idList.add(itemId);
                nbtBlockData.putLongArray("idList", idList);
                TicketSeatBlockEntity ticketSeatBlockEntity = (TicketSeatBlockEntity) level.getBlockEntity(positionClicked);
                ticketSeatBlockEntity.saveAdditional(nbtBlockData);
                ticketSeatBlockEntity.setChanged();

                player.sendMessage(new TextComponent("ID ADDED: " + Arrays.toString(ticketSeatBlockEntity.getTileData().getLongArray("idList"))), player.getUUID());
            }
        }
        return super.useOn(pContext);
    }

}