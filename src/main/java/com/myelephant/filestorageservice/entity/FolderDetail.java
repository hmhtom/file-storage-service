package com.myelephant.filestorageservice.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("FolderDetail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FolderDetail {

    // MongoDB ID
    @Id
    private String id;
    @NotBlank(message = "Folder name is mandatory")
    private String folderName;
    @DBRef(lazy = true)
    @Builder.Default
    private List<FolderDetail> subFolders = new ArrayList<FolderDetail>();
    @DBRef(lazy = true)
    @Builder.Default
    private List<FileDetail> files = new ArrayList<FileDetail>();

}
