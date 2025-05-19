package com.data.ss10.model.bt7;

import org.springframework.web.multipart.MultipartFile;

public class Document {
    private String name;
    private String url;
    private MultipartFile file;

    public Document() {}
    public Document(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public MultipartFile getFile() { return file; }
    public void setFile(MultipartFile file) { this.file = file; }
}