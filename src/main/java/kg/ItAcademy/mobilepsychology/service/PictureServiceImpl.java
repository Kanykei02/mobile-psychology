package kg.ItAcademy.mobilepsychology.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import kg.ItAcademy.mobilepsychology.entity.Picture;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.repository.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService{

    private static final String CLOUDINARY_URL = "cloudinary://782997131388484:ohfmFa9pXo1VdDc4zQBnSPeo-wI@it-academy-project/";

    @Autowired
    private PictureRepository pictureRepository;

    @Override
    public Picture createPicture(MultipartFile multipartFile) {
        Picture picture = new Picture();
        File file;
        try {
            file = Files.createTempFile(System.currentTimeMillis() + "",
                    multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().length() - 4)).toFile();
            multipartFile.transferTo(file);

            Cloudinary cloudinary = new Cloudinary(CLOUDINARY_URL);
            Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
            picture.setName((String) uploadResult.get("public_id"));
            picture.setUrl((String) uploadResult.get("url"));
            picture.setFormat((String) uploadResult.get("format"));
            picture.setCreateDate(LocalDateTime.now());
            System.out.println(picture.getUrl());
            return pictureRepository.save(picture);

        } catch (Exception e) {
            System.out.println("ImageService.createImage: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Picture> getAllPictures() {
        return pictureRepository.findAll();
    }

    @Override
    public Picture findById(Long id) throws ObjectNotFoundException {
        return pictureRepository.findById(id).orElse(null);
    }
}
