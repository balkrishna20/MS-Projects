package com.example.util;
/**
 * @author ankitaravalji
 */
public class SchedulerException extends Exception
{

    private static final long serialVersionUID = 1997753363232807009L;

		public SchedulerException()
		{
		}

		public SchedulerException(String message)
		{
			super(message);
		}

		public SchedulerException(Throwable cause)
		{
			super(cause);
		}

		public SchedulerException(String message, Throwable cause)
		{
			super(message, cause);
		}

		public SchedulerException(String message, Throwable cause,
								  boolean enableSuppression, boolean writableStackTrace)
		{
			super(message, cause, enableSuppression, writableStackTrace);
		}

}
