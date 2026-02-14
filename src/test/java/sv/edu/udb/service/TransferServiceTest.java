package sv.edu.udb.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import sv.edu.udb.configuration.TestInfrastructureConfig;
import sv.edu.udb.repository.AccountRepository;
import sv.edu.udb.service.implementation.TransferServiceImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TransferServiceTest {
    private ApplicationContext context;
    @BeforeEach
    void setUp() {
        //Creacion del contexto a traves del archivo de configuracion
        context = SpringApplication.run(TestInfrastructureConfig.class);
    }
    //Hay varias formas de acceder al bean
    //Las veremos en cada uno de los test
    @Test
    void getBeanByCastAndTransferMoney() {
        //Obteniendo el bean
        //(TransferService) tipo de objeto TransferService
        final TransferService transferService = (TransferService)
                context.getBean("transferService");
        //Utilizando el bean
        //En este punto observe que no hemos usado el operador new
        //Para crear la instancia.
        assertNotNull(transferService); //Not null object
        final Double amountTransfered = transferService.transfer(1, 2, 20D);
        assertEquals(400D, amountTransfered);
    }
    @Test
    void getBeanTypeMethodAndTransferMoney() {
        //Obteniendo el bean
        final TransferService transferService = context.getBean("transferService",
                TransferService.class);
        assertNotNull(transferService); //Not null object
        final Double amountTransfered = transferService.transfer(1, 2, 20D);
        assertEquals(400D, amountTransfered);
    }
    @Test
    void getBeanByBeanIdWhenIdIsUniqueAndTransferMoney() {
        //Obteniendo el bean
        final TransferService transferService = context.getBean(TransferService.class);
        assertNotNull(transferService); //Not null object
        final Double amountTransfered = transferService.transfer(1, 2, 20D);
        assertEquals(400D, amountTransfered);
    }

    @Test
    void getAccountByAccountNumber() {
        final AccountRepository accountRepository = context.getBean(AccountRepository.class);
        //AccountRepository no nulo
        assertNotNull(accountRepository, "accountRepository no debería ser nulo");
        final String getAccount = accountRepository.findAccountNumber(3);
        //Número de cuenta no nulo
        assertNotNull(getAccount, "El número no debería ser nulo");
        //Longitud de número de cuenta
        assertEquals(36, getAccount.length(), "La longitud del numero de cuenta debe ser de 36");
    }
}

