package studio.pixellite.patcher;

import me.lucko.helper.plugin.ExtendedJavaPlugin;
import studio.pixellite.patcher.patches.CommandColonSyntaxPatch;

public class PatcherPlugin extends ExtendedJavaPlugin {
  @Override
  protected void enable() {
    new CommandColonSyntaxPatch().setup(this);
  }
}
