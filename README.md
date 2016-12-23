# Dagger 2 sub-component map binding example

This attempts to add sub component builders to an application via the
`@Binds @IntoMap @ActivityKey(...)` method shown [here.](https://youtu.be/iwjXqRlEevg)

This is made possible by the new `@Module(subcomponents = {...})` mechanism in dagger 2.7

Previously this sample project defined interfaces to use for all of this, I have recently removed them and
created a separate library project that has the necessary interfaces. [trevjonez/Inject-Android](https://github.com/trevjonez/Inject-Android)

The main idea here is that we have a general contract to follow when requesting component builders
as well as when we define new components. Under most circumstances I prefer to directly annotate classes
to expose them on the object graph and allow dagger to do all the heavy lifting. In the case of android
activities and fragments we are not in control of creation thus the need to reach out and grab the necessary
components.

If you use fragments in your application you can replicate this pattern some what. However rather than
creating a scope based binder such as the `AppScopeActivityBinder` I recommend creating a fragment binder
module for each fragment.

A fragment binder would look something like:
```java
@Module(subcomponents = SomeFragmentComponent.class)
public abstract class SomeFragmentBinder {
    @Binds
    @IntoMap
    @FragmentKey(SomeFragment.class)
    abstract FragmentComponentBuilder someFragBuilder(SomeFragmentComponent.Builder impl);
}
```

While the accompanying fragment component would looks something like:
```java
@Subcomponent
@SomeFragmentScope //Optional and only necessary if you need to control an items lifecycle
public interface SomeFragmentComponent extends PlainComponent<SomeFragment> {
    @Subcomponent.Builder
    interface Builder extends FragmentComponentBuilder<SomeFragment, SomeFragmentComponent> {}
}
```

It is important to note that your multi bound map should be a map of class to provider of component builders.
This will ensure that you are given a new instance for each component builder you request. This looks
something like this:
```java
@Inject
Map<Class<? extends Fragment>, Provider<FragmentComponentBuilder>> fcbMap;
```

To include the fragment component as a sub component of your activity component you include the binder
as a module on the activity component
```java
@Subcomponent(modules = {SomeFragmentBinder.class})
public interface SomeActivityComponent extends PlainComponent<SomeActivity> {
    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<SomeActivity, SomeActivityComponent> {}
}
```