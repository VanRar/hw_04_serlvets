package ru.netology.servlets;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.netology.controller.PostController;
import ru.netology.repository.PostRepository;
import ru.netology.service.PostService;


@Configuration
public class JavaConfig {
    @Bean
    //Название метода - название бина
    public PostRepository postRepository() {
        //вызов метода и есть DI
        return new PostRepository();
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostController postController() {
        return new PostController(postService());
    }
}