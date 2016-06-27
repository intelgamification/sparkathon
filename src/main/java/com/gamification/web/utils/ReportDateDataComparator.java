package com.gamification.web.utils;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

import com.gamification.web.model.ReportDateData;

public class ReportDateDataComparator implements Comparator<ReportDateData> {

	public int compare(ReportDateData o1, ReportDateData o2) {
		Date date1 = DateUtil.getDateFormString(o1.getReportDate());
		Date date2 = DateUtil.getDateFormString(o2.getReportDate());
		return date1.compareTo(date2);
	}

}
