package com.ragheb.mvc.service.impl;

import com.ragheb.mvc.dto.EventDto;
import com.ragheb.mvc.mapper.EventMapper;
import com.ragheb.mvc.model.Club;
import com.ragheb.mvc.model.Event;
import com.ragheb.mvc.repository.ClubRepository;
import com.ragheb.mvc.repository.EventRepository;
import com.ragheb.mvc.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.ragheb.mvc.mapper.EventMapper.mapToEvent;
import static com.ragheb.mvc.mapper.EventMapper.mapToEventDto;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final ClubRepository clubRepository;

    @Override
    public void createEvent(Long clubId, EventDto eventDto) {
        Club club = clubRepository.findById(clubId).get();
        Event event = mapToEvent(eventDto);
        event.setClub(club);
        eventRepository.save(event);
    }

    @Override
    public List<EventDto> findAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(EventMapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public EventDto findByEventId(Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        return mapToEventDto(event);
    }

    @Override
    public void updateEvent(EventDto eventDto) {
        Event event = mapToEvent(eventDto);
        eventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }
}
