package se.kth.carins.Business;

import java.util.Iterator;
import java.util.List;

import se.kth.carins.Entities.Claim;
import se.kth.carins.Entities.Client;
import se.kth.carins.Entities.CustomerForm;
import se.kth.carins.storage.Storage;

public class ClientFunc {
	private List<Client> clients;
	
	public ClientFunc() {
		Storage storageInstance = Storage.getStorageInstance();
		clients = storageInstance.getClients();
	}
	
	public void addClient(Client client) {
		clients.add(client);
	}
	
	public Integer hasHistory(Client client) {
		List<Claim> claims = client.getClaimHistory();
		Integer history;
		if (claims == null)
			history = 0;
		else
			history = claims.size();
		return history;
	}
	
	public Client getClient(String name, String licencePlate) {
		Client result = null;
		Client tmpClient;
		Iterator<Client> iter = clients.iterator();
		
		while(iter.hasNext()) {
			tmpClient = iter.next();
			
			if (name.equals(tmpClient.getName()) && licencePlate.equals(tmpClient.getLicensePlate())) {
				result = tmpClient;
			}
		}
		//tmpClient = null;
		
		return result;
	}
	
	public boolean isClient(CustomerForm form) {
		String name = form.getCustomerName();
		String email = form.getCustomerEmail();
		String licensePlate = form.getLicensePlate();
		boolean result = false;
		Client tmpClient;
		
		Iterator<Client> iter = clients.iterator();
		
		while(iter.hasNext()) {
			tmpClient = iter.next();
			if (name.equals(tmpClient.getName()) && email.equals(tmpClient.getEmail()) &&
					licensePlate.equals(tmpClient.getLicensePlate())) {
				result = true;
				break;
			}
		}
		
		return result;
	}
}
