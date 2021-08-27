package UltraKits.Habilidades;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import UltraKits.*;
import org.bukkit.potion.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class Berserker implements Listener
{
    @EventHandler
    public void forca(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player p = e.getEntity().getKiller();
            if (Main.berserker.contains(p.getName())) {
                p.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 400, 0));
                p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 0));
                p.sendMessage(ChatColor.AQUA + "MODO " + ChatColor.DARK_PURPLE + "BERSERKER" + " ON!");
                p.playSound(p.getLocation(), Sound.AMBIENCE_THUNDER, 1.0f, 1.0f);
            }
        }
    }
}
