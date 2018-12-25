package cn.com.hfga.manageimpl.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.hfga.dao.file.FileDAO;
import cn.com.hfga.dto.file.FileInfoDTO;
import cn.com.hfga.entity.file.FileInfoEntity;
import cn.com.hfga.log.common.ILog;
import cn.com.hfga.manager.file.FileInfoManager;
import cn.com.hfga.util.common.BeanUtils;

/**
 * hfga's file : FileInfoManagerImpl
 * @author zhang.haifeng
 * 公共上传文件业务实现类
 */
@Service("fileInfoManager")
public class FileInfoManagerImpl implements FileInfoManager,ILog{

	@Autowired
	private FileDAO fileDAO;
	
	@Override
	public void save(FileInfoDTO dto) {
		// TODO Auto-generated method stub
		FileInfoEntity fileInfoEntity = null;
		if(dto.getId()!=null){
			fileInfoEntity = fileDAO.findOne(dto.getId());
		}else{
			fileInfoEntity = new FileInfoEntity();
		}
		BeanUtils.copyProperties(dto, fileInfoEntity);
		fileDAO.save(fileInfoEntity);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		FileInfoEntity fileInfoEntity = new FileInfoEntity();
		fileInfoEntity.setId(Long.valueOf(id));
		fileDAO.delete(fileInfoEntity);
	}

	@Override
	public FileInfoDTO findByFileId(String fileId) {
		// TODO Auto-generated method stub
		FileInfoEntity fileInfoEntity = fileDAO.findByFileId(fileId);
		FileInfoDTO fileInfoDTO = new FileInfoDTO();
		fileInfoDTO.setId(fileInfoEntity.getId());
		fileInfoDTO.setFileId(fileInfoEntity.getFileId());
		fileInfoDTO.setFileName(fileInfoEntity.getFileName());
		fileInfoDTO.setFilePath(fileInfoEntity.getFilePath());
		fileInfoDTO.setSize(fileInfoEntity.getSize());
		fileInfoDTO.setUserName(fileInfoEntity.getUserName());
		return fileInfoDTO;
	}

	@Override
	public void doModifyFileName(String id, String fileName) {
		// TODO Auto-generated method stub
		FileInfoEntity fileInfoEntity = fileDAO.findById(Long.valueOf(id));
		fileInfoEntity.setFileName(fileName);
		fileDAO.save(fileInfoEntity);
	}

}
