package io.tenera.filemanager.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@ConfigurationProperties("storage")
@Getter
@Setter
public class Storage
{
    private String root;
}
