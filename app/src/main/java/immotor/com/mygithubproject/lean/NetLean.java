package immotor.com.mygithubproject.lean;

import immotor.com.mygithubproject.entity.RecommendBannerInfo;
import immotor.com.mygithubproject.network.RetrofitHelper;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class NetLean {
    public static void main(String args[]){
        RetrofitHelper.getBiliAppAPI()
                .getRecommendedBannerInfo()
                .subscribe(recommendBannerInfo -> {
                    System.out.println(recommendBannerInfo.getCode());
                },throwable -> {
                    //不执行onComplete了
                    System.out.println(throwable.getMessage());
                },()->{
                    System.out.println("onComplete");
                },onSubscribe->{
                    System.out.println("onSubscribe");
                });
               /* .subscribe(recommendBannerInfo -> {
                    System.out.println(recommendBannerInfo.getCode());
                },throwable -> {
                    System.out.println(throwable.getMessage());
                });*/
               /* .subscribe(new Observer<RecommendBannerInfo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RecommendBannerInfo recommendBannerInfo) {
                        System.out.println(recommendBannerInfo.getCode());
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });*/
               /* .subscribe(recommendBannerInfo->{
                    System.out.println(recommendBannerInfo.getCode());
                });*/
    }
}
