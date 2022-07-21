package net.vukrosic.custommobswordsmod.item.custom;

import net.minecraft.client.MinecraftClient;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ClickType;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.Random;

public class InfinityBowItem extends BowItem {
    public InfinityBowItem(Settings settings) {
        super(settings);
    }







    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

        int arrowAmount = 40;

        for(int i = 0; i < arrowAmount / 3; i++){
            for(int j = 0; j < arrowAmount / 3; j++) {
                Random rand1 = new Random();
                float randPitch = rand1.nextFloat() * .7f;
                Random rand2 = new Random();
                float randYaw = rand2.nextFloat() * .7f;
                shootArrow(world, user, hand, (i  * .7f + randPitch), (j * .7f + randYaw));
            }

        }


        /*
        ItemStack itemStack = user.getStackInHand(hand);

        user.setCurrentHand(hand);
        System.out.println("1111111111");
        System.out.println("itemStack = " + itemStack.getName());
        //firing = true;
        return TypedActionResult.consume(itemStack);*/




        return TypedActionResult.success(user.getStackInHand(hand));
    }




    public void shootArrow(World world, PlayerEntity user, Hand hand, float deltaPitch, float deltaYaw){


        PlayerEntity playerEntity = user;
        ItemStack stack = user.getStackInHand(hand);
        boolean bl = playerEntity.getAbilities().creativeMode || EnchantmentHelper.getLevel(Enchantments.INFINITY, stack) > 0;
        ItemStack itemStack = playerEntity.getArrowType(stack);
        if (!itemStack.isEmpty() || bl) {
            if (itemStack.isEmpty()) {
                itemStack = new ItemStack(Items.ARROW);
            }

            int i = this.getMaxUseTime(stack) - 0;
            float f = getPullProgress(i);
            if (!((double)f < 0.1)) {
                boolean bl2 = bl && itemStack.isOf(Items.ARROW);
                if (!world.isClient) {
                    ArrowItem arrowItem = (ArrowItem)(itemStack.getItem() instanceof ArrowItem ? itemStack.getItem() : Items.ARROW);
                    PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world, itemStack, playerEntity);
                    System.out.println("deltaPitch = " + deltaPitch + "    " + "deltaYaw = " + deltaYaw);
                    persistentProjectileEntity.setVelocity(playerEntity, playerEntity.getPitch() + deltaPitch, playerEntity.getYaw() + deltaYaw, 0.0F, f * 3.0F, 1.0F);
                    if (f == 1.0F) {
                        persistentProjectileEntity.setCritical(true);
                    }

                    int j = EnchantmentHelper.getLevel(Enchantments.POWER, stack);
                    if (j > 0) {
                        persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double)j * 0.5 + 0.5);
                    }

                    int k = EnchantmentHelper.getLevel(Enchantments.PUNCH, stack);
                    if (k > 0) {
                        persistentProjectileEntity.setPunch(k);
                    }

                    if (EnchantmentHelper.getLevel(Enchantments.FLAME, stack) > 0) {
                        persistentProjectileEntity.setOnFireFor(100);
                    }

                    stack.damage(1, playerEntity, (p) -> {
                        p.sendToolBreakStatus(playerEntity.getActiveHand());
                    });
                    if (bl2 || playerEntity.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                        persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                    }

                    world.spawnEntity(persistentProjectileEntity);
                }

                //if(deltaPitch < 50)
                world.playSound((PlayerEntity)null, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                /*if (!bl2 && !playerEntity.getAbilities().creativeMode) {
                    itemStack.decrement(1);
                    if (itemStack.isEmpty()) {
                        playerEntity.getInventory().removeOne(itemStack);
                    }
                }*/

                playerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
            }
        }


        }
    }




