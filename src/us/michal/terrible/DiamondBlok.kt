package us.michal.terrible.us.michal.terrible

import org.bukkit.Bukkit.getServer
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.plugin.java.JavaPlugin


class DiamondBlok(val plugin: JavaPlugin) : Listener {

    infix fun Location.equalsBlock(other: Location?)=
        this.blockX == other?.blockX && this.blockY == other?.blockY && this.blockZ == other?.blockZ

    @EventHandler
    public fun onMove(e: PlayerMoveEvent){
        val badmats = listOf(Material.AIR, Material.GRASS, Material.CAVE_AIR, Material.VOID_AIR, Material.RAIL, Material.ACTIVATOR_RAIL, Material.DETECTOR_RAIL, Material.POWERED_RAIL)

        if(!(e.from equalsBlock e.to) && e.to!!.block.getLocation().subtract(0.0,1.0,0.0).block.blockData.material !in badmats){
            val mat = e.to!!.block

            e.player.sendBlockChange(e.to!!.block.getLocation().subtract(0.0,1.0,0.0), Material.DIAMOND_BLOCK.createBlockData())

            val scheduler = getServer().scheduler
            scheduler.scheduleSyncDelayedTask(plugin, Runnable (){
                val ourMat = mat.getBlockData()
                e.player.sendBlockChange(e.to!!.block.getLocation().subtract(0.0,1.0,0.0), e.to!!.block.getLocation().subtract(0.0,1.0,0.0).block.blockData)
            }, 20*5)
        }


    }


}