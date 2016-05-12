package pizzaservice.service.accumulationCardService;


import pizzaservice.domain.card.AccumulationCard;
import pizzaservice.domain.customer.Customer;

public interface AccumulationCardService {

	AccumulationCard getAccumulationCardByCustomer(Customer customer);

	Boolean hasAccumulationCard(Customer customer);

	Boolean assignNewAccumulationCardToCustomer(Customer customer);

	Boolean activateAccumulationCardForCustomer(Customer customer);

	Boolean deactivateAccumulationCardForCustomer(Customer customer);
}
