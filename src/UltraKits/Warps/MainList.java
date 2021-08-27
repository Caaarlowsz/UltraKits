package UltraKits.Warps;

import org.bukkit.event.*;
import java.util.logging.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import UltraKits.*;
import me.confuser.barapi.*;
import org.bukkit.*;

public class MainList implements Listener, CommandExecutor
{
    public final Logger logger;
    
    public MainList() {
        this.logger = Logger.getLogger("Minecraft");
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (commandLabel.equalsIgnoreCase("fps")) {
            final Player player = (Player)sender;
            Main.resetKit(player);
            final World world = player.getWorld();
            Main.restaurarItens(player);
            final Location loc = new Location(world, 380.4, 98.0, 488.8, -90.0f, 0.0f);
            loc.add(0.5, 0.0, 0.5);
            player.teleport(loc);
            BarAPI.setMessage(player, ChatColor.GREEN + "Voce Entrou na Warp FPS!", 4);
            return true;
        }
        return true;
    }
}
