package ArtistDatabase.lookup;

import ArtistDatabase.SheetsServiceUtil;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class PreProdTest {
    private static Sheets sheetsService;
    private static final String SPREADSHEET_ID = "1rBY1Oq3lZIciOV86IKUpGJc_c4PYdAqJLnTrlQmAaKY";

    public static void main() throws  IOException, GeneralSecurityException {
        setup();
        getVals();
    }

    public static void main(String[] args) throws IOException,GeneralSecurityException{
        System.out.println("1");
        setup();
        getVals();
        System.out.println("2");
    }


    public static void setup() throws GeneralSecurityException, IOException {
        sheetsService = SheetsServiceUtil.getSheetsService();
    }

    public static List<Object> getVals() throws IOException, GeneralSecurityException{
        sheetsService = SheetsServiceUtil.getSheetsService();
        String range = "E!D4";
        ValueRange result = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, range).execute();
        int numRows = result.getValues() != null ? result.getValues().size() : 0;
        System.out.printf("%d rows retrieved. \n", numRows, result.toPrettyString());
//        System.out.println(result.toPrettyString());
//        System.out.println(result.toString());
//        System.out.println("ok:");
//        System.out.println(result.getValues().get(0));
        return result.getValues().get(0);

    }

    public List<Object> getval() throws IOException, GeneralSecurityException{return getVals();}





}
