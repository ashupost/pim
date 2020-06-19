package com.pim.demo;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pim.jdbc.ExportsIPBean;
import com.pim.jdbc.GDException;
import com.pim.jdbc.ResultBean;
import com.pim.service.ExportService;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ExportService exportService;

	@CrossOrigin
	@GetMapping(path="/employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResultBean getAllEmployees() throws GDException {
		
		System.out.println("Hellooooooooooooo");
		ExportsIPBean bean = new ExportsIPBean();
		bean.setExportReqtId("1");
		System.out.println("KKKKK="+exportService);
		ResultBean res = exportService.getLoginList(bean);
		
		Employee e = new Employee();

		e.setFirstName("sa");
		e.setLastName("kumar");
		e.setEmailId("help@gmail.com");
		employeeRepository.save(e);

		return res;
	}

	@CrossOrigin
	@GetMapping(path="/employees/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(employee);
	}

	@CrossOrigin
	@PostMapping("/employees")
	public Employee createEmployee(@Valid @RequestBody Employee e) {
		e = new Employee();

		e.setFirstName("Mritunjay");
		e.setLastName("kumar");
		e.setEmailId("help@gmail.com");
		return employeeRepository.save(e);
	}

	@CrossOrigin
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employee.setEmailId(employeeDetails.getEmailId());
		employee.setLastName(employeeDetails.getLastName());
		employee.setFirstName(employeeDetails.getFirstName());
		final Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}

	@CrossOrigin
	@DeleteMapping("/employees/{id}")
	public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}