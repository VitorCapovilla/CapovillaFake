package br.com.vitorcapovilla.capovillafake;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.*;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.PlayerInfoData;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.bukkit.GameMode.SURVIVAL;

public class fake implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser executado por um jogador.");
            return true;
        }

        Player player = (Player) sender;

        if (!sender.hasPermission("fake.use")) {
            player.sendMessage("§1[FakeCapovilla]§r§b Você não tem permissão para executar este comando.");
            return true;
        }

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            player.sendMessage("§2---§1[FakeCapovilla HELPER]§2---");
            player.sendMessage("§1[FakeCapovilla]§r§b Use /fake random -> para alterar seu nick para um aleatório");
            player.sendMessage("§1[FakeCapovilla]§r§b Use /fake {nick} -> para alterar seu nick para o que foi digitado");
            player.sendMessage("§1[FakeCapovilla]§r§b Use /fake * -> para resetar seu nick ao original");
            player.sendMessage("§2---§1[FakeCapovilla HELPER]§2---");
            return true;
        }

        String newName = args[0];

        if (newName.equalsIgnoreCase("*")) {
            player.setDisplayName(player.getName());
            player.setPlayerListName(player.getName());
            player.sendMessage("§1[FakeCapovilla]§r§b Seu nome foi restaurado para " + player.getName());
        } else if (newName.equalsIgnoreCase("random")) {
            String randomNickname = generateRandomName();
            player.setDisplayName(randomNickname);
            player.setPlayerListName(randomNickname);
            player.sendMessage("§1[FakeCapovilla]§r§b Seu nome foi alterado para " + randomNickname);
        } else {
            if (newName.length() < 4) {
                player.sendMessage("§1[FakeCapovilla]§r§b O nome deve ser igual ou maior que 4 caracteres");
            } else if (newName.length() >= 21) {
                player.sendMessage("§1[FakeCapovilla]§r§b O nome deve ser menor que 20 caracteres");
            } else {
                for (int i = 0; i < newName.length(); i++) {
                    char caractere = newName.charAt(i);
                    if (!Character.isLetterOrDigit(caractere) && !Character.isWhitespace(caractere)) {
                        player.sendMessage("§1[FakeCapovilla]§r§b Seu nome não pode conter caracteres especiais ou espaços em branco!");
                        return true;
                    }
                }

                player.setDisplayName(newName);
                player.setPlayerListName(newName);
                player.sendMessage("§1[FakeCapovilla]§r§b Seu nome foi alterado para " + newName);

            }
        }

        return true;
    }


    public static final String[] nomes = {
            "Golldii", "DrSW99", "AwAkiiE", "LcGitech", "ItzMath", "Sleepyy", "", "GuerreiroLendario",
            "ReiDaMina", "DragaoDeFerro", "MestreDasSombras", "CavaleiroCelestial", "ShadowFighter", "StarExplorer",
            "CosmicCrafter", "DiamondDigger", "IronWarrior", "ElementalElder", "SolarSorcerer", "PhantomPharaoh", "", "MysticMage",
            "EternalEclipse", "NovaKnight", "FrostFang", "EmberEmpress", "ThunderTitan", "LunarLancer", "", "NebulaNinja",
            "GalacticGuardian", "CrystalCaster", "BlazeBard", "FrostbiteFury", "CelestialChampion", "ShadowShaman", "", "RuneRanger",
            "WildfireWarlock", "FrostbornFalcon", "StormySorcerer", "EclipseEnchanter"
    };

    public static String generateRandomName() {
        Random random = new Random();
        int index = random.nextInt(nomes.length);
        return nomes[index];
    }
}
