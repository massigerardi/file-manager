package io.tenera.filemanager.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigInteger;

@Builder
@Getter
public class FileDto
{
    private final String name;
    private final String path;
    private final String files;
    private final Long modified;
    private final BigInteger size;
    private final String downloadUrl;
    
}
