package com.coryleach.legends.lair;

import org.bukkit.command.*;
import org.bukkit.entity.*;

public class LairListCommand extends Command {

	private final Lair plugin;
	
	public LairListCommand(final Lair plugin, String command) {
		super(command);
		this.plugin = plugin;
	}
	
	/*@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] split ) {
		
		return false;
		
	}*/
	
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		
		if ( !(sender instanceof Player) ) {
			return false;
		}
		
		Player player = (Player)sender;
		
		plugin.log.info("lairlist");
		player.sendMessage("Exceuted List Command");
		
		return true;
		
	}
	
}
