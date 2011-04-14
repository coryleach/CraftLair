/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.coryleach.legends.lair;

import org.bukkit.entity.*;

/**
 *
 * @author cory
 */
public class EditSession {

    protected Player player;
    protected Dungeon dungeon;

    public EditSession(Player player, Dungeon dungeon) {

        this.player = player;
        this.dungeon = dungeon;

    }

    public Player getPlayer() {
        return player;
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

}
