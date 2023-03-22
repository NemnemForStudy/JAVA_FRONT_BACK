package com.nemnem.board.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MainService {
    
    //? 고정 딜레이 작업
    //? 스케줄 작업이 끝나는 시간 기준으로 실행
    // @Scheduled(fixedDelay=1000)
    // public void scheduleFixedDelay() {
    //     //? 1초마다 지속적으로 동작함
    //     System.out.println("고정 딜레이 작업 : " + System.currentTimeMillis() / 1000);
    // }

    // //? 스케줄 작업이 시작하는 시간 기준으로 실행
    // @Scheduled(fixedRate=1000)
    // public void schedlueFixedRate() {
    //     System.out.println("시작 고정 딜레이 작업 : " + System.currentTimeMillis() / 1000);
    // }

    //? 매 시간 ?2초마다 실행
    // @Scheduled(cron="2 * * * * ?")
    // public void ScheduleCronJob() {

    //     try {
    //         crawling();
    //     } catch(Exception exception){
    //         exception.printStackTrace();
    //     }
    // }

    //? ex) 영화예매 같은 사이트 정보를 받아올 때 크롤링을 이렇게 쓰는 방법이 있다.
    public void crawling() throws Exception {
        Document document = Jsoup.connect("https://naver.com").get();

        Elements elements = document.select("#NM_FAVORITE > div.group_nav > ul.list_nav.NM_FAVORITE_LIST > li > a");

        for(Element element: elements) {
            System.out.println(element.attr("href"));
        }

    }
}
