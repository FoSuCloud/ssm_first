package com.ssm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.domain.User;
import com.ssm.service.UserService;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserService userService;
	
	private Map<String,Object> get_o_result=new HashMap<String,Object>();
//	登录，返回code,修改表格的openid
	@RequestMapping(value="/login",produces = "application/json;charset=UTF-8")
	@ResponseBody  //用于转换对象为json
	public Map<String, Object> login(Integer id,String code) {
//		获取数据
		String url="https://api.weixin.qq.com/sns/jscode2session";
		String params="appid=wxcd183a3a1e87c7a7&secret=7414b7988220f233164fb180e43744a0&js_code="+code+"&grant_type=authorization_code";
		String result=util.http.Get(url, params);
//		json字符串转为json格式数据
		JSONObject object = JSONObject.fromObject(result);
//		json格式数据取某个键的值  修改openid
		//userService.open_update(id,object.get("openid").toString());
		//首先搜索是否存在该openid在用户表中，如果存在则返回数据，如果不存在也返回，前端判断数据跳转到注册页面
		User user=userService.get_openid(object.get("openid").toString());
		if(user!=null) {
			//直接调用getId()判断会失败
			get_o_result.put("code", 0);//表示成功
			get_o_result.put("data", user);
			get_o_result.put("info", "登录成功！");
		}else {
			get_o_result.put("code", 1);//表示失败
			get_o_result.put("info", "您还未注册！");
		}
		//获取该用户的数据，用于判断是否注册过
		return get_o_result;
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
		List<User> list=userService.findAll();
//		使用pageInfo封装查询到的数据
		PageInfo<User> pageInfo=new PageInfo<User>(list);
		
//		从PageInfo中获取到数据的总数
		long total=pageInfo.getTotal();
		
//		获取当前页的数据列表
		List<User> cutlist=pageInfo.getList();
		
		result.put("total",total);
		result.put("data",cutlist);
		
//		因为设置了@ResponseBody，所以会自动把map集合转换成json数据形式
		return result;
	}
	
//	保存客户信息,像这种规定是Customer customer形式对象的数据传入时，应该是post请求
	private Map<String,Object> s_result=new HashMap<String,Object>();
	@ResponseBody
	@RequestMapping("/save")
	public Map<String, Object> save(User user){
		userService.saveUser(user);//保存传入的用户数据
		try {
			s_result.put("code", 0);//表示成功
			s_result.put("info", "保存成功！");
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
		User user=userService.get(id);//保存传入的用户数据
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
	
//	测试多个参数test
	private Map<String,Object> t_result=new HashMap<String,Object>();
	@ResponseBody
	@RequestMapping("/test")
	public Map<String, Object> t_result(Integer id,String name){
		userService.delete(id);//保存传入的用户数据
		try {
			t_result.put("data", name);//表示成功
			t_result.put("id", id);//表示成功
			t_result.put("info", "测试成功！");
		} catch (Exception e) {
			// TODO: handle exception
			t_result.put("code", 1);
			t_result.put("info", e.getStackTrace());
		}
		return t_result;
	}
}
