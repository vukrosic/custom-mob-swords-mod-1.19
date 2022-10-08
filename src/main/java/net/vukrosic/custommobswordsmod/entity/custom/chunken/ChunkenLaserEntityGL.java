package net.vukrosic.custommobswordsmod.entity.custom.chunken;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.passive.CowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class ChunkenLaserEntityGL extends HostileEntity implements IAnimatable {

    private AnimationFactory factory = new AnimationFactory(this);
    public static final Identifier SPAWN_PACKET = new Identifier("custommobswordsmod", "chunken_laser");

    public ChunkenLaserEntityGL(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


    @Override
    protected void initDataTracker() {

    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        return null;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return null;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }

    @Override
    public Arm getMainArm() {
        return null;
    }

    @Override
    public void registerControllers(AnimationData animationData) {

    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    public static DefaultAttributeContainer.Builder setAttributes() {
        return CowEntity.createCowAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2000.0D);
    }
/*
    @Override
    public Packet<?> createSpawnPacket() {
        PacketByteBuf packet = new PacketByteBuf(Unpooled.buffer());
        packet.writeDouble(this.getX());
        packet.writeDouble(this.getY());
        packet.writeDouble(this.getZ());
        packet.writeInt(this.getId());
        packet.writeUuid(this.getUuid());

        return ServerPlayNetworking.createS2CPacket(SPAWN_PACKET, packet);
    }*/


}
