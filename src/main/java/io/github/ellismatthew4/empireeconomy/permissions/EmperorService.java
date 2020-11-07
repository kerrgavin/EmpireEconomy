package io.github.ellismatthew4.empireeconomy.permissions;

import io.github.ellismatthew4.empireeconomy.data.Data;
import io.github.ellismatthew4.empireeconomy.utils.DataStoreService;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.java.JavaPlugin;

public class EmperorService {
    private static EmperorService instance = null;
    private final Data data = DataStoreService.getInstance().data;
    public final JavaPlugin plugin;
    private String empName;
    private PermissionAttachment permissionAttachment;

    public EmperorService(JavaPlugin plugin) {
        this.plugin = plugin;
        this.empName = "";
    }

    public static EmperorService getInstance() {
        return EmperorService.getInstance(null);
    }

    public static EmperorService getInstance(JavaPlugin plugin) {
        if (instance == null) {
            instance = new EmperorService(plugin);
        }
        return instance;
    }

    private void _setEmperor(String playerName) {
        if (!empName.isEmpty()) {
            Bukkit.getPlayer(empName).removeAttachment(permissionAttachment);
        }
        Player target = Bukkit.getPlayer(playerName);
        this.empName = target.getDisplayName();
        this.permissionAttachment = target.addAttachment(plugin);
        this.permissionAttachment.setPermission("ee.emperor", true);
    }

    public String getEmpName() {
        return empName;
    }

    public boolean isEmperor (String playerName) {
        if (data.emperor != null) {
            return playerName.equals(data.emperor);
        }
        return false;
    }

    public void setEmperor (String playerName) {
        data.emperor = playerName;
        this._setEmperor(playerName);
    }

    public Player getEmperor() {
        return (data.emperor == null) ? null : Bukkit.getPlayer(data.emperor);
    }

    public boolean isChallengeActive() {
        return data.challengeActive;
    }
}
