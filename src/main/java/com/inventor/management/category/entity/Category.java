package com.inventor.management.category.entity;

import com.inventor.management.article.entity.Article;
import com.inventor.management.core.entities.AbstractEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
@EqualsAndHashCode(callSuper = true)
public class Category extends AbstractEntity {

    @Column(name = "code_categorie")
    private String codeCategory;

    @Column(name = "id_enterprise")
    private Long id_enterprise;

    @Column(name = "designation")
    private String designation;

    @OneToMany(mappedBy = "category")
    private List<Article> articles;
}
