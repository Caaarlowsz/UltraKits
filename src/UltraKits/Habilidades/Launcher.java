package UltraKits.Habilidades;

import UltraKits.*;
import org.bukkit.event.player.*;
import org.bukkit.entity.*;
import org.bukkit.*;
import org.bukkit.util.*;
import org.bukkit.event.*;

public class Launcher implements Listener
{
    public Main plugin;
    
    public Launcher(final Main plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerHitFishingrodThrower(final PlayerFishEvent event) {
        final Player player = event.getPlayer();
        if (Main.launcher.contains(player.getName()) && event.getCaught() instanceof Player) {
            final Player caught = (Player)event.getCaught();
            if (player.getItemInHand().getType() == Material.FISHING_ROD) {
                caught.setVelocity(new Vector(0, 2, 0));
            }
        }
    }
}
