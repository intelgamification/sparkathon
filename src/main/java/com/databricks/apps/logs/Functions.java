package com.databricks.apps.logs;

import java.io.Serializable;
import java.util.Comparator;

import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;

import parquet.it.unimi.dsi.fastutil.ints.IntComparator;

import com.gamification.domain.UserData;

import scala.Tuple2;

public class Functions {
  public static Function2<Long, Long, Long> SUM_REDUCER =
      new Function2<Long, Long, Long>() {
        @Override
        public Long call(Long a, Long b) throws Exception {
          return a+b;
        }
      };

  public static class ValueComparator<K, V>
      implements Comparator<Tuple2<K, V>>, Serializable {
    private Comparator<V> comparator;

    public ValueComparator(Comparator<V> comparator) {
      this.comparator = comparator;
    }

    @Override
    public int compare(Tuple2<K, V> o1, Tuple2<K, V> o2) {
      return comparator.compare(o1._2(), o2._2());
    }
  }

  public static class LongComparator
      implements Comparator<Long>, Serializable {

    @Override
    public int compare(Long a, Long b) {
        if (a > b) return 1;
        if (a.equals(b)) return 0;
        return -1;
    }
  }


  public static Comparator<Long> LONG_NATURAL_ORDER_COMPARATOR =
      new LongComparator();

	public static class UserDataComparator implements Comparator<UserData>,
			Serializable {

		@Override
		public int compare(UserData a, UserData b) {
			if (a.getAht() > b.getAht())
				return 1;
			if (a.getAht() == (b.getAht()))
				return 0;
			return -1;
		}
	}

public static Comparator<UserData> USER_DATA_NATURAL_ORDER_COMPARATOR =
  new UserDataComparator();
  

  public static Function<Tuple2<String, Long>, Boolean> FILTER_GREATER_10 =
      new Function<Tuple2<String, Long>, Boolean>() {
        @Override
        public Boolean call(Tuple2<String, Long> tuple) throws Exception {
          return tuple._2() > 10;
        }
      };

  public static Function<Tuple2<String, Long>, String> GET_TUPLE_FIRST =
      new Function<Tuple2<String, Long>, String>() {
        @Override
        public String call(Tuple2<String, Long> tuple) throws Exception {
          return tuple._1();
        }
      };


 public static Function<UserData, UserData> GET_HANDLING_TIME = 
		 new Function<UserData, UserData>() {
			@Override
			public UserData call(UserData userData) throws Exception {
				return userData;
			}
};



public static Comparator<Integer> INTEGER_COMPARATOR = new IntComparator() {
	
	@Override
	public int compare(Integer arg0, Integer arg1) {
		if(arg0 > arg1) return 1;
		if(arg0.equals(arg1)) return 0;
		return -1;
	}
	
	@Override
	public int compare(int arg0, int arg1) {
		if(arg0 > arg1) return 1;
		if(arg0 == arg1) return 0;
		return -1;
	}
};
      

}