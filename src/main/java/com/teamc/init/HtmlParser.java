package com.teamc.init;

import com.teamc.init.ConnectionAndDocument;
import com.teamc.model.News;
import com.teamc.service.NewsService;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Configuration
@NoArgsConstructor
@Slf4j
public class HtmlParser {

    private NewsService newsService;

    private static final String url = "http://www.vestifinance.ru/articles/companies/finance";

    @Autowired
    public HtmlParser(NewsService newsService) {
        this.newsService = newsService;
    }

    @Scheduled(fixedDelay = 30000)
    public void saveNews() throws MalformedURLException, IOException {

        final List<Element> elms = getDocumentJsoup(url).select(".Main");
        elms.stream().forEach((elem) -> {
            final String date = get(elem, "p[class=Date]");
            final String title = elem.select("a[href]").text().replaceAll("view", "");
            final String text = get(elem, "p[class=Desc]");
            final String link = "http://www.vestifinance.ru" + elem.select("a[href]").attr("href");
            final String img = elem.select("img[class=TitleImage]").attr("src");
            try{
                List<String> fullData = getFullfArticle(link);
                if (text != null) {
                    News news = new News(date, title, text, img, fullData.get(1), fullData.get(2), fullData.get(0));
                    newsService.update(title, news);
                }
            } catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }


        });


    }

    private List<String> getFullfArticle(String url) throws MalformedURLException, IOException{
        List<String> list = new ArrayList<>();
        final Element elem = getDocumentJsoup(url).body();
        final String tesis = elem.select( "span[class=Bold]").text();
        final String fullText =  elem.select("div[id=Article]").text();
        final String fullImg = elem.select(".Incut img").attr("src");
        list.add(tesis);
        list.add(fullText);
        list.add(fullImg);
        return list;
    }

    private Document getDocumentJsoup(String url) throws MalformedURLException, IOException {
        String line;
        StringBuffer tmp = new StringBuffer();
        BufferedReader in = new BufferedReader(new InputStreamReader(ConnectionAndDocument.getConnection(new URL(url)).getInputStream()));
        while ((line = in.readLine()) != null) {
            tmp.append(line);
        }
        return Jsoup.parse(String.valueOf(tmp));
    }

    private String get(Element element, String cssQuery) {
        final Element result = element.select(cssQuery).first();
        if (result != null) {
            return result.html()
                    // способен обрабатывать несколько или более переносов строки
                    .replaceAll("(<br>)\n+(\\1)*", "\n")
                    .replaceAll("<br>", "")
                    .replaceAll("&quot", "")
                    .replaceAll(";Акцент;", "\"Акцент\"")
                    .replaceAll(";", "");
        }
        return null;
    }

}
