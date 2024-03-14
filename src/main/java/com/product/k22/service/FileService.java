package com.product.k22.service;

import com.product.k22.model.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void uploadFile(int pid, MultipartFile file, int fid);
    FileInfo getFile(int pid);
    int findFid(int pid);
}
