package com.example.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

@Service
public interface FileService {

    String UploadFile(String path, MultipartFile file) throws IOException;

    InputStream DownloadFile(String path, String fileName) throws FileNotFoundException;
}