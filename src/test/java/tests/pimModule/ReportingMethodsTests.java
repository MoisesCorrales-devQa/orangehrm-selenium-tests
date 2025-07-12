package tests.pimModule;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.BaseTest;

@Epic("Pim module")
@Feature("Reporting methods")
public class ReportingMethodsTests extends BaseTest {

    @Story("Crear Reporting Method")
    @Description("""
            TC210 - Crear Reporting Method
            Precondición: Usuario autenticado
            Pasos:
            1. Ir a PIM > Configuration > Reporting Methods
            2. Clic en Add
            3. Ingresar nombre del método
            4. Guardar
            5. Validar que aparece en la lista
            Datos: Nombre: Test_Reporting
            Resultado esperado: Método creado correctamente y visible en la lista
            """)
    @Test
    @DisplayName("TC210 - Crear Reporting Method")
    public void TC210_crearReportingMethod() {
        // Implementación pendiente
    }

    @Story("Editar Reporting Method")
    @Description("""
            TC211 - Editar Reporting Method
            Precondición: Usuario autenticado
            Pasos:
            1. Ir a PIM > Configuration > Reporting Methods
            2. Seleccionar un método existente
            3. Editar nombre
            4. Guardar
            5. Validar que se actualizó
            Datos: Modificar nombre: Test_Reporting_Edit
            Resultado esperado: Nombre del método actualizado correctamente
            """)
    @Test
    @DisplayName("TC211 - Editar Reporting Method")
    public void TC211_editarReportingMethod() {
        // Implementación pendiente
    }

    @Story("Eliminar Reporting Method")
    @Description("""
            TC212 - Eliminar Reporting Method
            Precondición: Usuario autenticado
            Pasos:
            1. Ir a PIM > Configuration > Reporting Methods
            2. Seleccionar método existente
            3. Clic en Delete
            4. Confirmar eliminación
            5. Validar que ya no aparece
            Datos: Nombre: Test_Reporting_Edit
            Resultado esperado: Método eliminado correctamente
            """)
    @Test
    @DisplayName("TC212 - Eliminar Reporting Method")
    public void TC212_eliminarReportingMethod() {
        // Implementación pendiente
    }
}
