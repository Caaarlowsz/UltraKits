package UltraKits.Habilidades;

import org.bukkit.event.player.*;
import org.bukkit.block.*;
import org.bukkit.*;
import UltraKits.*;
import org.bukkit.potion.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Frosty implements Listener
{
    @EventHandler
    public void onPlayerCamel(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if ((e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.SNOW_BLOCK || e.getTo().getBlock().getRelative(BlockFace.DOWN).getType() == Material.ICE) && Main.frosty.contains(p.getName())) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 80, 0));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 80, 0));
        }
    }
}
