package com.nces.by.testtasknces.controllers;


import com.nces.by.testtasknces.entity.Currency;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@RestController
public class MainContoller {


    @GetMapping("/currencies")
    public String getCurrencies(){
        return "index";
    }

    @RequestMapping("/currency")
    public Currency[] getCurrency(@RequestParam String currencies,
                              @RequestParam String startdate,
                              @RequestParam String enddate ,Model model) throws IOException {


        try {
            checkDateCorrect(startdate,enddate);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        System.out.println(startdate);
        System.out.println(enddate);
        System.out.println(currencies);
        RestTemplate restTemplate = new RestTemplate();

        String fooResourceUrl
                = "https://www.nbrb.by/API/ExRates/Rates/Dynamics/"+currencies+"?startDate="+startdate+"&&endDate="+enddate;
        System.out.println(fooResourceUrl);


        Currency[] forObject = restTemplate
                .getForObject(fooResourceUrl, Currency[].class);
        Arrays.stream(forObject).map(Currency::getСurOfficialRate).forEach(System.out::println);

        return forObject;




       // return "index";

    }

    private void checkDateCorrect(String startDate,String endDate) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("yyyy-MM-dd");
        Date startDate1= format.parse(startDate);
        Date endDate1= format.parse(endDate);
        int result = startDate1.compareTo(endDate1);

        if(result == 0)
            System.out.println("Both dates are equal");
        else if (result > 0)
            throw new Exception("Date end cannot bigger than start");

    }




}
