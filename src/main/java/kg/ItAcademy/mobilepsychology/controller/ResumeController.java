package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.Resume;
import kg.ItAcademy.mobilepsychology.model.ResumeModel;
import kg.ItAcademy.mobilepsychology.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping
    public Resume createResume(@RequestBody ResumeModel resumeModel){
        return resumeService.create(resumeModel);
    }

    @GetMapping
    public List<Resume> getAllResume(){
        return resumeService.getAllResume();
    }

    @GetMapping("/{resumeId}")
    public ResponseEntity findById(@PathVariable Long resumeId){
        try{
            Resume resume = resumeService.findById(resumeId);
            return new ResponseEntity<>(resume, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @DeleteMapping("/{resumeId}")
    public void deleteResumeById(@PathVariable Long resumeId){
        resumeService.deleteById(resumeId);
    }

    @GetMapping("/status/{status}")
    public List<Resume> getAllResumeByStatus(@PathVariable Long status){
        return resumeService.getAllByStatus(status);
    }

    @PostMapping("/{resumeId}")
    public Resume updateResume(@RequestBody ResumeModel resume, @PathVariable  Long resumeId){
        return resumeService.updateResumeById(resume, resumeId);
    }
}
