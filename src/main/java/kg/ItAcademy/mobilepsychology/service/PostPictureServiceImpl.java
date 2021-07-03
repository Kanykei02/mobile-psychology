package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import kg.ItAcademy.mobilepsychology.entity.Post;
import kg.ItAcademy.mobilepsychology.entity.PostPicture;
import kg.ItAcademy.mobilepsychology.model.PostPictureModel;
import kg.ItAcademy.mobilepsychology.repository.PostPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostPictureServiceImpl implements PostPictureService{

    @Autowired
    private PostPictureRepository postPictureRepository;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private PostService postService;

    @Override
    public PostPicture create(PostPicture postPicture) {
        return postPictureRepository.save(postPicture);
    }

    @Override
    public PostPicture create(PostPictureModel postPictureModel) {
        Picture picture = pictureService.getById(postPictureModel.getPictureId());
        Post post = postService.findById(postPictureModel.getPostId());

        PostPicture postPicture = PostPicture.builder()
                .pictureId(picture)
                .postId(post)
                .build();
        return postPictureRepository.save(postPicture);
    }

    @Override
    public PostPicture findPostPictureById(Long id) {
        return null;
    }

    @Override
    public List<PostPicture> getAllPostPicture() {
        return postPictureRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        postPictureRepository.deleteById(id);
    }
}
