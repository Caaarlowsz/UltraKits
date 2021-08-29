package UltraKits.Habilidades;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import UltraKits.Main;

public class Neji implements Listener {
	public static HashMap<String, Long> cooldown;
	public static ArrayList<String> aplicando;
	public static ItemStack chakra;

	static {
		Neji.cooldown = new HashMap<String, Long>();
		Neji.aplicando = new ArrayList<String>();
		Neji.chakra = new ItemStack(Material.EYE_OF_ENDER);
	}

	@EventHandler
	public void interact(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
				&& Main.neji.contains(p.getName()) && p.getItemInHand().equals((Object) Neji.chakra)) {
			e.setCancelled(true);
			if (!Neji.cooldown.containsKey(p.getName())
					|| Neji.cooldown.get(p.getName()) <= System.currentTimeMillis()) {
				if (Main.areaPvP(p)) {
					apartaivos(p);
					Neji.cooldown.put(p.getName(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(25L));
					p.sendMessage(ChatColor.DARK_PURPLE + "HAKKESHOU KAITEN!");
				} else {
					p.sendMessage(ChatColor.RED + "Voce pode usar esta habilidade apenas em areas com PVP.");
				}
			} else {
				p.sendMessage(ChatColor.RED + "Faltam "
						+ TimeUnit.MILLISECONDS.toSeconds(Neji.cooldown.get(p.getName()) - System.currentTimeMillis())
						+ " segundos para poder usar novamente.");
			}
		}
	}

	public static void apartaivos(final Player p) {
		new BukkitRunnable() {
			int c = 0;

			public void run() {
				if (this.c < 10) {
					++this.c;
					Neji.bloco(p);
					Neji.knockback(p);
					if (!Neji.aplicando.contains(p.getName())) {
						Neji.aplicando.add(p.getName());
					}
				} else {
					Neji.aplicando.remove(p.getName());
					this.cancel();
				}
			}
		}.runTaskTimer(Main.plugin, 0L, 3L);
	}

	public static void bloco(final Player p) {
		final Location l = p.getLocation();
		final Effect ef = Effect.STEP_SOUND;
		final Material m = Material.BEACON;
		final Block b1 = l.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.EAST)
				.getRelative(BlockFace.NORTH);
		final Block b2 = l.getBlock().getRelative(BlockFace.EAST).getRelative(BlockFace.EAST)
				.getRelative(BlockFace.SOUTH);
		final Block b3 = l.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.WEST)
				.getRelative(BlockFace.NORTH);
		final Block b4 = l.getBlock().getRelative(BlockFace.WEST).getRelative(BlockFace.WEST)
				.getRelative(BlockFace.SOUTH);
		final Block b5 = b1.getRelative(BlockFace.UP);
		final Block b6 = b2.getRelative(BlockFace.UP);
		final Block b7 = b3.getRelative(BlockFace.UP);
		final Block b8 = b4.getRelative(BlockFace.UP);
		final Block b9 = l.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH)
				.getRelative(BlockFace.NORTH);
		final Block b10 = l.getBlock().getRelative(BlockFace.SOUTH).getRelative(BlockFace.SOUTH)
				.getRelative(BlockFace.SOUTH);
		final Block b11 = l.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH)
				.getRelative(BlockFace.NORTH);
		final Block b12 = l.getBlock().getRelative(BlockFace.NORTH).getRelative(BlockFace.NORTH)
				.getRelative(BlockFace.SOUTH);
		final Block b13 = b9.getRelative(BlockFace.UP);
		final Block b14 = b10.getRelative(BlockFace.UP);
		final Block b15 = b11.getRelative(BlockFace.UP);
		final Block b16 = b12.getRelative(BlockFace.UP);
		l.getWorld().playEffect(b1.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b2.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b3.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b4.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b5.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b6.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b7.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b8.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b9.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b10.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b11.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b12.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b13.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b14.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b15.getLocation(), ef, (Object) m, 10);
		l.getWorld().playEffect(b16.getLocation(), ef, (Object) m, 10);
	}

	public static void knockback(final Player p) {
		final Location loc = p.getLocation();
		final List<Entity> nearby = (List<Entity>) loc.getWorld().getEntities();
		for (final Entity e : nearby) {
			if (e.getLocation().distance(loc) < 6.0 && e instanceof Player) {
				final Player d = (Player) e;
				if (d == p) {
					continue;
				}
				d.damage(4.0, (Entity) p);
				d.setVelocity(d.getLocation().getDirection().multiply(-2));
			}
		}
	}

	@EventHandler
	public void damage(final EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			final Player p = (Player) e.getEntity();
			if (Neji.aplicando.contains(p.getName())) {
				e.setCancelled(true);
			}
		}
	}
}
