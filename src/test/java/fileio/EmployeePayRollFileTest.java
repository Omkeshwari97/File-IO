package fileio;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import fileio.EmployeePayrollService.IOService;

public class EmployeePayRollFileTest 
{
	@Test
	public void given3Employees_WhenWrittenToFile_ShouldMatchEmployeeEntries()
	{
		EmployeePayrollData arrayOfEmpsData[] = {
			new EmployeePayrollData(1, "Omkeshwari", 5000.0),
			new EmployeePayrollData(2, "Suruchi", 6000.0),
			new EmployeePayrollData(3, "Mital", 7000.0)
		};
		
		EmployeePayrollService employeePayrollService;
		employeePayrollService = new EmployeePayrollService(Arrays.asList(arrayOfEmpsData));
		employeePayrollService.writeEmployeePayrollData(IOService.FILE_IO);
		employeePayrollService.printData(IOService.FILE_IO);
		long entries = employeePayrollService.countEntries(IOService.FILE_IO);
		assertEquals(3, entries);
	}
}
