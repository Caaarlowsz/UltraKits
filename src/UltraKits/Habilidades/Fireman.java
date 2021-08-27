package UltraKits.Habilidades;

import org.bukkit.plugin.*;
import UltraKits.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.*;
import org.bukkit.entity.*;

public class Fireman implements Listener
{
    public Plugin plugin;
    
    public Fireman(final Main plugin) {
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler
    public void damage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (Main.fireman.contains(p.getName()) && (e.getCause() == EntityDamageEvent.DamageCause.LAVA || e.getCause() == EntityDamageEvent.DamageCause.FIRE || e.getCause() == EntityDamageEvent.DamageCause.FIRE_TICK)) {
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void Fogo(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            final Player p = (Player)e.getDamager();
            if (Main.fireman.contains(p.getName()) && p.getInventory().getItemInHand() != null && p.getInventory().getItemInHand().getType() == Material.STONE_SWORD && e.getEntity() instanceof LivingEntity) {
                final LivingEntity en = (LivingEntity)e.getEntity();
                if (en.isDead()) {
                    return;
                }
                en.setFireTicks(100);
            }
        }
    }
}
