package com.coryleach.legends.lair;

import java.lang.reflect.Field;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import net.minecraft.server.EntityLiving;
import org.bukkit.craftbukkit.entity.CraftLivingEntity;;

public class LairSpawnCommand extends Command {

	private final Lair plugin;
	
	public LairSpawnCommand(final Lair plugin,String command) {
		super(command);
		this.plugin = plugin;
	}
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		
		if ( !(sender instanceof Player) ) {
			return false;
		}
		
		Player player = (Player)sender;
		World world = player.getWorld();
		
		LivingEntity entity = world.spawnCreature(player.getLocation() , CreatureType.ZOMBIE);
		
		
		if ( entity instanceof CraftLivingEntity ) {
			
			try {
				
				CraftLivingEntity craftEntity = (CraftLivingEntity)entity;
				
				//Set Entity Texture to cow (Even though it's actually a zombie!!)
				Field f;
				f = EntityLiving.class.getDeclaredField("texture");
				f.setAccessible(true);
				f.set(craftEntity.getHandle(), "/mob/cow.png");
				plugin.log.info("Set zombie texture to /mob/cow.png");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			plugin.log.info("LivingEntity not CraftLivingEntity");
		}
		
		int health = 1;
		
		//Takes the enemy health as an argument
		if ( args.length > 0 ) {
			
			//Set the health to int val of first arg
			health = Integer.parseInt(args[0]);
			if ( health < 1 ) {
				health = 1;
			}
			
		}
		
		entity.setHealth(health);
		
		plugin.log.info("Spawning with health " + Integer.toString(health));
		player.sendMessage("You've spawned a zombie!!");
		
		/*if ( livingEntity instanceof Monster) {
			Monster
		}*/
		
		return true;
		
	}
	
}
