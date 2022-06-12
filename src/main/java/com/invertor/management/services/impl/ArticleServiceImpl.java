package com.invertor.management.services.impl;

import com.invertor.management.dto.ArticleDto;
import com.invertor.management.repository.ArticleRepository;
import com.invertor.management.services.interfaces.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private ArticleRepository articleRepository;
    @Override
    public ArticleDto saveArticle(ArticleDto articleDto) {
        return null;
    }

    @Override
    public ArticleDto findById(Long id) {
        return null;
    }

    @Override
    public ArticleDto findByCodeArticle(String codeArticle) {
        return null;
    }

    @Override
    public List<ArticleDto> listArticle() {
        return null;
    }

    @Override
    public void deleteArticle(Long id) {

    }
}
