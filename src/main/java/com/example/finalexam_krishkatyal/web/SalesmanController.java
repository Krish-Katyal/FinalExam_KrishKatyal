package com.example.finalexam_krishkatyal.web;

import com.example.finalexam_krishkatyal.entities.Salesman;
import com.example.finalexam_krishkatyal.repositories.SalesmanRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@SessionAttributes({"a","e"})
@Controller
@AllArgsConstructor
public class SalesmanController {

    static int num = 0;
    @Autowired
    private SalesmanRepository salesmanRepository;
    @GetMapping(path = "/index")
    public String sales(Model model, @RequestParam(name="keyword",defaultValue = "")String keyword) {

        List<Salesman> salesmen;
        if(keyword.isEmpty()) {
            salesmen = salesmanRepository.findAll();
        }else {
            long key = Long.parseLong(keyword);
            salesmen = salesmanRepository.findById(key);
        }
        model.addAttribute("listSalesmen", salesmen);

        return "salesman";
    }

    @GetMapping("/delete")
    public String delete(Long id){
        salesmanRepository.deleteById(id);

        return "redirect:/index";
    }

    @GetMapping("/editSales")
    public String editSales(Model model, Long id, HttpSession session){
        num = 2;
        session.setAttribute("info", 0);
        Salesman salesman = salesmanRepository.findById(id).orElse(null);
        if(salesman==null) throw new RuntimeException("sale does not exist");
        model.addAttribute("listSalesmen", salesman);
        return "editSales";
    }

    @GetMapping("/formSales")
    public String formSales(Model model)
    {
        model.addAttribute("listSalesmen", new Salesman());

        return "formSales";
    }

    @PostMapping(path="/save")
    public String save(Model model, Salesman salesman, BindingResult
            bindingResult, ModelMap mm, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "formSales";
        } else {
            salesmanRepository.save(salesman);
            if (num == 2) {
                mm.put("e", 2);
                mm.put("a", 0);
            } else {
                mm.put("a", 1);
                mm.put("e", 0);
            }
            return "redirect:index";
        }

    }
    @GetMapping(path = "/")
    public String sales2(Model model, @RequestParam(name="keyword",defaultValue = "")String keyword) {

        List<Salesman> salesmen;
        if(keyword.isEmpty()) {
            salesmen = salesmanRepository.findAll();
        }else {
            long key = Long.parseLong(keyword);
            salesmen = salesmanRepository.findById(key);
        }
        model.addAttribute("listSalesmen", salesmen);

        return "salesman";
    }



}
