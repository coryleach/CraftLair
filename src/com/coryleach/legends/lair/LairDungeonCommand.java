/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coryleach.legends.lair;

//import java.util.*;
import com.sk89q.minecraft.util.commands.*;
import com.sk89q.worldedit.bukkit.selections.*;
import org.bukkit.entity.Player;

/**
 *
 * @author cory
 */
public class LairDungeonCommand {
        @Command(
            aliases = {"dungeoncreate", "dcreate"},
            usage = "<name> [properties]",
            flags = "",
            desc = "Create a new Dungeon.",
            min = 1,
            max = 1
        )
        @CommandPermissions({"lair.dungeon.create"})
        public static void dungeonCreate(CommandContext args, Lair plugin, Player player) {

            //This command should create a dungeon and open an editing session
            String name = args.getString(0);
            player.sendMessage("Creating dungeon with name " + name);
            Dungeon dungeon = new Dungeon(name);
            plugin.addDungeon(dungeon);

        }

        @Command(
            aliases = {"dungeonedit", "dedit"},
            usage = "<name> [properties]",
            flags = "",
            desc = "Edit a Dungeon.",
            min = 1,
            max = 3
        )
        @CommandPermissions({"lair.dungeon.edit"})
        public static void dungeonEdit(CommandContext args, Lair plugin, Player player) {

            //This function should start an editing session for the specified dungeon
            player.sendMessage("Dungeon Edit");

        }

        @Command(
            aliases = {"dungeonsubzone", "dsubzone"},
            usage = "<subcommand> [properties]",
            flags = "",
            desc = "Edit a Dungeon.",
            min = 0,
            max = 3
        )
        @CommandPermissions({"lair.dungeon.edit"})
        public static void dungeonSubzone(CommandContext args, Lair plugin, Player player) {

            //This function should start an editing session for the specified dungeon
            player.sendMessage("Dungeon Add Subzone");

            //subcommands:
            // add
            // remove
            // list
            // current
            // select

            //Add current subzone to dungeon
            //Can only add a subzone that contains no part of any other subzone
            CuboidSelection selection = plugin.getSelection(player);

            //Check to see if this selection intersects with any existing

            //If Not then we can add it
            

        }

        @Command(
            aliases = {"dungeonlist", "dlist"},
            usage = "[page]",
            flags = "",
            desc = "List Dungeons.",
            min = 0,
            max = 1
        )
        @CommandPermissions({"lair.dungeon.list"})
        public static void dungeonList(CommandContext args, Lair plugin, Player player) {

            String[] dungeons = plugin.getDungeonList();

            //***Magical Pagination!!!!!***
            int totalDungeons = dungeons.length;
            int dungeonsPerPage = 10;
            int totalPages = (int)Math.ceil((double)totalDungeons/(double)dungeonsPerPage);
            int page = 1;

            //Set the page # if there is an extra argument
            if ( args.argsLength() > 0 ) {
                page = args.getInteger(0);
            }

            //If page is higher than max set to last page
            if ( page > totalPages ) {
                page = totalPages;
            }

            //If page number is negative set to first page
            if ( page <= 0 ) {
                page = 1;
            }

            //Print Page Info
            player.sendMessage("Page " + Integer.toString(page) + " of " + Integer.toString(totalPages));

            //Print Dungeon Names
            int startIndex = (page - 1) * dungeonsPerPage;
            int stopIndex = startIndex + 10;

            for ( int i = 0; i < stopIndex && i < dungeons.length; i++ ) {

                player.sendMessage(dungeons[i].toString());

            }

            //Print Total Dungeons
            player.sendMessage("Total Dungeon(s): " + Integer.toString(totalDungeons));

        }
        
}

//If you could only see the beast you made of me
