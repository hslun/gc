package cn.com.hfga.manageimpl.entertain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.hfga.dao.entertain.EntertainInvoiceUnitDAO;
import cn.com.hfga.entity.entertain.EntertainInvoiceUnitEntity;
import cn.com.hfga.manager.entertain.EntertainInvoiceUnitManage;

@Service("entertainInvoiceUniteImpl")
public class EntertainInvoiceUnitManageImpl implements EntertainInvoiceUnitManage {

	@Autowired
	private EntertainInvoiceUnitDAO entertainInvoiceUnitDAO;
	
	    // 存储新的发票出具单位名称
		@Transactional
		@Override
		public int saveInvoiceUnitType(String InvoiceUnitName) {
			SimpleDateFormat sdf= new SimpleDateFormat("yyyyMMddhhmmssSSS");
			String id = sdf.format(new Date());
			return entertainInvoiceUnitDAO.saveType(id,InvoiceUnitName);
		}
		
		// 获取发票出具单位名称
		@Transactional
		@Override
		public List<EntertainInvoiceUnitEntity> getInvoiceUnitType() {
			return entertainInvoiceUnitDAO.getInvoiceUnitType();
		}
}
