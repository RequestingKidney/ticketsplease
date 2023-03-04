package net.requestingkidney.ticketsplease.item;

import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullSupplier;

import net.minecraft.world.item.CreativeModeTab;
import static net.requestingkidney.ticketsplease.TicketsPlease.REGISTRATE;
import net.requestingkidney.ticketsplease.item.signedticket.SignedTicketItem;
import net.requestingkidney.ticketsplease.item.ticket.TicketItem;


public class ModItems {
    public static final RegistryEntry<TicketItem> TICKET = REGISTRATE.item("ticket", TicketItem::new)
            .tab(NonNullSupplier.of(() -> CreativeModeTab.TAB_MISC))
            .register();
            
    public static final RegistryEntry<SignedTicketItem> SIGNED_TICKET = REGISTRATE.item("signedticket", SignedTicketItem::new)
            .tab(NonNullSupplier.of(() -> CreativeModeTab.TAB_MISC))
            .register();

    public static void register() { }

}
