package Eventos;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static Eventos.aoPescarPeixes.*;

public class aoInteragirNoMenu implements Listener {

    public static Material Tipo;

    @EventHandler
    public static void aoClicar(InventoryClickEvent evento) {
        Player jogador = (Player) evento.getWhoClicked();
        ItemStack clicado = evento.getCurrentItem();
        Inventory inv = evento.getClickedInventory();

        if (inv == null)
            return;
        if (inv.getType() == InventoryType.PLAYER)
            return;
        if (clicado == null)
            return;
        if (getPescadores().contains(jogador)) {
            if (clicado.getType() == Material.BLUE_STAINED_GLASS_PANE) {
                evento.setCancelled(true);
            }
            if (clicado.getType() == Tipo) {
                evento.setCancelled(true);
                jogador.getInventory().addItem(new ItemStack(Tipo));
                jogador.playSound(jogador.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.5f, 0.5f);
                jogador.closeInventory();
                removerPescador(jogador);
            }
        }
    }
}
