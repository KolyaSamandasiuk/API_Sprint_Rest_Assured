package api.dto;

import lombok.Data;

@Data
public class MemberCreator {
    private String id;
    private String idMemberReferrer;
    private Boolean activityBlocked;
    private String avatarHash;
    private String avatarUrl;
    private String fullName;
    private String initials;
    private Boolean nonPublicAvailable;
    private String username;
    private NonPublic nonPublic;
}
