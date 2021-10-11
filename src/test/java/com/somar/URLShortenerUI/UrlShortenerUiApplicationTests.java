package com.somar.URLShortenerUI;


import com.somar.URLShortenerUI.model.Url;
import com.somar.URLShortenerUI.repository.UrlRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDateTime;

import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlShortenerUiApplicationTests {


    @Autowired
    UrlRepository urlRepository;

    @Test
    @Order(1)
    public void testcreate() {


        LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 12, 00, 20, 21);
        Url url = new Url();
        url.setId(1L);
        url.setUrl("https://www.google.com/".trim());
        url.setUrlShort("cac87a2c");
        url.setUrlDate(localDateTime);
        url.setUrlCallNumber(1);
        urlRepository.save(url);

        assertNotNull(urlRepository.findById(1L).get());

    }

    @Test
    @Order(2)
    public void testReadAll() {

        List<Url> urlList = urlRepository.findAll();
        assertThat(urlList).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void testUrl() {
        Url url = urlRepository.findById(1L).get();
        assertEquals(1,url.getUrlCallNumber());
    }

    @Test
    @Order(4)
    public void testretrieve() {

        String url = urlRepository.findByUrlShort("cac87a2c").get().getUrl();
        System.out.println(url);
        assertEquals("https://www.google.com/",url);
    }

    @Test
    @Order(5)
    public void testUpdate() {

        LocalDateTime localDateTime = LocalDateTime.of(2021, 10, 12, 00, 20, 21);
        Url url = new Url();
        url.setId(1L);
        url.setUrl("https://www.google.com/".trim());
        url.setUrlShort("cac87a2c");
        url.setUrlDate(localDateTime);
        url.setUrlCallNumber(4);
        urlRepository.save(url);
        assertEquals(4,url.getUrlCallNumber());

    }

    @Test
    @Order(6)
    public void testdelet() {

        urlRepository.deleteById(1L);
        assertThat(urlRepository.existsById(1L)).isFalse();


    }

}
