package UltraKits.Eventos;

import java.util.*;
import org.bukkit.event.player.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class AntiFlood implements Listener
{
    public static Map<String, Long> delays;
    public static Map<String, Long> delayenchant;
    
    static {
        AntiFlood.delays = new HashMap<String, Long>();
        AntiFlood.delayenchant = new HashMap<String, Long>();
    }
    
    @EventHandler(priority = EventPriority.LOW)
    public void chat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (p.hasPermission("uk.bypass.flood")) {
            return;
        }
        final Long agora = System.currentTimeMillis() / 1000L;
        if (AntiFlood.delays.containsKey(p.getName())) {
            final Long delay = AntiFlood.delays.get(p.getName());
            if (agora - delay < 3L) {
                p.sendMessage(ChatColor.RED + "Escreva mais devagar!");
                e.setCancelled(true);
                return;
            }
            AntiFlood.delays.remove(p.getName());
        }
        AntiFlood.delays.put(p.getName(), agora);
    }
}
