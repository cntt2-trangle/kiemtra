package vn.edu.leading.kiemtra.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.kiemtra.models.StudentModel;
import vn.edu.leading.kiemtra.services.StudentService;

import javax.validation.Valid;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String list(Model model) {
        model.addAttribute("students", studentService.findAll());
        return "students/list";
    }

    @GetMapping("students/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/students";
        }
        model.addAttribute("students", studentService.search(term));
        return "students/list";
    }

    @GetMapping("/students/add")
    public String add(Model model) {
        model.addAttribute("studentModel", new StudentModel());
        return "students/form";
    }

    @GetMapping("/students/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("studentModel", studentService.findById(id));
        return "students/form";
    }

    @PostMapping("/students/save")
    public String save(@Valid StudentModel customer, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "students/form";
        }
        studentService.save(customer);
        redirect.addFlashAttribute("successMessage", "Saved customer successfully!");
        return "redirect:/students";
    }

    @GetMapping("/students/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (studentService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted customer successfully!");
            return "redirect:/students";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return "redirect:/students";
        }
    }
}
