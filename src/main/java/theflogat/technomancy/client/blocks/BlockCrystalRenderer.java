package theflogat.technomancy.client.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.world.IBlockAccess;
import org.lwjgl.opengl.GL11;
import theflogat.technomancy.common.blocks.base.TMBlocks;
import theflogat.technomancy.common.tiles.technom.TileCrystal;
import theflogat.technomancy.lib.RenderIds;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class BlockCrystalRenderer implements ISimpleBlockRenderingHandler {

    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer) {
        GL11.glPushMatrix();
        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
        TileCrystal te = new TileCrystal();
        te.blockType = TMBlocks.crystalBlock;
        te.blockMetadata = metadata;
        TileEntityRendererDispatcher.instance.renderTileEntityAt(te, 0.0D, 0.0D, 0.0D, 0.0F);
        GL11.glPopMatrix();
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer) {
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int modelId) {
        return true;
    }

    @Override
    public int getRenderId() {
        return RenderIds.idCrystal;
    }
}
