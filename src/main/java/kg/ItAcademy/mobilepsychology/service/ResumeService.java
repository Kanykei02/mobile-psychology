package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Resume;
import kg.ItAcademy.mobilepsychology.model.ResumeModel;

import java.util.List;

public interface ResumeService {
    Resume create(Resume resume);
    Resume create(ResumeModel resumeModel);
    Resume findById(Long id);
    List<Resume> getAllResume();
    void deleteById(Long id);
    List<Resume> getAllByStatus(Long status);
    Resume updateResumeById (ResumeModel resume, Long id);
}
