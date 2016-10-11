# Dagger 2 sub-component map binding example

This attempts to add sub component builders to an application via the
`@Binds @IntoMap @ActivityKey(...)` method shown [here.](https://youtu.be/iwjXqRlEevg)

This is made possible by the new `@Module(subcomponents = {...})` mechanism in dagger 2.7

The main idea here is that we have a general contract to follow when requesting component builders
as well as when we define new components. Under most circumstances I prefer to directly annotate classes
to expose them on the object graph and allow dagger to do all the heavy lifting. In the case of android
activities and fragments we are not in control of creation thus the need to reach out and grab the necessary
components.

If you use fragments in your application you can replicate this patter some what. Typically what I have done
is to create a `@FragmentKey` annotation, a `FragmentComponentBuilder` interface, and
`FragmentComponentBuilderHost` interface. The fragment builder host is then implemented on activities
 that host fragments. From there you will create the sub component for your fragment following the same
  pattern as the `MainActivityComponent` in this repo. You will also want to define a module for the
  fragment that is intended as a reusable binding module for the fragment component.
  Typically I refer to these modules as "Binders" which you can
  then include on your host activities' component's `@Subcomponent` annotation as a module.

A fragment binder would look something like:
```java
@Module
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
This will ensure that you are given a new instance for each component builder you request. This would look
something like this:
```java
@Inject
Map<Class<? extends Fragment>, Provider<FragmentComponentBuilder>> fragmentBuilderMap;
```

To include the fragment component as a sub component of you activity component you include the binder
as module on the activity component
```java
@Subcomponent(modules = {SomeFragmentBinder.class})
public interface SomeActivityComponent extends PlainComponent<SomeActivity> {
    @Subcomponent.Builder
    interface Builder extends ActivityComponentBuilder<SomeActivity, SomeActivityComponent> {}
}
```