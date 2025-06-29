package com.sba301.orchid;

import com.sba301.orchid.pojo.Account;
import com.sba301.orchid.pojo.Category;
import com.sba301.orchid.pojo.Orchid;
import com.sba301.orchid.pojo.Role;
import com.sba301.orchid.service.AccountService;
import com.sba301.orchid.service.CategoryService;
import com.sba301.orchid.service.OrchidService;
import com.sba301.orchid.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class OrchidApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OrchidApplication.class, args);
    }

    private final AccountService accountService;
    private final RoleService roleService;
    private final CategoryService categoryService;
    private final OrchidService orchidService;
    @Override
    public void run(String... args) throws Exception {

        if (
                accountService.getAllAccounts().isEmpty() &&
                roleService.getAllRoles().isEmpty() &&
                categoryService.getAllCategories().isEmpty() &&
                orchidService.getAllOrchids().isEmpty()
        ) {

            var superAdminRole = roleService.createRole(
                    Role.builder()
                            .roleName("SUPER_ADMIN")
                            .build());

            var adminRole = roleService.createRole(
                    Role.builder()
                            .roleName("ADMIN")
                            .build());

            var userRole = roleService.createRole(
                    Role.builder()
                            .roleName("USER")
                            .build());

            var superAdminAccount = accountService.createAccount(
                    Account.builder()
                            .accountName("Super Admin")
                            .email("sa@orchid.vn")
                            .password("123")
                            .role(superAdminRole)
                            .build());

            var adminAccount = accountService.createAccount(
                    Account.builder()
                            .accountName("Admin")
                            .email("ad@orchid.vn")
                            .password("123")
                            .role(adminRole)
                            .build());

            var user1Account = accountService.createAccount(
                    Account.builder()
                            .accountName("Hoang")
                            .email("hoang@sba.vn")
                            .password("123")
                            .role(userRole)
                            .build());

            var user2Account = accountService.createAccount(
                    Account.builder()
                            .accountName("Long")
                            .email("long@sba.vn")
                            .password("123")
                            .role(userRole)
                            .build());

            var category1 = categoryService.createCategory(
                    Category.builder()
                            .categoryName("Phalaenopsis")
                            .build());
            var category2 = categoryService.createCategory(
                    Category.builder()
                            .categoryName("Cattleya")
                            .build());
            var category3 = categoryService.createCategory(
                    Category.builder()
                            .categoryName("Dendrobium")
                            .build());
            var category4 = categoryService.createCategory(
                    Category.builder()
                            .categoryName("Vanda")
                            .build());

            var category5 = categoryService.createCategory(
                    Category.builder()
                            .categoryName("Oncidium")
                            .build());

            var category6 = categoryService.createCategory(
                    Category.builder()
                            .categoryName("Paphiopedilum")
                            .build());

            var category7 = categoryService.createCategory(
                    Category.builder()
                            .categoryName("Cymbidium")
                            .build());

            var orchid1 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Phalaenopsis Aphrodite")
                            .orchidDescription("A beautiful Phalaenopsis orchid with white petals and a yellow center.")
                            .price(150000.0)
                            .category(category1)
                            .build());

            var orchid2 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Cattleya Walkeriana")
                            .orchidDescription("A stunning Cattleya orchid with vibrant purple petals.")
                            .price(200000.0)
                            .category(category2)
                            .build());

            var orchid3 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Dendrobium Nobile")
                            .orchidDescription("A classic Dendrobium orchid with delicate white flowers.")
                            .price(120000.0)
                            .category(category3)
                            .build());

            var orchid4 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Vanda Miss Joaquim")
                            .orchidDescription("A unique Vanda orchid with striking purple and yellow flowers.")
                            .price(180000.0)
                            .category(category4)
                            .build());

            var orchid5 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Oncidium Sharry Baby")
                            .orchidDescription("A fragrant Oncidium orchid with chocolate-scented flowers.")
                            .price(160000.0)
                            .category(category5)
                            .build());

            var orchid6 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Paphiopedilum Maudiae")
                            .orchidDescription("A beautiful Paphiopedilum orchid with unique slipper-shaped flowers.")
                            .price(220000.0)
                            .category(category6)
                            .build());

            var orchid7 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Cymbidium Golden Elf")
                            .orchidDescription("A stunning Cymbidium orchid with golden-yellow flowers.")
                            .price(250000.0)
                            .category(category7)
                            .build());

            var orchid8 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Phalaenopsis Sogo Yukidian")
                            .orchidDescription("A popular Phalaenopsis orchid with large white flowers and a yellow center.")
                            .price(170000.0)
                            .category(category1)
                            .build());

            var orchid9 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Cattleya Percivaliana")
                            .orchidDescription("A rare Cattleya orchid with vibrant pink and white flowers.")
                            .price(230000.0)
                            .category(category2)
                            .build());

            var orchid10 = orchidService.createOrchid(
                    Orchid.builder()
                            .orchidName("Dendrobium Phalaenopsis")
                            .orchidDescription("A hybrid Dendrobium orchid with large, showy flowers.")
                            .price(140000.0)
                            .category(category3)
                            .build());

        }
    }
}
