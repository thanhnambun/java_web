package controller.bt8910;

import model.bt8.Seed;
import model.bt8.User;
import model.bt8.WarehouseSeeds;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bt8910")
public class GameController {

    private List<User> users = new ArrayList<>();
    private List<Seed> seeds = new ArrayList<>();
    private List<WarehouseSeeds> warehouse = new ArrayList<>();
    private User loggedInUser;
    private String[] farm = new String[20];
    public GameController() {
        seeds.add(new Seed(1, "Tomato", 100.0, "https://suckhoedoisong.qltns.mediacdn.vn/324455921873985536/2021/9/25/tac-dung-cua-ca-chua-doi-voi-suc-khoe-1-1632310636-831-width640height427-1632567723926-16325677242441321628137.jpg"));
        seeds.add(new Seed(2, "Carrot", 50.0, "https://file.hstatic.net/200000240163/article/ca_rot_huu_co_b4b636bd8ac5431db8b3f8957634d14d.jpg"));
        seeds.add(new Seed(3, "Cucumber", 70.0, "https://upload.wikimedia.org/wikipedia/commons/a/a3/Cucumber_BNC.jpg"));

        warehouse.add(new WarehouseSeeds(1, 10, seeds.get(0)));
        warehouse.add(new WarehouseSeeds(2, 5, seeds.get(1)));
        warehouse.add(new WarehouseSeeds(3, 0, seeds.get(2)));
    }

    @GetMapping("/register")
    public String showRegister() {
        return "/register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email) {
        User newUser = new User(users.size() + 1, username, password, email);
        users.add(newUser);
        return "/login";
    }

    @GetMapping("/login")
    public String showLogin() {
        return "/login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username, @RequestParam String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                loggedInUser = user;
                return "/farm";
            }
        }
        return "/login";
    }

    @GetMapping("/shop")
    public String showShop(Model model) {
        model.addAttribute("seeds", seeds);
        return "/shop";
    }

    @GetMapping("/warehouse")
    public String showWarehouse(Model model) {
        model.addAttribute("warehouse", warehouse);
        return "/warehouse";
    }

    @PostMapping("/buySeed")
    public String buySeed(@RequestParam int seedId, @RequestParam int quantity) {
        Seed selectedSeed = null;
        for (Seed seed : seeds) {
            if (seed.getId() == seedId) {
                selectedSeed = seed;
                break;
            }
        }

        if (selectedSeed != null && loggedInUser.getBalance() >= selectedSeed.getPrice() * quantity) {
            loggedInUser.setBalance(loggedInUser.getBalance() - selectedSeed.getPrice() * quantity);

            boolean seedExists = false;
            for (WarehouseSeeds ws : warehouse) {
                if (ws.getSeed().getId() == seedId) {
                    ws.setQuantity(ws.getQuantity() + quantity);
                    seedExists = true;
                    break;
                }
            }
            if (!seedExists) {
                warehouse.add(new WarehouseSeeds(warehouse.size() + 1, quantity, selectedSeed));
            }
        }
        return "/shop";
    }


    @GetMapping("/farm")
    public String showFarm(Model model) {
        if (loggedInUser == null) {
            return "/login";
        }
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("farm", farm);
        model.addAttribute("seeds", seeds);
        return "/farm";
    }

    @PostMapping("/plantSeed")
    public String plantSeed(@RequestParam int plotId, @RequestParam int seedId) {
        if (farm[plotId - 1] == null) {
            boolean hasSeed = false;
            for (WarehouseSeeds ws : warehouse) {
                if (ws.getSeed().getId() == seedId && ws.getQuantity() > 0) {
                    farm[plotId - 1] = "Seed " + seedId;
                    ws.setQuantity(ws.getQuantity() - 1);
                    hasSeed = true;
                    break;
                }
            }

            if (!hasSeed) {
                return "redirect:/bt8910/farm";
            }
        }
        return "redirect:/bt8910/farm";
    }
}
