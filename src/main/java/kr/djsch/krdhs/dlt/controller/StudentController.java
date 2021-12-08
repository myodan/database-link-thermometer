package kr.djsch.krdhs.dlt.controller;

import kr.djsch.krdhs.dlt.model.Student;
import kr.djsch.krdhs.dlt.repository.StudentRepository;
import kr.djsch.krdhs.dlt.repository.TemperatureRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class StudentController {

    private final StudentRepository studentRepository;
    private final TemperatureRepository temperatureRepository;

    public StudentController(StudentRepository studentRepository, TemperatureRepository temperatureRepository) {
        this.studentRepository = studentRepository;
        this.temperatureRepository = temperatureRepository;
    }

    @GetMapping("/")
    public String getIndex(Model model,
                           Pageable pageable,
                           @RequestParam(required = false, defaultValue = "0") String grade,
                           @RequestParam(required = false, defaultValue = "0") String group) {
        Page<Student> students;
        long lGrade = Long.parseLong(grade);
        long lGroup = Long.parseLong(group);
        if (lGrade != 0 && lGroup != 0) {
            students = studentRepository.findByGradeAndGroup(lGrade, lGroup, pageable);
        } else if (lGrade != 0) {
            students = studentRepository.findByGrade(lGrade, pageable);
        } else if (lGroup != 0) {
            students = studentRepository.findByGroup(lGroup, pageable);
        } else {
            students = studentRepository.findAll(pageable);
        }
        int endPage = (int) Math.ceil((pageable.getPageNumber() + 1) / 10.0) * 10;
        int beginPage = endPage - 9;
        int realEnd = (int) (Math.ceil((double) students.getTotalElements() / pageable.getPageSize()));
        if (realEnd < endPage) {
            endPage = realEnd;
        }
        model.addAttribute("grade", Long.parseLong(grade));
        model.addAttribute("group", Long.parseLong(group));
        model.addAttribute("beginPage", beginPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("students", students);
        model.addAttribute("gradeCount", studentRepository.countDistinctGrade());
        model.addAttribute("groupCount", studentRepository.countDistinctGroup());
        return "index";
    }

    @PostMapping("/")
    public String postIndex(@RequestParam String id, @RequestParam String password, HttpServletRequest request) {
        if (id.equals("admin") && password.equals("admin1234")) {
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("login", "admin");
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping("/detail")
    public String getDetailById(Model model,
                                @RequestParam(required = false, defaultValue = "0") String id,
                                @RequestParam(required = false, defaultValue = "0") String grade,
                                @RequestParam(required = false, defaultValue = "0") String group) {
        Student student = new Student();
        student.setId(Long.parseLong(id));
        model.addAttribute("grade", Long.parseLong(grade));
        model.addAttribute("group", Long.parseLong(group));
        model.addAttribute("student", studentRepository.findById(Long.parseLong(id)).get());
        model.addAttribute("data", temperatureRepository.findTop20ByStudentOrderByDateDesc(student));
        return "detail";
    }
}
