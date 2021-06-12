package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Timetable;
import kg.ItAcademy.mobilepsychology.model.TimetableModel;
import kg.ItAcademy.mobilepsychology.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {
    @Autowired
    private TimetableService timetableService;

    @PostMapping
    public Timetable createOrUpdate(@RequestBody TimetableModel timetableModel){
        return timetableService.save(timetableModel);
    }

    @GetMapping
    public List<Timetable> getAllTimetables(){
        return timetableService.getAllTimetables();
    }

    @GetMapping("/{timetableId}")
    public Timetable getById(@PathVariable Long timetableId){
        return timetableService.findById(timetableId);
    }

    @DeleteMapping("/{timetableId}")
    public Timetable deleteById(@PathVariable Long timetableId){
        return timetableService.deleteById(timetableId);
    }

    @GetMapping("/my")
    public List<Timetable> getMyTimetables(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return timetableService.findAllByUsername(username);
    }
}
