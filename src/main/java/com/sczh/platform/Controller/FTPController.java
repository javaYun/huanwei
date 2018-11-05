package com.sczh.platform.Controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sczh.platform.po.ContractInfo;
import com.sczh.platform.po.DivisionTable;
import com.sczh.platform.po.LjflScoreRecord;
import com.sczh.platform.po.LjflStaffDetail;
import com.sczh.platform.po.UnitInfo;
import com.sczh.platform.po.UserInfo;
import com.sczh.platform.po.Model.ExportExcelFile;
import com.sczh.platform.po.Model.PageModelDomain;
import com.sczh.platform.service.UnitService;
import com.sczh.platform.service.UserService;
import com.yunlao.util.DownloadUtils;
import com.yunlao.util.ExcelUtil;
import com.yunlao.util.FileUploadUtil;
import com.yunlao.util.GetFileName;

@Controller
@RequestMapping(value = "ftp")
public class FTPController {

	private static String uploadFilePath = null;

	// String uploadFilePath="src/main/resources";//注意filepath的内容；
	// File file=new File(uploadFilePath);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private UnitService unitService;

	/**
	 * 上传合同
	 * 
	 * @param request
	 * @param response
	 * @param file
	 * @throws IOException
	 */
	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public void uploadFile(HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam("file") CommonsMultipartFile file) throws IOException {

		response.setContentType("text/html");
		response.setCharacterEncoding("GBK");
		PrintWriter out;
		boolean flag = false;
		if (file.getSize() > 0) {
			// 文件上传的位置可以自定义
			flag = FileUploadUtil.uploadFile(request, file);
		}
		out = response.getWriter();
		if (flag == true) {
			out.print("1");
		} else {
			out.print("2");
		}
	}

	/**
	 * 下载合同
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "down")
	public String downloadfile(HttpServletResponse response,
			HttpServletRequest request) {
		// 文件下载的路径
		uploadFilePath = request.getSession().getServletContext()
				.getRealPath("upload");
		String filename = request.getParameter("filename") == null ? ""
				: request.getParameter("filename");
		System.out.println("filename===" + filename);
		String filePath = uploadFilePath + "/" + filename;
		System.out.println(filePath);
		String encoding = "utf-8";
		DownloadUtils.download(response, filePath, filename, encoding);
		return "下载成功";
	}
	
	

	/**
	 * 进入上传页面
	 * 
	 * @return
	 *//*
	@RequestMapping(value = "joinupload")
	public ModelAndView joinupload() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("plan/upload");
		return mav;
	}*/

	/**
	 * 获取可下载文件
	 * 
	 * @return
	 */
	@RequestMapping(value = "joindownload")
	public ModelAndView joindownload(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 显示的文件的路径
		uploadFilePath = request.getSession().getServletContext()
				.getRealPath("upload");
		System.out.println("uploadFilePath===" + uploadFilePath);
		ArrayList<String> listFileName = new ArrayList<String>();
		GetFileName.getAllFileName(uploadFilePath, listFileName);

		List<String> filename = new ArrayList<String>();

		System.out.println("listfilename==" + listFileName);
		for (String name : listFileName) {
			System.out.println(name);
			filename.add(name);
		}

		if (filename.size() == 0) {

			mav.addObject("filename", "没有文件");
		} else if (filename.contains("1")) {

			mav.addObject("filename", "路径错误");
		} else {

			mav.addObject("filename", filename);
		}

		System.out.println("filename==" + filename);
		mav.setViewName("plan/download");
		return mav;
	}

/*	*//**
	 * 进入合同上传页面
	 * 
	 * @return
	 *//*
	@RequestMapping(value = "joinuploadcontractsPage")
	public ModelAndView joinuploadcontractsPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("plan/uploadcontractsPage");
		return mav;
	}*/
	
