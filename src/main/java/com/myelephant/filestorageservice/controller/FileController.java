package com.myelephant.filestorageservice.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myelephant.filestorageservice.entity.FileDetail;
import com.myelephant.filestorageservice.service.FileDetailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final FileDetailService fileDetailService;

    @PostMapping("/{folderId}")
    public ResponseEntity<?> addFileToFolder(@RequestBody FileDetail fileDetail, @PathVariable String folderId) {

        try {
            return new ResponseEntity<FileDetail>(fileDetailService.addFileToFolder(fileDetail, folderId),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{fileId}")
    public ResponseEntity<?> deleteFile(@PathVariable String fileId) {
        try {
            fileDetailService.deleteFile(fileId);
            return new ResponseEntity<String>("File deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
