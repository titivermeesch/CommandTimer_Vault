package me.playbosswar.vaultconditions;

import me.playbosswar.com.api.ConditionExtension;
import me.playbosswar.com.api.ConditionRules;
import me.playbosswar.com.api.events.EventExtension;
import me.playbosswar.vaultconditions.conditions.*;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CommandTimerVaultConditions extends ConditionExtension {
    ConditionRules rules = new ConditionRules();
    private static Economy econ = null;
    private static Permission perms = null;

    public CommandTimerVaultConditions() {
        if (!setupEconomy()) {
            getCommandTimerPlugin().getLogger().severe("CommandTimer Vault extension will not have economy capabilities because no economy plugin was found");
        }

        if (!setupPermissions()) {
            getCommandTimerPlugin().getLogger().severe("CommandTimer Vault extension will not have permission capabilities because no permission plugin was found");
        }

        if (econ != null) {
            rules.register(
                    new CheckPlayerBalance(),
                    new CheckBankBalance(),
                    new HasAccount(),
                    new IsBankMember(),
                    new IsBankOwner()
            );
        }

        if (perms != null) {
            rules.register(
                    new PlayerHasPermission(getCommandTimerPlugin())
            );
        }
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
        return "1.1.0";
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

    public static Permission getPerms() {
        return perms;
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

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getCommandTimerPlugin().getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }
}