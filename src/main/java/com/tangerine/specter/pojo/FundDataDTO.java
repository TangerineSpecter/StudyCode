package com.tangerine.specter.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@Data
public class FundDataDTO implements Serializable {


    @JsonProperty("data")
    private List<DataFunDataDTO> data;
    @JsonProperty("type")
    private Integer type;
    @JsonProperty("has_next_page")
    private Boolean hasNextPage;
    @JsonProperty("total_count")
    private Integer totalCount;

    @NoArgsConstructor
    @Data
    public static class DataFunDataDTO implements Serializable {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("filename")
        private String filename;
        @JsonProperty("duration")
        private Integer duration;
        @JsonProperty("weight")
        private Integer weight;
        @JsonProperty("content")
        private String content;
        @JsonProperty("state")
        private Integer state;
        @JsonProperty("record")
        private Integer record;
        @JsonProperty("users")
        private List<UsersFunDataDTO> users;
        @JsonProperty("album_id")
        private Integer albumId;
        @JsonProperty("media_size")
        private Integer mediaSize;
        @JsonProperty("lecturer_uids")
        private String lecturerUids;
        @JsonProperty("play_count")
        private Integer playCount;
        @JsonProperty("operator_id")
        private Long operatorId;
        @JsonProperty("published_at")
        private Long publishedAt;
        @JsonProperty("created_at")
        private Long createdAt;
        @JsonProperty("updated_at")
        private Long updatedAt;
        @JsonProperty("image_text_url")
        private String imageTextUrl;

        @NoArgsConstructor
        @Data
        public static class UsersFunDataDTO implements Serializable {
            @JsonProperty("subscribeable")
            private Boolean subscribeable;
            @JsonProperty("common_count")
            private Integer commonCount;
            @JsonProperty("remark")
            private Object remark;
            @JsonProperty("recommend_reason")
            private Object recommendReason;
            @JsonProperty("domain")
            private Object domain;
            @JsonProperty("type")
            private String type;
            @JsonProperty("friends_count")
            private Integer friendsCount;
            @JsonProperty("blog_description")
            private Object blogDescription;
            @JsonProperty("stocks_count")
            private Integer stocksCount;
            @JsonProperty("recommend")
            private Object recommend;
            @JsonProperty("st_color")
            private String stColor;
            @JsonProperty("intro")
            private Object intro;
            @JsonProperty("follow_me")
            private Boolean followMe;
            @JsonProperty("allow_all_stock")
            private Boolean allowAllStock;
            @JsonProperty("followers_count")
            private Integer followersCount;
            @JsonProperty("status_count")
            private Integer statusCount;
            @JsonProperty("stock_status_count")
            private Object stockStatusCount;
            @JsonProperty("location")
            private Object location;
            @JsonProperty("description")
            private String description;
            @JsonProperty("id")
            private Integer id;
            @JsonProperty("url")
            private Object url;
            @JsonProperty("status")
            private Integer status;
            @JsonProperty("following")
            private Boolean following;
            @JsonProperty("screen_name")
            private String screenName;
            @JsonProperty("blocking")
            private Boolean blocking;
            @JsonProperty("verified_type")
            private Integer verifiedType;
            @JsonProperty("verified")
            private Boolean verified;
            @JsonProperty("verified_description")
            private String verifiedDescription;
            @JsonProperty("last_status_id")
            private Integer lastStatusId;
            @JsonProperty("donate_count")
            private Integer donateCount;
            @JsonProperty("profile")
            private String profile;
            @JsonProperty("gender")
            private String gender;
            @JsonProperty("province")
            private String province;
            @JsonProperty("city")
            private String city;
            @JsonProperty("step")
            private String step;
            @JsonProperty("name")
            private Object name;
            @JsonProperty("verified_infos")
            private List<VerifiedInfosFunDataDTO> verifiedInfos;
            @JsonProperty("group_ids")
            private Object groupIds;
            @JsonProperty("verified_realname")
            private Boolean verifiedRealname;
            @JsonProperty("name_pinyin")
            private Object namePinyin;
            @JsonProperty("screenname_pinyin")
            private Object screennamePinyin;
            @JsonProperty("photo_domain")
            private String photoDomain;
            @JsonProperty("live_info")
            private LiveInfoFunDataDTO liveInfo;
            @JsonProperty("profile_image_url")
            private String profileImageUrl;

            @NoArgsConstructor
            @Data
            public static class LiveInfoFunDataDTO  implements Serializable{
            }

            @NoArgsConstructor
            @Data
            public static class VerifiedInfosFunDataDTO  implements Serializable{
                @JsonProperty("data")
                private Object data;
                @JsonProperty("verified_type")
                private String verifiedType;
                @JsonProperty("verified_desc")
                private String verifiedDesc;
            }
        }
    }
}
