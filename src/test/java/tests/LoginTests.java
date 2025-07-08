package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import utils.BaseTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Login")
@Feature("Autenticación")
public class LoginTests extends BaseTest {

    private LoginPage loginPage;

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
    }

    @Story("Login exitoso")
    @Description("""
        TC001 - Login exitoso con usuario válido
        Precondición: Navegador en https://saucedemo.com/
        Pasos:
        1. Ingresar usuario
        2. Ingresar password
        3. Clic en Login
        Datos: standard_user / secret_sauce
        Resultado esperado: Redirección a página de inventario
        """)
    @Test
    @DisplayName("TC001 - Login exitoso con credenciales válidas")
    void TC001_loginCorrecto() {
        loginPage.loginAs("Admin", "admin123");
        assertTrue(driver.getCurrentUrl().contains("dashboard"), "El login no redirige correctamente");
    }
}
