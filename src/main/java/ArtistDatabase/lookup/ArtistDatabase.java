//package Main.ArtistDatabase.lookup;
//
//import com.google.api.services.sheets.v4.Sheets;
//import com.google.api.services.sheets.v4.model.ValueRange;
//import com.jagrosh.jdautilities.command.Command;
//import com.jagrosh.jdautilities.command.CommandEvent;
//
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.List;
//
//public class Main.ArtistDatabase extends Command {
//    private static Sheets sheetsService;
//    private static final String SPREADSHEET_ID = "1rBY1Oq3lZIciOV86IKUpGJc_c4PYdAqJLnTrlQmAaKY";
//
//
//    public Main.ArtistDatabase() {
//        this.name = "a";
//        this.aliases = new String[]{"a"};
//        this.help = "Search for an artist.";
//    }
//
//    public static void main(String[] args) throws IOException,GeneralSecurityException {
//        setup();
//        getVals();
//    }
//
//    public static void setup() throws GeneralSecurityException, IOException {
//        sheetsService = Main.ArtistDatabase.SheetsServiceUtil.getSheetsService();
//    }
//
//    public static List<Object> getVals() throws IOException, GeneralSecurityException{
//        sheetsService = Main.ArtistDatabase.SheetsServiceUtil.getSheetsService();
//        String range = "E!D4";
//        ValueRange result = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();
//        int numRows = result.getValues() != null ? result.getValues().size() : 0;
//        System.out.printf("%d rows retrieved. \n", numRows, result.toPrettyString());
////        System.out.println(result.toPrettyString());
////        System.out.println(result.toString());
////        System.out.println("ok:");
////        System.out.println(result.getValues().get(0));
//        return result.getValues().get(0);
//
//    }
//
//    @Override
//    protected void execute(CommandEvent commandEvent) {
//        String initial = commandEvent.getMessage().getContentRaw();
//        String new_i = initial.substring(4, initial.length());
//
//        commandEvent.reply(new_i);
//
//    }
//}
