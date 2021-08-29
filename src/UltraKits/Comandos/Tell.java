package UltraKits.Comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Tell implements CommandExecutor, Listener {
	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		if (args.length == 0) {
			sender.sendMessage("§cUse /tell <player> <mensagem>.");
			return true;
		}
		if (args.length == 1) {
			sender.sendMessage("§cEspecifique uma mensagem!");
			return true;
		}
		if (args.length > 1) {
			final Player target = Bukkit.getPlayerExact(args[0]);
			if (target == null || !(target instanceof Player)) {
				sender.sendMessage("§cJogador nao encontrado!");
				return true;
			}
			final StringBuilder sb = new StringBuilder();
			for (int i = 1; i < args.length; ++i) {
				sb.append(String.valueOf(args[i]) + " ");
			}
			final String msg = sb.toString();
			target.sendMessage(String.format("§7[%s] §cpara voce > §r%s§c.", sender.getName(), msg));
			sender.sendMessage(String.format("§cVoce para §7[%s] §c> §r%s§c.", target.getName(), msg));
		}
		return false;
	}
}
