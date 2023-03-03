package com.example.grouptransportapi.beaninfo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuildWalkInfo {
    private Long id;
    private String name;
    private int members;
    private Long guildId;

    public GuildWalkInfo(String name, int members, Long guildId) {
        this.name = name;
        this.members = members;
        this.guildId = guildId;
    }

}
