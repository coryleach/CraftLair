package com.coryleach.legends.lair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.Server;

import static org.bukkit.util.Java15Compat.Arrays_copyOfRange;
import org.bukkit.command.*;

public final class LairCommandMap implements CommandMap {
    private final Map<String, Command> knownCommands = new HashMap<String, Command>();
    private final Server server;

    public LairCommandMap(final Server server) {
        this.server = server;
        setDefaultCommands(server);
    }

    private void setDefaultCommands(final Server server) {
    }

    /**
     * Registers multiple commands. Returns name of first command for which fallback had to be used if any.
     * @param plugin
     * @return
     */
    public void registerAll(String fallbackPrefix, List<Command> commands) {
        if (commands != null) {
            for(Command c : commands) {
                register(fallbackPrefix, c);
            }
        }
    }

    private void register(String fallbackPrefix, Command command) {
        List<String> names = new ArrayList<String>();
        names.add(command.getName());
        names.addAll(command.getAliases());

        for (String name : names) {
            register(name, fallbackPrefix, command);
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean register(String name, String fallbackPrefix, Command command) {
        boolean nameInUse = (getCommand(name) != null);
        
        if (nameInUse) {
            name = fallbackPrefix + ":" + name;
        }

        knownCommands.put(name.toLowerCase(), command);
        return !nameInUse;
    }
    
    /**
     * {@inheritDoc}
     */
    public boolean dispatch(CommandSender sender, String commandLine) {
        String[] args = commandLine.split(" ");
        String sentCommandLabel = args[0].toLowerCase();

        args = Arrays_copyOfRange(args, 1, args.length);

        Command target = getCommand(sentCommandLabel);
        boolean isRegisteredCommand = (target != null);
        if (isRegisteredCommand) {
            try {
                target.execute(sender, sentCommandLabel, args);
            } catch (CommandException ex) {
                throw ex;
            } catch (Throwable ex) {
                throw new CommandException("Unhandled exception executing '" + commandLine + "' in " + target, ex);
            }
        }
        return isRegisteredCommand;
    }

    public void clearCommands() {
        synchronized (this) {
            knownCommands.clear();
            setDefaultCommands(server);
        }
    }

    public Command getCommand(String name) {
        return knownCommands.get(name.toLowerCase());
    }
  
}
