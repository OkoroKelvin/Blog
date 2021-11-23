package com.Kelvin.Blog.service;

import com.Kelvin.Blog.data.dto.PostDto;
import com.Kelvin.Blog.data.model.Post;
import com.Kelvin.Blog.data.model.User;

public interface UserService {
    User createAndSaveUser(User user);

    Post createPost(Long userId, PostDto postDto);

    Post updatePost(Long userId, Long postId, PostDto toUpDatePostDto);
}
