package com.coryleach.legends.lair;

import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import net.minecraft.server.EntityLiving;
//import net.minecraft.server.EntityTypes;
//import net.minecraft.server.ItemInWorldManager;
//import net.minecraft.server.MathHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.WorldServer;
import org.bukkit.World;
import org.bukkit.Server;
import org.bukkit.craftbukkit.CraftWorld;
import org.bukkit.craftbukkit.CraftServer;

public class LairMonsterSpawner {

    protected static WorldServer GetWorldServer(World world) {
        try {
            CraftWorld w = (CraftWorld) world;
            Field f;
            f = CraftWorld.class.getDeclaredField("world");

            f.setAccessible(true);
            return (WorldServer) f.get(w);
            
            //f = EntityLiving.class.getDeclaredField("texture");
            //f.setAccessible(true);
            
        } catch (Exception e) {
           e.printStackTrace();
        }

        return null;
    }
    
    protected static MinecraftServer GetMinecraftServer(Server server) {

        if (server instanceof CraftServer) {
            CraftServer cs = (CraftServer) server;
            Field f;
            try {
                f = CraftServer.class.getDeclaredField("console");
            } catch (NoSuchFieldException ex) {
                return null;
            } catch (SecurityException ex) {
                return null;
            }
            MinecraftServer ms;
            try {
                f.setAccessible(true);
                ms = (MinecraftServer) f.get(cs);
            } catch (IllegalArgumentException ex) {
                return null;
            } catch (IllegalAccessException ex) {
                return null;
            }
            return ms;
        }
        return null;
        
    }
	
}