	/**
	 * 进入责任人报表上传页面
	 * 
	 * @return
	 */
	/*@RequestMapping(value = "joinuploadreportformsPage")
	public ModelAndView joinuploadreportformsPage(HttpSession session,HttpServletRequest request) {
		String userid = session.getAttribute("userid").toString();
		ModelAndView mav = new ModelAndView();
		
		UserInfo userInfo = userService.selectByPrimaryKey(userid);
		
		System.out.println(userInfo.getUsername()+"+++++++++++++++++++++++++");
		System.out.println(userInfo.getPassword()+"============================");
		mav.addObject("id", userid);
		mav.addObject("username", userInfo.getUsername());
		mav.setViewName("plan/uploadreportformsPage");
		return mav;
	}*/

	/**
	 * 显示全部合同
	 * @param domain
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "getcontractdata")
	public PageModelDomain getcontractdata(@RequestBody PageModelDomain domain,HttpSession session) {
		String userid = session.getAttribute("userid").toString();
		if(userid.equals("1")){
			PageModelDomain pageModelDomain = new PageModelDomain();
			pageModelDomain = domain;
			int startSize = domain.getPageSize() * (domain.getCurrentPage() - 1);
			List<ContractInfo> selectContractInfos = userservice.selectContractInfos(startSize + "", domain.getPageSize() + "");
			pageModelDomain.setContractInfo(selectContractInfos);
			pageModelDomain.setPageTotalSize(userservice.selectContractInfos(startSize + "", 1000 + "").size());
			System.out.println(pageModelDomain.toString());
			return pageModelDomain;
		}else{
			UserInfo userInfo = userService.selectByPrimaryKey(userid);
			String uploadname = userInfo.getUsername();
			PageModelDomain pageModelDomain = new PageModelDomain();
			pageModelDomain = domain;
			int startSize = domain.getPageSize() * (domain.getCurrentPage() - 1);
			List<ContractInfo> selectContractInfos = userservice.selectContractInfosss(startSize + "", domain.getPageSize() + "", uploadname+"");
			pageModelDomain.setContractInfo(selectContractInfos);
			pageModelDomain.setPageTotalSize(userservice.selectContractInfosss(startSize + "", 1000 + "", uploadname+"").size());
			System.out.println(pageModelDomain.toString());
			return pageModelDomain;
		}
	}
	
	/**
	 * 显示责任人报表模板
	 * @param domain
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "getreportformsdata")
	public PageModelDomain getreportformsdata(@RequestBody PageModelDomain domain,HttpSession session) {
		PageModelDomain pageModelDomain = new PageModelDomain();
		pageModelDomain = domain;
		int startSize = domain.getPageSize() * (domain.getCurrentPage() - 1);
		List<ContractInfo> selectContractInfos = userservice.selectReportformsTotal(startSize + "", domain.getPageSize() + "","personLiableReport");
		pageModelDomain.setContractInfo(selectContractInfos);
		pageModelDomain.setPageTotalSize(userservice.selectReportformsTotal(startSize + "", 1000 + "","personLiableReport").size());
		System.out.println(pageModelDomain.toString());
		return pageModelDomain;
	}
	/**
	 * 显示责任人报表(当前用户)
	 * @param domain
	 * @return
	 */

