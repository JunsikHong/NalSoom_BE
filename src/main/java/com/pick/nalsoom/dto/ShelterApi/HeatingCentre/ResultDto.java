package com.pick.nalsoom.dto.ShelterApi.HeatingCentre;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultDto {

    @JsonProperty("CODE")
    private String code;

    @JsonProperty("MESSAGE")
    private String message;
}
