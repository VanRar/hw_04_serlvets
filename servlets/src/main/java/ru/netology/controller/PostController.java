package ru.netology.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.netology.model.Post;
import ru.netology.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

//Controller - приём запросов и подготовка ответов
//сериализация и десериализация данных, вызов нужного сервиса.

@Controller
public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    //необязательная аннотация, если один конструктор:
@Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var data = service.all();
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }

    public void getById(long id, HttpServletResponse response) {
        // TODO: deserialize request & serialize response
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var data = service.getById(id);
        try {
            response.getWriter().print(gson.toJson(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);//преобразуем строку в объект, типа Post
        final var data = service.save(post);
        response.getWriter().print(gson.toJson(data));
    }

    public void removeById(long id, HttpServletResponse response) {
        // TODO: deserialize request & serialize response
        response.setContentType(APPLICATION_JSON);
        service.removeById(id);
    }
}