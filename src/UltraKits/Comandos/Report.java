package UltraKits.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import UltraKits.Main;

public class Report implements CommandExecutor, Listener {
	public String[] aliases;
	public String description;

	public Report() {
		this.aliases = new String[] { "report" };
		this.description = "Reportar";
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		final Player p = (Player) sender;
		if (args.length < 2) {
			p.sendMessage(ChatColor.RED + "Sintaxe correta: /report <jogador> <motivo>");
		} else if (args.length >= 2) {
			final Player target = Bukkit.getPlayer(args[0]);
			if (target == null) {
				p.sendMessage(ChatColor.RED + "Esse jogador n\u00e3o existe!");
			} else {
				final StringBuilder sb = new StringBuilder();
				for (int i = 1; i < args.length; ++i) {
					sb.append(args[i]).append(" ");
				}
				final String allArgs = sb.toString().trim();
				sender.sendMessage(ChatColor.GREEN + "Seu report foi enviado com sucesso!");
				Player[] onlinePlayers;
				for (int length = (onlinePlayers = Bukkit.getServer().getOnlinePlayers()).length,
						j = 0; j < length; ++j) {
					final Player staff = onlinePlayers[j];
					if (staff.hasPermission("uk.ver")) {
						staff.sendMessage("§4[" + Main.plugin.getConfig().getString("ServerName") + "] " + ChatColor.RED
								+ ChatColor.ITALIC + target.getName() + ChatColor.GRAY + " foi reportado por "
								+ ChatColor.RED + ChatColor.ITALIC + sender.getName() + ChatColor.GRAY + "!"
								+ ChatColor.RED + " Motivo: " + ChatColor.GRAY + ChatColor.ITALIC + allArgs);
					}
				}
			}
		}
		return true;
	}
}
