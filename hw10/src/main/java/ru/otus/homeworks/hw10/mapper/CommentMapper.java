package ru.otus.homeworks.hw10.mapper;

import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw10.dto.CommentDtoResponse;
import ru.otus.homeworks.hw10.entity.Comment;

import java.util.List;

@Component
public class CommentMapper {

    public CommentDtoResponse toDto(Comment comment) {
        return new CommentDtoResponse(comment.getId(), comment.getMessage(), comment.getUpdateOn());
    }

    public List<CommentDtoResponse> toDto(List<Comment> comments) {
        return comments.stream().map(this::toDto).toList();
    }

}
