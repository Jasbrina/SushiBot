import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class GetAvatar extends Command {
    public GetAvatar() {
        this.name = "avatar";
        this.aliases = new String[]{"avatar"};
        this.help = "Adds role to user.";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        String getmsg = commandEvent.getMessage().getContentRaw();
        String nm = getmsg.substring(8, getmsg.length());

        String FINAL = commandEvent.getGuild().getMember(commandEvent.getMessage().getMentionedUsers().get(0)).getUser().getAvatarUrl();
        commandEvent.getChannel().sendMessage(FINAL).queue();
    }
}