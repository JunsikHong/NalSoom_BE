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
public class RowDto {

    @JsonProperty("SNO")
    private String sno;

    @JsonProperty("RESTAREA_NM")
    private String restareaNm;

    @JsonProperty("ROAD_NM_ADDR")
    private String roadNmAddr;

    @JsonProperty("LOTNO_ADDR")
    private String lotnoAddr;

    @JsonProperty("FCLT_TYPE")
    private String fcltType;

    @JsonProperty("FCAR")
    private String fcar;

    @JsonProperty("UTZTN_PSBLTY_NOPE")
    private String utztnPsbltyNope;

    @JsonProperty("OPER_BGNG_YMD")
    private String operBgngYmd;

    @JsonProperty("OPER_END_YMD")
    private String operEndYmd;

    @JsonProperty("WD_OPN_YN")
    private String wdOpnYn;

    @JsonProperty("WD_OPN_HRM")
    private String wdOpnHrm;

    @JsonProperty("WD_END_HRM")
    private String wdEndHrm;

    @JsonProperty("SAT_OPN_YN")
    private String satOpnYn;

    @JsonProperty("SAT_OPN_HRM")
    private String satOpnHrm;

    @JsonProperty("SAT_END_HRM")
    private String satEndHrm;

    @JsonProperty("SUN_OPN_YN")
    private String sunOpnYn;

    @JsonProperty("SUN_OPN_HRM")
    private String sunOpnHrm;

    @JsonProperty("SUN_END_HRM")
    private String sunEndHrm;

    @JsonProperty("LHLDY_OPN_YN")
    private String lhldyOpnYn;

    @JsonProperty("LHLDY_OPN_HRM")
    private String lhldyOpnHrm;

    @JsonProperty("LHLDY_END_HRM")
    private String lhldyEndHrm;

    @JsonProperty("LAT")
    private String lat;

    @JsonProperty("LOT")
    private String lot;

    @JsonProperty("RMRK")
    private String rmrk;

    @JsonProperty("XCRD")
    private String xcrd;

    @JsonProperty("YCRD")
    private String ycrd;

    @JsonProperty("NGHT_OPN")
    private String nghtOpn;

}
