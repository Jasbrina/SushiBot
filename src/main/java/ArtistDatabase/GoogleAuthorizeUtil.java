package Main.ArtistDatabase;

import Main.Secret.Secret;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.MemoryDataStoreFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sqladmin.SQLAdmin;
import com.google.api.services.sqladmin.SQLAdminScopes;
//import com.google.api.services.sqladmin.SQLAdmin.Instances.List;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoogleAuthorizeUtil {
    public static Credential authorize() throws IOException, GeneralSecurityException {
        Secret secret = new Secret();

        InputStream in = GoogleAuthorizeUtil.class.getResourceAsStream(secret.getA());

        GoogleCredential cred = GoogleCredential.fromStream(new FileInputStream("C:\\Users\\SUSHIHAMMER\\Documents\\IdeaProjects\\sushibot\\sushibot\\src\\main\\resources\\test-Repository-efdd3c993e30.json")).createScoped(Collections.singleton(SQLAdminScopes.SQLSERVICE_ADMIN));

        SQLAdmin sqladmin =
                new SQLAdmin.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), cred).setApplicationName("SushiBot").build();

        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
        List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);

        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), clientSecrets, scopes).setDataStoreFactory(new MemoryDataStoreFactory())
                .setAccessType("offline").build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
        SQLAdmin.Instances.List instances =
                sqladmin.instances().list("SushiBot");
        return credential;
    }

}