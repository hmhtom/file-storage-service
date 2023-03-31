package com.myelephant.filestorageservice.service;

import java.util.Collections;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.myelephant.filestorageservice.entity.FolderDetail;
import com.myelephant.filestorageservice.repository.FolderDetailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FolderDetailService {

    private final FolderDetailRepository folderDetailRepository;

    @CachePut(value = "folder", key = "#folderDetail.id")
    public FolderDetail createInitialFolder(FolderDetail folderDetail) {
        return folderDetailRepository.save(folderDetail);
    }

    @CachePut(value = "folder", key = "#folderDetail.id")
    public FolderDetail addFolder(FolderDetail folderDetail, String folderId) {

        // Get the parent folder
        FolderDetail parentFolder = folderDetailRepository
                .findById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        // Save the child folder
        FolderDetail childFolder = folderDetailRepository.save(folderDetail);

        // Add the child folder to the parent folder and remove all null value
        parentFolder.getSubFolders().add(childFolder);
        parentFolder.getSubFolders().removeAll(Collections.singleton(null));

        // Save the parent folder
        return folderDetailRepository.save(parentFolder);

    }

    @Cacheable(value = "folder", key = "#folderId")
    public FolderDetail getFolderById(String folderId) {
        // Get folder
        FolderDetail folder = folderDetailRepository
                .findById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        // Remove null values from subfolders
        folder.getSubFolders().removeAll(Collections.singleton(null));
        folder.getFiles().removeAll(Collections.singleton(null));

        // Save and return folder
        return folderDetailRepository.save(folder);
    }

    @CachePut(value = "folder", key = "#folderDetail.id")
    public FolderDetail updateFolderName(FolderDetail folderDetail, String folderId) {

        FolderDetail folder = folderDetailRepository
                .findById(folderId)
                .orElseThrow(() -> new RuntimeException("Folder not found"));

        folder.setFolderName(folderDetail.getFolderName());

        return folderDetailRepository.save(folder);
    }

    @CacheEvict(value = "folder", key = "#folderId")
    public void deleteFolderById(String folderId) {
        folderDetailRepository.deleteById(folderId);
    }
}
