//package tech.bittales.backend.service;
//import java.io.InputStream;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.http.ByteArrayContent;
//import com.google.api.client.http.HttpRequest;
//import com.google.api.client.http.HttpRequestInitializer;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.drive.Drive;
//import com.google.api.services.drive.DriveScopes;
//import org.springframework.stereotype.Service;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.security.GeneralSecurityException;
//import java.util.Collections;
//import java.util.List;
//
//@Service
//public class AudioService {
//
//    private static final String APPLICATION_NAME = "MyDrive";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//    private static final List<String> SCOPES = Collections.singletonList(DriveScopes.DRIVE_READONLY);
//
//    public String getAudioFileFromGoogleDrive(String fileId, String accessToken) throws IOException, GeneralSecurityException {
//        GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
//
//        HttpTransport httpTransport = new NetHttpTransport();
//        Drive drive = new Drive.Builder(httpTransport, JSON_FACTORY, new HttpRequestInitializer() {
//            @Override
//            public void initialize(HttpRequest request) throws IOException {
//                credential.initialize(request);
//                request.setConnectTimeout(60000); // 60 seconds
//                request.setReadTimeout(60000); // 60 seconds
//            }
//        }).setApplicationName(APPLICATION_NAME).build();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        drive.files().get(fileId).executeMediaAndDownloadTo(outputStream);
//        return outputStream.toString();
//    }
//
//}
