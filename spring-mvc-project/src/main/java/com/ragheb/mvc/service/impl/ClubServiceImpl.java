package com.ragheb.mvc.service.impl;

import com.ragheb.mvc.dto.ClubDto;
import com.ragheb.mvc.mapper.ClubMapper;
import com.ragheb.mvc.model.Club;
import com.ragheb.mvc.model.User;
import com.ragheb.mvc.repository.ClubRepository;
import com.ragheb.mvc.repository.UserRepository;
import com.ragheb.mvc.security.SecurityUtil;
import com.ragheb.mvc.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ragheb.mvc.mapper.ClubMapper.mapToClub;
import static com.ragheb.mvc.mapper.ClubMapper.mapToClubDto;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final UserRepository userRepository;

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream()
                .map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        User user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto findClubById(Long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        User user = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(user);
        clubRepository.save(club);
    }

    @Override
    public void deleteClub(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream()
                .map(ClubMapper::mapToClubDto)
                .collect(Collectors.toList());
    }
}
