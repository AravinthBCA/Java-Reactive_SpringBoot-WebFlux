package Appli.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Appli.DTO.Response;
import Appli.Service.MathService;

@RestController
@RequestMapping("math")
public class MathController {
	
	@Autowired
	private MathService mathService;
	
	@GetMapping("square/{input}")
	public Response findSquare(@PathVariable int input) {
		return this.mathService.findSquare(input);
	}
	
	@GetMapping("table/{input}")
	public List<Response> multiplicationTable(@PathVariable int input) {
		return this.mathService.multiplicationTable(input);
	}
	
}
