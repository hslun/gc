
package cn.com.hfga.entity.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.com.hfga.entity.common.CommonEntity;

/**
 * hfga '
 * file: FileInfoEntity.java
 * @author zhang.haifeng
 * date:2014年10月15日
 * 公共上传文件表
 **/
@Entity
@Table(name="tb_file_info")
public class FileInfoEntity extends CommonEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="fileId")
    private String fileId;
	
	/**
	 * 文件路径
	 */
	@Column(name="filePath")
    private String filePath;
	
	/**
	 * 文件名称
	 */
	@Column(name="fileName")
    private String fileName;
	
	/**
	 * 用户
	 */
	@Column(name="userName")
    private String userName;
	
	/**
	 * 大小
	 */
	@Column(name="jarSize")
	private Long size;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

}

