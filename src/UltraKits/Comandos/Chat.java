package UltraKits.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitTask;

public class Chat implements CommandExecutor, Listener {
	public static ArrayList<BukkitTask> task;

	static {
		Chat.task = new ArrayList<BukkitTask>();
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("[UltraKits] Comando apenas para players in-game.");
			return true;
		}
		final Player p = (Player) sender;
		if (cmd.equalsIgnoreCase("cc")) {
			if (!p.hasPermission("uk.cc")) {
				p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
				return true;
			}
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(" ");
			Bukkit.broadcastMessage(new StringBuilder().append(ChatColor.WHITE).append(ChatColor.BOLD)
					.append("                   Chat Limpo").toString());
		}
		return false;
	}

	@EventHandler
	public void aoFalar(final AsyncPlayerChatEvent e) {
		if (e.getPlayer().hasPermission("uk.chatcolor") || e.getPlayer().hasPermission("uk.chatcolor")) {
			e.setFormat(String.valueOf(e.getPlayer().getDisplayName()) + ChatColor.GRAY + ": " + ChatColor.RESET
					+ e.getMessage().replaceAll("&", "§"));
		} else {
			e.setFormat(String.valueOf(e.getPlayer().getDisplayName()) + ChatColor.GRAY + ": " + ChatColor.RESET
					+ e.getMessage());
		}
	}
}
