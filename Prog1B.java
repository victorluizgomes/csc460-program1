import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;

/*=============================================================================
|   Assignment:  Program #1 (Prog1B.java)
|       Author:  Victor Gomes (victorluizgomes@email.arizona.edu)
|
|       Course:  CSc 460 Spring 2018
|   Instructor:  L. McCann
|     Due Date:  January 25th, at the beginning of class
|
|     Language:  Java 1.8
|  Compile/Run:  TODO
|
+-----------------------------------------------------------------------------
|
|  Description:  TODO
|                
|        Input:  TODO
|
|       Output:  TODO
|
|   Techniques:  TODO
|
|   Known Bugs:  None
|
*===========================================================================*/

public class Prog1B {

	public static void main(String[] args) {
		
		AllDataRecords records;
		
		RandomAccessFile dataStream = null;
		File fileRef = null;
		FileReader fileR = null;
		BufferedReader buffR = null;
		
		int strRecordsLen = 0;
		int totalLineLen = 0; // TODO: Not used yet
		
		try {
			fileR = new FileReader(args[0]);
			buffR = new BufferedReader(fileR); // TODO maybe dont need
			
			fileRef = new File(args[0]);
			dataStream = new RandomAccessFile(fileRef, "rw");
			records = new AllDataRecords();
			
			// Read the last 6 ints containing the lengths of the string fields
			dataStream.seek(dataStream.length() - 24);
			records.setDateLength(dataStream.readInt());
			records.setStratumLength(dataStream.readInt());
			records.setRaceOtherLength(dataStream.readInt());
			records.setDiagOtherLength(dataStream.readInt());
			records.setNarr1Length(dataStream.readInt());
			records.setNarr2Length(dataStream.readInt());
			
			// TODO ERASE SYSTEM>OUT
//			System.out.println("date length: " + records.getDateLength());
//			System.out.println("stratum length: " + records.getStratumLength());
//			System.out.println("race other length: " + records.getRaceOtherLength());
//			System.out.println("diag other length: " + records.getDiagOtherLength());
//			System.out.println("narr1 length: " + records.getNarr1Length());
//			System.out.println("narr2 length: " + records.getNarr2Length());
			
			// Combine the lengths of the string records
			strRecordsLen = records.getDateLength() + records.getStratumLength() + 
					records.getRaceOtherLength() + records.getDiagOtherLength() + 
					records.getNarr1Length() + records.getNarr2Length();
			
			// Sum of the string records + 13 ints (4 bytes) + 1 double (8 bytes) to get line length
			totalLineLen = strRecordsLen + (13 * 4) + 8;
			
			dataStream.seek(0);
			
			records.readBinary(dataStream);
			System.out.println("First line: ");
			System.out.println("Case #    trmt_date  narr1");
			System.out.print(records.getCpscCase() + " ");
			System.out.print(records.getTrmt_date() + " ");
			System.out.println(records.getNarr1());
			
			records.readBinary(dataStream);
			System.out.print(records.getCpscCase() + " ");
			System.out.print(records.getTrmt_date() + " ");
			System.out.println(records.getNarr1());
			
			records.readBinary(dataStream);
			System.out.print(records.getCpscCase() + " ");
			System.out.print(records.getTrmt_date() + " ");
			System.out.println(records.getNarr1());
			
			records.readBinary(dataStream);
			System.out.print(records.getCpscCase() + " ");
			System.out.print(records.getTrmt_date() + " ");
			System.out.println(records.getNarr1());
			
			records.readBinary(dataStream);
			System.out.print(records.getCpscCase() + " ");
			System.out.print(records.getTrmt_date() + " ");
			System.out.println(records.getNarr1());
			
			fileR.close();
			buffR.close(); // TODO if dont need take it out
			dataStream.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
