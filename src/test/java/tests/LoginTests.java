package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.DashboardPage;
import pages.LoginPage;
import utils.BaseTest;
import utils.TestProperties;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Login")
@Feature("Autenticación")
public class LoginTests extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LoginTests.class);
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    //Test data
    String adminUser = TestProperties.getProperty("admin.username");
    String adminPass = TestProperties.getProperty("admin.password");
    String essUser = TestProperties.getProperty("ess.username");
    String essPass = TestProperties.getProperty("ess.password");
    String adminLockedUser = TestProperties.getProperty("admin_locked.username");
    String adminLockedPass = TestProperties.getProperty("admin_locked.password");
    String essLockedUser = TestProperties.getProperty("ess_locked.username");
    String essLockedPass = TestProperties.getProperty("ess_locked.password");
    String incorrectUser = TestProperties.getProperty("incorrect.username");
    String incorrectPass = TestProperties.getProperty("incorrect.password");

    String essCasePass = TestProperties.getProperty("ess_case.password");

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
    }

    @Story("Login exitoso")
    @Description("""
            TC101 - Login exitoso con usuario Admin
            Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
            Pasos:
            1. Ingresar usuario Admin válido
            2. Ingresar password válida
            3. Clic en Login
            Datos: Admin / admin123
            Resultado esperado: Redirección al dashboard de Admin
            """)
    @Test
    @DisplayName("TC101 - Login exitoso con usuario Admin")
    void TC101_loginExitosoAdmin() {

        loginPage.loginAs(adminUser, adminPass);

        assertTrue(dashboardPage.checkDashboardPageIsDisplayed(), "No se muestra correctamente la pantalla de 'Dashboard'");

    }

    @Story("Login exitoso")
    @Description("""
            TC102 - Login exitoso con usuario ESS
            Precondición: Navegador en https://opensource-demo.orangehrmlive.com/; Usuario ESS creado habilitado
            Pasos:
            1. Ingresar usuario ESS válido
            2. Ingresar password válida
            3. Clic en Login
            Datos: ess_user / ess_pass
            Resultado esperado: Redirección al dashboard de ESS
            """)
    @Test
    @DisplayName("TC102 - Login exitoso con usuario ESS")
    void TC102_loginExitosoESS() {
        loginPage.loginAs(essUser, essPass);

        assertTrue(dashboardPage.checkDashboardPageIsDisplayed(), "No se muestra correctamente la pantalla de 'Dashboard'");
    }

    @Story("Login")
    @Description("""
    TC10103 - Login exitoso con usuario en mayúsculas
    Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
    Pasos:
    1. Ingresar usuario en minúsculas (admin)
    2. Ingresar password válida
    3. Clic en Login
    Datos: ADMIN / dbZJ58#7TxWe0z
    Resultado esperado: Redirección al dashboard de Admin
    """)
    @Test
    @DisplayName("TC103 - Login exitoso con usuario en mayúsculas")
    void TC103_loginExitosoUsuarioMayusculas() {
        loginPage.loginAs(adminUser.toUpperCase(), adminPass);

        assertTrue(dashboardPage.checkDashboardPageIsDisplayed(), "No se muestra correctamente la pantalla de 'Dashboard'");
    }

    @Story("Login fallido")
    @Description("""
        TC104 - Login con Admin bloqueado
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/; Usuario Admin bloqueado configurado
        Pasos:
        1. Ingresar usuario Admin bloqueado
        2. Ingresar password válida
        3. Clic en Login
        Datos: blocked_admin / admin123
        Resultado esperado: Mostrar mensaje: Usuario deshabilitado
        """)
    @Test
    @DisplayName("TC104 - Login con Admin bloqueado")
    void TC104_loginAdminBloqueado() {
        loginPage.loginAs(adminLockedUser, adminLockedPass);

        assertTrue(loginPage.isLockedUserErrorVisible());
    }

    @Story("Login fallido")
    @Description("""
        TC105 - Login con ESS bloqueado
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/; Usuario ESS bloqueado configurado
        Pasos:
        1. Ingresar usuario ESS bloqueado
        2. Ingresar password válida
        3. Clic en Login
        Datos: blocked_ess / ess_pass
        Resultado esperado: Mostrar mensaje: Usuario deshabilitado
        """)
    @Test
    @DisplayName("TC105 - Login con ESS bloqueado")
    void TC105_loginESSBloqueado() {
        loginPage.loginAs(essLockedUser, essLockedPass);

        assertTrue(loginPage.isLockedUserErrorVisible());
    }

    @Story("Login fallido")
    @Description("""
        TC106 - Login con username incorrecto
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
        Pasos:
        1. Ingresar usuario incorrecto
        2. Ingresar password válida
        3. Clic en Login
        Datos: wrong_user / admin123
        Resultado esperado: Mostrar mensaje: Invalid credentials
        """)
    @Test
    @DisplayName("TC106 - Login con username incorrecto")
    void TC106_loginUsernameIncorrecto() {
        loginPage.loginAs(incorrectUser, essPass);

        assertTrue(loginPage.isInvalidCredentialsErrorVisible());
    }

    @Story("Login fallido")
    @Description("""
        TC107 - Login con password incorrecta
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
        Pasos:
        1. Ingresar usuario válido
        2. Ingresar password incorrecta
        3. Clic en Login
        Datos: Admin / wrongpass
        Resultado esperado: Mostrar mensaje: Invalid credentials
        """)
    @Test
    @DisplayName("TC107 - Login con password incorrecta")
    void TC107_loginPasswordIncorrecta() {
        loginPage.loginAs(essUser, incorrectPass);

        assertTrue(loginPage.isInvalidCredentialsErrorVisible());
    }

    @Story("Login fallido")
    @Description("""
        TC108 - Login con username y password incorrectos
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
        Pasos:
        1. Ingresar usuario incorrecto
        2. Ingresar password incorrecta
        3. Clic en Login
        Datos: wrong_user / wrongpass
        Resultado esperado: Mostrar mensaje: Invalid credentials
        """)
    @Test
    @DisplayName("TC108 - Login con username y password incorrectos")
    void TC108_loginAmbosIncorrectos() {
        loginPage.loginAs(incorrectUser, incorrectPass);

        assertTrue(loginPage.isInvalidCredentialsErrorVisible());
    }

    @Story("Login fallido")
    @Description("""
        TC109 - Login con username vacío
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
        Pasos:
        1. Dejar campo username vacío
        2. Ingresar password válida
        3. Clic en Login
        Datos: " / admin123
        Resultado esperado: Mostrar mensaje: Required
        """)
    @Test
    @DisplayName("TC109 - Login con username vacío")
    void TC109_loginUsernameVacio() {
        loginPage.loginAs("", essPass);

        assertTrue(loginPage.isNoUserErrorVisible(), "No está visible la label de 'Required'");
    }

    @Story("Login fallido")
    @Description("""
        TC110 - Login con password vacía
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
        Pasos:
        1. Ingresar usuario válido
        2. Dejar campo password vacío
        3. Clic en Login
        Datos: Admin / ""
        Resultado esperado: Mostrar mensaje: Required
        """)
    @Test
    @DisplayName("TC110 - Login con password vacía")
    void TC110_loginPasswordVacia() {
        loginPage.loginAs(essUser, "");

        assertTrue(loginPage.isNoPsswdErrorVisible(), "No está visible la label de 'Required'");
    }

    @Story("Login fallido")
    @Description("""
        TC111 - Login con ambos campos vacíos
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
        Pasos:
        1. Dejar campo username vacío
        2. Dejar campo password vacío
        3. Clic en Login
        Datos: " / "
        Resultado esperado: Mostrar mensaje: Required en ambos campos
        """)
    @Test
    @DisplayName("TC111 - Login con ambos campos vacíos")
    void TC111_loginAmbosVacios() {
        loginPage.loginAs("", "");

        boolean areBothVisible = loginPage.areBothRequiredLabelsVisible();

        assertTrue(areBothVisible, "No se muestran ambas etiquetas de 'Required'");
    }

    @Story("Sesión y robustez")
    @Description("""
        TC112 - Validar sesión activa tras login
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/; Usuario Admin logueado
        Pasos:
        1. Iniciar sesión como Admin
        2. Refrescar página de dashboard manualmente tras el login
        Datos: Admin / admin123
        Resultado esperado: El sistema redirige al Dashboard y no muestra formulario de login
        """)
    @Test
    @DisplayName("TC112 - Validar sesión activa tras login")
    void TC112_validarSesionActiva() {
        loginPage.loginAs(adminUser, adminPass);

        dashboardPage.refresh();

        dashboardPage.checkDashboardPageIsDisplayed();
    }

    @Story("Sesión y robustez")
    @Description("""
        TC113 - Validar sesión activa tras login y navegación atrás
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/; Usuario Admin logueado
        Pasos:
        1. Iniciar sesión como Admin
        2. Pulsar botón atrás del navegador
        3. Refrescar
        Resultado esperado: El sistema redirige al Dashboard y no muestra formulario de login
        """)
    @Test
    @DisplayName("TC113 - Validar sesión activa tras login y navegación atrás")
    void TC113_validarSesionActivaTrasAtras() {
        loginPage.loginAs(adminUser, adminPass);

        dashboardPage.navigateBack();
        loginPage.refresh();

        dashboardPage.checkDashboardPageIsDisplayed();
    }

    @Story("Validaciones")
    @Description("""
        TC114 - Validar login case sensitive en contraseña
        Precondición: Navegador en https://opensource-demo.orangehrmlive.com/
        Pasos:
        1. Ingresar username correcto (Admin)
        2. Ingresar password en mayúsculas (ADMIN123)
        3. Clic en Login
        Resultado esperado: Mostrar mensaje 'Invalid credentials'
        """)
    @Test
    @DisplayName("TC114 - Validar login case sensitive en contraseña")
    void TC114_loginCaseSensitivePassword() {
        loginPage.loginAs(essPass, essCasePass.toUpperCase());

        assertTrue(loginPage.isInvalidCredentialsErrorVisible());
    }
}

