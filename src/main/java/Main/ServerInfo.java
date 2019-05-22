package Main;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.MessageEmbed;

import java.awt.*;
import java.time.OffsetDateTime;

public class ServerInfo extends Command {

    public ServerInfo(){
        this.name = "server";
        this.aliases = new String[]{"server"};
        this.help = "Use -server to display server information.";
    }

    @Override
    public void execute(CommandEvent e) {
        EmbedBuilder b = new EmbedBuilder();
        b.setColor(Color.PINK);
        b.setAuthor("Server Info");
        b.setTitle(e.getGuild().getName());
        b.setThumbnail(e.getGuild().getIconUrl());
        MessageEmbed.Field IDfield = new MessageEmbed.Field("ID", e.getGuild().getId(), true);
        MessageEmbed.Field owner = new MessageEmbed.Field("Owner", e.getGuild().getOwner().getUser().getName(), true);
        MessageEmbed.Field members = new MessageEmbed.Field("Members",
                Integer.toString(e.getGuild().getMembers().size()),
                true);
        MessageEmbed.Field textchannels = new MessageEmbed.Field("Text Channels",
                Integer.toString(e.getGuild().getTextChannels().size()),
                true);
        MessageEmbed.Field voicechannels = new MessageEmbed.Field("Voice Channels",
                Integer.toString(e.getGuild().getVoiceChannels().size()),
                true);
        OffsetDateTime d = e.getGuild().getCreationTime();
        String date =
                d.getMonth().getValue() + "." + d.getDayOfMonth() + "." + d.getYear() + "  " + d.getHour() +":" + d.getMinute();
        MessageEmbed.Field createdOn =
                new MessageEmbed.Field("Creation Date",date, true);;
        MessageEmbed.Field region = new MessageEmbed.Field("Region", e.getGuild().getRegionRaw(), true);
        MessageEmbed.Field numRoles = new MessageEmbed.Field("Roles", Integer.toString(e.getGuild().getRoles().size()),
                true);
        MessageEmbed.Field o = new MessageEmbed.Field("ID", e.getGuild().getId(), false);

        b.addField(IDfield).addField(owner).addField(members).addField(textchannels).addField(voicechannels).addField(createdOn).addField(region).addField(numRoles);
        e.getChannel().sendMessage(b.build()).queue();
    }

}
