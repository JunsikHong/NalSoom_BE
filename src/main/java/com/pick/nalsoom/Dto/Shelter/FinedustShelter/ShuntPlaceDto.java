package com.pick.nalsoom.Dto.Shelter.FinedustShelter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShuntPlaceDto {

    @JsonProperty("list_total_count")
    private int listTotalCount;

    @JsonProperty("RESULT")
    private ResultDto result;

    @JsonProperty("row")
    private List<RowDto> row;
}
