package org.acm.auth.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.acm.auth.utils.Threads;

import java.util.Arrays;

public class ThreadCreationCommand extends Command {
    public ThreadCreationCommand() {
        super("thread", "a messaging thread creator", false, new String[]{});
    }

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        String threadName = args[0];

        if (args.length == 2 && args[1].equals(".")) {
            if (Threads.deleteThread(threadName))
                event.getChannel().sendMessage(event.getAuthor().getAsMention() + " closed thread " + threadName).queue();
            else
                event.getChannel().sendMessage("There is nothing to close.").queue();
            return;
        }

        if (Threads.addThread(threadName)) {
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + " created thread " + "**" + threadName + "**").queue();
//            event.getChannel().pinMessageById(event.getChannel().getLatestMessageId()).queue();
        } else {
            event.getChannel().sendMessage("Thread " + threadName + " already exists.").queue();
        }
    }
}
