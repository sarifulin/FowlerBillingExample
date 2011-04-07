package fowler.energybilling.sites;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Test;

import fowler.energybilling.Dollars;
import fowler.energybilling.NoReadingsException;
import fowler.energybilling.Reading;

public class BusinessTests {
	@Test
	public void BusinessSite0() throws NoReadingsException {
		BusinessSite subject = new BusinessSite();
		subject.addReading(new Reading(10, new Date(1997, 1, 1)));
		subject.addReading(new Reading(10, new Date(1997, 2, 1)));
		assertTrue(subject.charge().getAmount() == 0.0);
	}
	
	
	@Test
	public void BusinessSite4000WholeYear() throws NoReadingsException {
		BusinessSite subject = new BusinessSite();
		subject.addReading(new Reading(1000, new Date(1997, 1, 1)));
		subject.addReading(new Reading(5000, new Date(1997, 12, 31)));
		//System.out.println("4000WholeYearCharge is: "+subject.charge().getAmount());
		assertEquals(new Dollars(24.5).getAmount(), subject.charge().getAmount());
	}
	
	
	@Test
	public void BusinessSite199Winter() throws NoReadingsException {
		BusinessSite subject = new BusinessSite();
		subject.addReading(new Reading(100, new Date(1997, 1, 1)));
		subject.addReading(new Reading(299, new Date(1997, 2, 1)));
		//System.out.println("199WinterCharge is: "+subject.charge().getAmount());
		assertEquals(new Dollars(1.88).getAmount(), subject.charge().getAmount());
	}
	
	@Test
	public void BusinessSite199Summer() throws NoReadingsException {
		BusinessSite subject = new BusinessSite();
		subject.addReading(new Reading(300, new Date(1997, 6, 15)));
		subject.addReading(new Reading(499, new Date(1997, 31, 8)));
		//System.out.println("199SummerCharge is: "+subject.charge().getAmount());
		assertEquals(new Dollars(1.88).getAmount(), subject.charge().getAmount());
	}
}