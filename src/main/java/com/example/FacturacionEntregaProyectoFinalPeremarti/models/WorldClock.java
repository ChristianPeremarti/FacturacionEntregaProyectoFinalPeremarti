package com.example.FacturacionEntregaProyectoFinalPeremarti.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WorldClock {

    @Getter
    @Setter
    private String currentDateTime;

    @Getter
    @Setter
    private String utcOffset;

    @Getter
    @Setter
    private Boolean isDayLightSavingsTime;

    @Getter
    @Setter
    private String dayOfTheWeek;

    @Getter
    @Setter
    private String timeZoneName;

    @Getter
    @Setter
    private Long currentFileTime;

    @Getter
    @Setter
    private String ordinalDate;

    @Getter
    @Setter
    private String serviceResponse;
}
