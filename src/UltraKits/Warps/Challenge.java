package UltraKits.Warps;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import UltraKits.Main;
import UltraKits.Habilidades.Ghost;
import UltraKits.Habilidades.Monk;
import me.confuser.barapi.BarAPI;

public class Challenge implements Listener, CommandExecutor {
	public final Logger logger;

	public Challenge() {
		this.logger = Logger.getLogger("Minecraft");
	}

	public boolean onCommand(final CommandSender sender, final Command cmd, final String commandLabel,
			final String[] args) {
		if (commandLabel.equalsIgnoreCase("lc")) {
			final Player player = (Player) sender;
			for (final PotionEffect effect : player.getActivePotionEffects()) {
				player.removePotionEffect(effect.getType());
			}
			Main.kits.remove(player.getName());
			Main.hg.remove(player.getName());
			Main.arqueiro.remove(player.getName());
			Main.fisherman.remove(player.getName());
			Main.stomper.remove(player.getName());
			Main.reaper.remove(player.getName());
			Main.kangaroo.remove(player.getName());
			Main.urgal.remove(player.getName());
			Main.fireman.remove(player.getName());
			Main.viper.remove(player.getName());
			Main.poseidon.remove(player.getName());
			Main.pyro.remove(player.getName());
			Main.whister.remove(player.getName());
			Main.camel.remove(player.getName());
			Main.endermage.remove(player.getName());
			Main.darkmage.remove(player.getName());
			Main.soldado.remove(player.getName());
			Main.tank.remove(player.getName());
			Main.desafio.remove(player.getName());
			Main.anchor.remove(player.getName());
			Main.ninja.remove(player.getName());
			Main.grappler.remove(player.getName());
			Main.Trocador.remove(player.getName());
			Main.specialist.remove(player.getName());
			Main.milkman.remove(player.getName());
			Main.tank.remove(player.getName());
			Main.thor.remove(player.getName());
			Main.frosty.remove(player.getName());
			Main.launcher.remove(player.getName());
			Main.flash.remove(player.getName());
			Main.skeleton.remove(player.getName());
			Main.turtle.remove(player.getName());
			Main.monk.remove(player.getName());
			Main.snail.remove(player.getName());
			Main.jumper.remove(player.getName());
			Main.switcher.remove(player.getName());
			Main.gladiator.remove(player.getName());
			Main.wither.remove(player.getName());
			Main.phantom.remove(player.getName());
			Main.reload.remove(player.getName());
			Main.cooldown.remove(player.getName());
			Main.cooldown1.remove(player.getName());
			Monk.coolmonk.remove(player.getName());
			Main.viking.remove(player.getName());
			Main.madman.remove(player.getName());
			Main.grandpa.remove(player.getName());
			Main.ghost.remove(player.getName());
			Main.barbarian.remove(player.getName());
			Main.spiderman.remove(player.getName());
			Main.berserker.remove(player.getName());
			Main.Teleporter.remove(player.getName());
			Main.indio.remove(player.getName());
			Main.ryu.remove(player.getName());
			Main.neji.remove(player.getName());
			Main.lobisomem.remove(player.getName());
			Main.granadier.remove(player.getName());
			Main.rhino.remove(player.getName());
			Main.alien.remove(player.getName());
			Main.hulk.remove(player.getName());
			Main.critical.remove(player.getName());
			Main.vitality.remove(player.getName());
			Main.quickdropper.remove(player.getName());
			Ghost.resetGhost(player);
			final World world = player.getWorld();
			player.getInventory().clear();
			final Location loc = new Location(world, 232.53, 38.0, 499.6, -90.0f, 0.0f);
			loc.add(0.5, 0.0, 0.5);
			player.teleport(loc);
			BarAPI.setMessage(player, ChatColor.GREEN + "Voce entrou na warp Lava Challenge!", 4);
			player.getInventory().setLeggings(new ItemStack(Material.AIR));
			player.getInventory().setHelmet(new ItemStack(Material.AIR));
			player.getInventory().setChestplate(new ItemStack(Material.AIR));
			player.getInventory().setBoots(new ItemStack(Material.AIR));
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.MUSHROOM_SOUP, 1) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.RED_MUSHROOM, 64) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BROWN_MUSHROOM, 64) });
			player.getInventory().addItem(new ItemStack[] { new ItemStack(Material.BOWL, 64) });
			Block.aff.add(player.getName());
			return true;
		}
		return true;
	}
}
