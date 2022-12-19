package board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {
	static String path = "/Users/jerry/Desktop/workplace/eclipse/SpringSpaProject/src/main/webapp/WEB-INF/upload";
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_insertR")
	public synchronized String insertR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo vo) {
		String msg = "";
		try {
			System.out.println("id : " + vo.getId());
			System.out.println("subject : " + vo.getSubject());
			List<AttVo> attList = new ArrayList<AttVo>();
			
			// 본문을 저장 
			attList = fileupload(mul);
			
			vo.setAttList(attList);
			boolean flag = service.insertR(vo);
			if(!flag) msg = "저장 중 오류 발생";
			
		}catch(Exception ex) {
			
		}
		
		return msg;
	}

	@RequestMapping("/board/board_replR")
	public synchronized String replR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo) {
		try {
			List<AttVo> attList = new ArrayList<AttVo>();
			attList = fileupload(mul);
			bVo.setAttList(attList);
			
			// 본문을 저장 
			boolean flag = service.replR(bVo);
			if(!flag) return "저장 중 오류 발생";
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return "redirect:/board/board_select";
	}
	
	@RequestMapping("/board/board_updateR")
	public String updateR(@RequestParam("attFile") List<MultipartFile> mul,
			@ModelAttribute BoardVo bVo, @ModelAttribute PageVo pVo,
			@RequestParam(name = "delFile", required = false) String[] delFile) {
		String msg = "";
		try {
			List<AttVo> attList = fileupload(mul);
			bVo.setAttList(attList);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		boolean flag = service.updateR(bVo, delFile);
		if(!flag) msg = "수정 중 오류 발생";
		
		return msg;
	}
	
	//file upload 공통부분
	public List<AttVo> fileupload(List<MultipartFile> mul) throws Exception{
		List<AttVo> attList = new ArrayList<AttVo>();
		
		for(MultipartFile m : mul) {
			if(m.isEmpty()) continue;
			
			System.out.println(m.getOriginalFilename());
			UUID uuid = UUID.randomUUID();
			System.out.println(uuid.toString());
			String oriFile = m.getOriginalFilename();
			String sysFile = "";
			File temp = new File(path + oriFile);
			m.transferTo(temp);
			sysFile = (uuid.getLeastSignificantBits()*-1) + "-" + oriFile;
			File f = new File(path + sysFile);
			temp.renameTo(f);
			
			AttVo attVo = new AttVo();
			attVo.setOriFile(oriFile);
			attVo.setSysFile(sysFile);
			
			attList.add(attVo);
		}
		
		return attList;
	}
}