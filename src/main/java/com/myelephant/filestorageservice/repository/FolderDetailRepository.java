package com.myelephant.filestorageservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myelephant.filestorageservice.entity.FolderDetail;

public interface FolderDetailRepository extends MongoRepository<FolderDetail, String> {

}