	@ResponseBody
	@RequestMapping(value = "getreportformsdate")
	public PageModelDomain getreportformsdate(@RequestBody PageModelDomain domain,HttpSession session) {
		String userid = session.getAttribute("userid").toString();
		if(userid.equals("1")){
			PageModelDomain pageModelDomain = new PageModelDomain();
			pageModelDomain = domain;
			int startSize = domain.getPageSize() * (domain.getCurrentPage() - 1);
			List<ContractInfo> selectContractInfos = userservice.selectReportforms(startSize + "", domain.getPageSize() + "","personLiableReport");
			pageModelDomain.setContractInfo(selectContractInfos);
			pageModelDomain.setPageTotalSize(userservice.selectReportforms(startSize + "", 1000 + "","personLiableReport").size());
			System.out.println(pageModelDomain.toString());
			return pageModelDomain;
		}else{
			UserInfo userInfo = userService.selectByPrimaryKey(userid);
			String uploadname = userInfo.getUsername();
			PageModelDomain pageModelDomain = new PageModelDomain();
			pageModelDomain = domain;
			int startSize = domain.getPageSize() * (domain.getCurrentPage() - 1);
			List<ContractInfo> selectContractInfos = userservice.selectReportformsUploadname(startSize + "", domain.getPageSize() + "",uploadname);
			System.out.println(selectContractInfos);
			pageModelDomain.setContractInfo(selectContractInfos);
			pageModelDomain.setPageTotalSize(userservice.selectReportformsUploadname(startSize + "", 1000 + "",uploadname).size());
			System.out.println(pageModelDomain.toString());
			return pageModelDomain;
		}
	}
	/**
	 * 合同文件下载
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "down1")
	public Map<String, String> downloadfile1(HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		Map<String, String> map = new HashMap<String, String>(); // 文件下载的路径
		uploadFilePath = request.getSession().getServletContext()
				.getRealPath("upload");
		String id = request.getParameter("id") == null ? ""
				: request.getParameter("id");
		
		ContractInfo selectByPrimaryKeyfile = userservice.selectByPrimaryKeyfile(id);
		System.out.println(selectByPrimaryKeyfile);
		
		String contracturl = selectByPrimaryKeyfile.getContracturl();
		String fileName = selectByPrimaryKeyfile.getName();
		File file1 = new File(contracturl);
		
		String name = file1.getName();
		System.out.println(name);
		
		fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath=request.getServletContext().getRealPath("/upload");
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path + "/" + fileName);
        //如果文件不存在
        if(!file.exists()){
            map.put("message", "您要下载的资源已被删除！！");
            return map;
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + "/" + fileName);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
		return map;
	}
	/**
	 * 报表模板文件下载
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "down2")
	public Map<String, String> downloadfile2(HttpSession session,HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		Map<String, String> map = new HashMap<String, String>(); // 文件下载的路径
		uploadFilePath = request.getSession().getServletContext().getRealPath("reportforms");
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		ContractInfo selectByPrimaryKeyfile = userservice.selectByPrimaryKeyfile(id);
		System.out.println(selectByPrimaryKeyfile);
		
		String contracturl = selectByPrimaryKeyfile.getContracturl();
		String fileName = selectByPrimaryKeyfile.getName();
		File file1 = new File(contracturl);
		
		String name = file1.getName();
		System.out.println(name+"***********************");
		String userid = session.getAttribute("userid").toString();
		UserInfo userInfo = userService.selectByPrimaryKey(userid);
		/*String realPath = "";
		if(userid.equals("1")){
			realPath = "/WEB-INF/reportforms";
		}else{
			realPath = "/WEB-INF/"+userInfo.getUsername();
		}*/
		
		fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath = request.getServletContext().getRealPath("/reportforms");
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path + "/" + name);
        //如果文件不存在
        String s = file.getAbsolutePath();
        System.out.println(s);
        if(!file.exists()){
            map.put("message", "您要下载的资源已被删除！！");
            return map;
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + "/" + name);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
		return map;
	}

	
	/**
	 * 报表公共(自己)文件下载
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value = "down3")
	public Map<String, String> downloadfile3(HttpSession session,HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		Map<String, String> map = new HashMap<String, String>(); // 文件下载的路径
		uploadFilePath = request.getSession().getServletContext().getRealPath("reportforms");
		String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		ContractInfo selectByPrimaryKeyfile = userservice.selectByPrimaryKeyfile(id);
		System.out.println(selectByPrimaryKeyfile);
		
		String contracturl = selectByPrimaryKeyfile.getContracturl();
		String fileName = selectByPrimaryKeyfile.getName();
		File file1 = new File(contracturl);
		
		String name = file1.getName();
		System.out.println(name+"***********************");
		String userid = session.getAttribute("userid").toString();
		UserInfo userInfo = userService.selectByPrimaryKey(userid);
		String realPath = null;
		realPath = "/reportforms";
		/*if(userid.equals("1")){
			realPath = "/WEB-INF/reportforms";
		}else{
			realPath = "/WEB-INF/"+userInfo.getUsername();
		}*/
		
		fileName = new String(fileName.getBytes("iso8859-1"),"UTF-8");
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当中
        String fileSaveRootPath = request.getServletContext().getRealPath(realPath);
        //通过文件名找出文件的所在目录
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path + "/" + name);
        //如果文件不存在
        String s = file.getAbsolutePath();
        if(!file.exists()){
            map.put("message", "您要下载的资源已被删除！！");
            return map;
        }
        //处理文件名
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入流
        FileInputStream in = new FileInputStream(path + "/" + name);
        //创建输出流
        OutputStream out = response.getOutputStream();
        //创建缓冲区
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下载
            out.write(buffer, 0, len);
        }
        //关闭文件输入流
        in.close();
        //关闭输出流
        out.close();
        System.out.println(""+s);
		return map;
	}
	/**
	 * 删除当前用户上传的责任人报表
	 */
	@ResponseBody
	@RequestMapping(value = "del1")
	public Map<String, String> deletefile1(String dataid,HttpSession session,HttpServletResponse response,
		HttpServletRequest request) throws IOException {
		Map<String, String> map = new HashMap<String, String>(); // 文件下载的路径
		String message = null;
		//String id = request.getParameter("id") == null ? "" : request.getParameter("id");
		String id = dataid;
		String userid = session.getAttribute("userid").toString();
		UserInfo userInfo = userService.selectByPrimaryKey(userid);
		String realPath = null;
		realPath = "/reportforms";
		/*if(userid.equals("1")){
			realPath = "/WEB-INF/reportforms";
		}else{
			realPath = "/WEB-INF/"+userInfo.getUsername();
		}*/
		//删除文件夹文件
		ContractInfo selectByPrimaryKeyfile = userservice.selectByPrimaryKeyfile(id);
		String contracturl = selectByPrimaryKeyfile.getContracturl();
		File file1 = new File(contracturl);
        boolean b = file1.delete();
		if(b != true){
			map.put("message", "删除失败,请重试！！");
		}else{
			map.put("message", "删除成功!");
		}
        //删除数据库信息
		int s = userService.deleteByPrimaryKeyfile(id);
		if(s == 0){
			map.put("message", "删除失败,请重试！！");
			message = "删除失败,请重试！！";
		}else{
			map.put("message", "删除成功!");
			message = "删除成功!";
		}
		return map;
		//return message;
		//response.getWriter().print(message);
	}
	
	/**
	 * 上传合同文件
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "upload1", method = RequestMethod.POST)
	public void uploadFile1(HttpSession session,HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String userid = session.getAttribute("userid").toString();
		UserInfo userInfo = userService.selectByPrimaryKey(userid);
		String uploadname = userInfo.getUsername();
		String type = "1";
		String originalname = null;
		System.out.println("mdnfsdkl");
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = request.getServletContext().getRealPath("/upload");
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			// 创建目录
			file.mkdirs();
		}
		// 消息提示
		String message = "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return ;
			}
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			ContractInfo contractInfo = new ContractInfo();
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			contractInfo.setId(id);
			Date date = new Date();
			contractInfo.setCreatetime(date);
			contractInfo.setLastchangtime(date);
			contractInfo.setType(type);
			String filenames = null;
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					if (name.equals("name")) {
						if(value.length() == 0){
							contractInfo.setUploadname(uploadname);
						}else{
							contractInfo.setUploadname(value);
						}
						System.out.println(name + "=" + value);
					}
					if (name.equals("filename")) {
						File f = new File(value);
						String fname = f.getName();
						if(value.length() == 0){
							//在input没有添加数值
							contractInfo.setName("00000000000000000");
						}else{
							contractInfo.setName(fname);
							filenames=value;
						}
					}

				} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
					String filename = item.getName();
					
					String suffix = filename.substring(filename.lastIndexOf("."),filename.length());//得到文件后缀名  
					Date dates = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
					String s = sdf.format(dates);
					long l = System.currentTimeMillis();//获得从1970年到当前时间的毫秒数
					originalname = filename;
					if(userid.equals("1")){
						
					}else{
						filename = s+l+suffix;
					}
					System.out.println(filename);
					
					
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					// 创建一个文件输出流
					System.out.println("filenames==="+filenames);
					FileOutputStream out = new FileOutputStream(savePath + "/" + filename);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					item.delete();
					message = "文件上传成功！";
					contractInfo.setOriginalname(originalname);
					contractInfo.setUploadname(uploadname);
					contractInfo.setName(filename);
					contractInfo.setContracturl(savePath + "/"+ filename);
					int insert = userservice.insert(contractInfo);
					if (insert == 1) {
						message = "上传成功";
					} else {
						message = "上传失败";
					}

				}
			}

		} catch (Exception e) {
			message = "文件上传失败！";
			e.printStackTrace();

		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message/message.jsp").forward(request, response);
	}
	
	/**
	 * 上传责任人报表文件
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "upload2", method = RequestMethod.POST)
	public void uploadFile2(HttpSession session,HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		String type = "0";
		String originalname = null;
		String userid = session.getAttribute("userid").toString();
		String realPath = "";
		UserInfo userInfo = userService.selectByPrimaryKey(userid);
		String uploadname = userInfo.getUsername();
		realPath = "/reportforms";
		System.out.println("mdnfsdkl");
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安全
		String savePath = request.getServletContext().getRealPath(realPath);
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存在
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，需要创建");
			// 创建目录
			file.mkdirs();
		}
		// 消息提示
		String message = "";
		try {
			// 使用Apache文件上传组件处理文件上传步骤：
			// 1、创建一个DiskFileItemFactory工厂
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 2、创建一个文件上传解析器
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 解决上传文件名的中文乱码
			upload.setHeaderEncoding("UTF-8");
			// 3、判断提交上来的数据是否是上传表单的数据
			if (!ServletFileUpload.isMultipartContent(request)) {
				// 按照传统方式获取数据
				return ;
			}
			// 4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
			List<FileItem> list = upload.parseRequest(request);
			ContractInfo contractInfo = new ContractInfo();
			String id = UUID.randomUUID().toString().replaceAll("-", "");
			contractInfo.setId(id);
			Date date = new Date();
			contractInfo.setCreatetime(date);
			contractInfo.setLastchangtime(date);
			contractInfo.setType(type);
			String filenames = null;
			for (FileItem item : list) {
				// 如果fileitem中封装的是普通输入项的数据
				if (item.isFormField()) {
					String name = item.getFieldName();
					// 解决普通输入项的数据的中文乱码问题
					String value = item.getString("UTF-8");
					// value = new String(value.getBytes("iso8859-1"),"UTF-8");
					System.out.println(name + "=" + value);
					if (name.equals("name")) {
						contractInfo.setUploadname(value);
						uploadname = value;
					}
					if (name.equals("filename")) {
						contractInfo.setName(value);
						filenames=value;
					}
				} else {// 如果fileitem中封装的是上传文件 // 得到上传的文件名称，
					String filename = item.getName();
					String suffix = filename.substring(filename.lastIndexOf("."),filename.length());//得到文件后缀名  
					Date dates = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");
					String s = sdf.format(dates);
					long l = System.currentTimeMillis();//获得从1970年到当前时间的毫秒数
					originalname = filename;
					if(userid.equals("1")){
						
					}else{
						filename = s+l+suffix;
					}
					System.out.println(filename);
					if (filename == null || filename.trim().equals("")) {
						continue;
					}
					// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
					// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
					// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
					filename = filename.substring(filename.lastIndexOf("\\") + 1);
					// 获取item中的上传文件的输入流
					InputStream in = item.getInputStream();
					
					// 创建一个文件输出流
					System.out.println("filenames==="+filenames);
					FileOutputStream out = new FileOutputStream(savePath + "/"+ filename);
					// 创建一个缓冲区
					byte buffer[] = new byte[1024];
					// 判断输入流中的数据是否已经读完的标识
					int len = 0;
					// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
					while ((len = in.read(buffer)) > 0) {
						// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\"
						// + filename)当中
						out.write(buffer, 0, len);
					}
					// 关闭输入流
					in.close();
					// 关闭输出流
					out.close();
					// 删除处理文件上传时生成的临时文件
					item.delete();
					message = "文件上传成功！";
					contractInfo.setOriginalname(originalname);
					contractInfo.setUploadname(uploadname);
					contractInfo.setName(filename);
					contractInfo.setContracturl(savePath + "/"+ filename);
					int insert = userservice.insert(contractInfo);
					if (insert == 1) {
						message = "上传成功";
					} else {
						message = "上传失败";
					}

				}
			}

		} catch (Exception e) {
			message = "文件上传失败！";
			e.printStackTrace();
		}
		request.setAttribute("message", message);
		request.getRequestDispatcher("/message/message.jsp").forward(request, response);
	}
	
	
	public String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
	
/*	*//**
	 * 进入合同下载页面
	 * @return
	 *//*
	@RequestMapping("joindowncontracts")
	public  ModelAndView joindowncontracts(){
		ModelAndView mav = new ModelAndView("plan/downcontractsPage");
		return mav;
	}*/
	
