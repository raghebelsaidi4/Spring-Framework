package com.ragheb.mvc.controller;

import com.ragheb.mvc.dto.EventDto;
import com.ragheb.mvc.model.Event;
import com.ragheb.mvc.model.User;
import com.ragheb.mvc.security.SecurityUtil;
import com.ragheb.mvc.service.EventService;
import com.ragheb.mvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class EventController {

    private final EventService eventService;
    private final UserService userService;

    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model) {
        Event event = new Event();
        model.addAttribute("clubId", clubId);
        model.addAttribute("event", event);
        return "events-create";
    }

    @PostMapping("/event/{clubId}")
    public String createEvent(
            @PathVariable("clubId") Long clubId,
            @ModelAttribute("event") EventDto eventDto,
            BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "clubs-create";
        }
        eventService.createEvent(clubId, eventDto);
        return "redirect:/clubs/" + clubId;
    }

    @GetMapping("/events")
    public String eventList(Model model) {
        User user = new User();
        List<EventDto> events = eventService.findAllEvents();
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("events", events);
        return "events-list";
    }

    @GetMapping("/events/{eventId}")
    public String viewEvent(@PathVariable("eventId") Long eventId, Model model) {
        User user = new User();
        EventDto eventDto = eventService.findByEventId(eventId);
        String username = SecurityUtil.getSessionUser();
        if (username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("club", eventDto);
        model.addAttribute("user", user);
        model.addAttribute("event", eventDto);
        return "events-detail";
    }

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId") Long eventId, Model model) {
        EventDto eventDto = eventService.findByEventId(eventId);
        model.addAttribute("event", eventDto);
        return "events-edit";
    }

    @PostMapping("/events/{eventId}/edit")
    public String updateEvent(
            @PathVariable("eventId") Long eventId,
            @ModelAttribute("event") EventDto eventDto,
            Model model,
            BindingResult result)
    {
        if (result.hasErrors()) {
            model.addAttribute("event", eventDto);
            return "events-edit";
        }
        EventDto id = eventService.findByEventId(eventId);
        eventDto.setId(eventId);
        eventDto.setClub(id.getClub());
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }

    @DeleteMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId) {
        eventService.deleteEvent(eventId);
        return "redirect:/events";
    }
}
