package io.tenera.filemanager;

import io.tenera.filemanager.utils.Storage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(Storage.class)
public class FileManagerApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(FileManagerApplication.class, args);
    }
    
}
