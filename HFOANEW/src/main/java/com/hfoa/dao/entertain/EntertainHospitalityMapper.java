package com.hfoa.dao.entertain;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import com.hfoa.entity.entertain.Entertainhospitality;
import com.hfoa.entity.entertain.Entertaininvoiceunit;


/**
 * 
 * @author wzx
 * 业务招待dao接口
 */
public interface EntertainHospitalityMapper {
	
	int insertEntertainhospitality(Entertainhospitality entertainhospitality);
	
	
	List<Entertainhospitality> getEntertainhospitality(String type,String year);
	
}