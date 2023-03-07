package net.requestingkidney.ticketsplease.item.ticket;

import com.mojang.logging.LogUtils;
import com.simibubi.create.content.contraptions.components.actors.SeatBlock;
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
import net.requestingkidney.ticketsplease.block.ModBlocks;
import net.requestingkidney.ticketsplease.block.entity.ticketseat.TicketSeatBlockEntity;
import net.requestingkidney.ticketsplease.item.ModItems;

import java.util.*;

import com.simibubi.create.AllBlocks;
import org.slf4j.Logger;

public class TicketItem extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();
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
            Block clickedBlock = seatBlockState.getBlock();

            Player player = pContext.getPlayer();

            if(player == null){
                return super.useOn(pContext);
            }

            Inventory inventory = player.getInventory();
            ItemStack ticketItemstack = pContext.getItemInHand();

            if (AllBlocks.SEATS.contains(clickedBlock)) {

                if (inventory.getFreeSlot() != -1 || ticketItemstack.getCount() == 1) {
                    ItemStack signedTicketItemStack = new ItemStack(ModItems.SIGNED_TICKET.get());
                    int ticketSlot = inventory.findSlotMatchingItem(ticketItemstack);
                    BlockState ticketSeatBlockState = ModBlocks.TICKET_SEATS.get(((SeatBlock)clickedBlock).getColor()).getDefaultState();
                    level.setBlockAndUpdate(positionClicked, ticketSeatBlockState);

                    CompoundTag nbtItemData = new CompoundTag();
                    long itemId = rand.nextLong();
                    nbtItemData.putLong("id", itemId);
                    signedTicketItemStack.setTag(nbtItemData);

                    inventory.removeItem(ticketSlot, 1);
                    inventory.add(signedTicketItemStack);

                    CompoundTag nbtBlockData = new CompoundTag();
                    List<Long> idList = new ArrayList<>();
                    idList.add(itemId);
                    nbtBlockData.putLongArray("idList", idList);
                    nbtBlockData.putUUID("ownerId", player.getUUID());
                    TicketSeatBlockEntity ticketSeatBlockEntity = (TicketSeatBlockEntity) level.getBlockEntity(positionClicked);
                    if (ticketSeatBlockEntity != null) {
                        ticketSeatBlockEntity.load(nbtBlockData);
                        ticketSeatBlockEntity.setChanged();
                    }
                }
            } else if (ModBlocks.TICKET_SEATS.contains(clickedBlock)) {
                CompoundTag nbtBlockData = new CompoundTag();
                TicketSeatBlockEntity ticketSeatBlockEntity = (TicketSeatBlockEntity) level.getBlockEntity(positionClicked);
                if (ticketSeatBlockEntity != null)
                    ticketSeatBlockEntity.saveAdditional(nbtBlockData);

                if (nbtBlockData.getUUID("ownerId").equals(player.getUUID())) {
                    if (inventory.getFreeSlot() != -1 || ticketItemstack.getCount() == 1) {
                        ItemStack signedTicketItemStack = new ItemStack(ModItems.SIGNED_TICKET.get());
                        int ticketSlot = inventory.findSlotMatchingItem(ticketItemstack);

                        CompoundTag nbtItemData = new CompoundTag();
                        long itemId = rand.nextLong();
                        nbtItemData.putLong("id", itemId);
                        signedTicketItemStack.setTag(nbtItemData);

                        inventory.removeItem(ticketSlot, 1);
                        inventory.add(signedTicketItemStack);

                        ArrayList<Long> temp = new ArrayList<>();

                        for (long id : nbtBlockData.getLongArray("idList"))
                            temp.add(id);
                        temp.add(itemId);

                        nbtBlockData.putLongArray("idList", temp);
                        if(ticketSeatBlockEntity != null) {
                            ticketSeatBlockEntity.load(nbtBlockData);
                            ticketSeatBlockEntity.setChanged();
                        }
                    }
                }
            }
        }
        return super.useOn(pContext);
    }

}