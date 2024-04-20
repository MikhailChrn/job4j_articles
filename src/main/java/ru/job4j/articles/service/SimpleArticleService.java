package ru.job4j.articles.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.articles.model.Article;
import ru.job4j.articles.model.Word;
import ru.job4j.articles.store.Store;

import java.util.Collections;
import java.util.List;


public class SimpleArticleService implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleArticleService.class.getSimpleName());

    private List<Word> words;

    public SimpleArticleService() {
    }

    @Override
    public void generate(Store<Word> wordStore, int count, Store<Article> articleStore) {
        LOGGER.info("Геренация статей в количестве {}", count);
        this.words = wordStore.findAll();
        StringBuilder articleBuilder = new StringBuilder();
        for (int i = 1; i <= count; i++) {
            LOGGER.info("Сгенерирована статья № {}", i);
            Collections.shuffle(words);
            words.forEach(word -> {
                articleBuilder.append(word.getValue());
                articleBuilder.append(" ");
            });
            articleStore.save(new Article(i, articleBuilder.toString()));
            articleBuilder.setLength(0);
        }
    }
}
