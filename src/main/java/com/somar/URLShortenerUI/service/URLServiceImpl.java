package com.somar.URLShortenerUI.service;


import com.google.common.hash.Hashing;
import com.somar.URLShortenerUI.model.URLDTO;
import com.somar.URLShortenerUI.model.Url;
import com.somar.URLShortenerUI.repository.UrlRepository;

import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;


@Component
public class URLServiceImpl implements URLServiceIF {


    @Autowired
    private UrlRepository urlRepository;

    @Override
    public Url createUrl(URLDTO urldto) {

        if (isUrlValid(urldto.getUrldto())) {
            String uhlShort = urlHashing(urldto.getUrldto());
            LocalDateTime dateTime = LocalDateTime.now();
            Url urlObj = new Url();
            urlObj.setUrl(urldto.getUrldto());
            urlObj.setUrlShort(uhlShort);
            urlObj.setUrlDate(dateTime);
            urlObj.setUrlCallNumber(1);
            Url urltoRep = persistUrl(urlObj);

            if (urltoRep != null) {
                return urltoRep;
            }
            return null;
        }
        return null;
    }

    @Override
    public Url updateUrl(URLDTO urldto) {

        Url urlObj = urlRepository.findByUrl(urldto.getUrldto().trim())
                .orElseThrow(() -> new ResourceNotFoundException("Url not found for this urldto :: " + urldto));
        long calls = urlObj.getUrlCallNumber();

        urlObj.setUrlDate(LocalDateTime.now());
        urlObj.setUrlCallNumber(calls + 1);
        urlRepository.save(urlObj);
        return urlObj;
    }

    @Override
    public Url persistUrl(Url urlObj) {

        Url urlToRep = urlRepository.save(urlObj);
        return urlToRep;

    }

    @Override
    public List<Url> urlAllObjs() {

        return urlRepository.findAll();
    }

    @Override
    public Url retrieveUrl(String urlShort) {

        Url urlObj = urlRepository.findByUrlShort(urlShort.trim())
                .orElseThrow(() -> new ResourceNotFoundException("Url not found for this urlShort : " + urlShort));
        return urlObj;
    }


    //is Url valid
    private boolean isUrlValid(String url) {

        if (url == null || url.trim().isEmpty()) {
            throw new RuntimeException("URL :"+ url +": is invalid");
        } else {
            String urll = url.trim();
            String[] schemes = {"http", "https"};
            UrlValidator urlValidator = new UrlValidator(schemes, UrlValidator.ALLOW_2_SLASHES);
            if (!urlValidator.isValid(urll)) {
                throw new RuntimeException("URL :"+ url +": is invalid");
            }
        }
        return true;
    }

    // Hashing for Url
    private String urlHashing(String url) {

        String urlHash;

        urlHash = Hashing.murmur3_32_fixed().hashString(url.trim(), StandardCharsets.UTF_8).toString();

        return urlHash;

    }


}
