package com.example.grouptransportapi.dao;
import com.example.grouptransportapi.bean.Guild;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface GuildRepository extends JpaRepository<Guild, Long> {
    public Guild findByName(String name);
}
