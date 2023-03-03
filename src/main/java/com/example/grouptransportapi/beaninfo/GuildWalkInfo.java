package com.example.grouptransportapi.beaninfo;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuildWalkInfo {
    private Long id;

    public GuildWalkInfo(String name, int members, String guildId) {
        this.name = name;
        this.members = members;
        this.guildId = guildId;
    }

    private String name;
    private int members;
    private String guildId;
}
