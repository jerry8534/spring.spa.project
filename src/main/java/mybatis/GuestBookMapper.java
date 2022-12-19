package mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import guestbook.GPageVo;
import guestbook.GuestbookVo;

@Repository
@Mapper
public interface GuestBookMapper {
	public int totSize(GPageVo vo);
	public List<GuestbookVo> list(GPageVo vo);
	public int insert(GuestbookVo vo);
	public int delete(GuestbookVo vo);
	public int update(GuestbookVo vo);
	public List<GuestbookVo> select10();
}