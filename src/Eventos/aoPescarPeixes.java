package Eventos;

import java.util.ArrayList;
import java.util.List;

import Principal.RandomAPI;
import com.mojang.datafixers.types.templates.Hook;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerFishEvent.State;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static Principal.RandomAPI.*;

public class aoPescarPeixes implements Listener {

    private static List<Player> Pescadores = new ArrayList<Player>();

    public static void removerPescador(Player jogador) {
        Pescadores.remove(jogador);
    }

    public static void addPescador(Player jogador) {
        Pescadores.add(jogador);
    }

    public static List<Player> getPescadores() {
        return Pescadores;
    }

    @EventHandler
    public static void aoPescar(PlayerFishEvent evento) {
        Player jogador = evento.getPlayer();
        Inventory inv = Bukkit.createInventory(jogador, 9 * 6, "Mar");
        FishHook.HookState estado = evento.getHook().getState();
        World mundo = evento.getHook().getWorld();
        Entity boia = evento.getHook();
        State eventoState = evento.getState();

        boia.setCustomNameVisible(true);
        boia.setCustomName("§eProcurando peixes...");

        if (eventoState == State.FAILED_ATTEMPT && estado == FishHook.HookState.BOBBING) {
            boia.setCustomNameVisible(true);
            boia.setCustomName("§cFalhou!");
            mundo.spawnParticle(Particle.FLAME, boia.getLocation(), 5);
            jogador.sendMessage("§cFalhou miseravelmente ao tentar pescar!");
            jogador.closeInventory();
            return;
        }
        if (eventoState == State.CAUGHT_FISH || eventoState == State.CAUGHT_ENTITY && estado == FishHook.HookState.BOBBING) {
            if (!getPescadores().contains(jogador)) {
                Item pescado = (Item) evento.getCaught();
                ItemStack mar = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
                assert pescado != null;
                aoInteragirNoMenu.Tipo = pescado.getItemStack().getType();
                boia.setCustomNameVisible(true);
                boia.setCustomName("§aPescou " + pescado.getItemStack().getType().toString().toLowerCase());
                ItemStack peixe = new ItemStack(pescado.getItemStack().getType());
                for (int i = 0; i < inv.getSize(); i++) {
                    if (inv.getItem(i) == null) {
                        inv.setItem(i, mar);
                    }
                }
                inv.setItem(GerarNumero(inv.getSize()), peixe);

                jogador.openInventory(inv);

                pescado.getItemStack().setAmount(0);

                mundo.spawnParticle(Particle.VILLAGER_HAPPY, boia.getLocation(), 10);

                addPescador(jogador);
            }
        }
    }
}
