package UltraKits.Habilidades;

import UltraKits.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.plugin.*;
import org.bukkit.event.entity.*;
import org.bukkit.event.*;

public class Pyro implements Listener
{
    private Main plugin;
    public int cooldown;
    
    public Pyro(final Main instance) {
        this.cooldown = 15;
        this.plugin = instance;
    }
    
    @EventHandler
    public void onPlayerInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (Main.pyro.contains(p.getName()) && (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_AIR) && p.getItemInHand().getType() == Material.FIREWORK_CHARGE) {
            if (Main.reload.contains(p.getName())) {
                p.sendMessage(ChatColor.RED + "Recarregando!");
            }
            else if (Main.areaPvP(p)) {
                p.playSound(p.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
                p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.SMOKE, 5);
                p.getLocation().getWorld().playEffect(p.getLocation(), Effect.GHAST_SHOOT, 1);
                p.getLocation().getWorld().playEffect(p.getLocation(), Effect.GHAST_SHRIEK, 1);
                final Fireball f = (Fireball)e.getPlayer().launchProjectile((Class)Fireball.class);
                f.setIsIncendiary(false);
                f.setYield(0.0f);
                Main.reload.add(p.getName());
                p.setExp(0.0f);
                p.setLevel(0);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.pyro.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 20L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.pyro.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 40L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.pyro.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 60L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.pyro.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 80L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.pyro.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 100L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.pyro.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 120L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.pyro.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 140L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.pyro.contains(p.getName())) {
                            p.setExp(1.0f);
                            p.playSound(p.getLocation(), Sound.ITEM_PICKUP, 1.0f, 1.0f);
                            Main.reload.remove(p.getName());
                        }
                    }
                }, 160L);
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
        }
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDamage(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Fireball) {
            final Player p = (Player)e.getEntity();
            final Fireball f = (Fireball)e.getDamager();
            if (f.getShooter() instanceof Player) {
                final Player s = (Player)f.getShooter();
                if (Main.pyro.contains(s.getName())) {
                    p.setFireTicks(100);
                }
            }
        }
    }
}
