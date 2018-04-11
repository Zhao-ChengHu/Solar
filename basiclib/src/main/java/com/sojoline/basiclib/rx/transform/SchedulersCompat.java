package com.sojoline.basiclib.rx.transform;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * <pre>
 *     @author : 李小勇
 *     time   : 2017/10/19
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class SchedulersCompat {

  //大量计算操作
  private static final Observable.Transformer computationTransformer =
      new Observable.Transformer() {
        @Override
        public Object call(Object observable) {
          return ((Observable) observable).subscribeOn(Schedulers.computation())
              .observeOn(AndroidSchedulers.mainThread());
        }
      };

  //网络请求、读写文件等io密集型操作
  private static final Observable.Transformer ioTransformer            = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }
  };

  //耗时等操作
  private static final Observable.Transformer newTransformer           = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread());
    }
  };
  private static final Observable.Transformer newTransformerDelayError = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).subscribeOn(Schedulers.newThread())
          .observeOn(AndroidSchedulers.mainThread(), true);
    }
  };
  private static final Observable.Transformer trampolineTransformer    = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).subscribeOn(Schedulers.trampoline())
          .observeOn(AndroidSchedulers.mainThread());
    }
  };

  private static final Observable.Transformer executorTransformer = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).subscribeOn(Schedulers.from(JobExecutor.eventExecutor))
          .observeOn(AndroidSchedulers.mainThread());
    }
  };

  private static final Observable.Transformer mainThreadTransformer = new Observable.Transformer() {
    @Override
    public Object call(Object observable) {
      return ((Observable) observable).observeOn(AndroidSchedulers.mainThread());
    }
  };

  /**
   * Don't break the chain: use RxJava's compose() operator
   */
  public static <T> Observable.Transformer<T, T> applyComputationSchedulers() {
    return (Observable.Transformer<T, T>) computationTransformer;
  }

  public static <T> Observable.Transformer<T, T> applyIoSchedulers() {
    return (Observable.Transformer<T, T>) ioTransformer;
  }

  public static <T> Observable.Transformer<T, T> applyNewSchedulers() {
//    return (Observable.Transformer<T, T>) newTransformer;
    return applyIoSchedulers();
  }

  public static <T> Observable.Transformer<T, T> applyNewSchedulersDelayError() {
    return (Observable.Transformer<T, T>) newTransformerDelayError;
  }

  public static <T> Observable.Transformer<T, T> applyTrampolineSchedulers() {
    return (Observable.Transformer<T, T>) trampolineTransformer;
  }

  public static <T> Observable.Transformer<T, T> applyExecutorSchedulers() {
    return (Observable.Transformer<T, T>) executorTransformer;
  }

  public static <T> Observable.Transformer<T, T> observeOnMainThread() {
    return (Observable.Transformer<T, T>) mainThreadTransformer;
  }
}
