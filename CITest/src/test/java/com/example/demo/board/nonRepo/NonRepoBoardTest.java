package com.example.demo.board.nonRepo;

import com.example.demo.board.controller.request.BoardRequest;
import com.example.demo.board.entity.Board;
import com.example.demo.board.repository.BoardRepository;
import com.example.demo.board.service.BoardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@DisplayName("일반 게시판에 대한 테스트")
@SpringBootTest
public class NonRepoBoardTest {

    @Mock
    private BoardRepository mockBoardRepository;

    @InjectMocks
    private BoardServiceImpl mockBoardService;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
    
    @DisplayName("Post method로 게시물 작성 테스트")
    @Test
    public void 게시물_작성_테스트 () {
        BoardRequest boardRequest = new BoardRequest(
                "제목", "작성자", "내용");

        Board board = boardRequest.toBoard();

        when(mockBoardRepository.save(board))
                .thenReturn(new Board("제목", "작성자", "내용"));

        assertThat(mockBoardService.register(boardRequest)).isEqualTo(board);
    }

    @DisplayName("Get method로 리스트 전체 요청하기 테스트")
    @Test
    public void 게시물_리스트_보기_테스트 () {
        when(mockBoardRepository.findAll()).thenReturn(Collections.emptyList());

        final BoardServiceImpl sut = new BoardServiceImpl(mockBoardRepository);

        final List<Board> actual = sut.list();

        assertTrue(actual.isEmpty());
    }

    @DisplayName("Get method로 게시물 읽기 테스트")
    @Test
    public void 게시물_읽기_테스트 () {
        when(mockBoardRepository.findById(0L))
                .thenReturn(Optional.of(
                        new Board("제목", "작성자", "내용")));

        final BoardServiceImpl sut = new BoardServiceImpl(mockBoardRepository);

        final Board actual = sut.read(0L);
        System.out.println("mocking result: " + actual);

        assertThat(mockBoardRepository.findById(0L).get()).isEqualTo(actual);
    }

    @DisplayName("Put method로 게시물 수정 테스트")
    @Test
    public void 게시물_수정_테스트 () {
        Board board = new Board("제목", "작성자", "내용");

        BoardRequest boardRequest =
                new BoardRequest("변경", "작성자", "내용");

        when(mockBoardRepository.findById(0L))
                .thenReturn(Optional.of(
                        new Board("제목", "작성자", "내용")));
        when(mockBoardRepository.save(boardRequest.toBoard()))
                .thenReturn(new Board("변경", "작성자", "내용"));

        final BoardServiceImpl sut = new BoardServiceImpl(mockBoardRepository);

        final Board actual = sut.modify(0L, boardRequest);
        System.out.println("mocking result: " + actual);

        assertThat(mockBoardRepository.findById(0L).get()).isEqualTo(actual);
    }

    @DisplayName("Delete method로 게시물 삭제 테스트")
    @Test
    public void 게시물_삭제_테스트 () {
        Board board = new Board("제목", "작성자", "내용");
        Optional<Board> maybeBoard = Optional.of(board);

        when(mockBoardRepository.findById(0L))
                .thenReturn(maybeBoard);

        final BoardServiceImpl sut = new BoardServiceImpl(mockBoardRepository);
        sut.remove(0L);

        verify(mockBoardRepository, times(1)).deleteById(0L);
    }
}
