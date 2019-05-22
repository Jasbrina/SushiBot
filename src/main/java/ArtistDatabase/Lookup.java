package Main.ArtistDatabase;

import Main.Secret.Secret;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.BatchGetValuesResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Lookup {
    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "";
    private static String name;
    private static List<String> listsites;
    Secret secret;

    public Lookup(){
        secret = new Secret();
        SPREADSHEET_ID = secret.getSPREADSHEET();
        try{
            sheetsService = SheetsServiceUtil.getSheetsService();
        }catch (IOException|GeneralSecurityException e){
            e.printStackTrace();
        }
    }

    public static void setup() throws GeneralSecurityException, IOException {
        sheetsService = SheetsServiceUtil.getSheetsService();
    }


    public  List<String> getArtist(String name) throws IOException, GeneralSecurityException{
        String n = name.substring(0,1).toUpperCase();
        String range = n;

        String rangetemplate = range+"!D";
        List<String> arrayList = new ArrayList<String>();

        for (int i = 3; i <= 100; i++) arrayList.add(rangetemplate+i);

        BatchGetValuesResponse result =
                sheetsService.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(arrayList).execute();

        String linkRange = null;
        for (int i = 0; i < result.getValueRanges().size(); i++){
            if (result.getValueRanges().get(i).getValues() != null) {
                String user = result.getValueRanges().get(i).getValues().get(0).get(0).toString();
                System.out.println(user);
                if (user.toLowerCase().contains(name.toLowerCase())){
                    //for some reason, the C Value is handled differently; i.e 'C'!D6 in comparison to E!D9. This
                    // workaround compensates for that difference.
                    if (range.equals("C"))
                        linkRange =result.getValueRanges().get(i).getRange().substring(5,
                                result.getValueRanges().get(i).getRange().length());
                    else linkRange = result.getValueRanges().get(i).getRange().substring(3,
                            result.getValueRanges().get(i).getRange().length());
                    this.name = user;
                    break;
                }
            }
        }

        List<String> linkList = new ArrayList<String>();
        String links = range + "!F";

        int j = Integer.parseInt(linkRange);
        List<String> secondl = new ArrayList<String>();

        for (int i = j; i < j+10; i++) secondl.add(links + i);

        BatchGetValuesResponse result_2 =
                sheetsService.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(secondl).execute();

        for (int i = 0; i < result_2.getValueRanges().size(); i++){
            if (result_2.getValueRanges().get(i).getValues() != null){
                linkList.add(result_2.getValueRanges().get(i).getValues().get(0).get(0).toString());
            }else break;
        }

        String linkname = range + "!E";
        List<String> thirdl = new ArrayList<String>();
        for (int i = Integer.parseInt(linkRange); i < Integer.parseInt(linkRange)+10; i++) thirdl.add(linkname + i);


        BatchGetValuesResponse result_3 =
                sheetsService.spreadsheets().values().batchGet(SPREADSHEET_ID).setRanges(thirdl).execute();

        listsites = new ArrayList<String>();
        for (int i = 0; i < result_3.getValueRanges().size(); i++){
            if (result_3.getValueRanges().get(i).getValues() != null){
                this.listsites.add(result_3.getValueRanges().get(i).getValues().get(0).get(0).toString());
            }else break;
        }
        return linkList;
    }

    public String getName(){return name;}
    public List<String> sites(){return listsites;}
}
