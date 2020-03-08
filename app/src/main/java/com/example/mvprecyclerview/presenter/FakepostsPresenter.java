package com.example.mvprecyclerview.presenter;

import com.example.mvprecyclerview.model.Post;
import com.example.mvprecyclerview.service.BackendServiceFactory;
import com.example.mvprecyclerview.service.response.GetPostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FakepostsPresenter implements BasePresenter {

    private FakepostsDisplay view;

    public FakepostsPresenter(FakepostsDisplay view) {
        this.view = view;
    }

    @Override
    public void start() {
        BackendServiceFactory.getBackEndService().getPosts().enqueue(new Callback<GetPostResponse>() {
            @Override
            public void onResponse(Call<GetPostResponse> call, Response<GetPostResponse> response) {
                if (response != null && response.isSuccessful()) {
                    if (response.body().getPosts() != null && !response.body().getPosts().isEmpty()) {
                        view.displayFakePosts(response.body().getPosts());
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPostResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void stop() {

    }

    public interface FakepostsDisplay {
        void displayFakePosts(List<Post> fakeposts);
    }

}
