package com.ssm.controller;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.druid.support.logging.Log;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.domain.User;
import com.ssm.service.UserService;

import net.sf.json.JSONObject;
import util.http;
import util.upload;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	private Map<String,Object> code_result=new HashMap<String,Object>();
//	登录，返回code,修改表格的openid
	@RequestMapping(value="/getcode",produces = "application/json;charset=UTF-8")
	@ResponseBody  //用于转换对象为json
	public Map<String, Object> getcode(String code) {
		Object user=http.Get("https://api.weixin.qq.com/sns/jscode2session","appid=wxcd183a3a1e87c7a7&secret=a1bd8ade6005c77b7bce319da17ff6d3&js_code="+code+"&grant_type=authorization_code");
		if(user!=null) {
			//直接调用getId()判断会失败
			code_result.put("code", 0);//表示成功
			code_result.put("data", user);
			code_result.put("info", "获取成功！");
		}else {
			code_result.put("code", 1);//表示失败
			code_result.put("info", "code错误！");
		}
		//获取该用户的数据，用于判断是否注册过
		return code_result;
	}
	
	private Map<String,Object> log_result=new HashMap<String,Object>();
//	登录，返回code,修改表格的openid
	@RequestMapping(value="/open",produces = "application/json;charset=UTF-8")
	@ResponseBody  //用于转换对象为json
	public Map<String, Object> open(String openid) {
		Object user=userService.get_openid(openid);
		if(user!=null) {
			//直接调用getId()判断会失败
			log_result.put("code", 0);//表示成功
			log_result.put("data", user);
			log_result.put("info", "登录成功！");
		}else {
			log_result.put("code", 1);//表示失败
			log_result.put("info", "您还未注册！");
		}
		return log_result;
	}
	
	private Map<String,Object> get_o_result=new HashMap<String,Object>();
//	登录，返回code,修改表格的openid
	@RequestMapping(value="/login",produces = "application/json;charset=UTF-8")
	@ResponseBody  //用于转换对象为json
	public Map<String, Object> login(String number,String password) {
		Object user=userService.get_pass(number,password);
		if(user!=null) {
			//直接调用getId()判断会失败
			get_o_result.put("code", 0);//表示成功
			get_o_result.put("data", user);
			get_o_result.put("info", "登录成功！");
		}else {
			get_o_result.put("code", 1);//表示失败
			get_o_result.put("info", "账号密码错误！");
		}
		//获取该用户的数据，用于判断是否注册过
		return get_o_result;
	}
	
	private Map<String,Object> get_address=new HashMap<String,Object>();
//	登录，返回code,修改表格的openid
	@RequestMapping(value="/getaddress",produces = "application/json;charset=UTF-8")
	@ResponseBody  //用于转换对象为json
	public Map<String, Object> get_address() {
		String jsonStr = "";
        try {
        	// 要用绝对路径，相对路径有问题
            File file = new File("/home/tomcat/apache-tomcat-7.0.100/webapps/work/address.json");
            FileReader fileReader = new FileReader(file);
            Reader reader = new InputStreamReader(new FileInputStream(file),"Utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
        } catch (Exception e) {
            throw new Error(e);
        }
		if(jsonStr!=null) {
			get_address.put("data", jsonStr);
		}else {
			get_address.put("data", "error");//表示失败
		}
		return get_address;
	}
	
//	上传图片到tomcat服务器
	private Map<String,Object> upload_result=new HashMap<String,Object>();
	@RequestMapping(value="/upload",produces = "application/json;charset=UTF-8")
	@ResponseBody  //用于转换对象为json
	public Map<String, Object> upload(HttpServletRequest request,@RequestParam("file")MultipartFile file) {
		//刚开始500是因为接收到的参数为null
		String img_path=new upload().getImgPath(file);
//		图片的访问路径为http://localhost:8080/ssm_imgs/1578110444116.wxcd183a3a1e87c7a7.o6zAJsw4SyWU4E0dy4IvNVSSSggo.PcdFc1px5qMs7688be6813e6d3d66604236119d9e385.jpg
//		所以还需要把图片路径保存到数据库
		if(img_path!=null) {
			//直接调用getId()判断会失败
			upload_result.put("code", 0);//表示成功
			upload_result.put("data", img_path);
			upload_result.put("info", "图片上传成功！");
		}else {
			upload_result.put("code", 1);//表示失败
			upload_result.put("info", "图片上传失败！");
		}
		//获取该用户的数据，用于判断是否注册过
		return upload_result;
	}
	
	
//	设计Map集合存储需要给页面的对象数据
	private Map<String,Object> result=new HashMap<String,Object>();
	
	
//	分页查询，使用mybatis插件pagehelper
	@RequestMapping(value="/listByPage",produces="application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> listByPage(Integer page) {
//		Integer page表示的是前端传入的参数，可以有多个接收，用,隔开
		PageHelper.startPage(page,10);//表示传入页数与每页条数
//		查询所有数据
		List<Object> list=userService.findAll();
//		使用pageInfo封装查询到的数据
		PageInfo<Object> pageInfo=new PageInfo<Object>(list);
//		从PageInfo中获取到数据的总数
		long total=pageInfo.getTotal();
//		获取当前页的数据列表
		List<Object> cutlist=pageInfo.getList();
		result.put("total",total);
		result.put("data",cutlist);
//		因为设置了@ResponseBody，所以会自动把map集合转换成json数据形式
		return result;
	}
	
//	注册客户信息,像这种规定是Customer customer形式对象的数据传入时，应该是post请求
	private Map<String,Object> s_result=new HashMap<String,Object>();
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> save(User user){
		userService.saveUser(user);//保存传入的用户数据
		try {
			s_result.put("code", 0);//表示成功
			s_result.put("info", "注册成功！");
		} catch (Exception e) {
			// TODO: handle exception
			s_result.put("code", 1);
			s_result.put("info", e.getStackTrace());
		}
		return s_result;
	}
	
	//修改客户信息
	private Map<String,Object> change_result=new HashMap<String,Object>();
	@ResponseBody
	@RequestMapping("/update")
	public Map<String, Object> update(User user){
		userService.update(user);//保存传入的用户数据
		try {
			change_result.put("code", 0);//表示成功
			change_result.put("data", user);
			change_result.put("info", "修改成功！");
		} catch (Exception e) {
			// TODO: handle exception
			change_result.put("code", 1);
			change_result.put("info", e.getStackTrace());
		}
		return change_result;
	}
	
	//根据id获取客户信息
	private Map<String,Object> get_result=new HashMap<String,Object>();
	@ResponseBody
	@RequestMapping("/get")
	public Map<String, Object> findById(Integer id){
		Object user=userService.get(id);//保存传入的用户数据
		try {
			get_result.put("code", 0);//表示成功
			get_result.put("data", user);
			get_result.put("info", "获取成功！");
		} catch (Exception e) {
			// TODO: handle exception
			get_result.put("code", 1);
			get_result.put("info", e.getStackTrace());
		}
		return get_result;
	}
	
//	删除客户信息delete
	private Map<String,Object> d_result=new HashMap<String,Object>();
	@ResponseBody
	@RequestMapping("/delete")
	public Map<String, Object> delete(Integer id){
		userService.delete(id);//保存传入的用户数据
		try {
			d_result.put("code", 0);//表示成功
			d_result.put("info", "删除成功！");
		} catch (Exception e) {
			// TODO: handle exception
			d_result.put("code", 1);
			d_result.put("info", e.getStackTrace());
		}
		return d_result;
	}
}