/*	*//**
	 * 进入责任人报表模板下载页面
	 * @return
	 *//*
	@RequestMapping("joindownreportformsPage")
	public  ModelAndView joindownreportforms(){
		ModelAndView mav = new ModelAndView("plan/downreportformsPage");
		return mav;
	}*/
	/**
	 * 进入责任人报表下载页面
	 * @return
	 *//*
	@RequestMapping("joindownreportformsPages")
	public  ModelAndView joindownreportformss(){
		ModelAndView mav = new ModelAndView("plan/downreportformsPages");
		return mav;
	}*/
	/**
	 * 导出单位信息
	 */
	@RequestMapping(value = "/exportExcel")
	public String exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		//PageModelDomain pageModel = new PageModelDomain();
		PageModelDomain page = new PageModelDomain();
		/**
		 * 封装PageModelDomain
		 */
		//String startTime = request.getParameter("startTime");
		//String endTime = request.getParameter("endTime");
		//String staffDetailId = request.getParameter("staffDetailId");
		//String f_add_type = request.getParameter("f_add_type");
		//pageModel.setStartTime(startTime);
		//pageModel.setEndTime(endTime);
		//pageModel.setStaffDetailId(staffDetailId);
		//pageModel.setF_add_type(f_add_type);
		//PageModelDomain page = getdeptsExportExcel(pageModel);
		/**
		 * 获取到数据
		 */
		//List<LjflScoreRecord> scoreRecordList = page.getScoreRecordList();
		List<UnitInfo> unitList = unitService.selectUnitInfoList();
		//List<UnitInfo> unitList = unitService.selectUnitListByAreaId(pageModelDomain);
		// 填充Objects数据
		// List<Object> Objects=createData(request);
		List<Map<String, Object>> list = createExcelRecord(unitList);
		String columnNames[] = { "id", "单位名称", "主要负责人", "联系电话", "单位类型", "地址" };// 列名
		String keys[] = { "unitcode", "unitname", "corporate", "phone", "unittype", "address" };// map中的key
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ExcelUtil.createWorkBook(list, keys, columnNames).write(os);
		} catch (IOException e) {
			e.printStackTrace();
		}
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		// 设置response参数，可以打开下载页面
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		String fileName = "单位明细表";
		response.setHeader("Content-Disposition", "attachment;filename="
				+ new String((ExportExcelFile.processFileName(request, fileName) + ".xls").getBytes(), "UTF-8"));
		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			// Simple read/write loop.
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (final IOException e) {
			throw e;
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		return null;
	}
	
	private List<Map<String, Object>> createExcelRecord(List<UnitInfo> unitList) {
		List<Map<String, Object>> listmap = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName", "单位明细表");
		listmap.add(map);
		UnitInfo unitinfo = null;
		//UnitInfo scoreRecord = null;
		for (int j = 0; j < unitList.size(); j++) {
			unitinfo = unitList.get(j);
			Map<String, Object> mapValue = new HashMap<String, Object>();
			mapValue.put("unitcode", unitinfo.getUnitcode());
			mapValue.put("unitname", unitinfo.getUnitname());
			mapValue.put("corporate", unitinfo.getCorporate());
			mapValue.put("phone", unitinfo.getPhone());
			mapValue.put("unittype", unitinfo.getUnittype());
			mapValue.put("address",unitinfo.getAddress());
			listmap.add(mapValue);
		}
		return listmap;
	}
	
}
