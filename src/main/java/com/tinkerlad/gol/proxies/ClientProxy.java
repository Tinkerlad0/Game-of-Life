package com.tinkerlad.gol.proxies;

import com.tinkerlad.gol.GameOfLifeMod;
import com.tinkerlad.gol.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

/**
 * Created by brock_000 on 21/01/2015.
 */
public class ClientProxy extends CommonProxy {

    public void registerRenders(){


        ItemModelMesher mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();

        mesher.register(Item.getItemFromBlock(ModBlocks.basicGOLBlock),0 , new ModelResourceLocation(GameOfLifeMod.MODID + ":" + ModBlocks.basicGOLBlock.name, "inventory"));

    }

}
