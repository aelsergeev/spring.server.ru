package ru.server.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.server.spring.dao.CachedDao;
import ru.server.spring.models.api.HelpdeskQueueCount;
import ru.server.spring.models.api.WalletLog;
import ru.server.spring.services.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final CachedDao cachedDao;

    @Autowired
    public AdminController(AdminService adminService, CachedDao cachedDao) {
        this.adminService = adminService;
        this.cachedDao = cachedDao;
    }

    @PostMapping("/item/activate")
    public String activateItem(@RequestParam int id) {
        return adminService.activateItem(id);
    }

    @PostMapping("/user/wallet/log")
    public List<WalletLog> walletLog(@RequestParam int id) {
        return adminService.getTransactionsFromWalletLogByUserId(id);
    }

    @PostMapping("/hd/group/count")
    public List<HelpdeskQueueCount> hdGroupCount() {
        return cachedDao.getGroupHelpDeskCount();
    }

}
