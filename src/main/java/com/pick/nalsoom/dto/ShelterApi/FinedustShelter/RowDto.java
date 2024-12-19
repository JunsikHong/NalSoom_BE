package com.pick.nalsoom.dto.ShelterApi.FinedustShelter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RowDto {

    @JsonProperty("SNO")
    private String sn;

    @JsonProperty("DONG_NM")
    private String dongNm;

    @JsonProperty("FCLT_NM")
    private String fcltNm;

    @JsonProperty("ADDR")
    private String addr;

    @JsonProperty("FCLT_TYPE")
    private String fcltType;

    @JsonProperty("UTZTN_PSBLTY_NOPE")
    private String utztnPsbltyNope;

    @JsonProperty("WD_UTZTN_HRM")
    private String wdUtztnHrm;

    @JsonProperty("WE_UTZTN_HRM")
    private String weUtztnHrm;

    @JsonProperty("MBSH_YN")
    private String mbshYn;

    @JsonProperty("XCRD")
    private String xcrd;

    @JsonProperty("YCRD")
    private String ycrd;

    @JsonProperty("AREA")
    private String area;

    @JsonProperty("RMRK")
    private String rmrk;

}
