package UltraKits.Habilidades;

import org.bukkit.plugin.*;
import org.bukkit.block.*;
import UltraKits.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import com.sk89q.worldguard.protection.flags.*;
import com.sk89q.worldguard.bukkit.*;
import com.sk89q.worldguard.protection.managers.*;
import com.sk89q.worldguard.protection.*;
import org.bukkit.util.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import java.util.*;

public class Jumper implements Listener
{
    public Plugin plugin;
    int cool;
    public static ArrayList<String> jumperkit;
    public static ArrayList<String> kitjumper;
    public static HashMap<String, Long> jcool;
    ArrayList<Block> blockList;
    
    static {
        Jumper.jumperkit = new ArrayList<String>();
        Jumper.kitjumper = new ArrayList<String>();
        Jumper.jcool = new HashMap<String, Long>();
    }
    
    public Jumper(final Main plugin) {
        this.cool = 10;
        this.blockList = new ArrayList<Block>();
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJumper(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (Main.jumper.contains(p.getName())) {}
        if (p.getItemInHand().getType() != Material.PUMPKIN) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            final WorldGuardPlugin worldGuard = Main.getWorldGuard();
            final RegionManager regionManager = worldGuard.getRegionManager(p.getWorld());
            final ApplicableRegionSet set = regionManager.getApplicableRegions(p.getLocation());
            if (set.allows(DefaultFlag.PVP)) {
                if (Jumper.jcool.containsKey(p.getName())) {
                    final long seconds = Jumper.jcool.get(p.getName()) / 1000L + this.cool - System.currentTimeMillis() / 1000L;
                    e.setCancelled(true);
                    p.updateInventory();
                    p.sendMessage(ChatColor.RED + "Voce pode usar sua habilidade novamente em " + seconds + " segundos");
                    return;
                }
                Jumper.jcool.put(p.getName(), System.currentTimeMillis());
                e.setCancelled(true);
                p.updateInventory();
                final Vector v = p.getLocation().getDirection().multiply(0).setY(2.0);
                p.setVelocity(v);
                Jumper.jumperkit.add(p.getName());
                Jumper.kitjumper.add(p.getName());
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this.plugin, (Runnable)new Runnable() {
                    @Override
                    public void run() {
                        Jumper.jcool.remove(p.getName());
                    }
                }, 200L);
            }
            else {
                p.sendMessage(ChatColor.DARK_GREEN + "Voce pode usar sua habilidade somente em PvP");
            }
        }
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJumperLeftClick(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (Main.jumper.contains(p.getName())) {}
        if (p.getItemInHand().getType() != Material.PUMPKIN) {
            return;
        }
        if (e.getAction() != Action.LEFT_CLICK_AIR) {
            return;
        }
        if (Jumper.jumperkit.contains(p.getName()) && Jumper.kitjumper.contains(p.getName())) {
            e.setCancelled(true);
            Jumper.kitjumper.remove(p.getName());
            final Vector v = p.getLocation().getDirection().multiply(2.0);
            p.setVelocity(v);
        }
    }
    
    @EventHandler
    public void onPlayerJumperFall(final EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            return;
        }
        final Player p = (Player)e.getEntity();
        if (e.getCause() != EntityDamageEvent.DamageCause.FALL) {
            return;
        }
        if (!Jumper.jumperkit.contains(p.getName())) {
            return;
        }
        final WorldGuardPlugin worldGuard = Main.getWorldGuard();
        final RegionManager regionManager = worldGuard.getRegionManager(p.getWorld());
        final ApplicableRegionSet set = regionManager.getApplicableRegions(p.getLocation());
        if (set.allows(DefaultFlag.PVP)) {
            for (final Entity plr : p.getNearbyEntities(8.0, 8.0, 8.0)) {
                if (plr instanceof Player) {
                    final Player pla = (Player)plr;
                    final Vector v = p.getLocation().getDirection().multiply(0).setY(2.0);
                    pla.setVelocity(v);
                    pla.playSound(pla.getLocation(), Sound.EXPLODE, 4.0f, 4.0f);
                    p.playSound(p.getLocation(), Sound.EXPLODE, 4.0f, 4.0f);
                }
            }
            Jumper.jumperkit.remove(p.getName());
            e.setDamage(9.0);
            return;
        }
        p.sendMessage(ChatColor.RED + "Voce so pode usar sua habilidade em area PvP!");
    }
}
