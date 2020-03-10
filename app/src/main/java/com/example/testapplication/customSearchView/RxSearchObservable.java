package com.example.testapplication.customSearchView;


import androidx.appcompat.widget.SearchView;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * this class convert SearchView into Observable
 *
 * @author Abbas Asasdi Github:AbbasAsadi
 * @since API 29
 */
public class RxSearchObservable {
    /**
     * this method use {@link PublishSubject} to convert searchView to Observable
     *
     * @param searchView that we work on it
     * @return an observable that observe query of searchView
     */
    static Observable<String> fromView(SearchView searchView) {
        final PublishSubject<String> subject = PublishSubject.create();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                subject.onComplete();
                searchView.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                subject.onNext(text);
                return false;
            }
        });

        return subject;
    }
}
