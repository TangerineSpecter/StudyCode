package com.tangerine.specter.jwt.tencent;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelMeetingRequest implements Serializable {

    @SerializedName("meeting_id")
    private String meetingId;

    private String userid;

    private Integer instanceid;

    @SerializedName("reason_code")
    private Integer reasonCode;

}
