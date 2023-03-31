package com.myelephant.filestorageservice.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.myelephant.filestorageservice.entity.FolderDetail;
import com.myelephant.filestorageservice.service.FolderDetailService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/folder")
@AllArgsConstructor
public class FolderController {

    private FolderDetailService folderDetailService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FolderDetail createInitialFolder(@RequestBody FolderDetail folderDetail) {
        return folderDetailService.createInitialFolder(folderDetail);
    }

    @PostMapping("/{folderId}")
    public ResponseEntity<?> addFolder(@RequestBody FolderDetail folderDetail, @PathVariable String folderId) {
        try {
            return new ResponseEntity<FolderDetail>(folderDetailService.addFolder(folderDetail, folderId),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{folderId}")
    public ResponseEntity<?> getAllFolders(@PathVariable String folderId) {
        try {
            return new ResponseEntity<FolderDetail>(folderDetailService.getFolderById(folderId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/{folderId}")
    public ResponseEntity<?> updateFolderName(@RequestBody FolderDetail folderDetail, @PathVariable String folderId) {
        try {
            return new ResponseEntity<FolderDetail>(folderDetailService.updateFolderName(folderDetail, folderId),
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{folderId}")
    public ResponseEntity<?> deleteFolderById(@PathVariable String folderId) {
        try {
            folderDetailService.deleteFolderById(folderId);
            return new ResponseEntity<String>("Folder deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
