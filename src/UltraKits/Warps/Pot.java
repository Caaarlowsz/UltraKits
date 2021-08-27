package UltraKits.Warps;

import org.bukkit.event.*;
import java.util.logging.*;
import org.bukkit.command.*;
import org.bukkit.entity.*;
import UltraKits.*;
import me.confuser.barapi.*;
import org.bukkit.*;

public class Pot implements Listener, CommandExecutor
{
    public final Logger logger;
    
    public Pot() {
        this.logger = Logger.getLogger("Minecraft");
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel, final String[] args) {
        if (commandLabel.equalsIgnoreCase("sky")) {
            final Player player = (Player)sender;
            Main.resetKit(player);
            Main.restaurarItens(player);
            final World world = player.getWorld();
            final Location loc = new Location(world, 239.3, 71.0, 352.5, -90.0f, 0.0f);
            loc.add(0.5, 0.0, 0.5);
            player.teleport(loc);
            BarAPI.setMessage(player, ChatColor.GREEN + "Voce Entrou na Warp SKY!", 4);
            return true;
        }
        return true;
    }
}
