package net.requestingkidney.ticketsplease.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.requestingkidney.ticketsplease.TicketsPlease;
import net.requestingkidney.ticketsplease.item.signedticket.SignedTicketItem;
import net.requestingkidney.ticketsplease.item.ticket.TicketItem;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
        DeferredRegister.create(ForgeRegistries.ITEMS, TicketsPlease.MOD_ID);

    public static final RegistryObject<Item> TICKET = ITEMS.register("ticket", 
        () -> new TicketItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

        public static final RegistryObject<Item> SIGNED_TICKET = ITEMS.register("signedticket", 
        () -> new SignedTicketItem(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
