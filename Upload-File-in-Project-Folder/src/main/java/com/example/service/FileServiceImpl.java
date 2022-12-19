package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String UploadFile(String path, MultipartFile file) throws IOException {

        //File name
        String name=file.getOriginalFilename();
        //abc.png

//        //random name generate file
        String randomID = UUID.randomUUID().toString();
        String fileName1=randomID.concat(name.substring(name.lastIndexOf(".")));

        //file fullpath
        String filepath = path+ File.separator+fileName1;
//        String filepath = path+ File.separator+name;


        //create folder if not created
        File f= new File(path);
        if(!f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filepath));

        return name;
    }

    @Override
    public InputStream DownloadFile(String path, String fileName) throws FileNotFoundException {
        String filePath=path+File.separator+fileName;
        InputStream is=new FileInputStream(filePath);
        return is;
    }
}
