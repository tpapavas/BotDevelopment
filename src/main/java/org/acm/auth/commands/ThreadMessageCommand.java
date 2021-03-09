package org.acm.auth.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.acm.auth.utils.Threads;

import java.util.Arrays;

public class ThreadMessageCommand extends Command {
    public ThreadMessageCommand() {
        super(">", "message of a thread", false, new String[]{});
    }

    @Override
    public void invoke(MessageReceivedEvent event, String[] args) {
        String threadName = args[0];

        if (Threads.threadExists(threadName)) {
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setAuthor(event.getAuthor().getName(), null, event.getAuthor().getAvatarUrl());
            embedBuilder.setColor(Threads.getThreadColor(threadName));
            embedBuilder.addField(threadName, String.join(" ", Arrays.copyOfRange(args, 1, args.length)), true);

            event.getChannel().deleteMessageById(event.getMessage().getId()).queue();
            MessageBuilder msg = new MessageBuilder(embedBuilder.build());
            event.getChannel().sendMessage(msg.build()).queue();
        } else {
            event.getChannel().sendMessage("No such thread").queue();
        }
    }
}
