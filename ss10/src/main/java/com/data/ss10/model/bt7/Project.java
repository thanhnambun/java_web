package com.data.ss10.model.bt7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Project {
    private int id;
    private String name;
    private String description;
    private List<Document> documents = new ArrayList<>();
}
