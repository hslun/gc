
package cn.com.hfga.controller.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * hfga '
 * file: FileController.java
 *
 * @author zhang.haifeng
 * date:2014楠烇拷0閺堬拷5閺冿拷
 * 缁辩姵娼�
 **/
@Controller
public class FileController {

  @RequestMapping("/downloadFile")
  public void download(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException {
    System.out.println("有请求");
    String path = httpServletRequest.getSession().getServletContext().getRealPath("/BorrowCar.apk");
    File file = new File(path);
    InputStream inStream = new FileInputStream(path);
    httpServletResponse.reset();
    httpServletResponse.setContentType("bin");
    String fileName = "BorrowCar.apk";
    String agent = httpServletRequest.getHeader("USER-AGENT");
    if (agent != null && agent.indexOf("MSIE") != -1) {
      fileName = URLEncoder.encode(fileName, "UTF8");
      fileName = fileName.replaceAll("\\+", "%20");
    } else {
      fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
    }
    httpServletResponse.addHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
    httpServletResponse.addHeader("Content-Length", "" + file.length());
    byte[] b = new byte[100];
    int len;
    try {
      while ((len = inStream.read(b)) > 0)
        httpServletResponse.getOutputStream().write(b, 0, len);
      inStream.close();
      httpServletResponse.getOutputStream().close();
      httpServletResponse.getOutputStream().flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}

