package me.playbosswar.vaultconditions;

import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.com.api.ConditionRules;
import me.playbosswar.com.api.events.EventExtension;
import me.playbosswar.vaultconditions.conditions.*;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandTimerVaultConditions extends ConditionExtension {
    ConditionRules rules = new ConditionRules();
    private static Economy econ = null;

    public CommandTimerVaultConditions() {
        if (!setupEconomy()) {
            getCommandTimerPlugin().getLogger().severe("CommandTimer disabled because extension requires Vault");
            getCommandTimerPlugin().getServer().getPluginManager().disablePlugin(getCommandTimerPlugin());
            return;
        }

        rules.register(
                new CheckPlayerBalance(),
                new CheckBankBalance(),
                new HasAccount(),
                new IsBankMember(),
                new IsBankOwner()
        );
    }

    @Override
    public @NotNull String getConditionGroupName() {
        return "VAULT";
    }

    @Override
    public @NotNull
    String[] getDescription() {
        return new String[]{"§7Look up Vault data for certain conditions"};
    }

    @Override
    public String getRequiredPlugin() {
        return "Vault";
    }

    @Override
    public @NotNull String getAuthor() {
        return "PlayBossWar";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    public @NotNull ConditionRules getRules() {
        return rules;
    }

    public ArrayList<EventExtension> getEvents() {
        return new ArrayList<>();
    }

    public static Economy getEcon() {
        return econ;
    }

    private boolean setupEconomy() {
        if (getCommandTimerPlugin().getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getCommandTimerPlugin().getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}