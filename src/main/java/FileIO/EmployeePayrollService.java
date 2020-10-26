package fileio;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeePayrollService 
{	
	public enum IOService {CONSOLE_IO, FILE_IO, DB_IO, REST_IO};
	private List<EmployeePayrollData> employeePayrollDataList;
	
	Scanner scanner = new Scanner(System.in);
	
	public EmployeePayrollService(List<EmployeePayrollData> employeePayrollDataList) 
	{	
		this.employeePayrollDataList = employeePayrollDataList;
	}

	public EmployeePayrollService() 
	{
	}

	public static void main(String[] args) 
	{
		List<EmployeePayrollData> employeePayrollDataList = new ArrayList<>();
		EmployeePayrollService employeePayrollService = new EmployeePayrollService(employeePayrollDataList);
		employeePayrollService.readEmployeePayrollData(IOService.CONSOLE_IO);
		employeePayrollService.writeEmployeePayrollData(IOService.CONSOLE_IO);
	}

	public void readEmployeePayrollData(IOService ioService) 
	{
		if(ioService.equals(IOService.CONSOLE_IO))
		{
			System.out.println("Enter Employee ID: ");
			int id = scanner.nextInt();
			
			System.out.println("Enter Employee Name: ");
			String name = scanner.next();
			
			System.out.println("Enter Employee Salary: ");
			double salary = scanner.nextDouble();
			
			EmployeePayrollData employeePayrollData = new EmployeePayrollData(id, name, salary);
			employeePayrollDataList.add(employeePayrollData);
		}
		else if(ioService.equals(IOService.FILE_IO))
		{
			employeePayrollDataList = new EmployeePayRollFileService().readData();
			System.out.println("Writing data from file" + employeePayrollDataList);
		}
	}
	
	public void writeEmployeePayrollData(IOService ioService) 
	{
		if(ioService.equals(IOService.CONSOLE_IO))
		{
			System.out.println("\n Writing employee payroll to console \n" + employeePayrollDataList);
		}
		else if(ioService.equals(IOService.FILE_IO))
		{
			new EmployeePayRollFileService().writeData(employeePayrollDataList);
		}
	}
	
	public void printData(IOService ioService)
	{
		if(ioService.equals(IOService.FILE_IO))
		{
			new EmployeePayRollFileService().printData();
		}
	}
	
	public long countEntries(IOService ioService)
	{
		long entries = 0;
		
		if(ioService.equals(IOService.FILE_IO))
		{
			entries = new EmployeePayRollFileService().countEntries();
		}
		
		return entries;
	}
}
