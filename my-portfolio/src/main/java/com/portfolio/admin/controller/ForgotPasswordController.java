package com.portfolio.admin.controller;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.portfolio.admin.entity.AboutMe;
import com.portfolio.admin.service.IAboutMeService;
import com.portfolio.common.exception.AboutMeNotFoundException;
import com.portfolio.common.utils.GetSiteURL;

import net.bytebuddy.utility.RandomString;

@Controller
public class ForgotPasswordController extends BaseController {
	@Autowired
	private IAboutMeService aboutMeService;

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping(value = "/forgot-password")
	public String showForgotPasswordPage(Model model) {
		setViewTitleAndFavicon("Forgot password", model);
		return "/admin/forgot-password";
	}

	@PostMapping(value = "/forgot-password")
	public String processForgotPassword(HttpServletRequest request, Model model)
			throws UnsupportedEncodingException, MessagingException {
		setViewTitleAndFavicon("Forgot password", model);
		String getEmail = request.getParameter("email");
		String token = RandomString.make(30);
		try {
			aboutMeService.updateResetPasswordToken(getEmail, token);
			String resetPasswordLink = GetSiteURL.getSiteUrl(request) + "/reset-password?token=" + token;
			sendMail(getEmail, resetPasswordLink);
			model.addAttribute("message", "We have sent a reset password link to your email. Please check.");
		} catch (AboutMeNotFoundException e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/admin/forgot-password";
	}

	public void sendMail(String recipientEmail, String resetPasswrodLink)
			throws MessagingException, UnsupportedEncodingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		helper.setFrom("nguyenduy27092002@gmail.com", "Support reset password");
		helper.setTo(recipientEmail);
		String subject = "Here's the link to reset your password";
		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + resetPasswrodLink
				+ "\">Change my password</a></p>" + "<br>" + "<p>Ignore this email if you do remember your password, "
				+ "or you have not made the request.</p>";
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
	}

	@GetMapping(value = "/reset-password")
	public String showReserPasswordPage(@RequestParam(value = "token") String token, Model model) {
		setViewTitleAndFavicon("Reset password", model);
		AboutMe aboutMe = aboutMeService.getByResetPasswordToken(token);
		if (aboutMe == null) {
			model.addAttribute("invalidToken", "Invalid token");
		} else {
			model.addAttribute("token", token);
		}
		return "/admin/reset-password";
	}

	@PostMapping("/reset-password")
	public String processResetPassword(HttpServletRequest request, RedirectAttributes redirectModel, Model model) {
		String token = request.getParameter("token");
		String password = request.getParameter("password");
		AboutMe aboutMe = aboutMeService.getByResetPasswordToken(token);
		if (aboutMe == null) {
			setViewTitleAndFavicon("Reset password", model);
			model.addAttribute("systemError", "Error while resetting the password. Please try again!");
			return "/admin/reset-password";
		} else {
			aboutMeService.updatePassword(aboutMe, password);
			redirectModel.addFlashAttribute("successMessage", "You have successfully changed your password.");
			return "redirect:/sign-in";
		}
	}
}
