package com.example.springwebapp.controller;

import com.example.springwebapp.model.Autor;
import com.example.springwebapp.model.Product;
import com.example.springwebapp.model.CurentClients;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by Denis on 2/20/2016.
 */

@Controller
public class Home {
    static ArrayList<Product> products = new ArrayList<>();
    static ArrayList<Autor> autors = new ArrayList<>();

    @RequestMapping("/")
    public String home(Model model, HttpSession session, HttpServletRequest request) {


        var cookies = request.getCookies();
        var filters_autor = Arrays.stream(cookies)
                .filter(s -> "Filter_autor".equals(s.getName()) && s.getValue() != null)
                .collect(Collectors.toList());
        var filters_type = Arrays.stream(cookies)
                .filter(s -> "Filter_type".equals(s.getName()) && s.getValue() != null)
                .collect(Collectors.toList());
        var order_type = Arrays.stream(cookies)
                .filter(s -> "order".equals(s.getName()) && s.getValue() != null)
                .collect(Collectors.toList());
        String sid = session.getId();
        model.addAttribute("sid", sid);
        CurentClients.tryAddNewClient(sid);

        var client = CurentClients.getClient(sid);
        model.addAttribute("client", client);
        if (products.size() == 0 && (filters_autor.size() != 0 || filters_type.size() != 0))
            return "MainWithoutProducts";
        else if (products.size() == 0 || filters_autor.size() == 0 && filters_type.size() == 0 && order_type.size() == 0)
            prodactGenerator();
        model.addAttribute("products", products);
        model.addAttribute("autors", this.autors);
        return "index";
    }

    @RequestMapping("/cheakOut")
    public String ChekOut(Model model, HttpSession session) {
        String sid = session.getId();
        var prodacts = CurentClients.getClient(sid).getShopList();
        var client = CurentClients.getClient(sid);
        model.addAttribute("client", client);
        model.addAttribute("sid", sid);
        model.addAttribute("products", prodacts);
        return "cheakOut";
    }

    public static void prodactGenerator() {
        var a = new Autor("default", 5.0);
        var b = new Autor("Nixed", 5.0);
        var c = new Autor("Jons", 3.5);
        Home.autors.clear();
        Home.autors.add(a);
        Home.autors.add(b);
        Home.autors.add(c);
        Home.products.clear();
        Home.products.add(new Product("Hi_cat", 1, "https://animegif.ru/up/photos/album/jan18/180102_879.gif", "https://imageup.ru/img128/3672390/hi_cat1.jpg", a, 2));
        Home.products.add(new Product("Animation", 15, "https://s8.gifyu.com/images/ezgif.com-gif-to-mp420843028e5b2dd8b.gif", "https://imageup.ru/img102/3674986/ezgifcom-gif-to-mp4_moment.jpg", a, 1));
        Home.products.add(new Product("Sleep", 15, "https://animegif.ru/up/photos/album/oct17/171021_3649.gif", "https://s8.gifyu.com/images/171021_3649.jpg", a, 2));
        Home.products.add(new Product("Hi_cat2", 40, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", b, 2));
        Home.products.add(new Product("Hi_cat", 3, "https://animegif.ru/up/photos/album/jan18/180102_879.gif", "https://imageup.ru/img128/3672390/hi_cat1.jpg", b, 2));
        Home.products.add(new Product("Hi_cat2", 5, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", c, 2));
        Home.products.add(new Product("Hi_cat2", 42, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", a, 2));
        Home.products.add(new Product("Hi_cat", 30, "https://animegif.ru/up/photos/album/jan18/180102_879.gif", "https://imageup.ru/img128/3672390/hi_cat1.jpg", c, 2));
        Home.products.add(new Product("Hi_cat2", 7, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", c, 3));
        Home.products.add(new Product("Hi_cat", 8, "https://animegif.ru/up/photos/album/jan18/180102_879.gif", "https://imageup.ru/img128/3672390/hi_cat1.jpg", b, 3));
        Home.products.add(new Product("Hi_cat2", 80, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", a, 2));

    }
}
