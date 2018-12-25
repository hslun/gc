package cn.com.hfga.manager.file;

import cn.com.hfga.dto.file.FileInfoDTO;

public interface FileInfoManager {

	public void save(FileInfoDTO dto);
	
	public void delete(String id);
	
	public FileInfoDTO findByFileId(String fileId);
	
	public void doModifyFileName(String id,String fileName);
	
}
