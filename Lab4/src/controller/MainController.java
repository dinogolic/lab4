package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;

import dao.GradBean;
import dao.LoginBean;
import dao.LoginHandler;
import dao.RegistracijaBean;
import dao.RegistracijaHandler;
import dao.RezervacijaBean;

@Controller
public class MainController {
	
	@RequestMapping(value = "/")
	public ModelAndView test(HttpServletResponse response) throws IOException {
		
		ModelAndView modelandview = new ModelAndView("Login");
		LoginBean loginBean = new LoginBean();
		modelandview.addObject("loginBean", loginBean);
		return modelandview;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)

	public ModelAndView displayLogin(HttpServletResponse response)throws IOException {
		 System.out.println("i dovde");
		ModelAndView model = new ModelAndView("Login");

		LoginBean loginBean = new LoginBean();

		model.addObject("loginBean", loginBean);

		return model;

	}
	
	@RequestMapping(value = "/registracija", method = RequestMethod.GET)

	public ModelAndView displayRegistracija(HttpServletResponse response)throws IOException {
		 System.out.println("Registracija");
		 ModelAndView model = new ModelAndView("Registracija");

			RegistracijaBean registracijaBean = new RegistracijaBean();

			model.addObject("registracijaBean", registracijaBean);

			return model;

	}
	
	@RequestMapping(value = "/registracija", method = RequestMethod.POST)

	public ModelAndView executeRegistracija(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("registracijaBean") RegistracijaBean registracijaBean)

	{
		ModelAndView model = null;
		boolean registrovan = RegistracijaHandler.registrujSe(registracijaBean);
		if(registrovan == true) {
			model = new ModelAndView("Login");
			Object loginBean = new LoginBean();
			model.addObject("loginBean", loginBean);
			
		} else {
			model = new ModelAndView("Registracija");
			model.addObject("registracijaBean", registracijaBean);
		}
		
		return model;
	}
	
	@RequestMapping(value = "/rezervacija", method = RequestMethod.POST)

	public ModelAndView rezervacija(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("gradBean") GradBean gradBean)

	{

		ModelAndView model = null;
		RegistracijaHandler rH = new RegistracijaHandler();
		model = new ModelAndView("Rezervacija");
		try

		{
			Map<String, String> parkinzi = rH.dajParkinge(gradBean.getNaziv());
			Object rezervacijaBean = new RezervacijaBean();
			model.addObject("rezervacijaBean", rezervacijaBean );
			request.setAttribute("parkinzi", parkinzi);
		}
		catch(Exception e){
			
		}
		return model;
	}
	
	@RequestMapping(value = "/rezervacijaKon", method = RequestMethod.POST)

	public ModelAndView rezervacijaKon(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("rezervacijaBean") RezervacijaBean rezervacijaBean)

	{

		ModelAndView model = null;
		RegistracijaHandler rH = new RegistracijaHandler();
		model = new ModelAndView("Konacno");
		try

		{
			boolean rezervisano = rH.rezervisi(rezervacijaBean);
			if(rezervisano) {
				model = new ModelAndView("Konacno");
				return model;
			}
		}
		catch(Exception e){
			
		}
		model = new ModelAndView("Parking");
		Map<String, String> gradovi = RegistracijaHandler.dajGradove();
		request.setAttribute("gradovi", gradovi);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)

	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("loginBean") LoginBean loginBean)

	{

		ModelAndView model = null;
		LoginHandler lH = new LoginHandler();
		
		try

		{

			String username = loginBean.getUserid();
			String pwd = loginBean.getPwd();
			
			boolean isValidUser = lH.checkLogin(username, pwd);
			 System.out.println("i dovde");
			if (isValidUser)

			{

				System.out.println("User Login Successful");

				request.setAttribute("loggedInUser", loginBean.getUserid());

				model = new ModelAndView("Parking");

				try {

					Map<String, String> gradovi = RegistracijaHandler.dajGradove();
					request.setAttribute("gradovi", gradovi);
					System.out.println("to to");

					
				} catch (Exception e) {
					System.out.println("Error: " + e.toString());
				}

			}
			else {
				System.out.println("Else: " + isValidUser);
				 model = new ModelAndView("Login");


				model.addObject("loginBean", loginBean);
			}

			

		}

		catch (Exception e)

		{

			e.printStackTrace();

		}

		return model;

	}

}
