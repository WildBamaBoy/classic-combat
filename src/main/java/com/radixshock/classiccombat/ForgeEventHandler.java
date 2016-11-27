package com.radixshock.classiccombat;

import java.lang.reflect.Field;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ForgeEventHandler 
{
	/** Grab the field used for the swing tick cooldown instead of looking it up for each player. It's all the same. */
	private static Field targetField = EntityLivingBase.class.getDeclaredFields()[23];
	
	static
	{
		//Don't waste time setting accessibility each time. Not required.
		targetField.setAccessible(true);
	}
	
	/**
	 * When a player updates, their swing tick cooldown will be set to 20 if it is less than 20.
	 * This effectively removes the cooldown aspect of gameplay, reverting the combat system
	 * back to a pseudo-classic feel.
	 * 
	 * Very very fast, measured to be completed in about 0.005 milliseconds.
	 */
	@SubscribeEvent
	public void handleLivingUpdate(LivingUpdateEvent event)
	{
		try
		{
			if (event.getEntity() instanceof EntityPlayerMP)
			{
				EntityPlayerMP player = (EntityPlayerMP)event.getEntity();

				if (targetField.getInt(player) < 20)
				{
					targetField.set(player, 20);
				}
			}
		}
		
		catch (Exception e)
		{
			//Can't foresee any exceptions, so just throw them out.
		}
	}
}
