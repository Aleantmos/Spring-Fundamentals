package softuni.likebooks.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.likebooks.model.dtos.AddPostDTO;
import softuni.likebooks.model.dtos.PostDTO;
import softuni.likebooks.model.entity.Mood;
import softuni.likebooks.model.entity.Post;
import softuni.likebooks.model.entity.User;
import softuni.likebooks.model.helper.LoggedUser;
import softuni.likebooks.repository.PostRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final MoodService moodService;
    private final LoggedUser loggedUser;
    private final UserService userService;

    @Autowired
    public PostService(PostRepository postRepository, ModelMapper modelMapper, MoodService moodService, LoggedUser loggedUser, UserService userService) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
        this.moodService = moodService;
        this.loggedUser = loggedUser;
        this.userService = userService;
    }

    public void addPost(AddPostDTO addPostDTO) {


        Post postToSave = modelMapper.map(addPostDTO, Post.class);

        Mood mood = moodService.getMood(addPostDTO.getMood());
        postToSave.setMood(mood);

        User userById = userService.getUserById(loggedUser.getId());
        postToSave.setCreator(userById);

        postToSave.setUsersLikes(new HashSet<>());

        postRepository.save(postToSave);
    }

    public Set<PostDTO> getPostsWithCreator(Long id) {
        return postRepository.findByCreator_Id(id)
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toSet());
    }

    public void removePostById(Long id) {
        postRepository.deleteById(id);
    }

    public Set<PostDTO> getPostsWithCreatorNot(Long id) {
        return postRepository.findByCreator_IdNot(id)
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toSet());

    }

    public void addLiker(Long id, Long loggedUserId) {
        Post postToLike = postRepository.findById(id).orElse(null);
        User loggedUser = userService.getUserById(loggedUserId);

        if (postToLike != null) {
            postToLike.getUsersLikes().add(loggedUser);
            postRepository.save(postToLike);
        }


    }
}
