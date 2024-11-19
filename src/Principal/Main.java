package Principal;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import Eventos.aoPescarPeixe;

public class Main extends JavaPlugin {

	private static Random Aleatorio = new Random();

	@Override
	public void onLoad() {
	}

	@Override
	public void onEnable() {

		// Mensagem abaixo será enviada ao Console quando o Plugin for Iniciado sem
		// erros.
		Bukkit.getConsoleSender().sendMessage(
				ChatColor.BLUE + "Pesque o Peixe " + ChatColor.GREEN + "(V: " + getDescription().getVersion() + ") - "
						+ ChatColor.WHITE + "Desenvolvedor: " + ChatColor.BLUE + "ySlaysherz_");
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.WHITE + "Acesse nossa Loja em " + ChatColor.BLUE + "discord.gg/crystalstore");

		// Registra as Classes que possuiem Eventos Listener
		 Bukkit.getServer().getPluginManager().registerEvents(new aoPescarPeixe(), this);

		// Registra as Classes que possuem Comandos
		// getCommand("modelo").setExecutor(new Modelo());
	}

	@Override
	public void onDisable() {
		// Mensagem abaixo será enviada ao Console quando o Plugin for desligado.
		Bukkit.getConsoleSender()
				.sendMessage(ChatColor.AQUA + "Pesque o Peixe" + ChatColor.WHITE + " Desligado com sucesso.");
	}

	// Gera um numero aleatorio de 0 à max, 0 à 100
	public static int getIntAleatrio(int max) {
		return Aleatorio.nextInt(max);
	}

	// Gera um numero aleatorio com um min e um max, Ex: 5 e 100
	public static int getIntAleatrioMinMax(int min, int max) {
		if (min > max) {
			throw new IllegalArgumentException("O valor mínimo não pode ser maior que o valor máximo.");
		}
		return Aleatorio.nextInt(max - min + 1) + min;
	}

	// Retorna verdadeiro caso o numero aleatorio gerado for menor ou igual à ao
	// numero "porcentagem", Ex: 10 = 10%
	public static boolean getChance(int porcentagem) {
		return Aleatorio.nextInt(100) <= porcentagem;
	}

	// Retorna verdadeiro caso o numero aleatorio for exatamente igual à
	// probabilidade, Ex: 1 em 55
	public static boolean get1em(int probabilidade) {
		return probabilidade == getIntAleatrioMinMax(1, probabilidade);
	}
}
