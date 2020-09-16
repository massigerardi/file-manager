package io.tenera.filemanager.services;

import io.tenera.filemanager.model.FileMapper;
import io.tenera.filemanager.model.dto.FileDto;
import io.tenera.filemanager.utils.Storage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService
{

    private final Path rootLocation;
    
    @Autowired
    public FileService(Storage storage) {
        this.rootLocation = Paths.get(storage.getRoot());
    }
    
    private File resolveFile(String filename) {
        return this.rootLocation.toAbsolutePath().resolve(filename).toFile();
    }
    
    public List<FileDto> getFiles(String filename) {
        File file = resolveFile(filename);
        if (!file.isDirectory())
            throw new IllegalArgumentException(filename);
        return Arrays.stream(file.listFiles())
            .filter(it -> !it.getName().startsWith("."))
            .map(FileMapper::toDto)
            .collect(Collectors.toList());
    }

    public FileDto getFile(String  filename) {
        return FileMapper.toDto(resolveFile(filename));
    }

    public FileDto createDir(String  dirname) {
        File dir = resolveFile(dirname);
        dir.mkdirs();
        return FileMapper.toDto(dir);
    }

    public FileDto saveFile(MultipartFile file, String dir) throws RuntimeException
    {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty() || filename.contains("..")) {
                throw new RuntimeException("Failed to store empty file " + filename);
            }
            file.transferTo(this.rootLocation.toAbsolutePath().resolve(dir).resolve(filename).toFile());
        }
        catch (IOException e) {
            throw new RuntimeException("Failed to store file " + filename, e);
        }
        return FileMapper.toDto(this.rootLocation.resolve(filename).toFile());
    }




}
