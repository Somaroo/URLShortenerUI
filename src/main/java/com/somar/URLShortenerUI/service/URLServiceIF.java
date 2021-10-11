package com.somar.URLShortenerUI.service;



import com.somar.URLShortenerUI.model.URLDTO;
import com.somar.URLShortenerUI.model.Url;

import java.util.List;

public interface URLServiceIF {

    public Url createUrl(URLDTO urldto);
    public List<Url> urlAllObjs();
    public Url updateUrl(URLDTO urldto);
    public Url persistUrl(Url url);
    public Url retrieveUrl(String urlShort);

}
