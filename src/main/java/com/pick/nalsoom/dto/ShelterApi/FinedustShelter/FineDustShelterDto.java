package com.pick.nalsoom.dto.ShelterApi.FinedustShelter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FineDustShelterDto {

    @JsonProperty("shuntPlace")
    private ShuntPlaceDto shuntPlace;
}
