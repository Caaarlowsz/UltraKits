package UltraKits.Habilidades;

import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import UltraKits.*;
import java.util.concurrent.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.scheduler.*;
import org.bukkit.block.*;

public class SpiderMan implements Listener
{
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Snowball> teias;
    
    static {
        SpiderMan.cooldown = new HashMap<String, Long>();
        SpiderMan.teias = new HashMap<String, Snowball>();
    }
    
    @EventHandler
    public void lancar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.spiderman.contains(p.getName()) && p.getItemInHand().getType() == Material.STRING) {
            e.setCancelled(true);
            p.updateInventory();
            if (!SpiderMan.cooldown.containsKey(p.getName()) || SpiderMan.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                if (Main.areaPvP(p)) {
                    final Snowball teia = (Snowball)p.launchProjectile((Class)Snowball.class);
                    SpiderMan.teias.put(p.getName(), teia);
                    SpiderMan.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(10L));
                    p.sendMessage(ChatColor.DARK_PURPLE + "Teia lan\u00e7ada!");
                    p.playSound(p.getLocation(), Sound.IRONGOLEM_THROW, 1.0f, 1.0f);
                    return;
                }
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
            else {
                p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(SpiderMan.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
            }
        }
    }
    
    @EventHandler
    public void teia(final ProjectileHitEvent e) {
        if (e.getEntity() instanceof Snowball && e.getEntity().getShooter() instanceof Player) {
            final Snowball teia = (Snowball)e.getEntity();
            final Player p = (Player)e.getEntity().getShooter();
            if (SpiderMan.teias.containsKey(p.getName()) && teia == SpiderMan.teias.get(p.getName())) {
                SpiderMan.teias.remove(p.getName());
                final Block b = teia.getLocation().getBlock();
                b.setType(Material.WEB);
                new BukkitRunnable() {
                    public void run() {
                        if (b.getType() == Material.WEB) {
                            b.setType(Material.AIR);
                        }
                    }
                }.runTaskLater(Main.plugin, 100L);
            }
        }
    }
}
