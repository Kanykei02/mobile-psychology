package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Timetable;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.TimetableModel;

import java.util.List;

public interface TimetableService {
    Timetable save(Timetable timetable);
    Timetable save(TimetableModel timetableModel) throws ObjectNotFoundException;
    List<Timetable> getAllTimetables();
    Timetable findById(Long id) throws ObjectNotFoundException;
    void deleteById(Long id);
    List<Timetable> findAllByUsername(String username);
}
