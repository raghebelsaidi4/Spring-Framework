package com.ragheb.mvc.controller;

import com.ragheb.mvc.dto.ClubDto;
import com.ragheb.mvc.model.Club;
import com.ragheb.mvc.model.User;
import com.ragheb.mvc.security.SecurityUtil;
import com.ragheb.mvc.service.ClubService;
import com.ragheb.mvc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final UserService userService;

    @GetMapping("/clubs")
    public String listClubs(Model model) {
        User user = new User();
        List<ClubDto> clubs = clubService.findAllClubs();
        String username = SecurityUtil.getSessionUser();
        if (username != null){
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user",user);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }

    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") Long clubId, Model model) {
        User user = new User();
        ClubDto clubDto = clubService.findClubById(clubId);
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("club", clubDto);
        return "clubs-detail";
    }

    @GetMapping("/clubs/new")
    public String createClubForm(Model model) {
        Club club = new Club();
        model.addAttribute("club", club);
        return "clubs-create";
    }

    @PostMapping("/club/new")
    public String saveClub(@Valid
                           @ModelAttribute("club") ClubDto clubDto,
                           BindingResult result, Model model)
    {
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") Long clubId, Model model) {
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("club", club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(
            @PathVariable("clubId") Long clubId,
            @Valid @ModelAttribute("club") ClubDto clubDto,
            BindingResult result,
            Model model
            ) {
        if (result.hasErrors()) {
            model.addAttribute("club", clubDto);
            return "clubs-edit";
        }
        clubDto.setId(clubId);
        clubService.updateClub(clubDto);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId) {
        clubService.deleteClub(clubId);
        return "redirect:/clubs";
    }

    @GetMapping("/clubs/search")
    public String searchClubs(@RequestParam(value = "query") String query, Model model) {
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs", clubs);
        return "clubs-list";
    }
}
