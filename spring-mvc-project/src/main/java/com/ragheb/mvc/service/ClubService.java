package com.ragheb.mvc.service;

import com.ragheb.mvc.dto.ClubDto;
import com.ragheb.mvc.model.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(Long clubId);

    void updateClub(ClubDto clubDto);

    void deleteClub(Long clubId);

    List<ClubDto> searchClubs(String query);
}
