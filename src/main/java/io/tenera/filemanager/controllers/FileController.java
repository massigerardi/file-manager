package io.tenera.filemanager.controllers;

import io.tenera.filemanager.model.dto.FileDto;
import io.tenera.filemanager.services.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/filemanager")
public class FileController
{
    
    @Autowired
    private FileService fileService;

    @RequestMapping(path = "/dir/**", method = RequestMethod.GET)
    public ResponseEntity<List<FileDto>> getDirFiles(HttpServletRequest request) {
        String path = request.getRequestURI()
            .split(request.getContextPath() + "/filemanager/dir/")[1];
        return ResponseEntity.ok(fileService.getFiles(path));
    }

    @RequestMapping(path = "/dir/**", method = RequestMethod.POST)
    public ResponseEntity<FileDto> createDir(HttpServletRequest request) {
        String path = request.getRequestURI()
            .split(request.getContextPath() + "/filemanager/dir/")[1];
        return ResponseEntity.ok(fileService.createDir(path));
    }

    @RequestMapping(path = "/file/**", method = RequestMethod.GET)
    public ResponseEntity<FileDto> getFile(HttpServletRequest request) {
        String path = request.getRequestURI()
            .split(request.getContextPath() + "/filemanager/file/")[1];
        return ResponseEntity.ok(fileService.getFile(path));
    }

    @RequestMapping(path = "/file/**", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<FileDto> uploadFile(
        HttpServletRequest request, 
        @RequestParam("file") MultipartFile file) {
        String path = request.getRequestURI()
            .split(request.getContextPath() + "/filemanager/file/")[1];
        return ResponseEntity.ok(fileService.saveFile(file, path));
    }


}
