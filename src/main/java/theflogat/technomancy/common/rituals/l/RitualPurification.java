package theflogat.technomancy.common.rituals.l;

import java.util.ArrayList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import theflogat.technomancy.api.rituals.IRitualEffectHandler;
import theflogat.technomancy.api.rituals.Ritual;
import theflogat.technomancy.common.tiles.technom.TileCatalyst;

public class RitualPurification extends Ritual implements IRitualEffectHandler {

    protected int radiusX, radiusZ, minY, maxY;

    public RitualPurification(Type[] frame, Type core, int radX, int radZ, int minY, int maxY) {
        super(frame, core);
        radiusX = radX;
        radiusZ = radZ;
        this.minY = minY;
        this.maxY = maxY;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void applyEffect(TileCatalyst te) {
        ArrayList<EntityLivingBase> e = (ArrayList<EntityLivingBase>) te.getWorldObj().getEntitiesWithinAABB(EntityLivingBase.class, AxisAlignedBB.getBoundingBox(te.xCoord - radiusX, te.yCoord + minY, te.zCoord - radiusZ, te.xCoord + radiusX, te.yCoord + maxY, te.zCoord + radiusZ));
        for (Entity ent : e) {
            if (ent.isCreatureType(EnumCreatureType.monster, false))
                ent.setDead();
        }
    }

    @Override
    public boolean canApplyEffect(World w, int x, int y, int z) {
        return true;
    }

    @Override
    public void applyEffect(World w, int x, int y, int z) {
        ((TileCatalyst) w.getTileEntity(x, y, z)).handler = this;
    }

}
