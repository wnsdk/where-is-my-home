package com.ssafy.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.board.model.BoardDto;
import com.ssafy.board.model.service.BoardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/board")
@Api("게시판 컨트롤러  API V1")
@CrossOrigin(origins= "*")
public class BoardController {
	
	private final BoardService boardService;
	
	@Autowired
	public BoardController(BoardService boardService) {
		this.boardService = boardService;
	}
	
	@GetMapping
	@ApiOperation(value = "게시판 글목록", notes = "모든 게시글의 정보를 반환한다.", response = List.class)
	private ResponseEntity<?> list(@RequestParam @ApiParam(value = "검색 조건.", required = true) Map<String,String> map) throws Exception {
		return new ResponseEntity<List<BoardDto>> (boardService.listArticle(map), HttpStatus.OK);
	}
	
//	@ResponseBody
//	@GetMapping("/list")
//	private ResponseEntity<?> list(@RequestParam Map<String,String> map, HttpSession session, Model model) {
//		MemberDto memberDto = (MemberDto) session.getAttribute("userinfo");
////		if (memberDto != null) {
//
//			try {
//				
//				List<BoardDto> list = boardService.listArticle(map);
//				int articlecnt = boardService.totalArticleCount(map);
//				
//				int spl = SizeConstant.SIZE_PER_LIST;
//				int pl = SizeConstant.LIST_SIZE;
//				
//				int pageno = Integer.parseInt(map.get("pgno"));
//				
//				int startpage = (pageno-1)/10*pl+1;
//				int endpage = startpage+9;
//				int lastpage = articlecnt/spl + 1;
//				
//				//33-20 < 10
//				if(articlecnt-spl*startpage<spl*pl) { //전체 개수-현재페이지까지 개수가 지정된 페이지 수 보다 작으면
//					endpage = articlecnt/spl+1;
//				}
//				
//				model.addAttribute("articles", list);
//				model.addAttribute("lastpage", lastpage);
//				model.addAttribute("endpage", endpage);
//				model.addAttribute("startpageno", startpage);
//			
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//				model.addAttribute("msg", "글목록 얻기 중 에러발생!!!");
//				return "/error/error";
//			}
////		} else {
////			model.addAttribute("msg", "로그인이 필요합니다.");
////			return "/user/login";
////		}
//	}

	@PostMapping
	@ApiOperation(value = "게시판 글작성", notes = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	private ResponseEntity<?> write(@RequestBody @ApiParam(value = "게시글 정보.", required = true) BoardDto boardDto) throws Exception {
		boardDto.setUserId("ssafy");
		boardService.writeArticle(boardDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글보기", notes = "글번호에 해당하는 게시글의 정보를 반환한다.", response = BoardDto.class)
	@GetMapping("/{articleNo}")
	private ResponseEntity<?> view(@PathVariable("articleNo") @ApiParam(value = "검색할 글정보.", required = true) int articleNo) throws Exception {
		BoardDto boardDto = boardService.getArticle(articleNo);
		boardService.updateHit(articleNo);
		return new ResponseEntity<BoardDto>(boardDto, HttpStatus.OK);
	}
	
	@ApiOperation(value = "게시판 글수정", notes = "수정할 게시글 정보를 입력한다.", response = String.class)
	@PutMapping
	private ResponseEntity<?> modify(@RequestBody @ApiParam(value = "수정할 글정보.", required = true) BoardDto boardDto) throws Exception {
		boardService.modifyArticle(boardDto);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
	@ApiOperation(value = "게시판 글삭제", notes = "글번호에 해당하는 게시글의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("/{articleNo}")
	private ResponseEntity<?> delete(@PathVariable("articleNo") @ApiParam(value = "삭제할 글의 글번호.", required = true) int articleNo) throws Exception {
		boardService.deleteArticle(articleNo);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
}
