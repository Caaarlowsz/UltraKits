package UltraKits.Habilidades;

import org.bukkit.plugin.*;
import UltraKits.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.util.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class Anchor implements Listener
{
    public Plugin plugin;
    
    public Anchor(final Main plugin) {
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onPlayerHitAnchor(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        if (!(e.getDamager() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        final Player a = (Player)e.getDamager();
        if (Main.anchor.contains(p.getName())) {
            p.setVelocity(new Vector());
            p.playSound(p.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.setVelocity(new Vector());
                }
            }, 1L);
        }
        if (Main.anchor.contains(a.getName())) {
            a.playSound(a.getLocation(), Sound.ANVIL_BREAK, 4.0f, 4.0f);
            p.setVelocity(new Vector());
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                @Override
                public void run() {
                    p.setVelocity(new Vector());
                }
            }, 1L);
        }
    }
}
