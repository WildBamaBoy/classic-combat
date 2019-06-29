package com.wildbamaboy.classiccombat;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.lang.reflect.Field;

@Mod("classiccombat")
public class ClassicCombat
{
    /** Grab the field used for the swing tick cooldown instead of looking it up for each player. It's all the same. */
    private static Field targetField = LivingEntity.class.getDeclaredFields()[26];
    static { targetField.setAccessible(true); }

    public ClassicCombat() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if (event.getEntity() instanceof ServerPlayerEntity) {
            ServerPlayerEntity player = (ServerPlayerEntity) event.getEntity();
            try {
                if (targetField.getInt(player) < 9999) {
                    targetField.set(player, 9999);
                }
            } catch (IllegalAccessException e) {
                //pass
            }
        }
    }
}
