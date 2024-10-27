package main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomePage {
	@GetMapping("/")
	public String getHome() {
		return "dangnhap";
	}
	@RequestMapping("/home")
	public String homePage() {
		return "home";
	}
	
	@GetMapping("/condominum")
	public String canHo() {
		return "condominum";
	}
	@GetMapping("/dichvu")
	public String goiDichVu() {
		return "dichvu";
	}
	@GetMapping("/chiphi")
	public String hienThi() {
		return "chiphi";
	}
	@GetMapping("/apartment")
	public String getApartment() {
		return "apartment";
	}
}
