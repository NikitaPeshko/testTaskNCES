package com.nces.by.testtasknces.source;


import com.nces.by.testtasknces.entity.Currency;
import com.nces.by.testtasknces.exception.DataIncorrectException;
import com.nces.by.testtasknces.exception.NoEntityException;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class GetJSON {



    public Currency[] getJSON( String currencies,
                               String startdate,
                               String enddate ) throws NoEntityException {


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


        Currency[] currenciesWithFilter = restTemplate
                .getForObject(fooResourceUrl, Currency[].class);
        if(currenciesWithFilter.length==0){
            throw new NoEntityException("No currency for this date","SOMERRORCODE");
        }

        return currenciesWithFilter;


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
            throw new DataIncorrectException("The end date connot bigger than start date","Some Errorcode");

    }

}
