package com.example.payload;

public class FileResponse {
    private String fileName;
    private String message;
    private String type;

    private String size;
    private String location;

   public FileResponse(String fileName, String message, String type, String size,String location){
        this.fileName=fileName;
        this.message=message;
        this.type=type;
        this.size=size;
        this.location=location;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
