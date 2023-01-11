package com.nces.by.testtasknces.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Currency {

    @JsonProperty("Cur_ID")
    private int сurId;
    @JsonProperty("Cur_OfficialRate")
    private String сurOfficialRate;
    @JsonProperty("Date")
    private String date;
}
