package com.example.springwebapp.controller;

import com.example.springwebapp.controller.repo.HistoryRepo;
import com.example.springwebapp.controller.repo.ProductRepo;
import com.example.springwebapp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Denis on 2/20/2016.
 */

@Controller
public class Home {
    @Autowired
    private ProductRepo prrepo;
    @Autowired
    private HistoryRepo Histrepo;

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

        var PShopList = (List<Product>) session.getAttribute(sid);
        Integer count;
        if (PShopList == null)
            count = 0;
        else
            count = PShopList.size();
        model.addAttribute("client", count);
        var c = prrepo.findAll();
        var b = (ArrayList<Product>) c;
        if (products.size() == 0 && (filters_autor.size() != 0 || filters_type.size() != 0))
            return "MainWithoutProducts";

        else if (b.size() == 0 || filters_autor.size() == 0 && filters_type.size() == 0 && order_type.size() == 0) {
            prodactGenerator();

        }

        try {
            var pr = prrepo.findAll();
            var a = prrepo.findAll();
            model.addAttribute("products", pr);
        } catch (Exception ex) {
            var a = ex.getMessage();
            model.addAttribute("error", a);

        }

        model.addAttribute("autors", this.autors);
        return "index";
    }

    @RequestMapping("/cheakOut")
    public String ChekOut(Model model, HttpSession session) {
        String sid = session.getId();
        var prodacts = (List<Product>) session.getAttribute(sid);
        var sum = prodacts.stream().filter(o -> o.getPrice() >= 0).mapToInt(Product::getPrice).sum();
        model.addAttribute("client", prodacts.size());
        model.addAttribute("sum", sum);
        model.addAttribute("sid", sid);
        model.addAttribute("products", prodacts);
        return "cheakOut";
    }


    public void prodactGenerator() {
        var a = new Autor("default", 5.0);
        var b = new Autor("Nixed", 5.0);
        var c = new Autor("Jons", 3.5);
        prrepo.deleteAll();
        Home.autors.clear();
        Home.autors.add(a);
        Home.autors.add(b);
        Home.autors.add(c);
        prrepo.save(new Product("Animation", 15, "https://s8.gifyu.com/images/ezgif.com-gif-to-mp420843028e5b2dd8b.gif", "https://imageup.ru/img102/3674986/ezgifcom-gif-to-mp4_moment.jpg", a, 1));
        prrepo.save(new Product("Sleep", 15, "https://animegif.ru/up/photos/album/oct17/171021_3649.gif", "https://s8.gifyu.com/images/171021_3649.jpg", a, 2));
        prrepo.save(new Product("Hi_cat2", 40, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", b, 2));
        prrepo.save(new Product("Hi_cat", 3, "https://animegif.ru/up/photos/album/jan18/180102_879.gif", "https://imageup.ru/img128/3672390/hi_cat1.jpg", b, 2));
        prrepo.save(new Product("Hi_cat2", 5, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", c, 2));
        prrepo.save(new Product("Hi_cat2", 42, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", a, 2));
        prrepo.save(new Product("Hi_cat", 30, "https://animegif.ru/up/photos/album/jan18/180102_879.gif", "https://imageup.ru/img128/3672390/hi_cat1.jpg", c, 2));
        prrepo.save(new Product("Hi_cat2", 7, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", c, 3));
        prrepo.save(new Product("Hi_cat", 8, "https://animegif.ru/up/photos/album/jan18/180102_879.gif", "https://imageup.ru/img128/3672390/hi_cat1.jpg", b, 3));
        prrepo.save(new Product("Hi_cat2", 80, "https://animegif.ru/up/photos/album/nov17/171129_9264.gif", "https://imageup.ru/img244/3672392/hi_cat2.jpg", a, 2));
        prrepo.save(new Product("Hi_cat", 1, "https://animegif.ru/up/photos/album/jan18/180102_879.gif", "https://imageup.ru/img128/3672390/hi_cat1.jpg", a, 2));

    }

    @GetMapping(value = "/GetForReact")
    public ArrayList<ProductMini> GetCartForReact(HttpSession session) {
        String sid = session.getId();
        var prodacts = (List<Product>) session.getAttribute(sid);
        ArrayList<ProductMini> request = new ArrayList<>();
        for (var prod :
                prodacts) {
            request.add(new ProductMini(prod.getPrevImage(), prod.getDescription(), prod.getPrice(), prod.getId()));
        }
        return request;
    }

    @PostMapping(value = "/cheakOut/confirmation")
    public String BuedProducts(@RequestBody Map<String, String> json, Model model, HttpSession session) {
        var a = json;
        var sid = session.getId();
        var prods = (ArrayList<Product>) session.getAttribute(sid);
        for (Product pr :
                prods) {
            Histrepo.save(new SellHistory(new Date(), a.get("Email"), pr.getId(), pr.getPrice(), a.get("Payment")));
        }
        ArrayList<SelHistoryProduct> shp = new ArrayList<>();
        var his = Histrepo.findAll();
        var His = (ArrayList<SellHistory>) his;
        var pr = prrepo.findAll();
        var b = (ArrayList<Product>) pr;
        for (SellHistory sh :
                His) {
            var prod = b.stream()
                    .filter(s -> sh.getProdId() == s.getId())
                    .collect(Collectors.toList());
            shp.add(new SelHistoryProduct(prod.get(0), sh));
        }
        model.addAttribute("shp", shp);
        return "OrderHistory";

    }


    @PostMapping(value = "/order")
    public String postSortRul(@RequestBody String form, Model model) {
        //Home.prodactGenerator();
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
        //Home.prodactGenerator();
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
                    .filter(s -> finalParam.equals(s.getAutor()))
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
    public String addToCart(@RequestBody String form, HttpSession session) {
        var sid = session.getId();
        sid = sid.substring(sid.indexOf(':') + 1);
        var prodId = form.substring(0, form.indexOf(','));
        prodId = prodId.substring(prodId.indexOf(':') + 1);
        prodId = prodId.substring(prodId.indexOf('"') + 1, prodId.lastIndexOf('"'));
        List<Product> prods = (List<Product>) session.getAttribute(sid);
        Iterable<Product> pr = prrepo.findAll();
        var b = (ArrayList<Product>) pr;
        if (prods == null)
            prods = new ArrayList<>();
        Integer finalProdId = Integer.parseInt(prodId);
        prods.addAll(b.stream()
                .filter(s -> finalProdId == s.getId())
                .collect(Collectors.toList()));
        session.setAttribute(sid, prods);
        return "redirect:/";
    }
}
