package UltraKits.Warps;

import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class Block implements Listener
{
    public static ArrayList<String> aff;
    
    static {
        Block.aff = new ArrayList<String>();
    }
    
    @EventHandler
    public void onPlayerCommandPreprocess(final PlayerCommandPreprocessEvent e) {
        final Player player = e.getPlayer();
        if (Block.aff.contains(player.getPlayer().getName())) {
            if (e.getMessage().contains("/sair")) {
                return;
            }
            player.sendMessage("§cComandos desse genero foram desativados!");
            player.sendMessage("§aUse /sair para sair!");
            player.playSound(player.getLocation(), Sound.CAT_HIT, 4.0f, 4.0f);
            e.setCancelled(true);
        }
    }
}
