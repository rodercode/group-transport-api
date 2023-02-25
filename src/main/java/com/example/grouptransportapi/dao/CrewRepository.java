package com.example.grouptransportapi.dao;
import com.example.grouptransportapi.bean.Crew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CrewRepository extends JpaRepository<Crew, Long> {
    public Crew findByName(String name);
}
