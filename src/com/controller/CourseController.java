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
	 * ��¼�ɹ�
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
	 * ��ת��ҳ��
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
				coursetype = "������";
			}
			if (coursetype.equals("2")) {
				coursetype = "���޿�";
			}
			if (coursetype.equals("3")) {
				coursetype = "ѡ�޿�";
			}
			courses.get(i).setCoursetype(coursetype);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "�γ�");
		model.addAttribute("menu", "ȫ���γ�");

		return "/main";
	}

	/**
	 * ��ת��ӿγ�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/addCoursePage")
	public String addCoursePage() {
		return "/addcourse";
	}

	/**
	 * ��תѡ��ҳ��
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
				coursetype = "������";
			}
			if (coursetype.equals("2")) {
				coursetype = "���޿�";
			}
			if (coursetype.equals("3")) {
				coursetype = "ѡ�޿�";
			}
			courses.get(i).setCoursetype(coursetype);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("creditsum", creditsum);
		model.addAttribute("mainmenu", "ѡ��");
		model.addAttribute("menu", "ѡ��γ�");
		model.addAttribute("count", 1);
		return "/selectcourse";
	}

	/**
	 * �༭�Լ���ѡ����Ϣ
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
				coursetype = "������";
			}
			if (coursetype.equals("2")) {
				coursetype = "���޿�";
			}
			if (coursetype.equals("3")) {
				coursetype = "ѡ�޿�";
			}
			courses.get(i).setCoursetype(coursetype);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("creditsum", creditsum);
		model.addAttribute("mainmenu", "ѡ��");
		model.addAttribute("menu", "ɾ��ѡ��");
		model.addAttribute("count", 0);

		return "/selectcourse";
	}

	/**
	 * ����courseIDɾ����ѡ�γ�
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
	 * ��������
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
		// ��ȡҳ������
		String courseID = httpServletRequest.getParameter("courseID");
		String coursename = httpServletRequest.getParameter("coursename");
		String coursetype = httpServletRequest.getParameter("coursetype");
		String coursehours = httpServletRequest.getParameter("coursehours");
		String teachhours = httpServletRequest.getParameter("teachhours");
		String experimenthours = httpServletRequest.getParameter("experimenthours");
		String credit = httpServletRequest.getParameter("credit");
		String coursestart = httpServletRequest.getParameter("coursestart");
		// ����ҳ������
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
				type = "������";
			}
			if (type.equals("2")) {
				type = "���޿�";
			}
			if (type.equals("3")) {
				type = "ѡ�޿�";
			}
			courses.get(i).setCoursetype(type);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "�γ�");
		model.addAttribute("menu", "ȫ���γ�");

		return "/main";
	}

	/**
	 * ��ѯ
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

		Course course = courseService.findCourseByName("����");

		model.addAttribute("course", course);

		return "/findResult";

	}

	/**
	 * ���ݿγ�IDɾ���γ�
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
	 * Ajax��ҳ�淵�ؿγ�ѧ��
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
	 * ���ѡ����Ϣ
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
				type = "������";
			}
			if (type.equals("2")) {
				type = "���޿�";
			}
			if (type.equals("3")) {
				type = "ѡ�޿�";
			}
			courses.get(i).setCoursetype(type);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "�γ�");
		model.addAttribute("menu", "��ѡ�γ�");

		return "/main";
	}

	/**
	 * �鿴��ѡ�γ�
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
				type = "������";
			}
			if (type.equals("2")) {
				type = "���޿�";
			}
			if (type.equals("3")) {
				type = "ѡ�޿�";
			}
			courses.get(i).setCoursetype(type);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "�γ�");
		model.addAttribute("menu", "��ѡ�γ�");
		return "/main";
	}

	/**
	 * �鿴δѡ�γ�
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
				type = "������";
			}
			if (type.equals("2")) {
				type = "���޿�";
			}
			if (type.equals("3")) {
				type = "ѡ�޿�";
			}
			courses.get(i).setCoursetype(type);

		}

		model.addAttribute("courses", courses);
		model.addAttribute("mainmenu", "�γ�");
		model.addAttribute("menu", "δѡ�γ�");
		return "/main";
	}

	/**
	 * ��ӿγ�ID�Ƿ��Ѿ������ж�
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
	 * �û��˳�
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
	 * �û���½״̬�ж�
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
