package com.coconet.server.repository;

import com.coconet.server.entity.Profil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfilRepository extends JpaRepository<Profil, Integer> {
    Profil save(Profil profil);

}
