package Main;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;
import net.dv8tion.jda.core.entities.User;

import java.awt.*;
import java.time.OffsetDateTime;

public class UserInfo extends Command {

    public UserInfo(){
        this.name = "user";
        this.aliases = new String[]{"user"};
        this.help = "Use -user @<username> or -user for self to display user information.";
    }


    @Override
    public void execute(CommandEvent e) {
        String username, nickname, avatarURL, ID;
        OffsetDateTime joins, joind;

        if (e.getMessage().getContentRaw().length() == 5){
            username = e.getAuthor().getName();
            avatarURL = e.getAuthor().getAvatarUrl();
            ID = e.getAuthor().getId();
            nickname =  e.getGuild().getMemberById(ID).getEffectiveName();

            joins = e.getGuild().getMemberById(ID).getJoinDate();
            joind = e.getGuild().getMemberById(ID).getUser().getCreationTime();

        }else{
            User u = e.getMessage().getMentionedUsers().get(0);
            username = u.getName();
            avatarURL = u.getAvatarUrl();
            ID = u.getId();
            nickname = e.getGuild().getMemberById(ID).getEffectiveName();

            joins = e.getGuild().getMemberById(ID).getJoinDate();
            joind = e.getGuild().getMemberById(ID).getUser().getCreationTime();

        }

        MessageEmbed.Field usernamef = new MessageEmbed.Field("Name", username, true);
        MessageEmbed.Field nicknamef = new MessageEmbed.Field("Nickname", nickname, true);
        MessageEmbed.Field IDf = new MessageEmbed.Field("ID", ID, true);

        String j = joins.getMonth().getValue() + "." + joins.getDayOfMonth() + "." + joins.getYear();
        String d = joind.getMonth().getValue() + "." + joind.getDayOfMonth() + "." + joind.getYear();

        MessageEmbed.Field join = new MessageEmbed.Field("Joined Server", j, true);
        MessageEmbed.Field create = new MessageEmbed.Field("Joined Discord", d, true);

        EmbedBuilder b = new EmbedBuilder();
        b.setColor(Color.MAGENTA);
        b.setThumbnail(avatarURL);
        b.addField(usernamef).addField(nicknamef).addField(IDf).addField(join).addField(create);


        e.getChannel().sendMessage(b.build()).queue();

    }

}
