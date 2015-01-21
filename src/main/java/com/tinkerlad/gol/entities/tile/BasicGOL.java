package com.tinkerlad.gol.entities.tile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;

/**
 * Created by brock_000 on 20/01/2015.
 */
public class BasicGOL extends TileEntity {

    public boolean active = false;

    public int getNumAdjacentLivingCells() {
        int livingAdjacent = 0;
        for (EnumFacing dir : EnumFacing.values()){
            TileEntity te = worldObj.getTileEntity(pos.offset(dir));
            if(te instanceof BasicGOL && ((BasicGOL) te).active){
                livingAdjacent++;
            }
        }
        return livingAdjacent;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        if(compound != null && compound.hasKey("active")){
            active = compound.getBoolean("active");
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        if(compound != null){
            compound.setBoolean("active", active);
        }
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
            readFromNBT(pkt.getNbtCompound());
    }

    @Override
    public Packet getDescriptionPacket() {
        NBTTagCompound compound = new NBTTagCompound();
        writeToNBT(compound);
        return  new S35PacketUpdateTileEntity(pos, 0, compound);
    }

    public void handleActivation() {
        active = !active;
        System.out.println("HANDLED INPUT");
        System.out.println("active = " + active);

        markDirty();
        worldObj.markBlockForUpdate(pos);
    }
}
