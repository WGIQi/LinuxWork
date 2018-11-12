package com.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.domain.Course;
import com.domain.CourseInfo;
import com.domain.User;
import com.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Resource
	private CourseService courseService;

	@RequestMapping("/login")
	public String login() {
		return "/login";
	}

	@RequestMapping(value = "/ajaxLogin", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> ajaxLogin(HttpServletRequest httpServletRequest) {

		String username = httpServletRequest.getParameter("username");
		String password = httpServletRequest.getParameter("password");

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> statemap = new HashMap<String, Object>();
		Integer state;
		map.put("username", username);
		map.put("password", password);
		User user = courseService.findUser(map);

		if (user == null) {
			state = 1;
		} else {
			HttpSession session = httpServletRequest.getSession();
			session.setAttribute("user", user);
			state = 0;
		}
		statemap.put("state", state);
		return statemap;
	}

	/**
	 * 登录成功
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("/main")
	public String login(HttpServletRequest httpServletRequest) {

		boolean loginState = loginStateCheck(httpServletRequest);
		if (!loginState) {
			return "redirect: /Course";
		}

		return "redirect: /Course/login.jsp";
	}

	/**
	 * 跳转主页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest httpServletRequest, Model model) {

		boolean loginState = loginStateCheck(httpServletRequest);
		if (!loginState) {
			return "redirect: /Course";
		}

		List<Course> courses = courseService.findAllCourse();

		for (int i = 0; i < courses.size(); i++) {

			String coursetype = courses.get(i).getCoursetype();
			if (coursetype.equals("1")) {
				coursetype = "公共课";
			}
			if (coursetype.equals("2")) {
				coursetype = "必修课";
			}
			if (coursetype.equals("3")) {
				coursetype = "选修课";
			}
			courses.get(i).setCoursetype(coursetype);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "课程");
		model.addAttribute("menu", "全部课程");

		return "/main";
	}

	/**
	 * 跳转添加课程页面
	 * 
	 * @return
	 */
	@RequestMapping("/addCoursePage")
	public String addCoursePage() {
		return "/addcourse";
	}

	/**
	 * 跳转选课页面
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("/selectCoursePage")
	public String selectCoursePage(HttpServletRequest httpServletRequest, Model model) {

		HttpSession httpSession = httpServletRequest.getSession();
		User user = (User) httpSession.getAttribute("user");

		if (user == null) {
			return "redirect: /Course";
		}

		List<Course> courses = courseService.findNotMyCourse(user.getUserID());
		Integer creditsum = courseService.findCreditSumById(user.getUserID());

		if (creditsum == null) {
			creditsum = 0;
		}

		for (int i = 0; i < courses.size(); i++) {

			String coursetype = courses.get(i).getCoursetype();
			if (coursetype.equals("1")) {
				coursetype = "公共课";
			}
			if (coursetype.equals("2")) {
				coursetype = "必修课";
			}
			if (coursetype.equals("3")) {
				coursetype = "选修课";
			}
			courses.get(i).setCoursetype(coursetype);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("creditsum", creditsum);
		model.addAttribute("mainmenu", "选课");
		model.addAttribute("menu", "选择课程");
		model.addAttribute("count", 1);
		return "/selectcourse";
	}

	/**
	 * 编辑自己的选课信息
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("/deleteSelectCoursePage")
	public String deleteSelectCoursePage(HttpServletRequest httpServletRequest, Model model) {

		HttpSession httpSession = httpServletRequest.getSession();
		User user = (User) httpSession.getAttribute("user");

		if (user == null) {
			return "redirect: /Course";
		}
		List<Course> courses = courseService.findMyCourse(user.getUserID());
		Integer creditsum = courseService.findCreditSumById(user.getUserID());

		if (creditsum == null) {
			creditsum = 0;
		}

		for (int i = 0; i < courses.size(); i++) {

			String coursetype = courses.get(i).getCoursetype();
			if (coursetype.equals("1")) {
				coursetype = "公共课";
			}
			if (coursetype.equals("2")) {
				coursetype = "必修课";
			}
			if (coursetype.equals("3")) {
				coursetype = "选修课";
			}
			courses.get(i).setCoursetype(coursetype);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("creditsum", creditsum);
		model.addAttribute("mainmenu", "选课");
		model.addAttribute("menu", "删除选课");
		model.addAttribute("count", 0);

		return "/selectcourse";
	}

	/**
	 * 根据courseID删除已选课程
	 * 
	 * @param httpServletRequest
	 */
	@RequestMapping("/deleteCourseInfoById")
	public void deleteCourseInfoById(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws IOException {

		Integer courseID = Integer.parseInt(httpServletRequest.getParameter("courseID"));

		courseService.deleteCourseInfoById(courseID);
	}

	/**
	 * 插入数据
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/insertCourse")
	public String insertCourse(HttpServletRequest httpServletRequest, Model model) {

		boolean loginState = loginStateCheck(httpServletRequest);
		if (!loginState) {
			return "redirect: /Course";
		}

		Course course = new Course();
		// 获取页面数据
		String courseID = httpServletRequest.getParameter("courseID");
		String coursename = httpServletRequest.getParameter("coursename");
		String coursetype = httpServletRequest.getParameter("coursetype");
		String coursehours = httpServletRequest.getParameter("coursehours");
		String teachhours = httpServletRequest.getParameter("teachhours");
		String experimenthours = httpServletRequest.getParameter("experimenthours");
		String credit = httpServletRequest.getParameter("credit");
		String coursestart = httpServletRequest.getParameter("coursestart");
		// 处理页面数据
		course.setCourseID(Integer.parseInt(courseID));
		course.setCoursename(coursename);
		course.setCoursetype(coursetype);
		course.setCoursehours(Integer.parseInt(coursehours));
		course.setTeachhours(Integer.parseInt(teachhours));
		course.setExperimenthours(Integer.parseInt(experimenthours));
		course.setCredit(Integer.parseInt(credit));
		course.setCoursestart(coursestart);

		courseService.insertCourse(course);

		List<Course> courses = courseService.findAllCourse();

		for (int i = 0; i < courses.size(); i++) {

			String type = courses.get(i).getCoursetype();
			if (type.equals("1")) {
				type = "公共课";
			}
			if (type.equals("2")) {
				type = "必修课";
			}
			if (type.equals("3")) {
				type = "选修课";
			}
			courses.get(i).setCoursetype(type);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "课程");
		model.addAttribute("menu", "全部课程");

		return "/main";
	}

	/**
	 * 查询
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("/findCourseById")
	public String findCourseById(HttpServletRequest httpServletRequest, Model model) {

		boolean loginState = loginStateCheck(httpServletRequest);
		if (!loginState) {
			return "redirect: /Course";
		}

		Course course = courseService.findCourseByName("语文");

		model.addAttribute("course", course);

		return "/findResult";

	}

	/**
	 * 根据课程ID删除课程
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @throws IOException
	 */
	@RequestMapping("/deleteCourseById")
	public void deleteCourseById(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse)
			throws IOException {

		Integer courseID = Integer.parseInt(httpServletRequest.getParameter("courseID"));

		courseService.deleteCourseById(courseID);
	}

	/**
	 * Ajax向页面返回课程学分
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/findCreditById", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> findCreditById(HttpServletRequest httpServletRequest) {

		Integer courseID = Integer.parseInt(httpServletRequest.getParameter("courseID"));
		Integer credit = courseService.findCreditById(courseID);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("credit", credit);
		return map;
	}

	/**
	 * 添加选课信息
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/addCourseInfo")
	public String addCourseInfo(HttpServletRequest httpServletRequest, Model model) {

		boolean loginState = loginStateCheck(httpServletRequest);
		if (!loginState) {
			return "redirect: /Course";
		}

		String courseIDs = httpServletRequest.getParameter("courseID");
		HttpSession httpSession = httpServletRequest.getSession();
		User user = (User) httpSession.getAttribute("user");

		CourseInfo courseInfo = new CourseInfo();
		Integer userID = user.getUserID();
		courseInfo.setuserID(userID);

		String[] courseID = courseIDs.split("-");

		for (String id : courseID) {
			courseInfo.setCourseID(Integer.parseInt(id));
			courseService.insertCourseInfo(courseInfo);
		}

		List<Course> courses = courseService.findMyCourse(user.getUserID());

		for (int i = 0; i < courses.size(); i++) {

			String type = courses.get(i).getCoursetype();
			if (type.equals("1")) {
				type = "公共课";
			}
			if (type.equals("2")) {
				type = "必修课";
			}
			if (type.equals("3")) {
				type = "选修课";
			}
			courses.get(i).setCoursetype(type);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "课程");
		model.addAttribute("menu", "已选课程");

		return "/main";
	}

	/**
	 * 查看已选课程
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("/myCourse")
	public String myCourse(HttpServletRequest httpServletRequest, Model model) {

		HttpSession httpSession = httpServletRequest.getSession();
		User user = (User) httpSession.getAttribute("user");

		if (user == null) {
			return "redirect: /Course";
		}

		List<Course> courses = courseService.findMyCourse(user.getUserID());

		for (int i = 0; i < courses.size(); i++) {

			String type = courses.get(i).getCoursetype();
			if (type.equals("1")) {
				type = "公共课";
			}
			if (type.equals("2")) {
				type = "必修课";
			}
			if (type.equals("3")) {
				type = "选修课";
			}
			courses.get(i).setCoursetype(type);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "课程");
		model.addAttribute("menu", "已选课程");
		return "/main";
	}

	/**
	 * 查看未选课程
	 * 
	 * @param httpServletRequest
	 * @param model
	 * @return
	 */
	@RequestMapping("/notMyCourse")
	public String notMyCourse(HttpServletRequest httpServletRequest, Model model) {

		HttpSession httpSession = httpServletRequest.getSession();
		User user = (User) httpSession.getAttribute("user");

		if (user == null) {
			return "redirect: /Course";
		}

		List<Course> courses = courseService.findNotMyCourse(user.getUserID());

		for (int i = 0; i < courses.size(); i++) {

			String type = courses.get(i).getCoursetype();
			if (type.equals("1")) {
				type = "公共课";
			}
			if (type.equals("2")) {
				type = "必修课";
			}
			if (type.equals("3")) {
				type = "选修课";
			}
			courses.get(i).setCoursetype(type);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "课程");
		model.addAttribute("menu", "未选课程");
		return "/main";
	}

	/**
	 * 添加课程ID是否已经存在判断
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping(value = "/courseIDCheck", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Map<String, Object> courseIDCheck(HttpServletRequest httpServletRequest) {

		Integer courseID = Integer.parseInt(httpServletRequest.getParameter("courseID"));
		Course course = courseService.findCourseById(courseID);

		String state = "1";
		if (course == null) {
			state = "0";
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", state);

		return map;
	}

	/**
	 * 用户退出
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest httpServletRequest) {

		HttpSession httpSession = httpServletRequest.getSession();
		httpSession.invalidate();

		return "redirect: /Course";
	}

	/**
	 * 用户登陆状态判断
	 * 
	 * @param httpServletRequest
	 * @return
	 */
	public static boolean loginStateCheck(HttpServletRequest httpServletRequest) {

		HttpSession httpSession = httpServletRequest.getSession();
		User user = (User) httpSession.getAttribute("user");
		if (user == null) {
			return false;
		}
		return true;
	}

}
