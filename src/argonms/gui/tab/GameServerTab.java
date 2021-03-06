/*
 * ArgonMS Server Manager - a process launcher and organizer utilizing Swing.
 * Copyright (C) 2011-2013  GoldenKevin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package argonms.gui.tab;

import argonms.gui.model.Environment;
import argonms.gui.model.Model;

/**
 * 
 * @author GoldenKevin
 */
@SuppressWarnings("serial")
public class GameServerTab extends ServerTab {
	private final byte serverId;

	/**
	 * This method is not thread-safe. It must be called from the Swing EDT.
	 */
	public GameServerTab(Model state, byte serverId) {
		super(state);
		this.serverId = serverId;
	}

	@Override
	protected String getDescription() {
		return "Game" + serverId;
	}

	@Override
	protected String[] getCommand() {
		return new String[] {
			Environment.JAVA_DIR,
			"-classpath", state.getConfig().getClasspath(),
			"-Xmx" + Environment.MAX_HEAP_SIZE + "m",
			"-Dargonms.game.serverid=" + serverId,
			"-Dargonms.game.config.file=" + state.getConfig().getGameServerPropertiesPath(serverId),
			"-Djava.util.logging.config.file=" + state.getConfig().getLoggerPropertiesPath(),
			"-Dargonms.db.config.file=" + state.getConfig().getDatabasePropertiesPath(),
			"-Dargonms.ct.macbanblacklist.file=" + state.getConfig().getMacBanBlacklistPath(),
			"-Dargonms.data.dir=" + state.getConfig().getWzPath(),
			"-Dargonms.scripts.dir=" + state.getConfig().getScriptsPath(),
			"argonms.game.GameServer"
		};
	}
}
