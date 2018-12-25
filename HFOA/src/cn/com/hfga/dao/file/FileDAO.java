package cn.com.hfga.dao.file;

import org.springframework.stereotype.Repository;

import cn.com.hfga.dao.common.SpringDataDAO;
import cn.com.hfga.entity.file.FileInfoEntity;

@Repository("fileDAO")
public interface FileDAO extends SpringDataDAO<FileInfoEntity>{

	public FileInfoEntity findByFileId(String fileId);
	
	public FileInfoEntity findById(Long id);
}
