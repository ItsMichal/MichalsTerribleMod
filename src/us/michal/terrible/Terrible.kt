package us.michal.terrible

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.Bukkit
import us.michal.terrible.us.michal.terrible.DiamondBlok

class Terrible : JavaPlugin() {
        companion object {
                var instance: Terrible? = null
                private set
        }

        override fun onEnable(){
                logger.info("Boilerplate started")
                Bukkit.getPluginManager().registerEvents(DiamondBlok(this), this)
                Bukkit.getPluginManager().registerEvents(CakeNuke(), this)
        }
}