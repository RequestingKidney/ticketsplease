package net.requestingkidney.ticketsplease.block;

import com.simibubi.create.AllTags.AllBlockTags;
import com.simibubi.create.AllTags.AllItemTags;
import com.simibubi.create.content.contraptions.components.actors.SeatInteractionBehaviour;
import com.simibubi.create.content.contraptions.components.actors.SeatMovementBehaviour;
import com.simibubi.create.content.logistics.block.display.source.EntityNameDisplaySource;
import com.simibubi.create.foundation.block.DyedBlockList;
import com.simibubi.create.foundation.data.SharedProperties;
import com.simibubi.create.foundation.item.TooltipHelper;

import static com.simibubi.create.AllInteractionBehaviours.interactionBehaviour;
import static com.simibubi.create.AllMovementBehaviours.movementBehaviour;
import static com.simibubi.create.foundation.data.TagGen.axeOnly;
import static com.simibubi.create.content.logistics.block.display.AllDisplayBehaviours.assignDataBehaviour;

import net.minecraft.core.Registry;
import net.minecraft.world.item.DyeColor;
import static net.requestingkidney.ticketsplease.TicketsPlease.REGISTRATE;
import net.requestingkidney.ticketsplease.block.ticketseat.TicketSeatBlock;

public class ModBlocks {
    
    public static final DyedBlockList<TicketSeatBlock> SEATS = new DyedBlockList<>(colour -> {
        String colourName = colour.getSerializedName();
        SeatMovementBehaviour movementBehaviour = new SeatMovementBehaviour();
        SeatInteractionBehaviour interactionBehaviour = new SeatInteractionBehaviour();
        return REGISTRATE.block(colourName + "_ticketseat", p -> new TicketSeatBlock(p, colour, colour == DyeColor.RED))
            .initialProperties(SharedProperties::wooden)
            .properties(p -> p.color(colour.getMaterialColor()))
            .transform(axeOnly())
            .onRegister(movementBehaviour(movementBehaviour))
            .onRegister(interactionBehaviour(interactionBehaviour))
            .onRegister(assignDataBehaviour(new EntityNameDisplaySource(), "entity_name"))
            .blockstate((c, p) -> {
                p.simpleBlock(c.get(), p.models()
                    .withExistingParent(colourName + "_seat", p.modLoc("block/seat"))
                    .texture("1", p.modLoc("block/seat/top_" + colourName))
                    .texture("2", p.modLoc("block/seat/side_" + colourName)));
            })
            .onRegisterAfter(Registry.ITEM_REGISTRY, v -> TooltipHelper.referTo(v, "block.create.brown_seat"))
            .tag(AllBlockTags.SEATS.tag)
            .item()
            .tag(AllItemTags.SEATS.tag)
            .build()
            .register();
    });

    public static void register() { }
}
