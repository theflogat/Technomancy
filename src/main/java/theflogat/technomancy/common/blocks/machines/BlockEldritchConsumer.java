package theflogat.technomancy.common.blocks.machines;

import theflogat.technomancy.common.blocks.base.BlockBase;
import theflogat.technomancy.common.tiles.thaumcraft.machine.TileEldritchConsumer;
import theflogat.technomancy.common.tiles.thaumcraft.machine.TileEldritchConsumer.Range;
import theflogat.technomancy.lib.Names;
import theflogat.technomancy.lib.Ref;
import theflogat.technomancy.lib.RenderIds;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockEldritchConsumer extends BlockBase{

	public BlockEldritchConsumer() {
		setBlockName(Ref.getId(Names.eldritchConsumer));
		
	}
	
	@Override
	public boolean onBlockActivated(World w, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntity te = w.getTileEntity(x, y, z);
		if(te==null || !(te instanceof TileEldritchConsumer)){
			return false;
		}
		if(!w.isRemote){
			if(player.isSneaking()){
				Range c = ((TileEldritchConsumer)te).current = ((TileEldritchConsumer)te).current.getNext();
				player.addChatComponentMessage(new ChatComponentText("Set to: "+c.toString()));
				player.addChatComponentMessage(new ChatComponentText("Range: "+Integer.toString(c.r*2 + 1)+"x"+Integer.toString(c.r*2 + 1)));
				player.addChatComponentMessage(new ChatComponentText("Depth: "+(c.h==-1 ? "To BedRock" : c.h)));
				return true;
			} else {
				Range c = ((TileEldritchConsumer)te).current;
				player.addChatComponentMessage(new ChatComponentText("Processing as: " + c.toString()));
				player.addChatComponentMessage(new ChatComponentText("Range: "+Integer.toString(c.r*2 + 1)+"x"+Integer.toString(c.r*2 + 1)));
				player.addChatComponentMessage(new ChatComponentText("Depth: " + (c.h==-1 ? "To BedRock" : c.h)));
				return true;
			}
		}
		return true;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public int getRenderType() {
		return RenderIds.idEldrichConsumer;
	}

	@Override
	public TileEntity createNewTileEntity(World w, int meta) {
		return new TileEldritchConsumer();
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icon = reg.registerIcon(Ref.getAsset("eldCons"));
	}
	
	@Override
	public IIcon getIcon(int par1, int par2) {
		return icon;
	}
	
	@Override
	public boolean isNormalCube() {
		return false;
	}
}