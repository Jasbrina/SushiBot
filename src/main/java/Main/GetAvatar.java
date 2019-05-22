package Main;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;

import java.awt.*;

public class GetAvatar extends Command {
    public GetAvatar() {
        this.name = "avatar";
        this.aliases = new String[]{"avatar"};
        this.help = "Pulls up a link to the user's avatar. If a user is linked, it will pull up that specified user's avatar.";
    }

    @Override
    protected void execute(CommandEvent commandEvent) {
        String getmsg = commandEvent.getMessage().getContentRaw();
        String avatarUrl;
        String username;

        if (getmsg.length() == 7){
            avatarUrl = commandEvent.getAuthor().getAvatarUrl();
            username = commandEvent.getAuthor().getName();
        }
        else{
            String nm = getmsg.substring(8, getmsg.length());
            String FINAL = commandEvent.getGuild().getMember(commandEvent.getMessage().getMentionedUsers().get(0)).getUser().getAvatarUrl();
            avatarUrl = FINAL;
            username = commandEvent.getMessage().getMentionedUsers().get(0).getName();
        }

        avatarUrl = avatarUrl.concat("?size=1024");
        EmbedBuilder b = new EmbedBuilder();
        b.setAuthor(username);
        b.setDescription("[" + "Direct link" + "]" +"(" +avatarUrl + ")");
        b.setColor(Color.GREEN);
        b.setImage(avatarUrl);
        commandEvent.reply(b.build());
    }
}