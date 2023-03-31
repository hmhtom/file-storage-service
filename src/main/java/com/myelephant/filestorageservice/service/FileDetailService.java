package com.myelephant.filestorageservice.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import com.myelephant.filestorageservice.entity.FileDetail;
import com.myelephant.filestorageservice.entity.FolderDetail;
import com.myelephant.filestorageservice.repository.FileDetailRepository;
import com.myelephant.filestorageservice.repository.FolderDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileDetailService {

    private final FileDetailRepository fileDetailRepository;
    private final FolderDetailRepository folderDetailRepository;

    @CachePut(value = "file", key = "#fileDetail.id")
    public FileDetail addFileToFolder(FileDetail fileDetail, String folderId) {

        // Get the parent folder
        FolderDetail parentFolder = folderDetailRepository.findById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        // Save the file
        FileDetail file = fileDetailRepository.save(fileDetail);

        // Add the file to the parent folder
        parentFolder.getFiles().add(file);

        // Save the parent folder
        folderDetailRepository.save(parentFolder);

        return file;
    }

    @CacheEvict(value = "file", key = "#fileId")
    public void deleteFile(String fileId) {
        fileDetailRepository.deleteById(fileId);
    }
}
