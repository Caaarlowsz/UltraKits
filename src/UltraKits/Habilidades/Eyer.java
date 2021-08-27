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

public class Eyer implements Listener
{
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, EnderPearl> tiros;
    
    static {
        Eyer.cooldown = new HashMap<String, Long>();
        Eyer.tiros = new HashMap<String, EnderPearl>();
    }
    
    @EventHandler
    public void disparar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && Main.Teleporter.contains(p.getName()) && p.getItemInHand().getType() == Material.ENDER_PEARL) {
            e.setCancelled(true);
            p.updateInventory();
            if (Main.areaPvP(p)) {
                if (!Eyer.cooldown.containsKey(p.getName()) || Eyer.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                    if (!Gladiator.fighting.containsKey(p.getName())) {
                        final EnderPearl tiro = (EnderPearl)p.launchProjectile((Class)EnderPearl.class);
                        Eyer.tiros.put(p.getName(), tiro);
                        Eyer.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15L));
                        p.sendMessage(ChatColor.GOLD + "Disparado!");
                        p.playSound(p.getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                    }
                    else {
                        p.sendMessage(ChatColor.RED + "Voce nao pode teleportar durante o Gladiator!");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(Eyer.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
                }
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
        }
    }
    
    @EventHandler
    public void teleportar(final ProjectileHitEvent e) {
        if (e.getEntity() instanceof EnderPearl && e.getEntity().getShooter() instanceof Player) {
            final EnderPearl tiro = (EnderPearl)e.getEntity();
            final Player p = (Player)e.getEntity().getShooter();
            if (Eyer.tiros.containsKey(p.getName()) && Eyer.tiros.get(p.getName()) == tiro) {
                p.teleport(tiro.getLocation());
                Eyer.tiros.remove(p.getName());
                p.sendMessage(ChatColor.BLUE + "Teleportado!");
                p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
            }
        }
    }
}
