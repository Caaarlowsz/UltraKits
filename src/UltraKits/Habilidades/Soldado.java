package UltraKits.Habilidades;

import org.bukkit.plugin.*;
import UltraKits.*;
import org.bukkit.event.player.*;
import org.bukkit.event.block.*;
import org.bukkit.util.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Soldado implements Listener
{
    Plugin plugin;
    
    public Soldado(final Main plugin) {
        this.plugin = (Plugin)plugin;
    }
    
    @EventHandler
    public void jogadorSoldado(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (Main.soldado.contains(p.getName()) && e.getAction() == Action.RIGHT_CLICK_BLOCK && p.getInventory().getItemInHand().getType() == Material.getMaterial(this.plugin.getConfig().getInt("Soldado.required_sword"))) {
            if (Main.areaPvP(p)) {
                p.setVelocity(new Vector(0, 1, 0));
            }
            else {
                p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
            }
        }
    }
}
