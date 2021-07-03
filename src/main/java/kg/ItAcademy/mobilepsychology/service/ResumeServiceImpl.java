package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import kg.ItAcademy.mobilepsychology.entity.Resume;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.ResumeModel;
import kg.ItAcademy.mobilepsychology.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeServiceImpl implements ResumeService{

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PictureService pictureService;

    @Override
    public Resume create(Resume resume) {
        return resumeRepository.save(resume);
    }

    @Override
    public Resume create(ResumeModel resumeModel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user1 = userService.findByUsername(username);
        Picture picture = pictureService.getById(resumeModel.getPicture());

        Resume resume = Resume.builder()
                .createdDate(LocalDateTime.now())
                .picture(picture)
                .username(user1)
                .text(resumeModel.getText())
                .title(resumeModel.getTitle())
                .build();
            resume.setStatus(0L);
            resumeRepository.save(resume);
        return resumeRepository.save(resume);
    }

    @Override
    public Resume findById(Long id) throws ObjectNotFoundException{
        return resumeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Resume is not found!"));
    }

    @Override
    public List<Resume> getAllResume() {
        return resumeRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        resumeRepository.deleteById(id);
    }

    @Override
    public List<Resume> getAllByStatus(Long status) {
        return resumeRepository.findAllByStatus(status);
    }

    @Override
    public Resume updateResumeById(ResumeModel resume, Long id) {
        Resume resume1 = findById(id);
        if (resume1 == null) throw new ObjectNotFoundException();
        resume1.setStatus(resume.getStatus());
        return resumeRepository.save(resume1);
    }
}
