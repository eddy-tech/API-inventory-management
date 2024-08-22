package com.inventor.management.inventor_management.category.entity;

import com.inventor.management.inventor_management.article.entity.Article;

import com.inventor.management.inventor_management.core.domains.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@SuperBuilder
public class Category extends AbstractEntity {
    @Column(name = "code_categorie")
    private String codeCategory;

    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;
}
