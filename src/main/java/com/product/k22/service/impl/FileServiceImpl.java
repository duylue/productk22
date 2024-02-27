package com.product.k22.service.impl;

import com.product.k22.model.FileInfo;
import com.product.k22.repository.FileRepository;
import com.product.k22.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private FileRepository fileRepository;

    @Override
    public void uploadFile(int pid, MultipartFile file) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setPid(pid);
        try {
            fileInfo.setFileName(file.getOriginalFilename());
            fileInfo.setContentType(file.getContentType());
            fileInfo.setContent(file.getBytes());
            fileRepository.save(fileInfo);
        }catch (Exception e){

        }

    }

    @Override
    public FileInfo getFile(int pid) {
        return fileRepository.findByPid(pid);
    }
}
