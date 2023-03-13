package com.example.demo.domain.forTest.board.response;

import lombok.Data;

@Data
public class CommentResponse {
    private String comment;

    public CommentResponse (String comment) { this.comment = comment; }
}