package net.requestingkidney.ticketsplease;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.requestingkidney.ticketsplease.block.ModBlocks;
import net.requestingkidney.ticketsplease.item.ModItems;

import org.slf4j.Logger;

@Mod(TicketsPlease.MOD_ID)
public class TicketsPlease {

    public static final String MOD_ID = "ticketsplease";

    public static final Registrate REGISTRATE = Registrate.create(MOD_ID);
    
    private static final Logger LOGGER = LogUtils.getLogger();

    public TicketsPlease(){
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register();
        ModBlocks.register();
        
        eventBus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event){
        LOGGER.info("HELLO FROM PREINIT");
    }

}
