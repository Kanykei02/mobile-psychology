package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Timetable;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.model.TimetableModel;
import kg.ItAcademy.mobilepsychology.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TimetableServiceImpl implements TimetableService{

    @Autowired
    private TimetableRepository timetableRepository;

    @Autowired
    private UserService userService;

    @Override
    public Timetable save(Timetable timetable) {
        return timetableRepository.save(timetable);
    }

    @Override
    public Timetable save(TimetableModel timetableModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        User user1 = userService.findById(timetableModel.getUser());

        Timetable timetable = Timetable.builder()
                .createdDate(LocalDateTime.now())
                .dateTime(timetableModel.getDateTime())
                .psychologistId(user)
                .description(timetableModel.getDescription())
                .user(user1)
                .build();
        return timetableRepository.save(timetable);
    }

    @Override
    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    @Override
    public Timetable findById(Long id) {
        return timetableRepository.findById(id).orElse(null);
    }

    @Override
    public Timetable deleteById(Long id) {
        Timetable timetable = findById(id);
        if(timetable != null){
            timetableRepository.delete(timetable);
            return timetable;
        }
        return null;
    }

    @Override
    public List<Timetable> findAllByUsername(String username) {
        return timetableRepository.findAllByPsychologistId_Username(username);
    }
}
