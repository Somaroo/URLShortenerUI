package com.somar.URLShortenerUI.controller;

import com.somar.URLShortenerUI.model.URLDTO;
import com.somar.URLShortenerUI.model.Url;
import com.somar.URLShortenerUI.repository.UrlRepository;
import com.somar.URLShortenerUI.service.URLServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UrlController {

    @Autowired
    private URLServiceImpl urlServiceImp;

    @Autowired
    UrlRepository urlRepository;

    @GetMapping("/")
    public String home(Model model) {
        URLDTO urldto = new URLDTO();
        model.addAttribute("urldto",urldto);
        return "index";
    }

    @PostMapping("/")
    public String createUrl(@ModelAttribute("urldto") URLDTO urldto) {

        try {
            if (!urlRepository.existsByUrl(urldto.getUrldto())) {
                urlServiceImp.createUrl(urldto);

                return "index";

            }
            urlServiceImp.updateUrl(urldto);

            return "index";

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "index";
    }
    @GetMapping("/url")
    public String retrieveUrl(Model model, @RequestParam(value="urlShort", required=false, defaultValue="urlShort") String urlShort){

        URLDTO urldto = new URLDTO();

        model.addAttribute("urldto",urldto);

        try {
            Url urlObj = urlServiceImp.retrieveUrl(urlShort);
            model.addAttribute("urlObj",urlObj);
            return "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/urlObjs")
    public String urlObjs(Model model) {
        URLDTO urldto = new URLDTO();
        List<Url> urlObjs = urlServiceImp.urlAllObjs();

        model.addAttribute("urldto",urldto);
        model.addAttribute("urlObjs",urlObjs);
        return "index";
    }

    @GetMapping("/del")
    public String deleteUrl(Model model, @RequestParam(value="deleteID", required=false, defaultValue="id") long id)  {

        URLDTO urldto = new URLDTO();
        model.addAttribute("urldto",urldto);
        try {
            Url urlObj = urlRepository.findById(id)
                    .orElseThrow(()-> new ResourceNotFoundException("Url not found for this id :: " + id));

            model.addAttribute("deleteUrl",urlObj);
            urlRepository.delete(urlObj);

        }catch (Exception e){
            e.printStackTrace();
        }

        return "index";
    }
}
