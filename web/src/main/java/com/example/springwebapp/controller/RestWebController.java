package com.example.springwebapp.controller;

import com.example.springwebapp.model.Autor;
import com.example.springwebapp.model.CurentClients;
import com.example.springwebapp.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
public class RestWebController {
    @PostMapping(value = "/order")
    public String postSortRul(@RequestBody String form, Model model) {
        var a = form;
        var b = Home.products;
        if (a.contains("Name")) {
            var c = b.stream()
                    .sorted(Comparator.comparing(Product::getDescription))
                    .collect(Collectors.toList());
            Home.products.clear();
            Home.products.addAll(c);
        } else {
            var c = b.stream()
                    .sorted(Comparator.comparingInt(Product::getPrice))
                    .collect(Collectors.toList());
            Home.products.clear();
            Home.products.addAll(c);
        }
        var test = "resolve";
        //Home.model.addAttribute("products", c);
        // model.addAttribute("autors", Home.autors);
        return "Prodacts HTML";
    }

    @PostMapping(value = "/Filter")
    public String postFilterRul(@RequestBody String form, HttpServletResponse response) {
        var a = form;
        var type = form.substring(form.indexOf(','));
        type = type.substring(type.indexOf(':') + 1);
        var param = form.substring(0, form.indexOf(','));
        param = param.substring(param.indexOf(':') + 1);
        type = type.substring(type.indexOf('"') + 1, type.lastIndexOf('"'));
        param = param.substring(param.indexOf('"') + 1, param.lastIndexOf('"'));

        var b = Home.products;
        if (type.contains("type")) {
            String finalParam = param;
            var c = b.stream()
                    .filter(s -> finalParam.equals(s.getType()))
                    .collect(Collectors.toList());
            Home.products.clear();
            Home.products.addAll(c);
        } else {
            String finalParam = param;
            var c = b.stream()
                    .filter(s -> finalParam.equals(s.getAutor().getName()))
                    .collect(Collectors.toList());
            Home.products.clear();
            Home.products.addAll(c);
        }

        var test = "resolve";
        //Home.model.addAttribute("products", c);
        // model.addAttribute("autors", Home.autors);
        return "test HTML";
    }

    @PostMapping(value = "/add")
    public String addToCart(@RequestBody String form, HttpServletResponse response) {
        var sid = form.substring(form.indexOf(','));
        sid = sid.substring(sid.indexOf(':') + 1);
        var prodId = form.substring(0, form.indexOf(','));
        prodId = prodId.substring(prodId.indexOf(':') + 1);
        sid = sid.substring(sid.indexOf('"') + 1, sid.lastIndexOf('"'));
        prodId = prodId.substring(prodId.indexOf('"') + 1, prodId.lastIndexOf('"'));
        var i = Integer.parseInt(prodId);
        CurentClients.AddClientNewItem(sid, Home.products.get(i));
        return null;
    }
}