package com.coryleach.legends.lair;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.entity.*;

public class LairEntityListener extends EntityListener {
	private final Lair plugin;
	
	public LairEntityListener(final Lair plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		
	}
	
	@Override
	public void onEntityDeath(EntityDeathEvent event) {
		
	}
	
	@Override
	public void onEntityDamage(EntityDamageEvent event) {
		
		if ( event.isCancelled() ) {
			return;
		}
		
		int damage = event.getDamage();
		LivingEntity damagedEntity = (LivingEntity)event.getEntity();
		
		if ( EntityDamageEvent.DamageCause.ENTITY_ATTACK == event.getCause() ) {
			
			LivingEntity attackingEntity = null;
			
			if ( event instanceof EntityDamageByEntityEvent ) {
				EntityDamageByEntityEvent damageByEntityEvent = (EntityDamageByEntityEvent)event;
				attackingEntity = (LivingEntity)damageByEntityEvent.getDamager();
			} else {
		        plugin.log.info("ENTITY_ATTACK event but not EntityDamageByEntityEvent?!?!");
				return;
			}
			
			//TODO: Do Special caluclations based on class or whatever to modify damage
			
			//Send results to Players if entities are players
			if ( damagedEntity instanceof Player ) {
				
				Player player = (Player)damagedEntity;
				String damageString = ChatColor.RED + "You have been hit for " + Integer.toString(damage) + " damage";
				player.sendMessage(damageString);
				
			}
			
			if ( attackingEntity instanceof Player ) {
				
				Player player = (Player)attackingEntity;
				String damageString = "Hit " + ChatColor.AQUA + Integer.toString(damage);
				damageString = damageString + ChatColor.WHITE + " -> " + Integer.toString(damagedEntity.getHealth());
				player.sendMessage(damageString);
				
			}
			
		} else {

		}
				
		
	}
	
	
	

}
