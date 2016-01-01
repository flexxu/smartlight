package org.smartlight.framework.maths;

import java.util.HashMap;
import java.util.List;

public interface StockIndicator {
	

	 /**
		 * Calculate EMA,
		 * 
		 * @param list
		 *            :Price list to calculate，the first at head, the last at tail.
		 * @return
		 */
		public Double getEXPMA(final List<Double> list, final int number);

		/**
		 * calculate MACD values
		 * 
		 * @param list
		 *            :Price list to calculate，the first at head, the last at tail.
		 * @param shortPeriod
		 *            :the short period value.
		 * @param longPeriod
		 *            :the long period value.
		 * @param midPeriod
		 *            :the mid period value.
		 * @return
		 */
		public HashMap<String, Double> getMACD(final List<Double> list, final int shortPeriod, final int longPeriod, int midPeriod);
}
