package com.myelephant.filestorageservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myelephant.filestorageservice.entity.FileDetail;

public interface FileDetailRepository extends MongoRepository<FileDetail, String> {

}
