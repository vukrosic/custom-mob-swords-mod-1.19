package net.vukrosic.custommobswordsmod.networking;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.vukrosic.custommobswordsmod.CustomMobSwordsMod;
import net.vukrosic.custommobswordsmod.networking.packet.C2S.DamageToolsC2SPacket;
import net.vukrosic.custommobswordsmod.networking.packet.C2S.ExampleC2SPacket;
import net.vukrosic.custommobswordsmod.networking.packet.C2S.RemoveItemsC2SPacket;
import net.vukrosic.custommobswordsmod.networking.packet.S2C.FireInfectedS2CPacket;
import net.vukrosic.custommobswordsmod.networking.packet.C2S.HasChickenEffectAndDimC2SPacket;
import net.vukrosic.custommobswordsmod.networking.packet.S2C.HasChickenEffectS2CPacket;
import net.vukrosic.custommobswordsmod.networking.packet.S2C.IsInNateDim;

public class ModMessages {
    public static final Identifier FIRE_INFECTED_ID = new Identifier(CustomMobSwordsMod.MOD_ID, "fire_infected");
    public static final Identifier FIRE_INFECTED_SYNC_ID = new Identifier(CustomMobSwordsMod.MOD_ID, "fire_infected_sync");
    public static final Identifier EXAMPLE_ID = new Identifier(CustomMobSwordsMod.MOD_ID, "example");
    public static final Identifier CHICKEN_EFFECT_GET_ID = new Identifier(CustomMobSwordsMod.MOD_ID, "chicken_effect_get");
    public static final Identifier CHICKEN_EFFECT_POST_ID = new Identifier(CustomMobSwordsMod.MOD_ID, "chicken_effect_post");
    public static final Identifier NATE_DIM_ID = new Identifier(CustomMobSwordsMod.MOD_ID, "nate_dim");

    public static final Identifier CONVERT_INVENTORY = new Identifier(CustomMobSwordsMod.MOD_ID, "convert_inventory");
    public static final Identifier DAMAGE_TOOLS = new Identifier(CustomMobSwordsMod.MOD_ID, "damage_tools");

    public static void registerC2SPacket(){
        ServerPlayNetworking.registerGlobalReceiver(EXAMPLE_ID, ExampleC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(CHICKEN_EFFECT_GET_ID, HasChickenEffectAndDimC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(CHICKEN_EFFECT_GET_ID, HasChickenEffectAndDimC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(CONVERT_INVENTORY, RemoveItemsC2SPacket::receive);
        ServerPlayNetworking.registerGlobalReceiver(DAMAGE_TOOLS, DamageToolsC2SPacket::receive);
    }

    public static void registerS2CPacket(){
        ClientPlayNetworking.registerGlobalReceiver(FIRE_INFECTED_ID, FireInfectedS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(CHICKEN_EFFECT_POST_ID, HasChickenEffectS2CPacket::receive);
        ClientPlayNetworking.registerGlobalReceiver(NATE_DIM_ID, IsInNateDim::receive);
    }
}
