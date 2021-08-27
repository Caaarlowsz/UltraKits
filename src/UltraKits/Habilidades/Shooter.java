package UltraKits.Habilidades;

import UltraKits.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.util.*;
import org.bukkit.metadata.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.potion.*;
import java.util.*;

public class Shooter implements Listener
{
    private Main plugin;
    
    public Shooter(final Main instance) {
        this.plugin = instance;
    }
    
    @EventHandler(priority = EventPriority.NORMAL)
    public void onPlayerInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (Main.whister.contains(p.getName())) {
            if (e.getAction() != Action.RIGHT_CLICK_AIR) {
                return;
            }
            if (e.getItem().getType() != Material.NETHER_STAR) {
                return;
            }
            p.removePotionEffect(PotionEffectType.WITHER);
            final Vector velo1 = p.getLocation().getDirection().normalize().multiply(5);
            velo1.add(new Vector(Math.random() * 0.0 + 0.0, 0.0, 0.0));
            if (Main.reload.contains(p.getName())) {
                p.sendMessage(ChatColor.RED + "Recarregando!");
            }
            else if (Main.areaPvP(p)) {
                p.playSound(p.getLocation(), Sound.EXPLODE, 1.0f, 1.0f);
                p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
                p.getLocation().getWorld().playEffect(p.getLocation(), Effect.GHAST_SHOOT, 1);
                p.getLocation().getWorld().playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);
                final WitherSkull skull = (WitherSkull)p.launchProjectile((Class)WitherSkull.class);
                skull.setVelocity(velo1);
                skull.setMetadata("shooter", (MetadataValue)new FixedMetadataValue(Main.plugin, (Object)true));
                Main.reload.add(p.getName());
                p.setExp(0.0f);
                p.setLevel(0);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.whister.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                        }
                    }
                }, 20L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.whister.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 40L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.whister.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 60L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.whister.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 80L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.whister.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 100L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.whister.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 120L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.whister.contains(p.getName())) {
                            p.giveExp(2);
                            p.playSound(p.getLocation(), Sound.CLICK, 1.0f, 1.0f);
                        }
                    }
                }, 140L);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        if (Main.whister.contains(p.getName())) {
                            p.setExp(1.0f);
                            p.playSound(p.getLocation(), Sound.WITHER_HURT, 1.0f, 1.0f);
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
    
    @EventHandler
    public void onHit(final ProjectileHitEvent e) {
        if (e.getEntity() instanceof WitherSkull) {
            final WitherSkull s = (WitherSkull)e.getEntity();
            if (s.hasMetadata("shooter")) {
                final List<Entity> nearby = (List<Entity>)e.getEntity().getNearbyEntities(5.0, 5.0, 5.0);
                for (final Entity en : nearby) {
                    if (en instanceof Player) {
                        final Player p = (Player)en;
                        if (Main.whister.contains(p.getName())) {
                            continue;
                        }
                        p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 400, 0));
                        if (!(s.getShooter() instanceof Player)) {
                            continue;
                        }
                        final Player sh = (Player)s.getShooter();
                        p.damage(4.0, (Entity)sh);
                    }
                }
            }
        }
    }
}
