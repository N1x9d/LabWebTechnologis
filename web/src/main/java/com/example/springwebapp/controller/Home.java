package com.example.springwebapp.controller;

import com.example.springwebapp.model.Autor;
import com.example.springwebapp.model.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Denis on 2/20/2016.
 */

@Controller
public class Home {
    ArrayList<Product> products = new ArrayList<>();
    ArrayList<Autor> autors = new ArrayList<>();

    @RequestMapping("/")
    public String home(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        ArrayList<Autor> autors = new ArrayList<>();
        ArrayList<Product> products = new ArrayList<>();
        var a=new Autor("default",5.0);
        var b=new Autor("Nixed",5.0);
        var c= new Autor("Jons",3.5);
        autors.add(a);
        autors.add(b);
        autors.add(c);
        products.add(new Product("Hi_cat", 1, "https://animegif.ru/up/photos/album/jan18/180102_879.gif","https://imageup.ru/img128/3672390/hi_cat1.jpg", a,2));
        products.add(new Product("Animation", 15, "https://s8.gifyu.com/images/ezgif.com-gif-to-mp420843028e5b2dd8b.gif","https://imageup.ru/img102/3674986/ezgifcom-gif-to-mp4_moment.jpg", a,1));
        products.add(new Product("Sleep", 15, "https://animegif.ru/up/photos/album/oct17/171021_3649.gif","https://s8.gifyu.com/images/171021_3649.jpg", a,2));
        products.add(new Product("Hi_cat2", 40, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif","https://imageup.ru/img244/3672392/hi_cat2.jpg", b,2));
        products.add(new Product("Hi_cat", 3, "https://animegif.ru/up/photos/album/jan18/180102_879.gif","https://imageup.ru/img128/3672390/hi_cat1.jpg", b,2));
        products.add(new Product("Hi_cat2", 5, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif","https://imageup.ru/img244/3672392/hi_cat2.jpg", c,2));
        products.add(new Product("Hi_cat2", 42, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif","https://imageup.ru/img244/3672392/hi_cat2.jpg", a,2));
        products.add(new Product("Hi_cat", 30,"https://animegif.ru/up/photos/album/jan18/180102_879.gif","https://imageup.ru/img128/3672390/hi_cat1.jpg", c,2));
        products.add(new Product("Hi_cat2", 7, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif","https://imageup.ru/img244/3672392/hi_cat2.jpg", c,2));
        products.add(new Product("Hi_cat", 8,"https://animegif.ru/up/photos/album/jan18/180102_879.gif","https://imageup.ru/img128/3672390/hi_cat1.jpg", b,2));
        products.add(new Product("Hi_cat2", 80,"https://animegif.ru/up/photos/album/nov17/171129_9264.gif","https://imageup.ru/img244/3672392/hi_cat2.jpg", a,2));
        model.addAttribute("products", products);
        model.addAttribute("autors", autors);
        return "index";
    }
    @RequestMapping("/cheakOut")
    public String ChekOut(Model model, HttpSession session) {
        String sid = session.getId();
        model.addAttribute("sid", sid);
        products.add(new Product("Hi_cat", 100,"https://animegif.ru/up/photos/album/jan18/180102_879.gif","https://imageup.ru/img244/3672392/hi_cat2.jpg", autors.get(0),2));
        products.add(new Product("Hi_cat2", 700,"https://animegif.ru/up/photos/album/nov17/171129_9264.gif","https://imageup.ru/img244/3672392/hi_cat2.jpg", autors.get(0),2));
        model.addAttribute("products", products);
        return "cheakOut";
    }
    


}
