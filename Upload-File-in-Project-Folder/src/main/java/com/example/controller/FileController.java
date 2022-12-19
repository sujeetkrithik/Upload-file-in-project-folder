package com.example.controller;

import com.example.payload.FileResponse;
import com.example.service.FileService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

@RestController
@RequestMapping("/file")
public class FileController {
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;
    String location;

    @PostMapping("/upload")
    public ResponseEntity<FileResponse> uploadFile(@RequestParam("image") MultipartFile image){
        String fileName= null;

        String type= image.getContentType();
        String size= String.valueOf(image.getSize());
        location= String.valueOf((Paths.get(image.getOriginalFilename())).normalize().toAbsolutePath());

        try {
            fileName = this.fileService.UploadFile(path,image);
        }
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(new FileResponse(null,"The image has not uploaded due to error on server", null, null, null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(new FileResponse(fileName,"Image has been successfully uploaded", type, size, location), HttpStatus.OK);
    }

    @GetMapping("/download/{fileName}")
    public void downloadFile(@PathVariable("fileName") String fileName,
                              HttpServletResponse response) throws IOException{

        InputStream resource=this.fileService.DownloadFile(path, fileName);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
