package UltraKits.Comandos;

import org.bukkit.event.player.*;
import UltraKits.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class MuteP implements Listener
{
    @EventHandler
    public void onPlayerMutado(final AsyncPlayerChatEvent e) {
        final Player player = e.getPlayer();
        if (Main.mute.contains(player.getName())) {
            e.setCancelled(true);
            player.sendMessage(Main.plugin.getConfig().getString("Silenciado").replaceAll("&", "§"));
        }
    }
}
