package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Timetable;
import kg.ItAcademy.mobilepsychology.model.TimetableModel;
import kg.ItAcademy.mobilepsychology.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {
    @Autowired
    private TimetableService timetableService;

    @PostMapping
    public ResponseEntity createOrUpdate(@RequestBody TimetableModel timetableModel){
        try{
            Timetable timetable = timetableService.save(timetableModel);
            return new ResponseEntity<>(timetable, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @GetMapping
    public List<Timetable> getAllTimetables(){
        return timetableService.getAllTimetables();
    }

    @GetMapping("/{timetableId}")
    public ResponseEntity getById(@PathVariable Long timetableId){
        try{
            Timetable timetable = timetableService.findById(timetableId);
            return new ResponseEntity<>(timetable, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{timetableId}")
    public void deleteById(@PathVariable Long timetableId){
         timetableService.deleteById(timetableId);
    }

    @GetMapping("/my")
    public List<Timetable> getMyTimetables(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return timetableService.findAllByUsername(username);
    }
}
