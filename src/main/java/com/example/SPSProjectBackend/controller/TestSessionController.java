package com.example.SPSProjectBackend.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/test")
public class TestSessionController {

    // Example:
    // POST /SPS/api/test/login?email=ES42310&deptId=423.10
    @PostMapping("/login")
    public Map<String, Object> login(@RequestParam String email,
                                     @RequestParam String deptId,
                                     HttpSession session) {
        session.setAttribute("email", email);   // used by /save as preparedBy/addUser/updUser
        session.setAttribute("deptId", deptId); // used by service to fill DEPT_ID
        return Map.of(
                "email", email,
                "deptId", deptId,
                "sessionId", session.getId()
        );
    }

    // (optional) check current session
    // GET /SPS/api/test/whoami
    @GetMapping("/whoami")
    public Map<String, Object> whoami(HttpSession session) {
        return Map.of(
                "email", session.getAttribute("email"),
                "deptId", session.getAttribute("deptId"),
                "sessionId", session.getId()
        );
    }
}
