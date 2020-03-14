package com.example.testapplication.customSearchView;

import androidx.appcompat.widget.SearchView;

import com.example.testapplication.viewModel.SearchActivityViewModel;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

/**
 * this class observe on searchView with some condition
 *
 * @author Abbas Asadi GitHub:AbbasAsadi
 * @since API 29
 */
public class ObserveSearchView {
    private final String TAG = "ObserveSearchView";

    /**
     * this method do every thing with some condition
     * if query length more than 3 character and
     * after {@param timeOut} millisecond that user does not type anything
     *
     * @param searchView that we work on it
     * @param timeOut    millisecond that user does not type anything
     */
    public ObserveSearchView(SearchView searchView, int timeOut) {
        searchView.setQueryHint("جستجو کنید ...");
        searchView.setIconified(false);
        Disposable disposable = RxSearchObservable.fromView(searchView)
                .debounce(timeOut, TimeUnit.MILLISECONDS)
                .filter(text -> !text.isEmpty() && text.length() >= 3)
                .map(text -> text.toLowerCase().trim())
                .distinctUntilChanged()
                .switchMap(Observable::just)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(query ->
                        {
                            SearchActivityViewModel.getInstance().doSearch(query);
                        }
                );
    }
}
