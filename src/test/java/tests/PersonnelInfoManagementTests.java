package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageObjects.DashboardPage;
import pageObjects.LoginPage;
import pageObjects.SidebarPage;
import pageObjects.personnellnfoManagement.PersonnelInfoBasePage;
import pageObjects.personnellnfoManagement.configuration.OptionalFieldsPage;
import pageObjects.personnellnfoManagement.employeeList.EmployeeDetailPage;
import pageObjects.personnellnfoManagement.employeeList.EmployeeListPage;
import utils.BaseTest;
import utils.TestProperties;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonnelInfoManagementTests extends BaseTest {

    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private EmployeeListPage employeeList;
    private PersonnelInfoBasePage personnelInfoBasePage;
    private SidebarPage sidebarPage;
    private OptionalFieldsPage optionalFieldsPage;
    private EmployeeDetailPage employeeDetailPage;

    //Test data
    String adminUser = TestProperties.getProperty("admin.username");
    String adminPass = TestProperties.getProperty("admin.password");

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
        dashboardPage = new DashboardPage(driver);
        employeeList = new EmployeeListPage(driver);
        sidebarPage = new SidebarPage(driver);
        optionalFieldsPage = new OptionalFieldsPage(driver);
        employeeDetailPage = new EmployeeDetailPage(driver);
    }

    @Story("Activar campos deprecados")
    @Description("""
        TC201 - Activar campos deprecados (Optional Fields)
        Precondición: Usuario autenticado
        Pasos:
        1. Ir a PIM > Configuration > Optional Fields
        2. Marcar todas las opciones de campos opcionales (Show Nick Name, Show Smoker, Show Military Service)
        3. Guardar
        4. Ir a PIM > Employee List > seleccionar empleado > Personal Details
        Datos: N/A
        Resultado esperado: Los campos Nick Name, Smoker y Military Service se muestran en Personal Details
        """)
    @Test
    @DisplayName("TC201 - Activar campos deprecados (Optional Fields)")
    void activarCamposDeprecados() {

        loginPage.loginAs(adminUser, adminPass);

        sidebarPage.goToPIM();
        employeeList.clickConfiguration();
        employeeList.goToOptionalFields();

        optionalFieldsPage.turnOnDeprecatedSwitch();
        optionalFieldsPage.clickSaveButton();

        optionalFieldsPage.clickEmployeeListButton();

        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean nicknameDisplayed = employeeDetailPage.checkNicknameGroupIsVisible();
        boolean militaryServiceDisplayed = employeeDetailPage.checkMilitaryServiceGroupIsVisible();
        boolean smokerDisplayed = employeeDetailPage.checkSmokerGroupIsVisible();

        assertTrue(nicknameDisplayed, "No se muestra correctamente el campo 'Nickname'");
        assertTrue(militaryServiceDisplayed, "No se muestra correctamente el campo 'Military service'");
        assertTrue(smokerDisplayed, "No se muestra correctamente el campo 'Smoker'");
    }

    @Story("Desactivar campos deprecados")
    @Description("""
        TC202 - Desactivar campos deprecados (Optional Fields)
        Precondición: Usuario autenticado
        Pasos:
        1. Ir a PIM > Configuration > Optional Fields
        2. Desmarcar todas las opciones de campos opcionales
        3. Guardar
        4. Ir a PIM > Employee List > seleccionar empleado > Personal Details
        Datos: N/A
        Resultado esperado: Los campos Nick Name, Smoker y Military Service no se muestran en Personal Details
        """)
    @Test
    @DisplayName("TC202 - Desactivar campos deprecados (Optional Fields)")
    void desActivarCamposDeprecados() {
        loginPage.loginAs(adminUser, adminPass);

        sidebarPage.goToPIM();
        employeeList.clickConfiguration();
        employeeList.goToOptionalFields();

        optionalFieldsPage.turnOffDeprecatedSwitch();
        optionalFieldsPage.clickSaveButton();

        optionalFieldsPage.clickEmployeeListButton();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean nicknameDisplayed = employeeDetailPage.checkNicknameGroupIsHidden();
        boolean militaryServiceDisplayed = employeeDetailPage.checkMilitaryServiceGroupIsHidden();
        boolean smokerDisplayed = employeeDetailPage.checkSmokerGroupIsHidden();

        assertTrue(nicknameDisplayed, "No se muestra correctamente el campo 'Nickname'");
        assertTrue(militaryServiceDisplayed, "No se muestra correctamente el campo 'Military service'");
        assertTrue(smokerDisplayed, "No se muestra correctamente el campo 'Smoker'");
    }

    @Story("Activar SSN field")
    @Description("""
        TC203 - Activar SSN field (Optional Fields)
        Precondición: Usuario autenticado
        Pasos:
        1. Marcar 'Show SSN field'
        2. Guardar
        3. Validar en Personal Details
        Datos: N/A
        Resultado esperado: El campo SSN se muestra
        """)
    @Test
    @DisplayName("TC203 - Activar SSN field (Optional Fields)")
    void TC203_activarSSNField() {
        loginPage.loginAs(adminUser, adminPass);

        sidebarPage.goToPIM();
        employeeList.clickConfiguration();
        employeeList.goToOptionalFields();

        optionalFieldsPage.turnOnSSNSwitch();
        optionalFieldsPage.clickSaveButton();

        optionalFieldsPage.clickEmployeeListButton();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean ssnDisplayed = employeeDetailPage.checkSSNGroupIsVisible();
        assertTrue(ssnDisplayed, "No se muestra correctamente el campo 'SSN'");
    }

    @Story("Desctivar SSN field")
    @Description("""
        TC204 - Desactivar SSN field (Optional Fields)
        Precondición: Usuario autenticado
        Pasos:
        1. Marcar 'Show SSN field'
        2. Guardar
        3. Validar en Personal Details
        Datos: N/A
        Resultado esperado: El campo SSN se muestra
        """)
    @Test
    @DisplayName("TC204 - Desactivar SSN field (Optional Fields)")
    public void TC204_desactivarSSNField() {
        loginPage.loginAs(adminUser, adminPass);

        sidebarPage.goToPIM();
        employeeList.clickConfiguration();
        employeeList.goToOptionalFields();

        optionalFieldsPage.turnOffSSNSwitch();
        optionalFieldsPage.clickSaveButton();

        optionalFieldsPage.clickEmployeeListButton();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean ssnDisplayed = employeeDetailPage.checkSSNGroupIsHidden();
        assertTrue(ssnDisplayed, "No se muestra correctamente el campo 'SSN'");
    }

    @Story("Activar SIN field")
    @Description("""
        TC205 - Activar SIN field (Optional Fields)
        Precondición: Usuario autenticado
        Pasos:
        1. Marcar 'Show SIN field'
        2. Guardar
        3. Validar en Personal Details
        Datos: N/A
        Resultado esperado: El campo SIN se muestra
        """)
    @Test
    @DisplayName("TC205 - Activar SIN field (Optional Fields)")
    public void TC205_activarSINField() {
        loginPage.loginAs(adminUser, adminPass);

        sidebarPage.goToPIM();
        employeeList.clickConfiguration();
        employeeList.goToOptionalFields();

        optionalFieldsPage.turnOnSINSwitch();
        optionalFieldsPage.clickSaveButton();

        optionalFieldsPage.clickEmployeeListButton();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean ssnDisplayed = employeeDetailPage.checkSINGroupIsVisible();
        assertTrue(ssnDisplayed, "No se muestra correctamente el campo 'SSN'");
    }

    @Story("Desactivar SIN field")
    @Description("""
        TC206 - Desactivar SIN field (Optional Fields)
        Precondición: Usuario autenticado
        Pasos:
        1. Desmarcar 'Show SIN field'
        2. Guardar
        3. Validar en Personal Details
        Datos: N/A
        Resultado esperado: El campo SIN no se muestra
        """)
    @Test
    @DisplayName("TC206 - Desactivar SIN field (Optional Fields)")
    public void TC206_desactivarSINField() {

    }

    @Story("Activar US Tax Exemptions")
    @Description("""
        TC207 - Activar US Tax Exemptions (Optional Fields)
        Precondición: Usuario autenticado
        Pasos:
        1. Marcar 'Show US Tax Exemptions'
        2. Guardar
        3. Validar que se muestra el menú
        Datos: N/A
        Resultado esperado: El menú US Tax Exemptions aparece
        """)
    @Test
    @DisplayName("TC207 - Activar US Tax Exemptions (Optional Fields)")
    public void TC207_activarUSTaxExemptions() {
        loginPage.loginAs(adminUser, adminPass);

        sidebarPage.goToPIM();
        employeeList.clickConfiguration();
        employeeList.goToOptionalFields();

        optionalFieldsPage.turnOnUSTaxExemptionsSwitch();
        optionalFieldsPage.clickSaveButton();

        sidebarPage.clickUserIcon();
        sidebarPage.clickLogout();

        loginPage.loginAs(adminUser, adminPass);
        sidebarPage.goToPIM();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean taxExemptionsDisplayed = employeeDetailPage.checkTaxExemtionsMenuIsVisible();
        assertTrue(taxExemptionsDisplayed, "No se muestra correctamente el menu de 'Tax Exemptions'");
    }

    @Story("Desactivar US Tax Exemptions")
    @Description("""
        TC208 - Desactivar US Tax Exemptions (Optional Fields)
        Precondición: Usuario autenticado
        Pasos:
        1. Desmarcar 'Show US Tax Exemptions'
        2. Guardar
        3. Validar que se oculta el menú
        Datos: N/A
        Resultado esperado: El menú US Tax Exemptions no aparece
        """)
    @Test
    @DisplayName("TC208 - Desactivar US Tax Exemptions (Optional Fields)")
    public void TC208_desactivarUSTaxExemptions() {
        loginPage.loginAs(adminUser, adminPass);

        sidebarPage.goToPIM();
        employeeList.clickConfiguration();
        employeeList.goToOptionalFields();

        optionalFieldsPage.turnOffUSTaxExemptionsSwitch();
        optionalFieldsPage.clickSaveButton();

        sidebarPage.clickUserIcon();
        sidebarPage.clickLogout();

        loginPage.loginAs(adminUser, adminPass);
        sidebarPage.goToPIM();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean taxExemptionsDisplayed = employeeDetailPage.checkTaxExemtionsMenuIsHidden();
        assertTrue(taxExemptionsDisplayed, "No se esconde correctamente el menu de 'Tax Exemptions'");
    }
}
