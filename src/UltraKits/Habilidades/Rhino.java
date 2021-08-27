package UltraKits.Habilidades;

import org.bukkit.event.player.*;
import UltraKits.*;
import org.bukkit.util.*;
import org.bukkit.scheduler.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.potion.*;
import java.util.*;
import org.bukkit.event.*;

public class Rhino implements Listener
{
    public static HashMap<String, BukkitTask> tasks;
    
    static {
        Rhino.tasks = new HashMap<String, BukkitTask>();
    }
    
    @EventHandler
    public void carregar(final PlayerToggleSneakEvent e) {
        final Player p = e.getPlayer();
        if (Main.rhino.contains(p.getName())) {
            if (p.isSneaking()) {
                if (Rhino.tasks.containsKey(p.getName())) {
                    final BukkitTask task = Rhino.tasks.get(p.getName());
                    task.cancel();
                    Rhino.tasks.remove(p.getName());
                }
                if (p.getLevel() >= 1) {
                    p.setLevel(0);
                    p.setExp(0.0f);
                    final List<Entity> nearby = (List<Entity>)p.getNearbyEntities(10.0, 10.0, 10.0);
                    for (final Entity en : nearby) {
                        if (en instanceof LivingEntity) {
                            en.setVelocity(en.getVelocity().multiply(-2));
                            en.setVelocity(new Vector(en.getVelocity().getX(), 1.0, en.getVelocity().getZ()));
                            ((LivingEntity)en).damage(6.0, (Entity)p);
                        }
                    }
                    new BukkitRunnable() {
                        int distance = 0;
                        
                        public void run() {
                            if (this.distance < 5) {
                                p.setVelocity(p.getLocation().getDirection().multiply(2.0));
                                ++this.distance;
                            }
                            else {
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Main.plugin, 0L, 5L);
                    new BukkitRunnable() {
                        int n = 0;
                        
                        public void run() {
                            if (this.n < 4) {
                                p.playSound(p.getLocation(), Sound.HORSE_GALLOP, 50.0f, 1.0f);
                                ++this.n;
                            }
                            else {
                                this.cancel();
                            }
                        }
                    }.runTaskTimer(Main.plugin, 0L, 5L);
                    return;
                }
                p.setLevel(0);
                p.setExp(0.0f);
            }
            else {
                p.setExp(0.0f);
                final BukkitTask id = new BukkitRunnable() {
                    public void run() {
                        if (p.getLevel() < 1) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.HORSE_SOFT, 1.0f, 1.0f);
                            p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 20, 0));
                        }
                        else {
                            p.playSound(p.getLocation(), Sound.HORSE_IDLE, 1.0f, 1.0f);
                            p.setLevel(1);
                            p.giveExp(-1);
                            this.cancel();
                        }
                    }
                }.runTaskTimer(Main.plugin, 0L, 20L);
                Rhino.tasks.put(p.getName(), id);
            }
        }
    }
}
