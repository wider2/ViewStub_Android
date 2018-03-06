It is just aa experiment project to better understand how ViewStub works in Android Java.

I prefer to create new clear small project to test tricky things of ViewStub with fragment of MVP pattern.

Because this works more interactive than <include> and <merge> tags

Lazy approach to load layout when it is necessary:
 
    private static void inflateContentStub(@LayoutRes int layoutRes, View rootView) {
        ViewStub viewStub = (ViewStub) rootView.findViewById(R.id.navigation_stub);
        viewStub.setLayoutResource(layoutRes);
        viewStub.inflate();
    }

In parallel thinking, in RxJava2 such idea named by "hot observable".

It means that emission will start when we need only.