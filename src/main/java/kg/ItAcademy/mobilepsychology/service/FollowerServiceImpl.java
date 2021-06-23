package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Follower;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.FollowerModel;
import kg.ItAcademy.mobilepsychology.repository.FollowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FollowerServiceImpl implements FollowerService{

    @Autowired
    private FollowerRepository followerRepository;

    @Autowired
    private UserService userService;

    @Override
    public Follower save(Follower follower) {
        return followerRepository.save(follower);
    }

    @Override
    public Follower save(FollowerModel followerModel) throws ObjectNotFoundException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUsername(username);
        User user1 = userService.findById(followerModel.getFollowedUser());

        Optional<Follower> followerCheckById = followerRepository.findById(user1.getId());
        if(followerCheckById.isPresent()){
            throw new ObjectNotFoundException("Вы уже подписаны!");
        } else if(user == user1){
            throw new ObjectNotFoundException("Это не допустимо!");
        } else {
            Follower follower = Follower.builder()
                    .dateFollowed(LocalDateTime.now())
                    .followerUser(user)
                    .followedUser(user1)
                    .build();
            return followerRepository.save(follower);
        }
    }

    @Override
    public List<Follower> getAllFollowers() {
        return followerRepository.findAll();
    }

    @Override
    public Follower findById(Long id) throws ObjectNotFoundException {
        return followerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Подписчик не найден: ", id));
    }

    @Override
    public void deleteById(Long id){
        followerRepository.deleteById(id);
    }

    @Override
    public List<Follower> findAllByUsername(String username) throws ObjectNotFoundException {
        List<Follower> followerCheckByUsername = followerRepository.findAllByFollowerUser_Username(new User().getUsername());
        if(followerCheckByUsername == null){
            throw new ObjectNotFoundException("Что-то пошло не так!");
        } else {
            return followerRepository.findAllByFollowerUser_Username(username);
        }
    }
}
