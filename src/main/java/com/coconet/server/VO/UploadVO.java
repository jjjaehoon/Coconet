package com.coconet.server.VO;

import lombok.Getter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@ToString
public class UploadVO {
    private String name;
    private MultipartFile file;
}
