package com.example.mvprecyclerview.presenter;

import android.util.Log;

import com.example.mvprecyclerview.model.Post;
import com.example.mvprecyclerview.service.BackendServiceFactory;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class FakepostsPresenter implements BasePresenter {

    private FakepostsDisplay view;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    public FakepostsPresenter(FakepostsDisplay view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        compositeDisposable.clear();
        compositeDisposable.dispose();
    }

    public void displayListBtnClicked() {
        compositeDisposable.add(getPostFromBackend()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(posts -> view.displayFakePosts(posts), throwable -> Log.d(TAG, throwable.getMessage() != null ? throwable.getMessage() : "")));
    }

    private Single<List<Post>> getPostFromBackend() {
        return Single.create(emitter -> BackendServiceFactory.getBackEndService()
                .getPosts().enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (response.body() != null && !response.body().isEmpty()) {
                            emitter.onSuccess(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {

                        emitter.onError(t);
                    }
                }));
    }

    public interface FakepostsDisplay {
        void displayFakePosts(List<Post> fakeposts);
    }

}
