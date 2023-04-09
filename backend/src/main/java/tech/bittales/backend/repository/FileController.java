//package tech.bittales.backend.repository;
//
//import org.springframework.core.io.FileSystemResource;
//import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.File;
//
//@RestController
//public class FileController {
//
//    private static final String ROOT_PATH = "static/assets/audio/";
//
//    @GetMapping("/assets/audio/{fileName}")
//    public ResponseEntity<Resource> getFile(@PathVariable String fileName) {
//
//        File file = new File(ROOT_PATH + fileName);
//
//        if (file.exists()) {
//            System.out.println("file exisits"+file.getAbsolutePath());
//            Resource resource = new FileSystemResource(file);
//            return ResponseEntity.ok()
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
//                    .body(resource);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}