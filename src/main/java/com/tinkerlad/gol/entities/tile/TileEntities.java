package com.tinkerlad.gol.entities.tile;

import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by brock_000 on 21/01/2015.
 */
public class TileEntities {

    public static void init(){
        GameRegistry.registerTileEntity(BasicGOL.class, "tnkgol:basic");
    }
}
