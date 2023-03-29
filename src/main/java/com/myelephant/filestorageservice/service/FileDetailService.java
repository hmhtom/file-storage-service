package com.myelephant.filestorageservice.service;

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

    public void deleteFile(String fileId) {
        fileDetailRepository.deleteById(fileId);
    }
}
