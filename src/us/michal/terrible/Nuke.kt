package us.michal.terrible

import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.util.Vector


class CakeNuke : Listener{
    @EventHandler
    public fun onInteract(e: PlayerInteractEvent){

        if(e.action.equals(Action.RIGHT_CLICK_BLOCK) && e.clickedBlock!!.blockData.material == Material.CAKE){
            e.player.getLocation()!!.world!!.createExplosion(e.player.getLocation()!!.add(0.0, 10.0,0.0), 4.0f, true, true)

            var loc: Location = e.player.getLocation()!!
            val w = e.player.getLocation()!!.world!!
            val diameter = 10 //Diameter of the circle centered on loc

            val gunnar = listOf(Vector(-5, 0, -5),
                                Vector(-4, 0, -5),
                                Vector(-5, 0, -4),
                                Vector(-5, 0, -3),
                                Vector(-5, 0, -2), //G
                                Vector(-4,0,-2),
                                Vector(-3,0,-2),
                                Vector(-3, 0, -3),

                                Vector(-1, 0, -5),
                                Vector(-1, 0, -4),
                                Vector(-1, 0,-3),
                                Vector(-1, 0, -2),
                                Vector(0, 0, -2),
                                Vector(1,0,-5),
                                Vector(1, 0, -4), //U
                                Vector(1, 0, -3),
                                Vector(1, 0, -2),

                                Vector(3, 0, -2),
                                Vector(3, 0, -3),
                                Vector(3, 0, -4),
                                Vector(3,0,-5),
                                Vector(4, 0, -5),
                                Vector(5, 0, -5),
                                Vector(5, 0, -4),
                                Vector(5,0,-3), //N
                                Vector(5,0,-2),

                                Vector(-4, 0 , 1),
                                Vector(-3,0,1),
                                Vector(-2, 0, 1),
                                Vector(-2,0,2),
                                Vector(-3, 0, 3),
                                Vector(-4,0,4),
                                Vector(-3, 0, 4), //2
                                Vector(-2,0,4),

                                Vector(0,0,1),
                                Vector(0,0,2),
                                Vector(0, 0,3),
                                Vector(0,0,4),
                                Vector(1, 0, 1),
                                Vector(1,0,4),
                                Vector(2,0,1),
                                Vector(2,0,2), //0
                                Vector(2, 0,3),
                                Vector(2,0,4),

                                Vector(4, 0, 1),
                                Vector(4, 0, 2), //1
                                Vector(4,0,4)
                )

            for (i in gunnar) {
                loc = e.player.location
                val newLocation: Location = loc.add(i.multiply(Vector(2.0, 0.0, -2.0)).add(Vector(0.0,15.0,-15.0)).rotateAroundX(-45.0).add(Vector(0.0,30.0,0.0)))
                var fw = loc.world!!.spawnEntity(newLocation, EntityType.FIREWORK) as Firework
                var fwm = fw.fireworkMeta
                fwm.power = 2
                fwm.addEffect(FireworkEffect.builder().trail(true).withFade(Color.ORANGE).withColor(Color.ORANGE).flicker(true).with(FireworkEffect.Type.BALL).build())

                fw.fireworkMeta = fwm
                fw.detonate()
            }
            e.player.playSound(e.player.location, Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f)
        }
    }

}