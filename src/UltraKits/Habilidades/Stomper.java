package UltraKits.Habilidades;

import org.bukkit.event.entity.*;
import UltraKits.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;
import org.bukkit.event.*;

public class Stomper implements Listener
{
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerStomp(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }
        if (Main.stomper.contains(p.getName())) {
            for (final Entity ent : p.getNearbyEntities(5.0, 2.0, 5.0)) {
                if (ent instanceof Player) {
                    final Player plr = (Player)ent;
                    if (e.getDamage() <= 4.0) {
                        e.setCancelled(true);
                        return;
                    }
                    if (plr.isSneaking()) {
                        plr.damage(6.0, (Entity)p);
                        plr.sendMessage(ChatColor.DARK_RED + "Voce foi pisoteado por: " + ChatColor.RED + p.getName());
                    }
                    else {
                        plr.damage(e.getDamage(), (Entity)p);
                        plr.sendMessage(ChatColor.DARK_RED + "Voce foi pisoteado por: " + ChatColor.RED + p.getName());
                    }
                }
            }
            e.setDamage(4.0);
        }
    }
}