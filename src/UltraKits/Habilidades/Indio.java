package UltraKits.Habilidades;

import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import UltraKits.*;
import org.bukkit.util.*;
import java.util.concurrent.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.entity.*;
import org.bukkit.potion.*;

public class Indio implements Listener
{
    public static HashMap<String, Long> cooldown;
    public static HashMap<String, Arrow> tiros;
    
    static {
        Indio.cooldown = new HashMap<String, Long>();
        Indio.tiros = new HashMap<String, Arrow>();
    }
    
    @EventHandler
    public void disparar(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && p.getItemInHand().getType() == Material.PUMPKIN_SEEDS && Main.indio.contains(p.getName())) {
            if (!Indio.cooldown.containsKey(p.getName()) || Indio.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
                if (Main.areaPvP(p)) {
                    e.setCancelled(true);
                    p.updateInventory();
                    final Arrow tiro = (Arrow)p.launchProjectile((Class)Arrow.class);
                    final Vector vec = p.getLocation().getDirection();
                    tiro.setVelocity(new Vector(vec.getX() * 1.0 * 3.5, vec.getY() * 1.0 * 4.0, vec.getZ() * 1.0 * 3.5));
                    Indio.tiros.put(p.getName(), tiro);
                    Indio.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(20L));
                    p.sendMessage(ChatColor.GOLD + "Dardo disparado!");
                    p.playSound(p.getLocation(), Sound.GLASS, 1.0f, 1.0f);
                    return;
                }
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
            else {
                p.sendMessage(ChatColor.RED + "Faltam " + TimeUnit.MILLISECONDS.toSeconds(Indio.cooldown.get(p.getName()) - System.currentTimeMillis()) + " segundos para poder usar novamente.");
            }
        }
    }
    
    @EventHandler
    public void aplicar(final EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Arrow) {
            final Player p = (Player)e.getEntity();
            final Arrow tiro = (Arrow)e.getDamager();
            if (tiro.getShooter() instanceof Player) {
                final Player shooter = (Player)tiro.getShooter();
                if (Indio.tiros.containsKey(shooter.getName()) && tiro == Indio.tiros.get(shooter.getName())) {
                    p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 300, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 300, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 300, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 300, 0));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 300, 0));
                    p.sendMessage(ChatColor.BLUE + "Voce foi atingido por um dardo do Patax\u00f3 " + ChatColor.DARK_PURPLE + shooter.getName());
                    p.playSound(p.getLocation(), Sound.BURP, 1.0f, 1.0f);
                    shooter.sendMessage(ChatColor.YELLOW + "O Sol conspira ao seu favor! " + ChatColor.DARK_PURPLE + p.getName() + ChatColor.YELLOW + " foi acertado pelo seu dardo!");
                    shooter.playSound(shooter.getLocation(), Sound.CAT_PURR, 1.0f, 1.0f);
                    Indio.tiros.remove(shooter.getName());
                }
            }
        }
    }
}
