package com.tinkerlad.gol.block;

import com.tinkerlad.gol.entities.tile.BasicGOL;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;

/**
 * Created by brock_000 on 20/01/2015.
 */
public class BasicGOLBlock extends Block implements ITileEntityProvider {

    public static final String name = "greenGOL";

    public static final PropertyBool ACTIVE_PROP = PropertyBool.create("active");

    protected BasicGOLBlock() {
        super(Material.rock);
        setCreativeTab(CreativeTabs.tabBlock);
        setDefaultState(this.blockState.getBaseState()
            .withProperty(ACTIVE_PROP,false));


        GameRegistry.registerBlock(this, name);
    }

    public static String getName() {
        return name;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ) {

        if (worldIn.isRemote) return false;

        TileEntity te = worldIn.getTileEntity(pos);

        if (te instanceof BasicGOL) {
            ((BasicGOL) te).handleActivation();
            return true;
        }

        return false;
    }


    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new BasicGOL();
    }

    //BLOCKSTATES

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

        return this.getDefaultState()
                .withProperty(ACTIVE_PROP, false);
    }

    @Override
    protected BlockState createBlockState() {
        return new BlockState(this,ACTIVE_PROP);
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity te = worldIn.getTileEntity(pos);

        boolean active = false;

        if(te instanceof BasicGOL){
            active = ((BasicGOL) te).active;
        }

        return state.withProperty(ACTIVE_PROP, active);
    }


    @Override
    public int getMetaFromState(IBlockState state) {
        return 0;
    }
}
