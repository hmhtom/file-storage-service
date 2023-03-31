package com.myelephant.filestorageservice.entity;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("FileDetail")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @NotBlank(message = "File name is mandatory")
    private String fileName;
    @NotBlank(message = "File type is mandatory")
    private String fileType;
    @NotBlank(message = "File url is mandatory")
    private String fileUrl;
    @NotNull(message = "File size is mandatory")
    private long fileSize;
}
