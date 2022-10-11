package net.vukrosic.custommobswordsmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.*;
import net.minecraft.entity.boss.ServerBossBar;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import net.vukrosic.custommobswordsmod.command.RestoreDeathCommand;
import net.vukrosic.custommobswordsmod.command.SetHunterCommand;
import net.vukrosic.custommobswordsmod.effect.ModEffects;
import net.vukrosic.custommobswordsmod.entity.ModEntities;
import net.vukrosic.custommobswordsmod.entity.custom.EnderZoglinEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.PlayerEntityExt;
import net.vukrosic.custommobswordsmod.entity.custom.ShieldingShulkerEntity;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.ChunkenPhaseManager;
import net.vukrosic.custommobswordsmod.entity.custom.chunken.SpittingChickenEntity;
import net.vukrosic.custommobswordsmod.entity.custom.fireenderman.FireEndermanEntityGL;
import net.vukrosic.custommobswordsmod.entity.custom.frogking.FrogKingEntity;
import net.vukrosic.custommobswordsmod.entity.custom.summoner.SummonerEntityGL;
import net.vukrosic.custommobswordsmod.item.ModItems;
import net.vukrosic.custommobswordsmod.item.custom.ItemEntityMixinExt;
import net.vukrosic.custommobswordsmod.util.CarbonPoisoningEffectManager;
import net.vukrosic.custommobswordsmod.util.FireInfectedPlayers;
import net.vukrosic.custommobswordsmod.world.dimension.ModDimensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityExt {

    FrogKingEntity frogKingEntity;
    ShieldingShulkerEntity shieldingShulkerEntity;
    ServerBossBar serverBossBar;
    public float BeamProgress = 0;

    int ShieldingShulkerShield = 0;

    boolean isInChickenDimention = false;
    public boolean hasChickenEffect = false;
    public SummonerEntityGL summonerEntityGL = null;

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }





    @Shadow private PlayerInventory inventory;

    @Shadow public abstract void sendMessage(Text message, boolean overlay);
    @Shadow public HungerManager getHungerManager() {
        return null;
    }

    @Shadow public abstract Text getName();

    boolean fireInfected = false;

    public boolean hasChickenEffect(){
        return hasChickenEffect;
    };
    @Override
    public void setFireInfected(boolean fireInfected) {

        this.fireInfected = fireInfected;
    }

    public void setSummonerEntityGL(SummonerEntityGL summonerEntityGL){
        this.summonerEntityGL = summonerEntityGL;
    }
    public SummonerEntityGL getSummonerEntityGL(){
        return summonerEntityGL;
    }

    public void setInChickenDimention(boolean isInChickenDimention){
        if(isInChickenDimention){
            CommandManager commandManager = world.getServer().getCommandManager();
            commandManager.executeWithPrefix(world.getServer().getCommandSource(), "gamemode adventure");
        }
        else {
            CommandManager commandManager = world.getServer().getCommandManager();
            commandManager.executeWithPrefix(world.getServer().getCommandSource(), "gamemode survival");
        }
        this.isInChickenDimention = isInChickenDimention;
    }

    public boolean isInChickenDimention(){
        return isInChickenDimention;
    }

    @Override
    public boolean getFireInfected() {
        return fireInfected;
    }

    @Override
    public void setFrogKingEntity(FrogKingEntity frogKingEntity) {
        if(world.isClient()){
            return;
        }
        this.frogKingEntity = frogKingEntity;
    }

    @Override
    public void stopRiding() {
        super.stopRiding();
    }

    @Override
    public FrogKingEntity getFrogKingEntity() {
        return frogKingEntity;
    }






    @Inject(method = "tick", at = @At("HEAD"), cancellable = true)
    public void tick (CallbackInfo info) {
        if (!world.isClient) {
            // check if chicken dimension every 20th tick

            if (world.getRegistryKey() == ModDimensions.CHICKENDIM_DIMENSION_KEY) {
                double y = this.getPos().y;
                System.out.println("y = " + y);
                System.out.println("y < (-100) = " + (y < (-100)));
                if(y < (-100)){
                    System.out.println("Setting position");
                    this.teleport(this.getPos().x + Math.random() * 100, 200, this.getPos().z + Math.random() * 100);
            }
                setInChickenDimention(true);
            } else {
                setInChickenDimention(false);
            }


///execute in minecraft:overworld teleport ~ ~ ~

            if(isInChickenDimention) {
                if (Math.random() < 1) {
                    for (int i = 0; i < 2; i++) {
                        SpittingChickenEntity spittingChickenEntity = new SpittingChickenEntity(ModEntities.SPITTING_CHICKEN, world);
                        double x = this.getPos().x + (Math.random() * 60) - 30;
                        double z = this.getPos().z + (Math.random() * 60) - 30;
                        // random yaw between 0 and 360
                        float yawC = (float) (Math.random() * 360);
                        // random pitch between -90 and 90
                        float pitchC = (float) ((Math.random() * 180) - 90);
                        spittingChickenEntity.refreshPositionAndAngles(x, this.getPos().y, z, yawC, pitchC);
                        world.spawnEntity(spittingChickenEntity);
                        world.addParticle(ParticleTypes.WITCH, x, this.getPos().y, z, 2, 2, 2);
                    }

                }
            }
        }






        double randomNumber = Math.random();
        if (randomNumber > 0.92) {
            if (super.isOnFire()) {
                BlockPos pos = new BlockPos(world.getRandom().nextInt(10), world.getRandom().nextInt(10), world.getRandom().nextInt(10));
                FireEndermanEntityGL fireEndermanEntity = new FireEndermanEntityGL(ModEntities.FIRE_ENDERMAN, world);
                fireEndermanEntity.refreshPositionAndAngles(this.getX() + pos.getX(), this.getY() + pos.getY(), this.getZ() + pos.getZ(), 0, 0);
                world.spawnEntity(fireEndermanEntity);
                // set fireEndermanEntity angry at player
                fireEndermanEntity.setTarget(this);
            }
        }
    }
