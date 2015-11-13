package wordfinder;

import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

@SuppressWarnings("unused")

public class Log 
{
	private final String logPath;

	public Log( String path )
   {
	   this.logPath = path;
   }

   public void write( String content ) throws FileNotFoundException, IOException
   {
		FileWriter writer = null;
		try
		{
			writer = new FileWriter( this.logPath, true );		// If True will append to file
			writer.write( content );
			System.out.print( content );
		}
		finally
		{
			writer.close();
		}
	}


}
