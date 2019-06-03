package com.radixshock.classiccombat;

import org.apache.logging.log4j.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ClassicCombat.MODID, version = ClassicCombat.VERSION)
public class ClassicCombat {
	@Instance(ClassicCombat.MODID)
	private static ClassicCombat instance;
	
    public static final String MODID = "classiccombat";
    public static final String VERSION = "1.0.1";
    private Logger log = null;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	instance = this;
    	log = event.getModLog();
    	MinecraftForge.EVENT_BUS.register(new ForgeEventHandler());
    }
    
    public Logger getLog()
    {
    	return log;
    }
    
    public static ClassicCombat getInstance()
    {
    	return instance;
    }
}