//}



    @Inject(method = "dropItem", at = @At("HEAD"), cancellable = true)
    public ItemEntity dropItem(ItemStack stack, boolean retainOwnership, CallbackInfoReturnable ci) {
        if (stack.isEmpty()) {
            return null;
        } else {
            if (this.world.isClient) {
                this.swingHand(Hand.MAIN_HAND);
            }

            double d = this.getEyeY() - 0.30000001192092896;
            ItemEntity itemEntity = new ItemEntity(this.world, this.getX(), d, this.getZ(), stack);



            if(FireInfectedPlayers.isPlayerInList(this.inventory.player)){
                itemEntity.setPickupDelay(40);
                itemEntity.setOnFireFor(500);
                ((ItemEntityMixinExt)itemEntity).setThrownBy(this.inventory.player);
                //((ItemEntityMixinExt)itemEntity).setBurnTimer(500);
                if (retainOwnership) {
                    itemEntity.setThrower(this.getUuid());
                }
            }
            else {
                itemEntity.setPickupDelay(40);
                if (retainOwnership) {
                    itemEntity.setThrower(this.getUuid());
                }
            }
            //world.spawnEntity(itemEntity);

            boolean throwRandomly = false;

            float f;
            float g;
            if (throwRandomly) {
                f = this.random.nextFloat() * 0.5F;
                g = this.random.nextFloat() * 6.2831855F;
                itemEntity.setVelocity((double)(-MathHelper.sin(g) * f), 0.20000000298023224, (double)(MathHelper.cos(g) * f));
            } else {
                f = 0.3F;
                g = MathHelper.sin(this.getPitch() * 0.017453292F);
                float h = MathHelper.cos(this.getPitch() * 0.017453292F);
                float i = MathHelper.sin(this.getYaw() * 0.017453292F);
                float j = MathHelper.cos(this.getYaw() * 0.017453292F);
                float k = this.random.nextFloat() * 6.2831855F;
                float l = 0.02F * this.random.nextFloat();
                itemEntity.setVelocity((double)(-i * h * 0.3F) + Math.cos((double)k) * (double)l, (double)(-g * 0.3F + 0.1F + (this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(j * h * 0.3F) + Math.sin((double)k) * (double)l);
            }

            return itemEntity;
        }
    }




    // inject into eating method
    @Inject(method = "eatFood", at = @At("HEAD"), cancellable = true)
    void eatFood(World world, ItemStack stack, CallbackInfoReturnable<ItemStack> cir) {
        if (!world.isClient()) {
            //if(this.getHungerManager().getFoodLevel() < 20) {
                if (stack.getItem() == ModItems.BURNT_APPLE ||
                        stack.getItem() == ModItems.BURNT_BREAD ||
                        stack.getItem() == ModItems.BURNT_GOLDEN_APPLE ||
                        stack.getItem() == ModItems.BURNT_CARROT ||
                        stack.getItem() == ModItems.BURNT_CHICKEN ||
                        stack.getItem() == ModItems.BURNT_COD ||
                        stack.getItem() == ModItems.BURNT_MUTTON ||
                        stack.getItem() == ModItems.BURNT_MELON ||
                        stack.getItem() == ModItems.BURNT_PORKCHOP ||
                        stack.getItem() == ModItems.BURNT_POTATO ||
                        stack.getItem() == ModItems.BURNT_RABBIT ||
                        stack.getItem() == ModItems.BURNT_SALMON ||
                        stack.getItem() == ModItems.BURNT_BEEF ||
                        stack.getItem() == ModItems.BURNT_STEW) {
                    this.addStatusEffect(new StatusEffectInstance(ModEffects.CARBONPOISONING, CarbonPoisoningEffectManager.maxticksLeft, 0));
                }
            //}
        }
    }








/*
    @Overwrite
    @Nullable
    public ItemEntity dropItem(ItemStack stack, boolean throwRandomly, boolean retainOwnership) {
        if (stack.isEmpty()) {
            return null;
        } else {
            if (this.world.isClient) {
                this.swingHand(Hand.MAIN_HAND);
            }

            double d = this.getEyeY() - 0.30000001192092896;
            ItemEntity itemEntity = new ItemEntity(this.world, this.getX(), d, this.getZ(), stack);


            if(FireInfectedPlayers.isPlayerInList(this.inventory.player)){
                itemEntity.setPickupDelay(40);
                itemEntity.setOnFireFor(500);
                ((ItemEntityMixinExt)itemEntity).setThrownBy(this.inventory.player);
                //((ItemEntityMixinExt)itemEntity).setBurnTimer(500);
                if (retainOwnership) {
                    itemEntity.setThrower(this.getUuid());
                }
            }
            else {
                itemEntity.setPickupDelay(40);
                if (retainOwnership) {
                    itemEntity.setThrower(this.getUuid());
                }
            }
            //world.spawnEntity(itemEntity);



            float f;
            float g;
            if (throwRandomly) {
                f = this.random.nextFloat() * 0.5F;
                g = this.random.nextFloat() * 6.2831855F;
                itemEntity.setVelocity((double)(-MathHelper.sin(g) * f), 0.20000000298023224, (double)(MathHelper.cos(g) * f));
            } else {
                f = 0.3F;
                g = MathHelper.sin(this.getPitch() * 0.017453292F);
                float h = MathHelper.cos(this.getPitch() * 0.017453292F);
                float i = MathHelper.sin(this.getYaw() * 0.017453292F);
                float j = MathHelper.cos(this.getYaw() * 0.017453292F);
                float k = this.random.nextFloat() * 6.2831855F;
                float l = 0.02F * this.random.nextFloat();
                itemEntity.setVelocity((double)(-i * h * 0.3F) + Math.cos((double)k) * (double)l, (double)(-g * 0.3F + 0.1F + (this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(j * h * 0.3F) + Math.sin((double)k) * (double)l);
            }

            return itemEntity;
        }
    }
*/

    @Override
    public void onAttacking(Entity target) {
        if(target.getName() == ModEntities.ENDER_ZOGLIN.getName()){
            EnderZoglinEntityGL enderZoglinEntity = new EnderZoglinEntityGL(ModEntities.ENDER_ZOGLIN, world);
            enderZoglinEntity.refreshPositionAndAngles(target.getX(), target.getY(), target.getZ(), 0, 0);
            world.spawnEntity(enderZoglinEntity);
        }
        super.onAttacking(target);
    }








/*
    @Inject(method = "attackLivingEntity", at = @At("HEAD"), cancellable = true)
    protected void attackLivingEntity(LivingEntity target, CallbackInfo ci) {
        if(target.getName() == ModEntities.ENDERZOGLIN.getName()){
            EnderZoglinEntity enderZoglinEntity = new EnderZoglinEntity(ModEntities.ENDERZOGLIN, world);
            enderZoglinEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
            world.spawnEntity(enderZoglinEntity);
        }
    }*/

    public void SummonShieldingShulker(){
        if(!this.world.isClient() && this.getShieldingShulkerEntity() == null) {

            ShieldingShulkerEntity shieldingShulkerEntity = new ShieldingShulkerEntity(EntityType.SHULKER, this.world);
            shieldingShulkerEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), 0, 0);
            shieldingShulkerEntity.updatePosition(this.getX(), this.getY(), this.getZ());
            this.world.spawnEntity(shieldingShulkerEntity);

            setShieldingShulkerEntity(shieldingShulkerEntity);
            shieldingShulkerEntity.owner = SetHunterCommand.pray;
        }
    }

    @Override
    public void setShieldingShulkerEntity(ShieldingShulkerEntity shieldingShulkerEntity) {
        serverBossBar = new ServerBossBar(this.getDisplayName(), ServerBossBar.Color.BLUE, ServerBossBar.Style.PROGRESS);
        serverBossBar.addPlayer((ServerPlayerEntity) SetHunterCommand.pray);
        serverBossBar.setPercent(BeamProgress);
        // make name of the bar "" to hide it
        serverBossBar.setName(Text.of("Shield Progress"));
        this.shieldingShulkerEntity = shieldingShulkerEntity;
    }

    public ShieldingShulkerEntity getShieldingShulkerEntity() {
        return this.shieldingShulkerEntity;
    }

    @Inject(method = "damageShield", at = @At("HEAD"), cancellable = true)
    public void damageShield(float amount, CallbackInfo ci) {
        if (this.activeItemStack.isOf(ModItems.ABSORPTION_SHIELD_ITEM)) {
            if (!this.world.isClient && this.shieldingShulkerEntity != null) {
                this.addBeamProgress(amount);
            }
            if(this.world.isClient()){
                //add crit particles
                this.world.addParticle(ParticleTypes.GLOW, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                this.world.addParticle(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
                this.world.addParticle(ParticleTypes.WITCH, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }
    }


    public void addBeamProgress(float beamProgress) {
        this.BeamProgress += beamProgress / 500;
        serverBossBar.setPercent(BeamProgress);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if(RestoreDeathCommand.speedruner != null && this.getName() == RestoreDeathCommand.speedruner.getName()){
            RestoreDeathCommand.saveState();
        }
    }
/*
    // inject into interraction with blocks
    @Inject(method = "shouldCancelInteraction", at = @At("HEAD"), cancellable = true)
    public void shouldCancelInteraction(CallbackInfoReturnable<Boolean> cir) {
        if(isInChickenDimention){
            cir.setReturnValue(true);
            return;
        }
    }
*/
    @Inject(method = "interact", at = @At("HEAD"), cancellable = true)
    public void interact(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        if(isInChickenDimention){
            entity = new EnderZoglinEntityGL(ModEntities.ENDER_ZOGLIN, world);
            //cir.setReturnValue(ActionResult.FAIL);
        }
    }

    @Override
    public void setHealth(float health) {

        if(isInChickenDimention()){
            //super.setHealth(getHealth());
        }
        else {
            super.setHealth(health);
        }

    }

}
