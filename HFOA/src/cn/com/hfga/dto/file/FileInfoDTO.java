
package cn.com.hfga.dto.file;

import cn.com.hfga.dto.common.CommonDTO;


/**
 * hfga '
 * file: FileInfoDTO.java
 * @author zhang.haifeng
 * date:2014年10月15日
 **/
public class FileInfoDTO extends CommonDTO{

    private String fileId;
	
	/**
	 * 文件路径
	 */
    private String filePath;
	
	/**
	 * 文件名称
	 */
    private String fileName;
	
	/**
	 * 用户
	 */
    private String userName;
	
	/**
	 * 大小
	 */
	private Long size;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}


}

