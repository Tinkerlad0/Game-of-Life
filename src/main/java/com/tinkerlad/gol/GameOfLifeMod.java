package com.tinkerlad.gol;

import com.tinkerlad.gol.block.ModBlocks;
import com.tinkerlad.gol.entities.tile.TileEntities;
import com.tinkerlad.gol.proxies.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = GameOfLifeMod.MODID, version = GameOfLifeMod.VERSION)
public class GameOfLifeMod {
    public static final String MODID = "tnkgol";
    public static final String VERSION = "0.0.1";


    @Mod.Instance(MODID)
    public GameOfLifeMod instance;

    @SidedProxy(clientSide="com.tinkerlad.gol.proxies.ClientProxy", serverSide="com.tinkerlad.gol.proxies.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {

        ModBlocks.init();
        TileEntities.init();

    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

        proxy.registerRenders();
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }

    @EventHandler
    public void onLoadComplete(FMLLoadCompleteEvent event) {

    }
}
