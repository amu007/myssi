package org.amu.demo.myssi.web.my;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.amu.demo.myssi.common.crud.Page;
import org.amu.demo.myssi.common.crud.SearchFilter;
import org.amu.demo.myssi.common.crud.Servlets;
import org.amu.demo.myssi.entity.User;
import org.amu.demo.myssi.service.AccountManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping(value = "/my/demo")
@Controller
public class DemoController {

	@Autowired
	private AccountManager accountManager;
	
	@RequestMapping(value = { "list", "" })
	public String list(Model model, ServletRequest request, @RequestParam(value = "pageNo", defaultValue = "1") int pageNo) {
		List<SearchFilter> searchFilterList = Servlets.getParametersStartingWith(request, "search_");
		Page<User> page = accountManager.findPage(searchFilterList, pageNo);
		model.addAttribute("page", page);
		return "my/demo";
	}

	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		accountManager.delete(id);
		return "redirect:/my/demo/";
	}

	@RequestMapping(value = "input")
	public String input(@ModelAttribute("user") User user, Model model, HttpSession session, HttpServletRequest request) {
		return "my/demoForm";
	}

	@RequestMapping(value = "json")
	@ResponseBody
	public String json() {
		return "3333333";
	}
	
	@RequestMapping(value = "save")
	public String save(@ModelAttribute("user") User user, Model model, HttpSession session, HttpServletRequest request) {
		if (null == user.getId()) {
			accountManager.save(user);
		} else {
			accountManager.update(user);
		}
		return "redirect:/my/demo/";
	}
	
	@ModelAttribute("user")
	public User getAccount(Long id) {
		if (null == id) {
			return new User();
		} else {
			return accountManager.get(id);
		}
	}
}
