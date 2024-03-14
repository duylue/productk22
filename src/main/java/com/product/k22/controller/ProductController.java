package com.product.k22.controller;

import com.product.k22.model.Category;
import com.product.k22.model.FileInfo;
import com.product.k22.model.Product;
import com.product.k22.service.CategoryService;
import com.product.k22.service.FileService;
import com.product.k22.service.ProductService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RequestMapping("/product")
@Controller
public class ProductController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    @Autowired
    private FileService fileService;

    @GetMapping("/list")
    public String getList(Model model, HttpServletResponse response, HttpServletRequest request) {
//        ArrayList<Product> list = productService.getList();
        Cookie [] cookies = request.getCookies();
        List<Map<String, Object>> list = productService.getListDetail();

        model.addAttribute("list", list);
        return "product/list";
    }
//    @GetMapping("/test")
//    public String test(Model model) {
//        List<Map<String, Object>> maps = productService.getListDetail();
//        model.addAttribute("list", maps);
//        return "product/list";
//    }

    @GetMapping("/save")
    public String save(Model model) {

        model.addAttribute("product", new Product());
        List<Category> cList = categoryService.getlist();
        model.addAttribute("cList", cList);

        return "/product/save";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute @Validated Product p, BindingResult bindingResult) {

        p.setSid(1);
        productService.save(p);
        return "redirect:/product/upload?pid="+p.getPid();
    }
    @PostMapping("/upload")
    public String upload(@RequestParam int pid , @RequestParam MultipartFile file) {
        int fid = fileService.findFid(pid);
     fileService.uploadFile(pid,file,fid);
        return "redirect:/product/list";
    }
    @GetMapping("/upload")
    public String uploadFile(@RequestParam int pid , Model model) {
        model.addAttribute("pid",pid);
        return "/product/uploadFile";
    }
    @GetMapping("/updateImage")
    public String updateImage(@RequestParam int pid , Model model) {
        model.addAttribute("pid",pid);
        return "/product/uploadFile";
    }
    @GetMapping ("/get-file")
    public ResponseEntity<?> getFile(@RequestParam int pid ) {
        FileInfo fileInfo = fileService.getFile(pid);
        return ResponseEntity.status(200).contentType(MediaType.parseMediaType(fileInfo.getContentType()))
                .body(fileInfo.getContent());
    }
}
