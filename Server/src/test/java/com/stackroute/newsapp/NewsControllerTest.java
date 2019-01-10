package com.stackroute.newsapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.newsapp.controller.NewsAppController;
import com.stackroute.newsapp.domain.News;
import com.stackroute.newsapp.service.NewsManagerService;
import com.stackroute.newsapp.util.UserUtil;

/**
 * @author Adarsh Test class for controller level
 */
@RunWith(SpringRunner.class)
@WebMvcTest(NewsAppController.class)

public class NewsControllerTest {

	@Autowired
	private transient MockMvc mvc;
	@MockBean
	private transient NewsManagerService service;
	private transient News news1;
	static List<News> newsList;
	@MockBean
	private transient UserUtil userUtil;

	@Before
	public void setUp() {
		news1 = new News();
		news1.setAuthor("authorE");
		news1.setDescription("descriptionE");
		news1.setTitle("titleE");
		news1.setId("idE");
		news1.setNewsId(1);
		News news2 = new News();
		news2.setAuthor("authorF");
		news2.setDescription("descriptionF");
		news2.setTitle("titleF");
		news2.setId("idF");
		newsList = new ArrayList<>();
		newsList.add(news1);
		newsList.add(news2);

	}

	@Test
	public void saveNewsTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NDE4MjUyMDB9.j-XY2GFppw0jfhasOx8TnMFi3iZlkcCLNwRJukMel3Q";
		when(service.saveNews(news1)).thenReturn(true);
		when(userUtil.isAdmin(Mockito.any(HttpServletRequest.class))).thenReturn(true);
		mvc.perform(post("/api/v1/news").header("authorization", "Bearer " + token)
				.contentType(MediaType.APPLICATION_JSON).content(jsonToString(news1))).andExpect(status().isCreated());
		verify(service, times(1)).saveNews(Mockito.any(News.class));
		verifyNoMoreInteractions(service);
	}

	@Test
	public void deleteByIdTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NDE4MjUyMDB9.j-XY2GFppw0jfhasOx8TnMFi3iZlkcCLNwRJukMel3Q";
		when(service.deleteNewsById(1)).thenReturn(true);
		when(userUtil.isAdmin(Mockito.any(HttpServletRequest.class))).thenReturn(true);
		mvc.perform(delete("/api/v1/news/delete/{id}", 1).header("authorization", "Bearer " + token))
				.andExpect(status().isOk());
		verify(service, times(1)).deleteNewsById(1);
		verifyNoMoreInteractions(service);
	}

	@Test
	public void fetchNewsTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NDE4MjUyMDB9.j-XY2GFppw0jfhasOx8TnMFi3iZlkcCLNwRJukMel3Q";

		when(service.getAllNews()).thenReturn(newsList);
		mvc.perform(get("/api/v1/news", 1).header("authorization", "Bearer " + token)).andExpect(status().isOk());
		verify(service, times(1)).getAllNews();
		verifyNoMoreInteractions(service);
	}

	@Test
	public void searchNewsByTitleTest() throws Exception {
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxMDAiLCJpYXQiOjE1NDE4MjUyMDB9.j-XY2GFppw0jfhasOx8TnMFi3iZlkcCLNwRJukMel3Q";

		when(service.searchByTitle("title")).thenReturn(newsList);
		mvc.perform(get("/api/v1/news/search/title", 1).header("authorization", "Bearer " + token))
				.andExpect(status().isOk());
		verify(service, times(1)).searchByTitle("title");
		verifyNoMoreInteractions(service);
	}

	private static String jsonToString(final Object obj) throws JsonProcessingException {
		// TODO Auto-generated method stub
		String result;
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			result = jsonContent;
		} catch (JsonProcessingException e) {
			// TODO: handle exception
			result = "Json Processing error";
		}
		return result;
	}

}
