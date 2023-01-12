package com.nces.by.testtasknces.controllers;


import com.nces.by.testtasknces.entity.Currency;
import com.nces.by.testtasknces.exception.DataIncorrectException;
import com.nces.by.testtasknces.exception.NoEntityException;
import com.nces.by.testtasknces.source.GetJSON;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/v1")
public class MainContoller {

    @Autowired
    private GetJSON getJSON;


    @GetMapping("/currency")
    public Currency[] getCurrency(@RequestParam( defaultValue = "298") String currencies,
                              @RequestParam (defaultValue = "2021-06-01")String startdate,
                              @RequestParam (defaultValue = "2022-06-06")String enddate ) throws IOException, NoEntityException {


        Currency[] json = getJSON.getJSON(currencies, startdate, enddate);
        return json;
    }
}
