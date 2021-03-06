package otp.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.util.ChatComponentText;
import otp.persist.TeleportRegistry;

import java.util.Arrays;
import java.util.List;

public class CommandClear implements ISubCommand
{
    @Override
    public int getPermissionLevel()
    {
        return 3;
    }

    @Override
    public String getCommandName()
    {
        return "clear";
    }

    @Override
    public void handleCommand(ICommandSender sender, String[] arguments)
    {
        if (arguments.length == 2)
        {
            if (arguments[1].equals("tp"))
                TeleportRegistry.clearTP();
            else if (arguments[1].equals("logouts"))
                TeleportRegistry.clearLogouts();
            else if (arguments[1].equals("names"))
                TeleportRegistry.clearNames();
            else
                throw new WrongUsageException(ParentCommand.textMap.get("clear.syntax"));
            sender.addChatMessage(new ChatComponentText(ParentCommand.textMap.get("clear." + arguments[1])));
            return;
        }
        throw new WrongUsageException(ParentCommand.textMap.get("clear.syntax"));
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args)
    {
        return CommandBase.getListOfStringsFromIterableMatchingLastWord(args, Arrays.asList("otp", "logouts", "players"));
    }
}
