package UltraKits.Comandos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.PlayerInventory;

import UltraKits.Main;

public class Admin implements CommandExecutor, Listener {
	public static ArrayList<String> vanish;

	static {
		Admin.vanish = new ArrayList<String>();
	}

	public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage("[UltraKits] Comando apenas para players in-game.");
			return true;
		}
		final Player p = (Player) sender;
		if (!p.hasPermission("uk.admin")) {
			p.sendMessage(ChatColor.RED + "Voce nao tem permissao.");
			return true;
		}
		if (cmd.equalsIgnoreCase("v")) {
			if (Admin.vanish.contains(p.getName())) {
				Admin.vanish.remove(p.getName());
				Player[] onlinePlayers;
				for (int length = (onlinePlayers = Bukkit.getOnlinePlayers()).length, j = 0; j < length; ++j) {
					final Player o = onlinePlayers[j];
					o.showPlayer(p);
				}
				p.sendMessage(ChatColor.GOLD + "Todos podem te ver agora.");
			} else {
				Admin.vanish.add(p.getName());
				Player[] onlinePlayers2;
				for (int length2 = (onlinePlayers2 = Bukkit.getOnlinePlayers()).length, k = 0; k < length2; ++k) {
					final Player o = onlinePlayers2[k];
					o.hidePlayer(p);
				}
				p.sendMessage(ChatColor.GOLD + "Ninguem consegue te ver agora.");
			}
		} else if (cmd.equalsIgnoreCase("admin")) {
			if (Main.admin.contains(p.getName())) {
				Main.admin.remove(p.getName());
				Bukkit.broadcastMessage(ChatColor.GREEN + "+ " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET
						+ ChatColor.WHITE + " entrou no servidor");
				Player[] onlinePlayers3;
				for (int length3 = (onlinePlayers3 = Bukkit.getOnlinePlayers()).length, l = 0; l < length3; ++l) {
					final Player s1 = onlinePlayers3[l];
					s1.showPlayer(p);
				}
				p.sendMessage(ChatColor.LIGHT_PURPLE + "Voce saiu do modo admin.");
				p.setGameMode(GameMode.SURVIVAL);
			} else {
				Main.admin.add(p.getName());
				Player[] onlinePlayers4;
				for (int length4 = (onlinePlayers4 = Bukkit.getOnlinePlayers()).length, n = 0; n < length4; ++n) {
					final Player s1 = onlinePlayers4[n];
					s1.hidePlayer(p);
				}
				Bukkit.broadcastMessage(ChatColor.RED + "- " + ChatColor.RESET + p.getDisplayName() + ChatColor.RESET
						+ ChatColor.WHITE + "  saiu do servidor");
				p.sendMessage(ChatColor.LIGHT_PURPLE + "Voce entrou no modo admin.");
				p.setGameMode(GameMode.CREATIVE);
			}
		} else if (cmd.equalsIgnoreCase("invsee")) {
			if (args.length == 0) {
				p.sendMessage(ChatColor.GOLD + "/invsee " + ChatColor.RED + "<player>");
				return true;
			}
			if (args.length == 1) {
				final Player target = Bukkit.getPlayer(args[0]);
				if (target != null) {
					verInv(p, target);
				} else {
					p.sendMessage(ChatColor.RED + "Este player nao est\u00e1 online.");
				}
			}
		} else if (cmd.equalsIgnoreCase("ac")) {
			if (sender.hasPermission("uk.ac")) {
				final StringBuilder sb = new StringBuilder();
				for (int i = 0; i < args.length; ++i) {
					sb.append(String.valueOf(args[i]) + " ");
				}
				final String allArgs = sb.toString().trim();
				Player[] onlinePlayers5;
				for (int length5 = (onlinePlayers5 = Bukkit.getServer().getOnlinePlayers()).length,
						n2 = 0; n2 < length5; ++n2) {
					final Player staff = onlinePlayers5[n2];
					if (staff.hasPermission("uk.admin")) {
						staff.sendMessage("§4[ADMIN-CHAT] " + p.getName() + " §cMSG: §f" + allArgs);
					}
				}
			}
			return true;
		}
		return false;
	}

	@EventHandler
	public void adminListener(final PlayerInteractEntityEvent e) {
		if (e.getRightClicked() instanceof Player) {
			final Player p = e.getPlayer();
			final Player target = (Player) e.getRightClicked();
			if (Main.admin.contains(p.getName()) && p.hasPermission("uk.admin")) {
				verInv(p, target);
			}
		}
	}

	public static void verInv(final Player p, final Player de) {
		final PlayerInventory inv = de.getInventory();
		p.openInventory((Inventory) inv);
	}
}
