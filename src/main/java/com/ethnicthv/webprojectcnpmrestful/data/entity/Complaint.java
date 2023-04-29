package com.ethnicthv.webprojectcnpmrestful.data.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "complaints")
public class Complaint {
    @Id
    private Long id;

    private String title;

    private String overview;

    private String description;
}
