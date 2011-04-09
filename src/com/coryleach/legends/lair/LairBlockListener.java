package com.coryleach.legends.lair;

import org.bukkit.event.block.*;

/**
 * Legends Lair block listener
 * @author coryleach
 */
public class LairBlockListener extends BlockListener {
    private final Lair plugin;

    public LairBlockListener(final Lair plugin) {
        this.plugin = plugin;
    }

    /*
    @Override
    public void onBlockPhysics(BlockPhysicsEvent event) {
        Block block = event.getBlock();

        if ((block.getType() == Material.SAND) || (block.getType() == Material.GRAVEL)) {
            Block above = block.getFace(BlockFace.UP);
            if (above.getType() == Material.IRON_BLOCK) {
                event.setCancelled(true);
            }
        }
    }
	//*/
    
    @Override
    public void onBlockCanBuild(BlockCanBuildEvent event) {
        plugin.log.info("Can Build Event");
    	/*Material mat = event.getMaterial();

        if (mat.equals(Material.CACTUS)) {
            event.setBuildable(true);
        }*/
    	
    }
    
}


