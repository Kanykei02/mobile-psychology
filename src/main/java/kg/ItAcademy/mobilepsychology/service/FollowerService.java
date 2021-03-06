package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Follower;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.FollowerModel;

import java.util.List;

public interface FollowerService {
    Follower save(Follower follower);
    Follower save(FollowerModel friendListModel) throws Exception;
    List<Follower> getAllFollowers();
    Follower findById(Long id) throws ObjectNotFoundException;
    void deleteById(Long id);
    List<Follower> findAllByUsername(String username) throws ObjectNotFoundException;
}
