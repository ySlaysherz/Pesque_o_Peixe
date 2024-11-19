package Eventos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static Principal.Main.*;

public class aoPescarPeixe implements Listener {

	@EventHandler
	public static void aoPescar(PlayerFishEvent evento) {
		Player jogador = evento.getPlayer();
		Inventory inv = Bukkit.createInventory(jogador, 9 * 6, "Mar");
		if (evento.isCancelled())
			return;
		State estado = evento.getState();
		if (estado == State.IN_GROUND) {
			jogador.sendMessage("§cTente pescar na água!");
			return;
		}
		if (estado == State.FAILED_ATTEMPT) {
			jogador.sendMessage("§cFalhou miseravelmente ao tentar pescar!");
			return;
		}
		if (estado == State.CAUGHT_FISH || estado == State.CAUGHT_ENTITY) {
			ItemStack mar = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
			ItemStack peixe = new ItemStack(Material.TROPICAL_FISH);

			for (int i = 0; i < inv.getSize(); i++) {
				if (inv.getItem(i) == null) {
					inv.setItem(i, mar);
				}
			}
			inv.setItem(getIntAleatrio(inv.getSize()), peixe);
			jogador.openInventory(inv);
		}
	}
}
