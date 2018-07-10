package com.accolite.invoice_backend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.accolite.invoice_backend.dto.DraftDto;
import com.accolite.invoice_backend.entity.Timesheet;
import com.accolite.invoice_backend.repository.CalendarRepository;
import com.accolite.invoice_backend.repository.TimesheetRepository;

@Service
public class TimesheetService {

	DraftDto draftDto = new DraftDto();

	@Autowired
	TimesheetRepository timesheetRepository;
	@Autowired
	CalendarRepository calendarRepository;

	public HashMap<String, Double> getInvoice(boolean inMonth) {
		// List<InvoiceDto> l=new ArrayList<InvoiceDto>();
		HashMap<String, Double> map = new HashMap<String, Double>();
		Iterable<Timesheet> r = timesheetRepository.findAll();
		Iterator<Timesheet> rs = r.iterator();

		// logic
		double totalInvoiceB = 0.0;
		double totalInvoiceM = 0.0;
		Date curDate = new Date();
		int curMonth = curDate.getMonth();
		int curYear = curDate.getYear();
		while (rs.hasNext()) {
			Timesheet timesheet = rs.next();
			String status = timesheet.getStatus();
			boolean invoiced = timesheet.isInvoiced();
			if (status.compareTo("invoiced") == 0 && invoiced == false) {

				Date tEnd = timesheet.getTimestampend();
				// System.out.println(rs.getString("location"));
				if (inMonth) {
					if (curYear == tEnd.getYear() && curMonth == tEnd.getMonth()) {
						// if(timesheet.getLocation().equalsIgnoreCase("blr")) {
						// totalInvoiceB += timesheet.getSthours()*timesheet.getStrate() +
						// timesheet.getOthours()*timesheet.getOtrate() ;
						// }
						// else {
						// totalInvoiceM += timesheet.getSthours()*timesheet.getStrate() +
						// timesheet.getOthours()*timesheet.getOtrate() ;
						// }
						if (map.get(timesheet.getLocation()) == null)
							map.put(timesheet.getLocation(), 0.0);
						map.put(timesheet.getLocation(),
								map.get(timesheet.getLocation()) + timesheet.getSthours() * timesheet.getStrate()
										+ timesheet.getOthours() * timesheet.getOtrate());

					}
				} else {
					if (curYear > tEnd.getYear() || (curYear == tEnd.getYear() && curMonth > tEnd.getMonth())) {

						// if(timesheet.getLocation().equalsIgnoreCase("blr")) {
						// totalInvoiceB += timesheet.getSthours()*timesheet.getStrate() +
						// timesheet.getOthours()*timesheet.getOtrate() ;
						// }
						// else if(timesheet.getLocation().equalsIgnoreCase("mum")){
						// //System.out.println("mum");
						// totalInvoiceM += timesheet.getSthours()*timesheet.getStrate() +
						// timesheet.getOthours()*timesheet.getOtrate() ;
						//
						// }
						if (map.get(timesheet.getLocation()) == null)
							map.put(timesheet.getLocation(), 0.0);
						map.put(timesheet.getLocation(),
								map.get(timesheet.getLocation()) + timesheet.getSthours() * timesheet.getStrate()
										+ timesheet.getOthours() * timesheet.getOtrate());
					}
				}
			}
		}

		// timesheetDtoB.setTotalInvoice(totalInvoiceB);
		// timesheetDtoB.setLocation("bangalore");
		// timesheetDtoM.setTotalInvoice(totalInvoiceM);
		// timesheetDtoM.setLocation("mumbai");
		//
		// l.add(timesheetDtoB);
		// l.add(timesheetDtoM);
		return map;
	}

	public HashMap<String, List<DraftDto>> getDraft() {

		HashMap<String, List<DraftDto>> h = new HashMap<String, List<DraftDto>>();
		List<DraftDto> l2;
		Iterable<Timesheet> r2 = timesheetRepository.findAll();
		Iterator<Timesheet> rs2 = r2.iterator();

		while (rs2.hasNext()) {
			l2 = new ArrayList<DraftDto>();
			Timesheet timesheet = rs2.next();
			String status = timesheet.getStatus();
			if (status.equalsIgnoreCase("draft")) {
				if (!h.containsKey(timesheet.getLocation()))
					h.put(timesheet.getLocation(), l2);

				draftDto.setTimesheetid(timesheet.getTimesheetid());
				draftDto.setWorkername(timesheet.getWorkername());
				l2 = h.get(timesheet.getLocation());
				l2.add(draftDto);
				h.put(timesheet.getLocation(), l2);

			}

		}
		return h;
	}
}
