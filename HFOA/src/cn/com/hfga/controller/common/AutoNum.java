package cn.com.hfga.controller.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ���������Զ�������ǰ�������е����������
 * 
 * @author ymx
 * @param startΪ��ſ�ͷ
 * @param numΪ���ݿ���в�ѯ���������
 * @return
 */
public class AutoNum {

	public String getNum(String num) { // numΪ���ݿ���в�ѯ���������
		String n = num.substring(4); // ��ȡ��ǰ��������ŵĺ���λ��ˮ��

		String number = ""; // ������������ű�������ʼΪ��

		// ��ȡ��ǰ�����е���ݺ���λ
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		String year = str.substring(2, 4);

		// �������û�м�¼��������ʼ���
		if (n.equals("")) {
			number = "ZD" + year + "0001";
		} else {
			String s2 = ""; // �ڵ�ǰ���������ˮ�ŵĻ�����+1
			int lg = Integer.parseInt(n); // ����ǰ���������ˮ��תΪ����

			// ����ˮ�Ž�β����λ���ֽ����жϣ��������������
			if (lg > 0 && lg < 9) {
				s2 = "000" + (lg + 1);
			} else if (lg >= 9 && lg < 99) {
				s2 = "00" + (lg + 1);
			} else if (lg >= 99 && lg < 999) {
				s2 = "0" + (lg + 1);
			} else if (lg >= 999 && lg < 9999) {
				s2 = "" + (lg + 1);
			}
			number = "ZD" + year + s2;
		}
		return number;
	}
}
