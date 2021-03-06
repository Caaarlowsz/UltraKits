package UltraKits.Comandos;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public final class CommandBuild implements Listener, CommandExecutor {
	public static final String prefix = "?4[UltraKits] ";
	private static boolean build;

	static {
		CommandBuild.build = false;
	}

	public final boolean onCommand(final CommandSender sender, final Command cmd, final String label,
			final String[] args) {
		final Player p = (Player) sender;
		if (!cmd.getName().equalsIgnoreCase("build") || !p.hasPermission("uk.build")) {
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("?b[UltraKits] Construir esta:  "
					+ (CommandBuild.build ? (ChatColor.GREEN + "ativado") : (ChatColor.RED + "desativado")));
			sender.sendMessage(ChatColor.RED + "Sintaxe correta:" + ChatColor.RED + "/build [on|off]");
			return true;
		}
		if (args[0].equalsIgnoreCase("on")) {
			CommandBuild.build = true;
			sender.sendMessage("?b[UltraKits] Construir esta: "
					+ (CommandBuild.build ? (ChatColor.GREEN + "ativado") : (ChatColor.RED + "desativado")));
		} else if (args[0].equalsIgnoreCase("off")) {
			CommandBuild.build = false;
			sender.sendMessage("?b[UltraKits] Construir esta: "
					+ (CommandBuild.build ? (ChatColor.RED + "desativado") : (ChatColor.RED + "desativado")));
		} else {
			sender.sendMessage(ChatColor.RED + "Sintaxe correta:" + ChatColor.RED + "/build [on|off]");
		}
		return true;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public final void onBlockPlace(final BlockPlaceEvent event) {
		if (!CommandBuild.build) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public final void onBlockBreak(final BlockBreakEvent event) {
		if (!CommandBuild.build) {
			event.setCancelled(true);
		}
	}
}
