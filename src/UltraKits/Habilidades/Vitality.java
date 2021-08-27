package UltraKits.Habilidades;

import org.bukkit.event.entity.*;
import org.bukkit.entity.*;
import UltraKits.*;
import org.bukkit.inventory.*;
import org.bukkit.*;
import org.bukkit.event.*;

public class Vitality implements Listener
{
    @EventHandler
    public void onKill(final PlayerDeathEvent e) {
        if (e.getEntity().getKiller() instanceof Player) {
            final Player k = e.getEntity().getKiller();
            if (Main.vitality.contains(k.getName())) {
                k.getInventory().remove(Material.BOWL);
                try {
                    for (int i = 0; i < 36; ++i) {
                        k.getInventory().setItem(k.getInventory().firstEmpty(), new ItemStack(Material.MUSHROOM_SOUP));
                    }
                }
                catch (ArrayIndexOutOfBoundsException ex) {}
                k.sendMessage(ChatColor.GREEN + "Seu inventario est\u00e1 cheio de sopas!");
                k.playSound(k.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
            }
        }
    }
}
