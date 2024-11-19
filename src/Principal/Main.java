package Principal;

import Eventos.aoInteragirNoMenu;
import Eventos.aoPescarPeixes;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public Main main = this;

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

        // Mensagem abaixo será enviada ao Console quando o Plugin for Iniciado sem
        // erros.
        Bukkit.getConsoleSender().sendMessage(
                ChatColor.AQUA + "Pesque o Peixe " + ChatColor.GREEN + "(Versao: " + getDescription().getVersion() + ") - "
                        + ChatColor.WHITE + "Desenvolvedor: " + ChatColor.GOLD + "ySlaysherz_");

        // Registra as Classes que possuiem Eventos Listener
        Bukkit.getServer().getPluginManager().registerEvents(new aoPescarPeixes(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new aoInteragirNoMenu(), this);


        // Registra as Classes que possuem Comandos
        // getCommand("modelo").setExecutor(new Modelo());
    }

    @Override
    public void onDisable() {
        // Mensagem abaixo será enviada ao Console quando o Plugin for desligado.
        Bukkit.getConsoleSender()
                .sendMessage(ChatColor.AQUA + "Pesque o Peixe" + ChatColor.WHITE + " Desligado com sucesso.");
    }
}