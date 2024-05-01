package br.com.vitorcapovilla.capovillafake;

import org.bukkit.Bukkit;
import br.com.vitorcapovilla.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public final class CapovillaFake extends JavaPlugin {

//    private static Map<UUID, String> playerTags = new HashMap<>();

    @Override
    public void onEnable() {
        try {
            Objects.requireNonNull(getCommand("fake")).setExecutor(new fake());
            Bukkit.getConsoleSender().sendMessage("§b§l[CapovillaFake] Fake ativado  com sucesso!");
        }catch (Exception e){
            Bukkit.getConsoleSender().sendMessage("§4§l[CapovillaFake] Erro ao habilitar plugin; Contete o desenvolvedor!");
        }

    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§b§l[CapovillaFake] Fake desativado com sucesso!");
    }


}
