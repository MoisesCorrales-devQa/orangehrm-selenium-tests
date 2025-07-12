package tests.pimModule;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.*;
import pageObjects.LoginPage;
import pageObjects.SidebarPage;
import pageObjects.personnellnfoManagement.PersonnelInfoBasePage;
import pageObjects.personnellnfoManagement.configuration.CustomFieldsPage;
import pageObjects.personnellnfoManagement.configuration.OptionalFieldsPage;
import pageObjects.personnellnfoManagement.employeeList.EmployeeDetailPage;
import pageObjects.personnellnfoManagement.employeeList.EmployeeListPage;
import utils.BaseTest;
import utils.TestProperties;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Pim module")
@Feature("Custom fields")
public class CustomFieldsTests extends BaseTest {

    private LoginPage loginPage;
    private EmployeeListPage employeeList;
    private PersonnelInfoBasePage personnelInfoBasePage;
    private SidebarPage sidebarPage;
    private OptionalFieldsPage optionalFieldsPage;
    private EmployeeDetailPage employeeDetailPage;
    private CustomFieldsPage customFieldsPage;

    //Test data
    String adminUser = TestProperties.getProperty("admin.username");
    String adminPass = TestProperties.getProperty("admin.password");

    String fieldName = TestProperties.getProperty("cf1.fieldName");
    String type = TestProperties.getProperty("cf1.type");
    String screen = TestProperties.getProperty("cf1.screen");
    String dropDownOptions = TestProperties.getProperty("cf1.dropdownOptions");

    String updatedFieldName = TestProperties.getProperty("cf1.updatedFieldName");
    String updatedType = TestProperties.getProperty("cf1.updatedType");
    String updatedScreen = TestProperties.getProperty("cf1.updatedScreen");

    @BeforeEach
    void initPageObjects() {
        loginPage = new LoginPage(driver);
        employeeList = new EmployeeListPage(driver);
        sidebarPage = new SidebarPage(driver);
        optionalFieldsPage = new OptionalFieldsPage(driver);
        employeeDetailPage = new EmployeeDetailPage(driver);
        customFieldsPage = new CustomFieldsPage(driver);

        loginPage.loginAs(adminUser, adminPass);
        sidebarPage.goToPIM();
        employeeList.clickConfiguration();

    }
    @Order(1)
    @Story("Agregar Custom Field")
    @Description("""
    TC206 - Agregar Custom Field
    Precondición: Usuario autenticado
    Pasos:
    1. Ir a PIM > Configuration > Custom Fields
    2. Clic en Add
    3. Completar nombre, pantalla y tipo de campo
    4. Guardar
    5. Ir a Personal Details
    Datos: Ej: CustomFieldTest / Personal Details / Text
    Resultado esperado: El nuevo campo se muestra en Personal Details
    """)
    @Test
    @DisplayName("TC206 - Agregar Custom Field")
    public void TC206_agregarCustomField() {

        employeeList.goToCustomFields();

        customFieldsPage.clickAdd();
        customFieldsPage.fillFieldName(fieldName);
        customFieldsPage.selectType(type);
        customFieldsPage.selectScreen(screen);
        customFieldsPage.clickSave();

        optionalFieldsPage.clickEmployeeListButton();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean customFieldDisplayed = employeeDetailPage.checkCustomInputGroupIsVisible(fieldName);
        assertTrue(customFieldDisplayed, "No se muestra correctamente el campo custom");
    }

    @Order(2)
    @Story("Editar Custom Field")
    @Description("""
    TC207 - Editar Custom Field
    Precondición: Usuario autenticado
    Pasos:
    1. Ir a PIM > Configuration > Custom Fields
    2. Clic en Edit de un campo existente
    3. Modificar valor
    4. Guardar
    5. Validar que se actualizó
    Datos: Modificar nombre de campo
    Resultado esperado: Campo actualizado correctamente
    """)
    @Test
    @DisplayName("TC207 - Editar Custom Field")
    public void TC207_editarCustomField() {


        employeeList.goToCustomFields();

        customFieldsPage.clickEditIcon(fieldName);

        customFieldsPage.fillFieldName(updatedFieldName);
        customFieldsPage.selectType(updatedType);
        customFieldsPage.selectScreen(updatedScreen);
        customFieldsPage.fillSelectOptions(dropDownOptions);

        customFieldsPage.clickSave();

        optionalFieldsPage.clickEmployeeListButton();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        employeeDetailPage.goToContactDetailsMenu();

        boolean customFieldIsVisible = employeeDetailPage.checkCustomDropDownGroupIsVisible(updatedFieldName);

        assertTrue(customFieldIsVisible, "No se ha editado correctamente el campo custom");
    }

    @Order(3)
    @Story("Eliminar Custom Field")
    @Description("""
    TC208 - Eliminar Custom Field
    Precondición: Usuario autenticado
    Pasos:
    1. Ir a PIM > Configuration > Custom Fields
    2. Seleccionar campo
    3. Clic en Delete
    4. Confirmar
    5. Validar que ya no aparece
    Datos: N/A
    Resultado esperado: El campo se elimina y no aparece en Personal Details
    """)
    @Test
    @DisplayName("TC208 - Eliminar Custom Field")
    public void TC208_eliminarCustomField() {

        employeeList.goToCustomFields();

        customFieldsPage.clickDeleteIcon(updatedFieldName);
        customFieldsPage.confirmDelete();

        optionalFieldsPage.clickEmployeeListButton();
        employeeList.scrollBy(50);
        employeeList.clickEmployee();

        boolean customFieldIsHidden = employeeDetailPage.checkCustomGroupIsHidden(updatedFieldName);
        assertTrue(customFieldIsHidden, "No se ha eliminado correctamente el campo custom");
    }

    @Story("Validar límite de Custom Fields")
    @Description("""
    TC209 - Validar límite de Custom Fields
    Precondición: Usuario autenticado
    Pasos:
    1. Crear Custom Fields hasta llegar al límite permitido
    2. Intentar crear uno adicional
    Datos: Límite: 10
    Resultado esperado: El sistema muestra mensaje de límite alcanzado
    """)
    @Test
    @DisplayName("TC209 - Validar límite de Custom Fields")
    public void TC209_validarLimiteCustomFields() {

        employeeList.goToCustomFields();;
        while(!customFieldsPage.customFieldsLimitReached()){
            customFieldsPage.clickAdd();
            customFieldsPage.fillFieldName(fieldName + " " + (int)(Math.random() * 101));
            customFieldsPage.selectType(type);
            customFieldsPage.selectScreen(screen);
            customFieldsPage.clickSave();
        }

        boolean test = customFieldsPage.maxCapacityReached();

        assertTrue(test, "No se pueden añadir mas custom fields aunque no se ha llegado al limite");
    }
}
