package io.tenera.filemanager.model;

import io.tenera.filemanager.model.dto.FileDto;
import org.apache.commons.io.FileUtils;

import java.io.File;

public class FileMapper
{
    private static final File ROOT = new File("/Users/massi");
    
    public static FileDto toDto(File file) {
        String relativePath =  file.getAbsolutePath().replace(ROOT.getAbsolutePath(), "");
        String downloadUrl = "file://".concat(file.getAbsolutePath());
        String files = null;
        if (file.isDirectory()) {
            downloadUrl = "file://".concat(file.getParentFile().getAbsolutePath()).concat("/").concat(file.getName()).concat(".zip");
            files = "http://localhost:8080/filemanager/dir/".concat(relativePath);
        }
        return FileDto.builder()
            .name(file.getName())
            .path("http://localhost:8080/filemanager/file/".concat(relativePath))
            .files(files)
            .modified(file.lastModified())
            .size(FileUtils.sizeOfAsBigInteger(file))
            .downloadUrl(downloadUrl)
            .build();
    }
}
