package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) throws ParseException {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
		
		System.out.println("Enter rental data");
		System.out.print("Car model: ");
		String carModel = sc.nextLine();
		System.out.print("Pickup (DD/MM/YYYY HH:MM): ");
		Date start = sdf.parse(sc.nextLine());
		System.out.print("Return (DD/MM/YYYY HH:MM): ");
		Date finish = sdf.parse(sc.nextLine());
		
		CarRental cR = new CarRental(start, finish, (new Vehicle(carModel)));
		
		System.out.print("Enter price per hour: ");
		double pricePerHour = sc.nextDouble();
		System.out.print("Enter price per day: ");
		double pricePerDay = sc.nextDouble();
		
		RentalService rS = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());

		rS.processInvoice(cR);
		
		System.out.println("INVOICE:");
		System.out.println("Basic payment: " + String.format("%.2f", cR.getInvoice().getBasicPayment()));
		System.out.println("Tax: "+ String.format("%.2f", cR.getInvoice().getTax()));
		System.out.println("Total payment: "+ String.format("%.2f", cR.getInvoice().gettotalPayment()));
				
		sc.close();
	}
	
}
