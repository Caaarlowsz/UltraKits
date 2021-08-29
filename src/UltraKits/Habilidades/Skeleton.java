package UltraKits.Habilidades;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Vector;

import UltraKits.Main;

public class Skeleton implements Listener {
	private Main plugin;

	public Skeleton(final Main instance) {
		this.plugin = instance;
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(final PlayerInteractEvent e) {
		final Player p = e.getPlayer();
		if (Main.skeleton.contains(p.getName())) {
			if (e.getAction() != Action.RIGHT_CLICK_AIR) {
				return;
			}
			if (e.getItem().getType() != Material.BONE) {
				return;
			}
			final Vector velo2 = p.getLocation().getDirection().normalize().multiply(100);
			velo2.add(new Vector(Math.random() * 0.0 - 0.0, 0.0, 0.0));
			if (Main.reload.contains(p.getName())) {
				p.sendMessage(ChatColor.RED + "Sua habilidade esta recarregando");
			} else {
				p.playSound(p.getLocation(), Sound.SKELETON_WALK, 6.0f, 6.0f);
				p.getEyeLocation().getWorld().playEffect(p.getLocation(), Effect.BOW_FIRE, 5);
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.ZOMBIE_CHEW_IRON_DOOR, 1);
				p.getLocation().getWorld().playEffect(p.getLocation(), Effect.BLAZE_SHOOT, 1);
				((Arrow) p.launchProjectile(Arrow.class)).setVelocity(velo2);
				Main.reload.add(p.getName());
				p.setExp(0.0f);
				p.setLevel(0);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_DEATH, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 10L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 20L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 30L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 40L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 60L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 70L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 80L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 90L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 100L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 110L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 120L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 130L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 140L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 160L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 170L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 180L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 190L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 200L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 210L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 220L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 230L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 240L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 250L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 260L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 270L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 280L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 290L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.playSound(p.getLocation(), Sound.SKELETON_WALK, 1.0f, 1.0f);
									p.giveExp(1);
								}
							}
						}, 300L);
				Bukkit.getServer().getScheduler().scheduleSyncDelayedTask((Plugin) this.plugin,
						(Runnable) new Runnable() {
							@Override
							public void run() {
								if (Main.skeleton.contains(p.getName())) {
									p.setExp(1.0f);
									p.playSound(p.getLocation(), Sound.SKELETON_IDLE, 2.0f, 2.0f);
									Main.reload.remove(p.getName());
								}
							}
						}, 300L);
			}
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamage(final EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Arrow) {
			final Arrow s = (Arrow) e.getDamager();
			final Player damaged = (Player) e.getEntity();
			if (s.getShooter() instanceof Player) {
				final Player shooter = (Player) s.getShooter();
				if (shooter.getItemInHand().getType() == Material.BONE) {
					e.setDamage(15.0);
					damaged.getLocation().getWorld().playEffect(damaged.getLocation(), Effect.STEP_SOUND,
							(Object) Material.REDSTONE_WIRE);
				}
			}
		}
	}
}
