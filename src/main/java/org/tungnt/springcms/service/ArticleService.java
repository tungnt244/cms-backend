package org.tungnt.springcms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tungnt.springcms.dao.ArticleDAO;
import org.tungnt.springcms.entity.ArticleEnt;
import org.tungnt.springcms.form.ArticleSummaryForm;

@Service
public class ArticleService {

	@Autowired
	ArticleDAO articleDAO;

	public void create(ArticleEnt newArticle) {
		articleDAO.create(newArticle);
	}

	public void delete(ArticleEnt desiredDeleteArticle) {
		articleDAO.delete(desiredDeleteArticle);
	}

	public void updateArticleById(ArticleEnt updateArticle, long articleId) {
		ArticleEnt tempArticle = articleDAO.findById(articleId);
		tempArticle.setTitle(updateArticle.getTitle());
		tempArticle.setContent(updateArticle.getContent());
		articleDAO.update(tempArticle);
	}

	public Optional<ArticleEnt> findArticleById(long id) {
		ArticleEnt temp = articleDAO.findById(id);
		return Optional.ofNullable(temp);
	}

	public List<ArticleEnt> findAll() {
		List<ArticleEnt> listArticles = articleDAO.findAll();
		return listArticles;
	}

	public List<ArticleEnt> findTenArticlesAtPage(long page) {
		return articleDAO.findTenArticleAtPage(page);
	}

	public List<ArticleSummaryForm> findFourSummaryArticle(int page) {
		List<ArticleEnt> lists = articleDAO.findFourArticle(page);
		List<ArticleSummaryForm> listResults = new ArrayList<>();
		for (ArticleEnt articleEnt : lists) {
			ArticleSummaryForm temp = ArticleSummaryForm.builder().id(articleEnt.getId()).articleTitle(articleEnt.getTitle())
					.articleSubTitle(articleEnt.getSubTitle()).createdDate(articleEnt.getCreatedDate()).build();
			listResults.add(temp);
		}
		return listResults;
	}

	public long getTotalNumberArticle() {
		return articleDAO.getTotalNumberArticle();
	}
}
