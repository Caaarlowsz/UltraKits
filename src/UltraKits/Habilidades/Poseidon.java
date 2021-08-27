package UltraKits.Habilidades;

import UltraKits.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.potion.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Poseidon implements Listener
{
    public Main plugin;
    
    public Poseidon(final Main plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerMove(final PlayerMoveEvent event) {
        final Player player = event.getPlayer();
        if (Main.poseidon.contains(player.getName())) {
            if (player.getLocation().getBlock().getType() == Material.WATER || player.getLocation().getBlock().getType() == Material.STATIONARY_WATER) {
                if (Main.areaPvP(player)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1000000, 0));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1000000, 1));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 1000000, 0));
                }
            }
            else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 0, 0), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 0, 0), true);
                player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 0, 0), true);
            }
        }
    }
}
