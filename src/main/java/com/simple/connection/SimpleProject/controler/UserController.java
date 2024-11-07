package com.simple.connection.SimpleProject.controler;

import com.simple.connection.SimpleProject.configuration.ResponseStructure;
import com.simple.connection.SimpleProject.dto.User;
import com.simple.connection.SimpleProject.entity.UserInfo;
import com.simple.connection.SimpleProject.service.ExcelWritingService;
import com.simple.connection.SimpleProject.service.IUserService;
import com.simple.connection.SimpleProject.service.PDFGenerationService;
import com.simple.connection.SimpleProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService service;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ExcelWritingService excelWritingService;

    @Autowired
    private PDFGenerationService pdfGenerationService;

    @PostMapping("saveUser")
    public ResponseEntity<ResponseStructure> saveUser(@RequestBody UserInfo userInfo){
        return service.saveUser(userInfo);
    }

    @PostMapping("findUser")
    public ResponseEntity<ResponseStructure> findUser(@RequestParam Long userId){
        return iUserService.findUser(userId);
    }

    @PostMapping("saveUserCustom")
    public ResponseEntity<ResponseStructure> saveUserCustom(@RequestBody User user){
        return iUserService.saveUser(user);
    }

    @DeleteMapping("deleteUser")
    public ResponseEntity<ResponseStructure> deleteUserById(@RequestParam Long userId){
        return iUserService.deleteUser(userId);
    }

    @PutMapping("updateUser")
    public ResponseEntity<ResponseStructure> updateUserById(@RequestBody UserInfo userInfo,@RequestParam Long userId){
        return iUserService.updateUser(userInfo,userId);
    }

    @PostMapping("writeAllUserDetailsToExcel")
    public ResponseEntity<ResponseStructure> writeAllUserDetailsToExcel(){
        return excelWritingService.writeAllUserDetailsToExcelFile();
    }

    @PostMapping("writeAllUserDetailsToCSV")
    public ResponseEntity<ResponseStructure> writeAllUserDetailsToCSV(){
        return excelWritingService.writeAllUserDetailsToCSVFile();
    }

    @PostMapping("writeAllUserDetailsToCSVUsingOpenCSV")
    public ResponseEntity<ResponseStructure> writeAllUserDetailsToCSVUsingOpenCSV(){
        return excelWritingService.writeAllUserDetailsToCSVFileUsingOpenCSV();
    }

    @PostMapping("generatePdf")
    public ResponseEntity<ResponseStructure> generatePdf(){
        return pdfGenerationService.generatePdf();
    }

}
