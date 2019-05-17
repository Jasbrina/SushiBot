import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class GetAvatar extends Command {
    public GetAvatar() {
        this.name = "avatar";
        this.aliases = new String[]{"avatar"};
        this.help = "Pulls up a link to the user's avatar. If a user is linked, it will pull up that specified user's avatar.";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        String getmsg = commandEvent.getMessage().getContentRaw();
        String nm = getmsg.substring(8, getmsg.length());

        String FINAL = commandEvent.getGuild().getMember(commandEvent.getMessage().getMentionedUsers().get(0)).getUser().getAvatarUrl();
        commandEvent.getChannel().sendMessage(FINAL).queue();
    }
}