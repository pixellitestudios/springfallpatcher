package studio.pixellite.patcher.patches;

import me.lucko.helper.Events;
import me.lucko.helper.terminable.TerminableConsumer;
import me.lucko.helper.terminable.module.TerminableModule;
import me.lucko.helper.utils.Players;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.jetbrains.annotations.NotNull;

public class LiteBansSqlExecPatch implements TerminableModule {
  @Override
  public void setup(@NotNull TerminableConsumer consumer) {
    Events.subscribe(PlayerCommandPreprocessEvent.class)
            .handler(e -> {
              String command = e.getMessage().toLowerCase();

              if(command.contains("litebans sqlexec")) {
                e.setCancelled(true);
                Players.msg(e.getPlayer(), "&cThat command is disabled.");
              }
            }).bindWith(consumer);
  }
}
