package by.epam.training.task2;


import by.epam.training.task2.builders.Customer;
import by.epam.training.task2.domain.Order;
import by.epam.training.task2.exceptions.MenuItemNotFoundException;
import by.epam.training.task2.state.MenuContext;

public class Runner {
    public static void main(String[] args) {
        Customer customer = new Customer
                .Builder("SomeName","000-00-00")
                .addAddress("SomeAddress")
                .addHomePhone("111-11-11")
                .build();

        MenuContext menuContext = new MenuContext();
        /* optional step
        System.out.println("\tView menu");
        System.out.println(menuContext);
        */

        System.out.println("\tOrder creating:");
        Order someOrder = new Order();
        try {
            someOrder.addItemToOrder(menuContext.getMenuItemByName("tea"));
            someOrder.addItemToOrder(menuContext.getMenuItemByName("summer cake"));
        }catch (MenuItemNotFoundException ex){
            ex.printStackTrace();
        }

        System.out.println("\tInvoicing and saving order status:");
        customer.setOrder(someOrder);
        System.out.println(customer.getOrder());

        //and order payment...
        customer.payOrder();

        System.out.println("\tRestore the last order:");
        System.out.println("Order before restoring ->" + customer.getOrder());
        customer.restoreLastOrder();
        System.out.println("Order after restoring ->" + customer.getOrder());

        System.out.println("\tAdd something new to it:");
        try {
            someOrder.addItemToOrder(menuContext.getMenuItemByName("summer cookies"));
            someOrder.addItemToOrder(menuContext.getMenuItemByName("summer cookies"));
            someOrder.addItemToOrder(menuContext.getCustomDrink("coffee").addChocolate().addChocolate().addSugar());
        }catch (MenuItemNotFoundException ex){
            ex.printStackTrace();
        }

        System.out.println("\tInvoicing:");
        System.out.println(customer.getOrder());

        customer.payOrder();
    }
}
