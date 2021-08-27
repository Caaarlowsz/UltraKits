package UltraKits.Comandos;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.bukkit.*;

public class Tag implements CommandExecutor
{
    public boolean onCommand(final CommandSender sender, final Command command, final String cmd, final String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("[UltraKits]");
            return true;
        }
        final Player p = (Player)sender;
        if (cmd.equalsIgnoreCase("cor")) {
            if (!p.hasPermission("uk.cor")) {
                return true;
            }
            if (args.length == 0) {
                p.sendMessage(ChatColor.GOLD + "/cor " + ChatColor.RED + "<cor> " + ChatColor.WHITE + "ou " + ChatColor.GOLD + "/cor " + ChatColor.RED + "<player> <cor>");
                return true;
            }
            if (args.length == 1) {
                try {
                    final ChatColor cor = ChatColor.valueOf(args[0].toUpperCase());
                    p.setPlayerListName(cor + p.getName() + ChatColor.RESET);
                    p.setDisplayName(cor + p.getName() + ChatColor.RESET);
                    p.sendMessage(ChatColor.GREEN + "Alterado a cor do seu nome para: " + cor + cor.name());
                }
                catch (IllegalArgumentException ex) {
                    p.sendMessage(ChatColor.RED + "Esta cor nao existe.");
                }
            }
            else if (args.length == 2) {
                final Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    try {
                        final ChatColor cor2 = ChatColor.valueOf(args[1].toUpperCase());
                        target.setPlayerListName(cor2 + target.getName() + ChatColor.RESET);
                        target.setDisplayName(cor2 + target.getName() + ChatColor.RESET);
                        target.sendMessage(ChatColor.GREEN + "Alterado a cor do seu nome para: " + cor2 + cor2.name());
                        p.sendMessage(ChatColor.GREEN + "Alterado a cor do nome de " + ChatColor.WHITE + target.getName() + ChatColor.GREEN + " para: " + cor2 + cor2.name());
                    }
                    catch (IllegalArgumentException ex2) {
                        p.sendMessage(ChatColor.RED + "Esta cor nao existe.");
                    }
                }
                else {
                    p.sendMessage(ChatColor.RED + "Este player nao esta online.");
                }
            }
        }
        return false;
    }
}
