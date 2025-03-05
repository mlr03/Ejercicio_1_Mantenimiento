package bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

//PARA EJECUTAR PONER EN TERMINAL DE VS. CODE: mvn jacoco:prepare-agent test install jacoco:report

public class BankAccountTest { //SOLO HACER PRUEBAS DE LOS MÉTODOS PÚBLICOS

    @Test
    @DisplayName("Retirada correcta de dinero") //PONER SIN ; 

    public void withdraw_Correct(){ //NO PONER ARGUMENTOS

        // Arrange
        BankAccount account = new BankAccount(1000); 
        
        /*
        SE CREA UN OBJETO CUENTA DE BANCO
        CON UN SALDO INICIAL PASADO COMO SRGUMENTO DE 1000
        */

        // Act
        boolean result = account.withdraw(500); 

        /*
        SE INTENTA RETIRAR 500 LLAMANDO AL 
        MÉTODO DESDE EL OBJETO APLIACNDO objeto.metodo(argumento) SIENDO OBJETO EL 
        OBJETO CREADO EN Arrange CUYO TIPO DE DATO ES OTRA CLASE QUE TIENE EL MÉTODO 
        QUE SE LLAMA Y EL ATRIBUTO PASADO COMO ARGUMENTO

        SE CREA UNA VARIABLE QUE ALMACENE EL VALOR ESPERADO PARA LUEGO USAR EN Assert SI SE PUEDE OBTENER CON UN MÉTODO
        */

        // Assert
        assertTrue(result, "La retirada debería ser exitosa");
        /* ASSERT PARA VALIDAR UNA EXPRESIÓN BOOLEANA */

        assertEquals(500, account.getBalance(), "El saldo después del retiro debería ser 500");
        /*ASSERT PARA COMPROBAR SI EL RESULTADO OBTENIDO ES IGUAL AL ESPERADO */
    }

    @Test
    @DisplayName("Retirada incorrecta de dinero")

    public void withdraw_Incorrect(){

        //Arrange
        BankAccount account=new BankAccount(500);

        //Act
        boolean result = account.withdraw(1000); 

        //Assert

        assertFalse(result,"La retirada no fue existosa");

    }


    

    @Test
    @DisplayName("Depósito incorrecto con cantidad negativa")
    public void deposit_Incorrect() {
        // Arrange
        BankAccount account = new BankAccount(1000);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-500), "Depositar un valor negativo debería lanzar IllegalArgumentException");

    }

    @Test
    @DisplayName("Depósito correcto con cantidad positiva")
    public void deposit_Correct() {
        // Arrange
        BankAccount account = new BankAccount(1000);

        
        // Act 
        int result=account.deposit(500);

        //Assert
        assertEquals(1500, result,"El saldo después del ingreso debería ser 1500");
    }


    @Test
    @DisplayName("Comprobar saldo correcto")
    public void balance_Correct() {
        // Arrange
        BankAccount account = new BankAccount(1000);

        
        // Act 
        int result=account.getBalance();

        //Assert
        assertEquals(1000, result,"El saldo debería ser 1000");

    }

    @Test
    @DisplayName("Calculo de un mes de préstamo")
    public void payment(){

        //Arrange
        BankAccount account = new BankAccount(0); 
        /*
        CREAMOS EL OBJTETO YA QUE SE INVOCA EN Act UN MÉTODO DUYO 
        PERO SE CREA CON SALDO O
        */

        double totalAmount = 10000; 
        double interest = 0.05 / 12; 
        int npayments = 24; 
        /*
        INICIALIZAMOS EN ARRANGE EL VALOR DE LAS VARIABLES QUE PASAREMOS COMO PARÁMETRO EN Act
         */

    
        double expectedPayment = totalAmount * (interest * Math.pow(1 + interest, npayments) / 
        (Math.pow(1 + interest, npayments) - 1));
        /*
        CREAMOS EN ARRANGE UNA VARIABLE QUE ALMACENE EL VALOR ESPERADO EN Asser
         */

        // Act
        double actualPayment = account.payment(totalAmount, interest, npayments);

        // Assert
        assertEquals(expectedPayment, actualPayment, 0.01, "El pago mensual calculado debería ser correcto");
    }

    @Test
    @DisplayName("Calculo de cantidad pendiente de un préstamo")
    public void pending() {
        
        // Arrange
        BankAccount account = new BankAccount(0);

        double amount = 10000; 
        double interest = 0.05 / 12; 
        int npayments = 24; 
        int month = 12; 

        // Act
        double pendingAmount = account.pending(amount, interest, npayments, month);

        // Assert
        assertTrue(pendingAmount > 0, "La cantidad pendiente después de 12 meses debe ser positiva");
        assertTrue(pendingAmount < amount, "La cantidad pendiente debe ser menor que el préstamo inicial");
    }

    }



