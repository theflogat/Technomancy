package theflogat.technomancy.common.blocks.bloodmagic.machines;

import java.util.ArrayList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import theflogat.technomancy.Technomancy;
import theflogat.technomancy.common.blocks.base.BlockProcessor;
import theflogat.technomancy.common.tiles.bloodmagic.machines.TileBMProcessor;

public class BlockBMProcessor extends BlockProcessor {
	
	public BlockBMProcessor() {
		name = "BM";
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if(super.onBlockActivated(w, x, y, z, player, side, hitX, hitY, hitZ))
			return true;
		if(player != null) {
			TileEntity te = w.getTileEntity(x, y, z);
			if(te instanceof TileBMProcessor) {
				if(((TileBMProcessor)te).owner.equals("")){
					((TileBMProcessor)te).owner = player.getDisplayName();
				}
				player.openGui(Technomancy.instance, 1, w, x, y, z);
			}
		}
		return true;
	}
	
	@Override
	public void onBlockPlacedBy(World w, int x, int y, int z, EntityLivingBase entity, ItemStack items) {
		super.onBlockPlacedBy(w, x, y, z, entity, items);
		TileEntity tile = w.getTileEntity(x, y, z);
		if(tile instanceof TileBMProcessor && entity instanceof EntityPlayer) {
			((TileBMProcessor)tile).owner = ((EntityPlayer)entity).getDisplayName();
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World w, int meta) {
		return new TileBMProcessor();
	}
	
	@Override
	public void getNBTInfo(NBTTagCompound comp, ArrayList<String> l, int meta) {
		super.getNBTInfo(comp, l, meta);
		l.add("Owner:" + comp.getString("owner"));
	}
}